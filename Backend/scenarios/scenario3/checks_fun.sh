# mauer_tasks.sh
#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Betrete das Mauer-Verzeichnis
cd /home/Burgmauer || mkdir -p /home/Burgmauer && cd /home/Burgmauer

echo "Mauerverzeichnis betreten."

# Mauern überprüfen
ls

echo "Mauerinhalt aufgelistet."

# Pläne sichern
cp -f sicherung.txt sicherung_sicher.txt

echo "Pläne gesichert."

# Reparaturen dokumentieren
touch reparatur.txt
echo "Reparaturdetails hier eintragen" > reparatur.txt

echo "Reparaturen dokumentiert."

# Überprüfung der Sicherung
check_file sicherung_sicher.txt

# Schutzzauber hinzufügen
chmod 600 sicherung_sicher.txt

echo "Schutzzauber hinzugefügt. Mauern sind nun geschützt."
