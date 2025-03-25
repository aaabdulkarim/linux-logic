from dataclasses import dataclass
from ScenarioTrack import ScenarioTrack

@dataclass
class UserDockerConnection:
    scm: ScenarioTrack
    container_name: str