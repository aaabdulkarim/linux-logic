from sqlmodel import SQLModel, Field




class ScenarioBase(SQLModel):
    id: int = Field(index=True)
    hints_verwendet: int = Field(index=True)
    loesungen_verwendet: int = Field(index=True)

class ScenarioDB(ScenarioBase, table=True):
    __tablename__ = "progress" 

    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True, foreign_key="user.id")