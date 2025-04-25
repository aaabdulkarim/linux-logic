from typing import Annotated, Optional
from fastapi import FastAPI, HTTPException, Depends, Response, Cookie, Request

from dotenv import load_dotenv
from sqlmodel import Session, create_engine, select

from fastapi.middleware.cors import CORSMiddleware

from models.UserModels import *
from models.ProgressModels import *
from models.ScenarioModels import *
from models.AccessModels import *

import uuid
from datetime import datetime, timedelta, timezone


from passlib.context import CryptContext
import os

# Websocekt Imports
import websockets
import json

from fastapi.websockets import WebSocket
from fastapi import WebSocketDisconnect

from ScenarioTrack import ScenarioTrack
from DockerManager import DockerManager 

import asyncio
import re

from collections import deque
from datetime import datetime, timedelta

EMAIL_REGEX = r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"


# docs: https://fastapi.tiangolo.com/tutorial/sql-databases/
# sqlmodel docs: https://sqlmodel.tiangolo.com/tutorial/where/#where-land


# Laden des Connection Strings
load_dotenv()
connectionString = os.getenv("CONNECTION_STRING")

# Passwort-Hashing Setup
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

def hash_password(password: str) -> str:
    return pwd_context.hash(password)

def verify_password(plain_password: str, hashed_password: str) -> bool:
    return pwd_context.verify(plain_password, hashed_password)


# Passwort Security check
def is_strong_password(password: str) -> bool:
    # Mindestens 8 Zeichen, 1 Großbuchstabe, 1 Kleinbuchstabe, 1 Zahl, 1 Sonderzeichen
    pattern = r'^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$'
    return re.fullmatch(pattern, password) is not None


def is_valid_username(username: str) -> bool:
    return re.fullmatch(r'^[a-zA-Z0-9]+$', username) is not None



# Datenbank-Engine erstellen
engine = create_engine(connectionString, pool_pre_ping=True)


# FastAPI App
app = FastAPI()

