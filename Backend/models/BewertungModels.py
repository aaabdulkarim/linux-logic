class Bewertung(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    user_id: int = Field(index=True)
    scenario_id: int = Field(index=True)
    bewertung: int
    kommentar: str = None  
