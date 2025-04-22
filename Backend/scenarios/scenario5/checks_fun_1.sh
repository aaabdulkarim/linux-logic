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

# Einzelne Überprüfung für die Archiv-Datei
check_artefakt_hinweis() {
    # Verzeichnis wechseln
    cd /home/Archiv || { echo "Verzeichnis /home/Archiv konnte nicht betreten werden"; exit 1; }

    # Überprüfen, ob die Datei existiert
    check_file "/home/Archiv/artefakt_hinweis.txt"
}

# Hauptüberprüfung
check_artefakt_hinweis
