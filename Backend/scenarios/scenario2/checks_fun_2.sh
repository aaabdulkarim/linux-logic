#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine bestimmte Datei existiert (gibt nur true/false zurück)
check_file_exists() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung für magische Blumen
check_magische_blumen() {
    local garden_dir="/home/Garten"
    local flower1="Zauberblume1.txt"
    local flower2="Zauberblume2.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$garden_dir" ]; then
        echo "false"
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$garden_dir"

    # Überprüfen, ob beide Dateien existieren
    check_file_exists "$flower1"
    check_file_exists "$flower2"
}

# Führe die Hauptüberprüfung aus
check_magische_blumen
