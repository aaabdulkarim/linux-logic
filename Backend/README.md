# Backend

Das Backend bleibt gleich für beide Branches, weswegen eine Änderungen immer in beide Branches ausgeführt werden sollen


# REST - API

## Endpoints

- getProgress(userId) **GET-Method** 
- login(userName, password) **GET-Method**
- login(email, password) **GET-Method**
- editPassword(userName, password) **PUT-Method**

- register(userName, email, password) **POST-Method**
- saveProgress(userId, solvedChallengeId) **POST-Method**
- addComment(userId, text) **POST-Method**

- addChallenge(docker-file) **POST-Method**
    - Dass diese Methode für das Frontend zugänglich ist, ist noch nicht fix, da wir noch nicht wissen ob der User Challenges hinzufügen kann. Jedoch wird das nicht zur Datenbank hinzugefügt, sondern in einer Speicherstrategie, wo die vorgefertigten Docker Files bestehen


## Anbindung an die Datenbank

Die Datenbank läuft auf Neon über psql weil FastAPI gut für diese Datenbank geeignet ist. Das Python Package sqlmodel ist geschrieben von dem Autor von FastAPI und basiert auf SQLAlchemy mit Pydantic: 

- SQLAlchemy ist ein ORM, welches eine einfache Datenbankprogrammierung durch Python ermöglicht 
- Pydantic ist ein Tool, welches einfache Daten in Python Objekte/Klassen umwandelt. Mit diesen Models kann man einfacher arbeiten.



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

```powershell
python -m venv venv  # zum Erstellen
.\venv\Scripts\activate
```

*Falls dieser Fehler vorkommt:*
```powershell
"about_Execution_Policies" (https:/go.microsoft.com/fwlink/?LinkID=135170).
In Zeile:1 Zeichen:1
+ .\venv\Scripts\activate
+ ~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : Sicherheitsfehler: (:) [], PSSecurityException
    + FullyQualifiedErrorId : UnauthorizedAccess
```

*Muss davor dieser Command ausgeführt werden*
```Powershell
Set-ExecutionPolicy Unrestricted -Scope Process
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

## Datenbankanbindung

Damit das Backend sich mit der Datenbank verbinden kann, muss im Directory *linux-logic/Backend* eine *.env* Datei erstellt werden und mit folgenden Inhalt gefüllt sein:

```python
CONNECTION_STRING="DEIN NEON CONNECTION STRING"
```



# Ausführen der API

Solange man im Backend Folder ist, die Virtual Environment mit ihren requirements installiert hat, sollte man fastapi dev main.py ausführen können

**Alternativ mit uvicorn starten:**

*uvicorn dockersocketmain:app --host {IP vom Computer} --port 8000*

# Test - Infos

Um die Websocket Verbindung zu testen gibt es folgende Voraussetzungen

- FastAPI und Python VENV Setup wie Oben erklärt; [Voraussetzungen](#dependencies)
- Docker

Befehl:
```bash
docker image pull debian
``` 

## Testprozess

#### Backend Setup

- Starten der venv  [venv](#venv)
  
- Starten der API [Ausführen-der-Api](#ausführen-der-api)

#### Frontend Setup

- [test.html](test/test.html) im Browser öffnen

- Sicherstellen, dass [test.js](test/test.html) in [test](test/) vorhanden ist

#### Benutzen des Debian Containers

Standard Befehle ausprobieren

| Befehl               | Erwarteter Output                                        |
|----------------------|----------------------------------------------------------|
| `ls`                 | `bin boot dev etc home lib lib64 media mnt opt proc root run sbin srv sys tmp usr var` |
| `pwd`                | `/`                                                      |
| `cd`                 | *leer*                                                   |
| `nano`               | `sh: 1: nano: not found`                                 |
| `mkdir test`        | *leer* |


#### Docker Befehle zum Testen

Im Backend/dockerfolder

``docker build -t newone .``

``docker run -p 1000:1000 newone``


und *dockersocketmain.py* starten wie bei [Ausführen-der-Api](#ausführen-der-api) nur dass, der Name der Datei *dockersocketmain.py* ist
