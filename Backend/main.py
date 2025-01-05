from typing import Annotated

from fastapi import FastAPI, HTTPException, Depends
from pydantic import BaseModel
from dotenv import load_dotenv, get_key
from sqlmodel import Field, Session, SQLModel, create_engine, select


# docs: https://fastapi.tiangolo.com/tutorial/sql-databases/
# sqlmodel docs: https://sqlmodel.tiangolo.com/tutorial/where/#where-land

# Laden des Connection Strings
load_dotenv()
connectionString = get_key(".env", "CONNECTION_STRING")


# Das Erstellen der psql/Neon Engine
engine = create_engine(connectionString)


class User(SQLModel, table=True):
    """
    Model Klasse für einen Linux Logic user
    """
    id: int | None = Field(default=None, primary_key=True)
    username: str = Field(index=True)
    email: str | None = Field(default=None, index=True)
    password_hash: int



# FastAPI App Variable
app = FastAPI()


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
            return True
        
    return False

@app.post("/register")
async def register(userModel : User, session: SessionDep):
    """
    Ein User wird registriert und zur Datenbank hinzugefügt
    """


@app.get("/progress")
async def getProgress(userId : int, session: SessionDep):
    """
    Der Progress wird als Zahl zurückgegeben. Die Zahl ist die ID des Progress.
    """
    try:
        userId = int(userId)
        for i in fakeProgress:
            if i["userId"] == userId:
                return {"userId": userId, "progress": i["scenarioId"]}
    
    except ValueError:
        raise HTTPException(status_code=404, detail=f"Invalid Paramater Value given as User ID")

    # Exception nachdem der User nicht gefunden wurde
    raise HTTPException(status_code=404, detail=f"Progress not found with User Id {userId}")

