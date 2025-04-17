from dataclasses import dataclass
from ScenarioTrack import ScenarioTrack
from datetime import datetime

@dataclass
class UserDockerConnection:
    scm: ScenarioTrack
    container_name: str
    last_interaction = datetime.now()

    def update_interaction(self):
        self.last_interaction = datetime.now()
