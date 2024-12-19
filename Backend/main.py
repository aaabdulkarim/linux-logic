from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from dotenv import load_dotenv, get_key
from sqlmodel import create_engine, select


# Laden des Connection Strings
load_dotenv()
connectionString = get_key(".env", "CONNECTION_STRING")


# Das Erstellen der psql/Neon Engine
connect_args = {"check_same_thread": False}
engine = create_engine(connectionString, connect_args=connect_args)

app = FastAPI()


@app.get("/progress")
async def getProgress(userId):
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

