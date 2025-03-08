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
    && apt-get clean && rm -rf /var/lib/apt/lists/*  

# Verzeichnisse erstellen  
RUN mkdir -p /home/Burgmauer  

# Beispiel-Dateien erstellen  
RUN touch /home/Burgmauer/sicherung.txt && echo "Keine Sicherheit :(" > /home/Burgmauer/sicherung.txt  
RUN touch /home/Burgmauer/reparatur.txt  

# Skripte und Dateien kopieren  
COPY dockersocket.py .  
COPY healthcheck.py .  
COPY ICHBIN_SCENARIO3 .  
COPY checks_fun.sh .  

# Python-Umgebung einrichten  
RUN bash -c "python3 -m venv .venv && source .venv/bin/activate && pip install 'fastapi[all]'"  

# FastAPI starten  
CMD ["bash", "-c", "source .venv/bin/activate && uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]  

# Healthcheck  
HEALTHCHECK --interval=10s CMD ["bash" , "-c", "source .venv/bin/activate && python3 healthcheck.py"]  
