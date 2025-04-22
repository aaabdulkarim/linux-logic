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

# Hauptüberprüfung für die Reparaturdatei im Verzeichnis /home/Burgmauer
check_reparatur_dokument() {
    local burgmauer_dir="/home/Burgmauer"
    local file="reparatur.txt"

    # Überprüfung der Datei
    check_file "$file"
}

# Führe die Hauptüberprüfung aus
check_reparatur_dokument
