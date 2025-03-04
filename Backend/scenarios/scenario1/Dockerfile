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

# Erstelle die benötigten Verzeichnisse und Dateien
RUN mkdir -p /home/Veranstaltung \
    /home/Einladungen \
    /home/Einladungen/gaesteliste.txt \
    /home/Dekoration \
    /home/Dekoration/banner.txt \
    /home/Kueche \
    /home/Kueche/menue.txt \
    /home/Musik \
    /home/Musik/musikliste.txt \
    /home/Beleuchtung \
    /home/Reinigung \
    /home/Uhr \
    /home/Uhr/zeit.txt\
    && echo "11:00" > /home/Uhr/zeit.txt

# Kopiere die benötigten Skripte
COPY dockersocket.py .
COPY healthcheck.py .
COPY ICHBIN_SCENARIO1 .

# Set up Python virtual environment and install dependencies
RUN bash -c "python3 -m venv .venv && source .venv/bin/activate && pip install 'fastapi[all]'"

# Start the application
CMD ["bash", "-c", "source .venv/bin/activate && uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]

# Healthcheck
HEALTHCHECK --interval=10s CMD ["bash", "-c", "source .venv/bin/activate && python3 healthcheck.py"]
