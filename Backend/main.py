from typing import Annotated, Optional
from validate_email import validate_email
from fastapi import FastAPI, HTTPException, Depends, Response, Cookie, Request

from pydantic import BaseModel
from dotenv import load_dotenv, get_key
from sqlmodel import Field, Session, SQLModel, create_engine, select

from fastapi.middleware.cors import CORSMiddleware

from models.UserModels import *
from models.ProgressModels import *
from models.ScenarioModels import *

import uuid
from datetime import datetime, timedelta, timezone


from passlib.context import CryptContext
import os

# Websocekt Imports
import websockets

from fastapi.websockets import WebSocket
from fastapi import WebSocketDisconnect

from ScenarioTrack import ScenarioTrack
from DockerManager import DockerManager 

import asyncio



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

# Datenbank-Engine erstellen
engine = create_engine(connectionString, pool_pre_ping=True)


# FastAPI App
app = FastAPI()

origins = ["http://localhost", "http://localhost:8080", "http://localhost:8081"]
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
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
    user = session.exec(select(UserDB).where(UserDB.username == userModel.username)).first()
    if not user or not verify_password(userModel.password, user.password):
        raise HTTPException(status_code=401, detail="Login fehlgeschlagen")
    
    session_id = str(uuid.uuid4())
    session_expiry = datetime.now(timezone.utc) + (timedelta(days=30) if userModel.stayLoggedIn else timedelta(minutes=15))
    user.session_id = session_id
    user.session_expiry = session_expiry
    
    session.add(user)
    session.commit()
    
    response.set_cookie(key="session_id", value=session_id, httponly=True, secure=True, samesite="Strict")
    return {"message": "Login erfolgreich"}

@app.post("/register")
async def register(userModel: UserRead, session: SessionDep):
    if not validate_email(userModel.email, check_blacklist=False):
        raise HTTPException(status_code=400, detail="Ungültige E-Mail")
    
    existing_user = session.exec(select(UserDB).where(UserDB.email == userModel.email)).first()
    if existing_user:
        raise HTTPException(status_code=400, detail="E-Mail bereits registriert")
    
    if len(userModel.password) < 8:
        raise HTTPException(status_code=400, detail="Passwort muss mindestens 8 Zeichen lang sein")
    
    hashed_password = hash_password(userModel.password)
    new_user = UserDB(username=userModel.username, email=userModel.email, password=hashed_password)
    session.add(new_user)
    session.commit()
    
    return {"message": "Registrierung erfolgreich"}
 
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
    

    user.password = hash_password(editBody.newPassword)
    session.add(user)
    session.commit()
    
    return {"message": "Passwort erfolgreich geändert"}


# @app.post("/bewertung") - Ausgeschlossene Funktion
async def addBewertung(userId : int, levelId : int, value : int, kommentar : str, session: SessionDep):
    bewertung = Bewertung()
    bewertung.user_id = userId
    bewertung.scenario_id = levelId
    bewertung.bewertung = value
    bewertung.kommentar = kommentar


    session.add(bewertung)
    session.commit()
    return bewertung


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
            hints_verwendet = progressBody.hints_verwendet

        )
        session.add(new_progress)

    session.commit()
    return {"message": "Progress erfolgreich gespeichert oder aktualisiert"}


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


@app.websocket("/ws")
async def websocket(mainsocket: WebSocket):
    await mainsocket.accept()
    

    session_id = str(uuid.uuid4())
    frontend_user_name = await mainsocket.receive_text()    
    
    print(frontend_user_name)
    
    frontend_container_choice = await mainsocket.receive_text()
    
    print(frontend_container_choice)

    container_name = await dm.add_connection(
        userSessionId=session_id,
        userName=frontend_user_name,
        frontendChoice=frontend_container_choice
    )

    # TODO: Graceful Closure

    if container_name:

        scm = await dm.get_scm(frontend_user_name, frontend_container_choice)

        # TODO: Nach 1h Inaktivität automatisch schließen 
        while await dm.get_container_health(container_name) != "healthy":
            print(await dm.get_container_health(container_name))
            await asyncio.sleep(2)
            
        await mainsocket.send_text("Container Startup successful")
        container_socket_port = await dm.get_dynamic_port(container_name)
        print("Das isses: ", container_socket_port)

        # Connection mit dem docker socket mit dem modul websockets
        container_socket_url = f"ws://127.0.0.1:{container_socket_port}/dockersocket"
        try:
            async with websockets.connect(container_socket_url) as container_socket:
                print("connected to external")
                while True:

                    # Interaktion mit Frontend Socket
                    frontend_cmd = await mainsocket.receive_text()

                    try:
                        if ">clue" == frontend_cmd:
                            # TODO: SCM Korrekt mit User Connection identifizieren 
                            # TODO: Update Progress wird nur bei einem Check ausgeführt
                            clues = "".join(scm.get_clue())

                            await mainsocket.send_text(clues)
                        
                        if ">solution" == frontend_cmd:
                            solution = "".join(scm.get_solution())
                            await mainsocket.send_text(solution)



                        if ">check" == frontend_cmd:
                            await container_socket.send("bash /app/checks_fun.sh")
                            data = await container_socket.recv()
                            await mainsocket.send_text(data)
                            print(data)

                            

                        else:
                            await container_socket.send(frontend_cmd)
                            data = await container_socket.recv()
                            await mainsocket.send_text(data)
                            print(data)

                
                    except WebSocketDisconnect:
                        print("WebSocket client disconnected")
                        break

        except Exception as e:
            print(f"Error with external WebSocket: {e}")

        finally:
            await mainsocket.close()
            # if container:
            #     container.stop()
            #     container.remove()
            #     print("WebSocket stopped and container removed")
        
            await dm.close(container_name)

    else:
        print("Help")

    


@app.get("/")
async def root():
    print("Auf root gegangen")
    return {"message": "Hello World"}