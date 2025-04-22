#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob Unkraut entfernt wurde (gibt nur true/false zurück)
check_unkraut_entfernt() {
    local garden_dir="/home/Garten"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$garden_dir" ]; then
        echo "false"
        return 1
    fi

    # Prüfen, ob Dateien mit dem Namen 'Unkraut*.txt' existieren
    if ls "$garden_dir"/Unkraut*.txt 1>/dev/null 2>&1; then
        echo "false"
    else
        echo "true"
    fi
}

# Führe die Hauptüberprüfung aus
check_unkraut_entfernt