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

# Einzelne Überprüfung: Artefakt
check_artefakt() {
    cd /home/Tresor || { echo "Verzeichnis /home/Tresor konnte nicht betreten werden"; exit 1; }
    check_file "/home/Tresor/artefakt.txt"
}

# Hauptüberprüfung
check_artefakt
