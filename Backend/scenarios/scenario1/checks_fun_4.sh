#!/bin/bash

set -e  # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob die Datei richtig bearbeitet wurde
check_file() {
    local file="$1"
    local content="Neues Gericht"
    
    if [ -f "$file" ] && grep -Fxq "$content" "$file"; then
        echo true

    else
        echo false
    fi
}

check_dish() {
    # Wechsel ins Küchenverzeichnis
    cd /home/Kueche || { echo "Verzeichnis /home/Kueche existiert nicht!"; exit 1; }
    
    local menu_file="menue.txt"
    
    # Überprüfen, ob die Datei richtig bearbeitet wurde
    check_file "$menu_file"
}

# Hauptüberprüfung
check_dish