# Backend

Das Backend bleibt gleich für beide Branches, weswegen eine Änderungen immer in beide Branches ausgeführt werden sollen


# REST - API

## Endpoints

- getProgress(userId) **GET-Method** 
- login(userName, password) **GET-Method**
- login(email, password) **GET-Method**

- register(userName, email, password) **POST-Method**
- saveProgress(userId, solvedChallenge) **POST-Method**
- addComment(userId, text) **POST-Method**

- addChallenge(docker-file) **POST-Method**
    - Dass diese Methode für das Frontend zugänglich ist, ist noch nicht fix, da wir noch nicht wissen ob der User Challenges hinzufügen kann. Jedoch wird das nicht zur Datenbank hinzugefügt, sondern in einer Speicherstrategie, wo die vorgefertigten Docker Files bestehen

# Dependencies
- Python
- FastAPI
    - pip install fastapi
    