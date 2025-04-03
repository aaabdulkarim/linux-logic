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

# Gehe ins Dekorationsverzeichnis und kopiere das Banner
copy_banner() {
    # Wechsel ins Dekorationsverzeichnis
    cd /home/Dekoration
    
    # Überprüfen, ob die Datei banner.txt existiert
    check_file "/home/Veranstaltung/banner.txt"
    
    
}

# Hauptüberprüfung
copy_banner
