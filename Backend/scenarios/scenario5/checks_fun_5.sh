#!/bin/bash

set -e  # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    local dir="$1"
    if [ -d "$dir" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Einzelne Überprüfung: Sicherheitsmaßnahmen
check_sicherheitsmassnahmen() {
    # Überprüfung der Artefaktdatei im Tresor
    cd /home/Tresor || { echo "Verzeichnis /home/Tresor konnte nicht betreten werden"; exit 1; }
    check_file "/home/Tresor/artefakt.txt"

    # Überprüfung von Dateien in der geheimen Kammer
    cd /home/GeheimeKammer || { echo "Verzeichnis /home/GeheimeKammer konnte nicht betreten werden"; exit 1; }
    check_file "/home/GeheimeKammer/sicherheitsprotokoll.txt"
    check_file "/home/GeheimeKammer/aktivieren_alarm.sh"
}

# Hauptüberprüfung
check_sicherheitsmassnahmen
