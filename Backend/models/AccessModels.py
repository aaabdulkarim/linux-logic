from sqlmodel import SQLModel, Field
from datetime import datetime
from typing import Optional


class AccessCode(SQLModel, table=True):

    id: int | None = Field(default=None, primary_key=True)
    code: str
    used: bool
