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

# Erstelle die benötigten Verzeichnisse
RUN mkdir -p /home/Veranstaltung \
    /home/Einladungen \
    /home/Dekoration \
    /home/Kueche \
    /home/Musik \
    /home/Beleuchtung \
    /home/Reinigung \
    /home/Uhr

# Erstelle die benötigten Dateien
RUN touch /home/Einladungen/gaesteliste.txt \
    /home/Dekoration/banner.txt \
    /home/Kueche/menue.txt \
    /home/Musik/musikliste.txt \
    /home/Uhr/zeit.txt && echo "11:00" > /home/Uhr/zeit.txt

# Kopiere die benötigten Skripte
COPY dockersocket.py .
COPY healthcheck.py .
COPY ICHBIN_SCENARIO1 .
COPY checks_fun.sh .

# Set up Python virtual environment and install dependencies
RUN python3 -m venv .venv && \
    . .venv/bin/activate && \
    pip install --no-cache-dir 'fastapi[all]'

# Start the application
CMD ["/bin/bash", "-c", ". .venv/bin/activate && exec uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]

# Healthcheck
HEALTHCHECK --interval=10s CMD ["/bin/bash", "-c", ". .venv/bin/activate && exec python3 healthcheck.py"]
