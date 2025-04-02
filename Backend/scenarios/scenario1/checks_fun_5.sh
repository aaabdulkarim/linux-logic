#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Musikabteilung..."

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

# Wechsel ins Musikverzeichnis und überprüfe, ob Musikdatei existiert
check_musik() {
    # Wechsel ins Musikverzeichnis
    cd /home/Musik
    
    # Zeige alle Dateien im Musikverzeichnis an
    ls
    
    # Überprüfen, ob eine Musikdatei (z.B. musikliste.txt) existiert
    check_file "/home/Musik/musikliste.txt"
}

# Hauptüberprüfung
echo "Starte Überprüfung der Musikabteilung..."
check_musik
echo "Überprüfung abgeschlossen."