origins = [
    "http://localhost:8080",   # for dev
    "https://linux-logic.com",  # production
    "https://www.linux-logic.com"
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

def get_session():
    with Session(engine) as session:
        yield session

SessionDep = Annotated[Session, Depends(get_session)]

@app.post("/login")
async def login(response: Response, userModel: UserRead, session: SessionDep):

    if userModel.username:
        user = session.exec(select(UserDB).where(UserDB.username == userModel.username)).first()
    elif userModel.email:
        user = session.exec(select(UserDB).where(UserDB.email == userModel.email)).first()


    if not user or not verify_password(userModel.password, user.password):
        raise HTTPException(status_code=401, detail="Anmeldedaten nicht richtig")
    
    session_id = str(uuid.uuid4())
    session_expiry = datetime.now(timezone.utc) + (timedelta(days=30) if userModel.stayLoggedIn else timedelta(minutes=15))
    user.session_id = session_id
    user.session_expiry = session_expiry
    
    # Grant Access
    if userModel.accesscode:

        # Check if accesscode is valid
        accesscode = session.exec(select(AccessCode).where(AccessCode.code == userModel.accesscode)).first()
        if accesscode and accesscode.used == False:
            accesscode.used = True
            user.accessgranted = True
            session.add(accesscode)
            session.commit()


    else:
        print("Kein accesscode")


    session.add(user)
    session.commit()
    
    response.set_cookie(key="session_id", value=session_id, httponly=True, secure=True, samesite="Strict")
    return {"message": "Login erfolgreich", "username" : user.username, "email" : user.email}


@app.post("/register")
async def register(userModel: UserRead, session: SessionDep):

    if not re.fullmatch(EMAIL_REGEX, userModel.email):
        raise HTTPException(status_code=400, detail="Ungültiges E-Mail-Format")

    if not is_valid_username(userModel.username):
        raise HTTPException(status_code=400, detail="Benutzername darf nur Buchstaben und Zahlen enthalten")

    # Überprüfen, ob die E-Mail bereits registriert ist
    existing_email_user = session.exec(select(UserDB).where(UserDB.email == userModel.email)).first()
    if existing_email_user:
        raise HTTPException(status_code=400, detail="E-Mail bereits registriert")

    # Überprüfen, ob der Benutzername bereits existiert
    existing_username_user = session.exec(select(UserDB).where(UserDB.username == userModel.username)).first()
    if existing_username_user:
        raise HTTPException(status_code=400, detail="Benutzername bereits vergeben")

    if len(userModel.password) < 8:
        raise HTTPException(status_code=400, detail="Passwort muss mindestens 8 Zeichen lang sein")

    if not is_strong_password(userModel.password):
        raise HTTPException(status_code=400, detail="Passwort muss Groß- und Kleinbuchstaben, Zahlen und Sonderzeichen enthalten")


    hashed_password = hash_password(userModel.password)
    new_user = UserDB(username=userModel.username, email=userModel.email, password=hashed_password)
    session.add(new_user)
    session.commit()

    return {"status": 200, "username": userModel.username, "email": userModel.email}
 
 
@app.put("/edit")
async def editPassword(request: Request, editBody : UserEdit, session: SessionDep):
    session_id = request.cookies.get("session_id")  

    print(session_id)
    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")

    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()

    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")

    if user.session_expiry < datetime.now(timezone.utc):
        raise HTTPException(status_code=401, detail="Anmeldung notwendig")
    
    if len(editBody.newPassword) < 8:
        raise HTTPException(status_code=400, detail="Passwort muss mindestens 8 Zeichen lang sein")

    if not is_strong_password(editBody.newPassword):
        raise HTTPException(status_code=400, detail="Passwort muss Groß- und Kleinbuchstaben, Zahlen und Sonderzeichen enthalten")



    user.password = hash_password(editBody.newPassword)
    session.add(user)
    session.commit()
    
    return {"message": "Passwort erfolgreich geändert"}




@app.post("/progress")
async def saveProgress(progressBody : ProgressBase, request: Request, session: SessionDep):
    """
    Der Progress wird gespeichert
    """

    session_id = request.cookies.get("session_id")  


    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")


    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()


    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")
    
    if user.session_expiry < datetime.now(timezone.utc):
        raise HTTPException(status_code=401, detail="Anmeldung notwendig")


    progress_query = select(ProgressDB).where(
        (ProgressDB.user_id == user.id) & 
        (ProgressDB.scenario_id == progressBody.scenario_id)
    )

    
    existing_progress = session.exec(progress_query).first()

    if existing_progress:

        # Nur wenn das neue Ergebnis besser ist, wird es aktualisiert
        if existing_progress.loesungen_verwendet > progressBody.loesungen_verwendet:
            existing_progress.loesungen_verwendet = progressBody.loesungen_verwendet
        
        if existing_progress.hints_verwendet > progressBody.hints_verwendet:
            existing_progress.hints_verwendet = progressBody.hints_verwendet

    else:
        new_progress = ProgressDB(
            scenario_id=progressBody.scenario_id,
            loesungen_verwendet = progressBody.loesungen_verwendet,
            hints_verwendet = progressBody.hints_verwendet,
            user_id = user.id
        )
        session.add(new_progress)

        session.commit()

    anzahl_sterne = 3

    if progressBody.hints_verwendet > 0:
        anzahl_sterne = 2

    if progressBody.loesungen_verwendet > 0:
        anzahl_sterne = 1

    
    return {
        "message": "Progress erfolgreich gespeichert oder aktualisiert", 
        "loesungen_verwendet" : progressBody.loesungen_verwendet, 
        "hints_verwendet" :  progressBody.hints_verwendet,
        "sterne" : anzahl_sterne
    }


@app.get("/me")
async def userData(request: Request, session: SessionDep):
    session_id = request.cookies.get("session_id")  


    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")


    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()


    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")
    
    if user.session_expiry < datetime.now(timezone.utc):
        raise HTTPException(status_code=401, detail="Anmeldung notwendig")


    return {
        "username": user.username,
        "email" : user.email
    }



@app.get("/progress")
async def getProgress(request: Request, session: SessionDep):
    """
    Der Progress wird als Zahl zurückgegeben. Die Zahl ist die ID des Progress.
    """
    
    session_id = request.cookies.get("session_id")  


    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")

    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()

    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")

    if user.session_expiry < datetime.now(timezone.utc):
        raise HTTPException(status_code=401, detail="Anmeldung notwendig")

    # SQL Abfrage
    progress_statement = select(ProgressDB).where(ProgressDB.user_id == user.id)
    progress_results = session.exec(progress_statement).all()


    # Fortschritt zusammenstellen
    progress_list = []

    highest_scenario_id = 0
    for progress in progress_results:
        item = ProgressBase(
            loesungen_verwendet=progress.loesungen_verwendet,
            hints_verwendet=progress.hints_verwendet,
            scenario_id=progress.scenario_id
        )
        if progress.scenario_id > highest_scenario_id:
            highest_scenario_id = progress.scenario_id

        progress_list.append(item)


    nextCourse = -1
    if highest_scenario_id < 5:
        nextCourse = highest_scenario_id + 1
    
    return {"completedCourses" : progress_list, "currentCourse" : highest_scenario_id, "nextCourse" : nextCourse}


@app.get("/sterne")
async def getSterne(request: Request,  session : SessionDep):
    """
    Die Sterne für jedes Szenario die ein User abgeschlossen hat werden zusammengezählt und zurückgegeben

    wenn hints_verwendet > 0, dann bekommt der User 2 Sterne für das Szenario
    wenn loesungen_verwendet > 0, dann bekommt der User 1 Stern für das Szenario
    ansonsten bekommt er 3 
    """    
    session_id = request.cookies.get("session_id")  

    print(session_id)
    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")

    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()

    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")

    if user.session_expiry < datetime.now(timezone.utc):
        raise HTTPException(status_code=401, detail="Anmeldung notwendig")

    statement = select(ProgressDB)
    results = session.exec(statement)

    userId = user.id
    anzahlSterne = 0

    try:
        for resObj in results:
            if resObj.user_id == userId:
                erreichbareSterne = 3
                if resObj.hints_verwendet > 0:
                    erreichbareSterne = 2

                if resObj.loesungen_verwendet > 0:
                    erreichbareSterne = 1

                anzahlSterne += erreichbareSterne 

        return anzahlSterne

        
    except ValueError:
        raise HTTPException(status_code=404, detail=f"Invalid Paramater Value given as User ID")

    
    # Exception nachdem der User nicht gefunden wurde
    raise HTTPException(status_code=404, detail=f"Progress not found with User Id {userId}")


@app.get("/logout")
async def logout(response : Response, request: Request,  session : SessionDep):
    session_id = request.cookies.get("session_id")  

    if not session_id:
        response.delete_cookie("session_id")

    else:
        # Benutzer per session id finden
        user_statement = select(UserDB).where(UserDB.session_id == session_id)
        user = session.exec(user_statement).first()

        if user:
            user.session_id = None
            user.session_expiry = None
            session.commit()

        # Cookie löschen
        response.delete_cookie("session_id", httponly=False, secure=False)
        return {"message": "Logout successful"}

dm = DockerManager()

@app.on_event("startup")
async def startup_event():
    asyncio.create_task(dm.start_auto_delete_containers())


@app.websocket("/ws")
async def websocket(mainsocket: WebSocket, session: SessionDep):
    await mainsocket.accept()


    # Cookie Parsing
    cookie_header = mainsocket.headers.get("cookie")
    cookies = {}

    if cookie_header:
        for cookie in cookie_header.split("; "):
            key, value = cookie.split("=", 1)
            cookies[key] = value

    session_id = cookies.get("session_id")

    if not session_id:
        await mainsocket.send_json({"error": "Session cookie not found"})
        await asyncio.sleep(0.1)  
        await mainsocket.close()
        return


    # Verify user session
    user = session.exec(select(UserDB).where(UserDB.session_id == session_id)).first()

    if not user or user.session_expiry < datetime.now(timezone.utc):
        await mainsocket.send_json({"error": "Invalid session. Please log in again."})
        await asyncio.sleep(0.1)  
        await mainsocket.close()
        return 

    if user.accessgranted != True:
        await mainsocket.send_json({"error": "Kein Accesscode. Erneut anmelden mit Access Code"})
        await asyncio.sleep(0.1)  
        await mainsocket.close()
        return 

    # Starten des normalen Prozedere
    frontend_user_name = await mainsocket.receive_text()

    print(frontend_user_name)

    frontend_scenario_id = await mainsocket.receive_text()

    if int(frontend_scenario_id) > 5:
        await mainsocket.send_json({"error": f"Kapitel {frontend_scenario_id} noch nicht verfügbar"})
        await asyncio.sleep(0.1)  
        await mainsocket.close()
        return


    frontend_container_choice = "scenario" + str(frontend_scenario_id)
    print(frontend_container_choice)


    container_session_id = str(uuid.uuid1())
    container_name = await dm.add_connection(
        userSessionId=container_session_id, 
        userName=frontend_user_name,
        frontendChoice=frontend_container_choice
    )


    if container_name:
        scm = await dm.get_scm(frontend_user_name, frontend_container_choice)
        
        # Falls reconnected wird und der das SCM schon beim Letzten level ist. 
        if scm.is_last_level():
            await mainsocket.send_json(
                {
                    "last_level" : True,
                    "hints_verwendet": scm.clues_used,
                    "loesungen_verwendet" : scm.solutions_used,
                })

        while await dm.get_container_health(container_name) != "healthy":
            print(await dm.get_container_health(container_name))
            await asyncio.sleep(2)

        await mainsocket.send_json({"output": "Container Startup successful"}) 
        container_socket_port = await dm.get_dynamic_port(container_name)
        print("Das isses: ", container_socket_port)

        container_socket_url = f"ws://127.0.0.1:{container_socket_port}/dockersocket"
        try:
            async with websockets.connect(container_socket_url) as container_socket:
                print("connected to external")

                # User soll im home Directory anfangen
                # await container_socket.send("cd /home")
                desc = "".join(scm.get_desc())
                await mainsocket.send_json({
                    "description": desc,
                    "levelNr": scm.subscenario_progress + 1}
                ) 
                
                response_type = ""
                response_data = ""
                current_directory = ""
                description = ""
                levelNr = 0

                command_timestamps = deque(maxlen=10)

                while True:

                    # TODO: Schutz gegen While True Commands

                    frontend_cmd = await mainsocket.receive_text()
                    await dm.update_last_interaction(frontend_user_name, frontend_container_choice)

                    # Das ist der Schutz gegen Spam
                    now = datetime.now()
                    command_timestamps.append(now)
                    if len(command_timestamps) >= 5:
                        if (now - command_timestamps[0]) < timedelta(seconds=10):
                            await mainsocket.send_json({
                                "output": "Das sind viele Befehle, komm mal runter",
                                "current_directory": current_directory,
                                "description": description,
                                "levelNr": levelNr
                            })
                            continue

                    try:
                        if ">clue" == frontend_cmd:
                            clues = "".join(scm.get_clue())

                            response_type = "hint"
                            response_data = clues

                        elif ">solution" == frontend_cmd:
                            solution = "".join(scm.get_solution())
                            
                            response_type = "solution"
                            response_data = solution

                        elif ">reset" == frontend_cmd:
                            await dm.close(frontend_user_name, frontend_container_choice)
                            response_type = "reset"
                            response_data = "Der Container wurde neu gestartet, laden Sie die Seite neu"

                        elif ">check" == frontend_cmd:

                            if scm.is_last_level():
                                await mainsocket.send_json(
                                    {
                                        "last_level" : True,
                                        "hints_verwendet": scm.clues_used,
                                        "loesungen_verwendet" : scm.solutions_used,
                                    })


                            else:
                                await container_socket.send(f"bash -c /app/checks_fun_{scm.subscenario_progress + 1}.sh")
                                bash_check = await container_socket.recv()
                                print(bash_check)
                                check_json = json.loads(bash_check)

                                if check_json["output"].strip() == "true":

                                    
                                    scm.update_progress()
                                    response_type = "check"
                                    response_data = "successful"
                                    description = "".join(scm.get_desc())
                                    levelNr = scm.subscenario_progress + 1

                                else:
                                    response_type = "check"
                                    response_data = "unsuccessful"


                        else:
                            await container_socket.send(frontend_cmd)
                            output_json = await container_socket.recv()
                            output_dict = json.loads(output_json) 
                            response_type = "output"
                            
                            # Aus irgendeinem Grund viel langsamer wenn auch das current directory ermittelt wird.
                            response_data = output_dict["output"]
                            current_directory = output_dict["cd"].strip()


                        

                    except WebSocketDisconnect as wbs:

                        print("WebSocket client disconnected")
                        response_type = "error"
                        response_data = wbs
                        break
                    

                    # Get Current Directory
                    # await container_socket.send("pwd")
                    # current_directory = await container_socket.recv()

                    # Sending response
                    await mainsocket.send_json({
                        response_type: response_data,
                        "current_directory" : current_directory,
                        "description" : description,
                        "levelNr" : levelNr
                        })

        except Exception as e:
            print(f"Error with external WebSocket: {e}")

        finally:
            await mainsocket.close()
            await dm.close(frontend_user_name, frontend_container_choice)

    else:
        print("Help")
        return
    


@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}