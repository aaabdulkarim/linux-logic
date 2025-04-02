#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Einladungen..."

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "✔ Datei existiert: $file"
        echo "true"
    else
        echo "✖ Datei fehlt: $file"
        echo "false"
    fi
}

# Gehe ins Verzeichnis der Einladungen und lade einen Ritter ein
check_guest_list() {
    # Wechsel ins Einladungsverzeichnis
    cd /home/Einladungen
    
    # Zeige alle Dateien im Verzeichnis an
    ls /home/Einladungen
    
    # Überprüfen, ob die Gästeliste (gaesteliste.txt) existiert
    check_file "/home/Einladungen/ritter.txt"
}

# Hauptüberprüfung
echo "Starte Überprüfung der Einladungen..."
check_guest_list
echo "Überprüfung abgeschlossen."
