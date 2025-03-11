from typing import Annotated
from validate_email import validate_email
from fastapi import FastAPI, HTTPException, Depends, Response
from pydantic import BaseModel
from dotenv import load_dotenv, get_key
from sqlmodel import Field, Session, SQLModel, create_engine, select

from fastapi.middleware.cors import CORSMiddleware



# docs: https://fastapi.tiangolo.com/tutorial/sql-databases/
# sqlmodel docs: https://sqlmodel.tiangolo.com/tutorial/where/#where-land

# Laden des Connection Strings
load_dotenv()
connectionString = get_key(".env", "CONNECTION_STRING")


# Das Erstellen der psql/Neon Engine
engine = create_engine(connectionString)

# SQL Models
class User(SQLModel, table=True):
    """
    Model Klasse für einen Linux Logic user
    """
    id: int | None = Field(default=None, primary_key=True)
    username: str = Field(index=True)
    email: str | None = Field(default=None, index=True)
    password_hash: int


class Progress(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True, foreign_key="user.id")
    scenario_id: int = Field(index=True, foreign_key="scenarios.id")
    hints_verwendet: int = Field(index=True)
    loesungen_verwendet: int = Field(index=True)
    
    
class Bewertung(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True)
    scenario_id: int = Field(index=True)
    bewertung: int
    kommentar: str = None   



# Pydantic Models

class ProgressPyModel(BaseModel):
    loesungen_verwendet : int
    hints_verwendet : int
    scenario_id : int



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
    user = session.get(User, userId)

    if not user:
        raise HTTPException(status_code=404, detail=f"User with Id {userId} not found")

    return user        


@app.get("/login/")
async def login(userName : str, userPassword : str, session: SessionDep):
    """
    Die Datenbank wird nach userNamen durchsucht und wenn das Passwort übereinstimmt, dann wird true zurückgegeben
    """
    statement = select(User)
    results = session.exec(statement)

    for user in results:
        print(user)
        if user.username == userName and user.password_hash == userPassword:
            # TODO: User ID zurückgegben
            return True
        
    return False


@app.post("/register")
async def register(userModel : User, session: SessionDep):
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
    statement = select(User)
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
    statement = select(User)
    user = session.get(User, userId)

    if not user:
        raise HTTPException(status_code=404, detail=f"User with Id {userId} not found")


    new_hashed_password = userPassword # Benötigt Password Hashing
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


    

@app.get("/progress")
async def getProgress(userId : int, session: SessionDep):
    """
    Der Progress wird als Zahl zurückgegeben. Die Zahl ist die ID des Progress.
    """

    progressList = []
    
    try:
        statement = select(Progress)
        results = session.exec(statement)


        for resObj in results:
            if resObj.user_id == userId:
                progressObj = ProgressPyModel(
                    loesungen_verwendet = resObj.loesungen_verwendet,
                    hints_verwendet = resObj.hints_verwendet,
                    scenario_id = resObj.scenario_id,
                )
                progressList.append(progressObj)

        return progressList

        
    except ValueError:
        raise HTTPException(status_code=404, detail=f"Invalid Paramater Value given as User ID")

    # Exception nachdem der User nicht gefunden wurde
    raise HTTPException(status_code=404, detail=f"Progress not found with User Id {userId}")


@app.get("/sterne")
async def getSterne(userId : int, session : SessionDep):
    """
    Die Sterne für jedes Szenario die ein User abgeschlossen hat werden zusammengezählt und zurückgegeben
    """    
    statement = select(Progress)
    for i in statement:
        print(i)
