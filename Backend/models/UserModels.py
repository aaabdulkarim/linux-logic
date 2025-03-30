from sqlmodel import SQLModel, Field
from datetime import datetime
from typing import Optional

class UserBase(SQLModel):
    username: str = Field(index=True)

class UserDB(UserBase, table=True):

    __tablename__ = "user" 
    
    
    email: str | None = Field(default=None, index=True)
    id: int | None = Field(default=None, primary_key=True)
    password_hash: str
    session_id: int | None = Field(default=None, index=True)
    session_expiry: datetime | None = Field(default=None)


class UserRead(UserBase):
    stayLoggedIn : bool | None = Field(default=False) 
    email: str | None = Field(default=None, index=True)
    password: str  

    