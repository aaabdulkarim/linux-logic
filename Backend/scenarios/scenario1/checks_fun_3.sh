#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Dekoration..."

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
    check_file "/home/Dekoration/banner.txt"
    
    # Kopiere die Datei in das Veranstaltungsverzeichnis
    if [ -f "/home/Dekoration/banner.txt" ]; then
        echo "Das Banner wurde nach /home/Veranstaltung kopiert."
    fi
}

# Hauptüberprüfung
copy_banner
