#!/bin/bash

set -e  # Beende das Skript bei Fehlern


# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
  local file="$1"
    if [ -f "$file" ]; then
        if [ -s "$file" ]; then
            echo "true"
        else
            echo "false"
        fi
    else
        echo "false"
    fi
}

# Wechsel ins Musikverzeichnis und überprüfe, ob Musikdatei existiert
check_musik() {
    # Wechsel ins Musikverzeichnis
    cd /home/Musik
    
    
    # Überprüfen, ob eine Musikdatei (z.B. musikliste.txt) existiert
    check_file "/home/Musik/musikliste.txt"
}

# Hauptüberprüfung
check_musik
