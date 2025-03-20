from sqlmodel import SQLModel, Field


class ProgressBase(SQLModel):
    scenario_id: int = Field(index=True, foreign_key="scenarios.id")
    hints_verwendet: int = Field(index=True)
    loesungen_verwendet: int = Field(index=True)

class ProgressDB(ProgressBase, table=True):
    __tablename__ = "progress" 

    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True, foreign_key="user.id")
    