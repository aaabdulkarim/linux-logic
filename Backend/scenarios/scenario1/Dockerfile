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
    sudo \  
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Create a user 'user' with password 'password' and add them to the sudo group
RUN useradd -m -s /bin/bash user && \
    echo 'user:password' | chpasswd && \
    adduser user sudo && \  
    chmod 755 /home/user  

# Create the required directories
RUN mkdir -p /home/Veranstaltung \
    /home/Einladungen \
    /home/Dekoration \
    /home/Kueche \
    /home/Musik \
    /home/Beleuchtung \
    /home/Reinigung \
    /home/Uhr 

RUN chmod -R 777 /home/*



# Create the required files
RUN touch /home/Einladungen/gaesteliste.txt && echo "Gast1" >  /home/Einladungen/gaesteliste.txt\
    /home/Dekoration/banner.txt \
    /home/Kueche/menue.txt \
    /home/Musik/musikliste.txt \
    /home/Uhr/zeit.txt && echo "11:00" > /home/Uhr/zeit.txt

RUN touch /home/Dekoration/banner.txt

# Copy the required scripts
COPY dockersocket.py .  
COPY healthcheck.py .  
COPY ICHBIN_SCENARIO1 .  
COPY checks_fun.sh .  
COPY checks_fun_1.sh .
COPY checks_fun_2.sh .
COPY checks_fun_3.sh .
COPY checks_fun_4.sh .
COPY checks_fun_5.sh .
COPY checks_fun_7.sh .
COPY checks_fun_8.sh .

RUN sed -i 's/\r$//' /app/checks_fun.sh
RUN sed -i 's/\r$//' /app/checks_fun_1.sh
RUN sed -i 's/\r$//' /app/checks_fun_2.sh
RUN sed -i 's/\r$//' /app/checks_fun_3.sh
RUN sed -i 's/\r$//' /app/checks_fun_4.sh
RUN sed -i 's/\r$//' /app/checks_fun_5.sh
RUN sed -i 's/\r$//' /app/checks_fun_7.sh
RUN sed -i 's/\r$//' /app/checks_fun_8.sh

RUN chown -R user:user /app /home
# Set up Python virtual environment and install dependencies
RUN python3 -m venv .venv && \
    . .venv/bin/activate && \
    pip install --no-cache-dir 'fastapi[all]'

    

# Switch to the new user
USER user



# Start the application
CMD ["/bin/bash", "-c", ". .venv/bin/activate && exec uvicorn dockersocket:app --host 0.0.0.0 --port 1000"]

# Healthcheck
HEALTHCHECK --interval=10s CMD ["/bin/bash", "-c", ". .venv/bin/activate && exec python3 healthcheck.py"]
