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
RUN mkdir -p /home/Garten 

RUN touch /home/Garten/Loewenzahn.txt \
/home/Garten/Lavendel.txt \
/home/Garten/Unkraut_Brennnessel.txt \
/home/Garten/Unkraut2.txt \
/home/Garten/Unkraut3.txt \
/home/Garten/Unkraut4.txt \
/home/Garten/Gänseblümchen.txt

COPY dockersocket.py .
COPY healthcheck.py .
COPY ICHBIN_SCENARIO2 .
COPY checks_fun .

RUN bash -c "python3 -m venv .venv && source .venv/bin/activate && pip install 'fastapi[all]'"

CMD ["bash", "-c", "source .venv/bin/activate && uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]

HEALTHCHECK --interval=10s CMD ["bash" , "-c", "source .venv/bin/activate && python3 healthcheck.py"]