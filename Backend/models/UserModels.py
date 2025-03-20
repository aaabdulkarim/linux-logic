from sqlmodel import SQLModel, Field

class UserBase(SQLModel):
    username: str = Field(index=True)

class UserDB(UserBase, table=True):

    __tablename__ = "user" 
    
    
    email: str | None = Field(default=None, index=True)
    id: int | None = Field(default=None, primary_key=True)
    password_hash: int
    session_id: int

class UserCreate(UserBase):
 
    email: str | None = Field(default=None, index=True)
    password: str  
    
class UserRead(UserBase):
    id: int
