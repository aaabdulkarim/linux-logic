from sqlmodel import SQLModel, Field

class UserBase(SQLModel):
    """
    Basis User Model. Enthält Attribute die für alle Operationen notwendig sind
    """
    username: str = Field(index=True)
    email: str | None = Field(default=None, index=True)

class UserDB(UserBase, table=True):
    """
    Database model für User 
    """
    __tablename__ = "user"

    id: int | None = Field(default=None, primary_key=True)
    password_hash: int
    session_id: int

class UserCreate(UserBase):
    """
    Model für Body Request beim Erstellen von User
    """
    password: str  

class UserRead(UserBase):
    """
    Response model um einen User zurückzugeben
    Exkludiert password_hash wegen Sicherheit
    """
    id: int
