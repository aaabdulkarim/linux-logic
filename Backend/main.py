from typing import Annotated, Optional
from validate_email import validate_email
from fastapi import FastAPI, HTTPException, Depends, Response, Cookie, Request

from pydantic import BaseModel
from dotenv import load_dotenv, get_key
from sqlmodel import Field, Session, SQLModel, create_engine, select

from fastapi.middleware.cors import CORSMiddleware

from models.UserModels import *
from models.ProgressModels import *

import uuid

# docs: https://fastapi.tiangolo.com/tutorial/sql-databases/
# sqlmodel docs: https://sqlmodel.tiangolo.com/tutorial/where/#where-land

# Laden des Connection Strings
load_dotenv()
connectionString = get_key(".env", "CONNECTION_STRING")


# Das Erstellen der psql/Neon Engine
engine = create_engine(connectionString)


    
class Bewertung(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True)
    scenario_id: int = Field(index=True)
    bewertung: int
    kommentar: str = None   



# Pydantic Models



# FastAPI App Variable
app = FastAPI()



origins = [
    "http://localhost",
    "http://localhost:8080",
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

@app.get("/login/{userId}")
async def login(userId : int, session: SessionDep):
    """
    Die Datenbank wird nach userId durchsucht und wenn der User gefunden wurde, dann wird dieser zurückgegeben
    """
    user = session.get(UserDB, userId)

    if not user:
        raise HTTPException(status_code=404, detail=f"User with Id {userId} not found")

    return user        


@app.get("/login/")
async def login(response : Response, userName : str, userPassword : str, session: SessionDep):
    """
    Die Datenbank wird nach userNamen durchsucht und wenn das Passwort übereinstimmt, dann wird true zurückgegeben
    """
    statement = select(UserDB).where(
        UserDB.username == userName,
        UserDB.password_hash == userPassword
    )
    user = session.exec(statement).first()  

    if user:
        session_id = str(uuid.uuid4())

        # Update der Session ID im User-Objekt
        user.session_id = session_id

        session.add(user)
        session.commit()

        # Cookie setzen
        response.set_cookie(key="session_id", value=session_id, httponly=True)
        return {"message": "Login erfolgreich", "user_id": user.id}

    return {"message": "Login fehlgeschlagen"}

@app.post("/register")
async def register(userModel : UserCreate, session: SessionDep):
    """
    Ein User wird registriert und zur Datenbank hinzugefügt
    """

    # Check ob das übergebene User Model invalide Daten hat
    email = userModel.email
    print(email)
    # Überprüfen ob die Email existiert
    if validate_email(userModel.email, check_blacklist=False) == False:
        return False
    
    print("email valid")

    # Überprüfen ob die Email schon in unserem System vorhanden ist
    statement = select(UserDB)
    userlist = session.exec(statement)
    for user in userlist:
        if user.email == email:
            return False
    
    # Überprüfen ob das Password nicht leer ist
    if len(userModel.password_hash) < 1:
        return False
    
    session.add(userModel)
    session.commit()

    return True
    


@app.put("/edit")
async def editPassword(userId: int, userName : str, userPassword : str, session: SessionDep):
    statement = select(UserDB)
    user = session.get(UserDB, userId)

    if not user:
        raise HTTPException(status_code=404, detail=f"User with Id {userId} not found")


    new_hashed_password = userPassword # TODO: Password Hashing
    user.password_hash = new_hashed_password
    session.add(user)
    session.commit()
    return user


@app.post("/bewertung")
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

    session_id = request.headers.get("session_id")  


    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")

    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()

    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")

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
            user_id=user.id,
            scenario_id=progressBody.scenario_id,
            loesungen_verwendet = progressBody.loesungen_verwendet,
            hints_verwendet = progressBody.hints_verwendet

        )
        session.add(new_progress)

    session.commit()
    return {"message": "Progress erfolgreich gespeichert oder aktualisiert"}

    

@app.get("/progress")
async def getProgress(request: Request, session: SessionDep):
    """
    Der Progress wird als Zahl zurückgegeben. Die Zahl ist die ID des Progress.
    """
    
    session_id = request.headers.get("session_id")  


    if not session_id:
        raise HTTPException(status_code=401, detail="Kein gültiges Session-Cookie gefunden")

    # Benutzer per session id finden
    user_statement = select(UserDB).where(UserDB.session_id == session_id)
    user = session.exec(user_statement).first()

    if not user:
        raise HTTPException(status_code=401, detail="Ungültige Session-ID")

    # SQL Abfrage
    progress_statement = select(ProgressDB).where(ProgressDB.user_id == user.id)
    progress_results = session.exec(progress_statement).all()

    # Fortschritt zusammenstellen
    progress_list = [
        ProgressBase(
            loesungen_verwendet=progress.loesungen_verwendet,
            hints_verwendet=progress.hints_verwendet,
            scenario_id=progress.scenario_id,
        )
        for progress in progress_results
    ]

    return progress_list


@app.get("/sterne")
async def getSterne(userId : int, session : SessionDep):
    """
    Die Sterne für jedes Szenario die ein User abgeschlossen hat werden zusammengezählt und zurückgegeben

    wenn hints_verwendet > 0, dann bekommt der User 2 Sterne für das Szenario
    wenn loesungen_verwendet > 0, dann bekommt der User 1 Stern für das Szenario
    ansonsten bekommt er 3 
    """    
    statement = select(ProgressDB)
    results = session.exec(statement)
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

