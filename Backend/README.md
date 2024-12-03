# Backend

Das Backend bleibt gleich für beide Branches, weswegen eine Änderungen immer in beide Branches ausgeführt werden sollen


# REST - API

## Endpoints

- getProgress(userId) **GET-Method** 
- login(userName, password) **GET-Method**
- login(email, password) **GET-Method**

- register(userName, email, password) **POST-Method**
- saveProgress(userId, solvedChallengeId) **POST-Method**
- addComment(userId, text) **POST-Method**

- addChallenge(docker-file) **POST-Method**
    - Dass diese Methode für das Frontend zugänglich ist, ist noch nicht fix, da wir noch nicht wissen ob der User Challenges hinzufügen kann. Jedoch wird das nicht zur Datenbank hinzugefügt, sondern in einer Speicherstrategie, wo die vorgefertigten Docker Files bestehen

# Dependencies

## venv

Damit das Projekt ganzohne Dependency Clashes ausgeführt werden kann, wird eine Python Virtual Environment erstellt.
Diese kann über folgende Commands aktiviert werden.

**Achtung! Zuerst muss man im Backend Folder sein**


### Linux
```bash
python -m venv venv  # zum Erstellen
source venv/bin/activate
```

### Windows
Es ist wichtig den Befehl in einem CMD Terminal auszuführen. In Powershell wird die Virtual Environment nicht aktiviert und das Git Bash akzeptiert die Syntax nicht

```cmd
python -m venv venv  # zum Erstellen
.\venv\Scripts\activate
```

---
### Installation der Packages
Erst wenn die Environment erstellt und aktiviert wurde, muss man die Packages welche in der *requirements.txt* Datei installieren:

```bash
pip install -r requirements.txt
```

### Hinzufügen von Packages

Falls man Packages hinzufügen will, und venv aktiviert hat, kann man mit pip das gewünschte package installieren und die requirements.txt aktualisieren. Das geht mit folgenden Befehl:

```bash
pip freeze > requirements.txt
```



# Ausführen der API

Solange man im Backend Folder ist, die Virtual Environment mit ihren requirements installiert hat, sollte man fastapi dev main.py ausführen können