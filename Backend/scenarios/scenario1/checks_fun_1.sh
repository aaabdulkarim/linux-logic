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

# Funktion zur Überprüfung, ob das Verzeichnis existiert
check_directory() {
    local dir="$1"
    if [ -d "$dir" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Einzelne Überprüfung für den roten Teppich
check_roter_teppich() {
    # Verzeichnis wechseln
    cd /home/Veranstaltung || { echo "Verzeichnis /home/Veranstaltung konnte nicht betreten werden"; exit 1; }
    
    # Überprüfen, ob die Datei existiert
    check_file "/home/Veranstaltung/roter_teppich.txt"
}

# Hauptüberprüfung
check_roter_teppich
