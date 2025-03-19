from sqlmodel import Field, Session, SQLModel, create_engine, select
from pydantic import BaseModel



class Progress(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True, foreign_key="user.id")
    scenario_id: int = Field(index=True, foreign_key="scenarios.id")
    hints_verwendet: int = Field(index=True)
    loesungen_verwendet: int = Field(index=True)




class ProgressPyModel(BaseModel):
    loesungen_verwendet : int
    hints_verwendet : int
    scenario_id : int

