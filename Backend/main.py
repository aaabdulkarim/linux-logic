from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI()

fakeProgress = [
    {
        "userId": 0,
        "scenarioId": 14
    },
    {
        "userId": 1,
        "scenarioId": 4
    }
]




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

