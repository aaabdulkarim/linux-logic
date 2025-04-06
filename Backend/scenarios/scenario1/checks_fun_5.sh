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

# Funktion zur Überprüfung und Erstellung der Datei "licht1.txt"
check_beleuchtung() {
    # Wechsel ins Beleuchtungsverzeichnis
    cd /home/Beleuchtung
    
    # Überprüfen, ob die Datei "licht1.txt" existiert
    check_file "/home/Beleuchtung/licht1.txt"
    
    
}

# Hauptüberprüfung
check_beleuchtung
