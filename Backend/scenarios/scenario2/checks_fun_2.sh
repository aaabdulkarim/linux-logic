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
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$garden_dir" || return 1

    # Prüfen, ob beide Dateien existieren
    if [ -f "$flower1" ] && [ -f "$flower2" ]; then
        echo "true"
        return 0
    else
        return 1
    fi
}


# Führe die Hauptüberprüfung aus
check_magische_blumen
