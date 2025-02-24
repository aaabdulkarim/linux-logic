

# garten_tasks.sh
#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Betrete den Garten
cd /home/Garten || mkdir -p /home/Garten && cd /home/Garten

echo "Gartenverzeichnis betreten."

# Zustand des Gartens erkunden
ls

echo "Garteninhalt aufgelistet."

# Unkraut entfernen
rm -f Unkraut*.txt

echo "Unkraut entfernt."

# Magische Blumen pflanzen
touch Zauberblume1.txt Zauberblume2.txt

echo "Magische Blumen gepflanzt."

# Überprüfung der magischen Blumen
check_file Zauberblume1.txt
check_file Zauberblume2.txt

# Garten verzaubern
chmod 644 Zauberblume*.txt

echo "Magische Blumen verzaubert."