FROM debian:latest  
WORKDIR /app  

# Install system dependencies and Python  
RUN apt-get update && apt-get install -y --no-install-recommends \  
    build-essential \  
    libssl-dev \  
    libffi-dev \  
    python3 \  
    python3-pip \  
    python3-venv \  
    gpg \  
    sudo \  
    && apt-get clean && rm -rf /var/lib/apt/lists/*  

# Create a user 'user' with password 'password'  
RUN useradd -m -s /bin/bash user && \
    echo 'user:password' | chpasswd && \
    adduser user sudo && \  
    chmod 755 /home/user 

# Verzeichnisse erstellen  
RUN mkdir -p /home/Archiv \  
    /home/GeheimeKammer \  
    /home/Tresor  

# Beispiel-Dateien erstellen (leer)  
RUN touch /home/Archiv/geheime_nachricht.txt.gpg  
RUN touch /home/Archiv/artefakt_hinweis.txt  
RUN touch /home/GeheimeKammer/schalter_aktivieren.sh 
RUN touch /home/aktivieren_alarm.sh  

COPY schalter_aktivieren.sh /home/Archiv/schalter_aktivieren.sh

# Skripte und Dateien kopieren  
COPY dockersocket.py .  
COPY healthcheck.py .  
COPY ICHBIN_SCENARIO5 .
COPY checks_fun_1.sh .
COPY checks_fun_2.sh .
COPY checks_fun_3.sh .
COPY checks_fun_4.sh .
COPY checks_fun_5.sh .

RUN sed -i 's/\r$//' /app/checks_fun_1.sh
RUN sed -i 's/\r$//' /app/checks_fun_2.sh
RUN sed -i 's/\r$//' /app/checks_fun_3.sh
RUN sed -i 's/\r$//' /app/checks_fun_4.sh
RUN sed -i 's/\r$//' /app/checks_fun_5.sh

# Python-Umgebung einrichten  
RUN bash -c "python3 -m venv .venv && source .venv/bin/activate && pip install 'fastapi[all]'"  

# Setze den Besitzer der Dateien auf den neuen Benutzer  
RUN chown -R user:user /app /home

# Wechsel zum Benutzer 'user'  
USER user

# FastAPI starten  
CMD ["bash", "-c", "source .venv/bin/activate && uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]  

# Healthcheck  
HEALTHCHECK --interval=10s CMD ["bash" , "-c", "source .venv/bin/activate && python3 healthcheck.py"]  
