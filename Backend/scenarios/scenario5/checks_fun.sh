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

# Betrete das Garten-Verzeichnis
cd /home/MagischerGarten || mkdir -p /home/MagischerGarten && cd /home/MagischerGarten

echo "Gartenverzeichnis betreten."

# Pflanzen überprüfen
ls -l

echo "Pflanzeninhalt aufgelistet."

# Heilrezepte sichern
cp -f rezepte.txt rezepte_backup.txt

echo "Heilrezepte gesichert."

# Vergiftete Pflanzen heilen
touch heilung.txt
echo "Heilungsdetails hier eintragen" > heilung.txt

echo "Vergiftete Pflanzen dokumentiert."

# Überprüfung der Zaubertränke
check_file zaubertraenke.txt

# Berechtigungen für Zaubertränke hinzufügen
chmod 600 zaubertraenke.txt

echo "Zaubertränke sind nun geschützt."

# Fortschritt dokumentieren
touch fortschritt.txt
echo "Fortschritt dokumentieren."

echo "Wiederherstellungsprozess dokumentiert."
