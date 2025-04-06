from dataclasses import dataclass
from ScenarioTrack import ScenarioTrack
import datetime

@dataclass
class UserDockerConnection:
    scm: ScenarioTrack
    container_name: str
    # created_at : datetime.datetime