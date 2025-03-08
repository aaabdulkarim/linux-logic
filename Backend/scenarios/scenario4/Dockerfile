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
    && apt-get clean && rm -rf /var/lib/apt/lists/*  

# Verzeichnisse erstellen  
RUN mkdir -p /home/Kommunikation \  
    /home/Sicherheit \  
    /home/Hauptquartier \  
    /home/Schatzkammer \  
    /home/Burgmauer \  
    /home/Geheimdienst \  
    /home/Versorgung \  
    /home/Archiv  

# Beispiel-Dateien erstellen (leer)  
RUN touch /home/Kommunikation/nachricht_geheim.txt  
RUN touch /home/Sicherheit/system.log  
RUN touch /home/Sicherheit/blacklist_ip.txt  
RUN touch /home/Hauptquartier/backup_bericht.txt  
RUN touch /home/Schatzkammer/schatz.txt  
RUN touch /home/Burgmauer/exploit.txt  
RUN touch /home/Geheimdienst/scan_bedrohungen.sh  
RUN touch /home/Geheimdienst/ergebnisse.txt  
RUN touch /home/Versorgung/config_neu.txt  
RUN touch /home/Archiv/wichtige_daten  

# Skripte und Dateien kopieren  
COPY dockersocket.py .  
COPY healthcheck.py .  
COPY ICHBIN_SCENARIO4 .  
COPY checks_fun.sh .  

# Python-Umgebung einrichten  
RUN bash -c "python3 -m venv .venv && source .venv/bin/activate && pip install 'fastapi[all]'"  

# FastAPI starten  
CMD ["bash", "-c", "source .venv/bin/activate && uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]  

# Healthcheck  
HEALTHCHECK --interval=10s CMD ["bash" , "-c", "source .venv/bin/activate && python3 healthcheck.py"]  
