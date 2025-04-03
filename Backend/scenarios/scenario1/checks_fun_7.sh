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

# Funktion zur Überprüfung und Erstellung der Datei "boden_sauber.txt"
check_bodenreinigung() {
    # Wechsel ins Reinigungsverzeichnis
    cd /home/Reinigung
    
    # Überprüfen, ob die Datei "boden_sauber.txt" existiert
    check_file "/home/Reinigung/boden_sauber.txt"
    
    
}

# Hauptüberprüfung
check_bodenreinigung
