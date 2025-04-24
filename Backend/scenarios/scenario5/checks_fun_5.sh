#!/bin/bash

set -e

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    [ -f "$file" ]
}

# Hauptüberprüfung: Sicherheitsmaßnahmen
check_sicherheitsmassnahmen() {
    # Überprüfen, ob die sicherheitsprotokoll.txt erstellt wurde
    if check_file "/home/GeheimeKammer/sicherheitsprotokoll.txt" && \
       check_file "/home/GeheimeKammer/sicher_ist_sicher.zip"; then
        echo "true"
        return 0
    else
        echo "Fehler: Eine oder beide Dateien wurden nicht erstellt!"
        return 1
    fi
}

# Hauptfunktion aufrufen
check_sicherheitsmassnahmen
