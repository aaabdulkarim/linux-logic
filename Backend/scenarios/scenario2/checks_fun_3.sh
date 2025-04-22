#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei existiert und bestimmte Rechte hat
check_file_with_permissions() {
    local file="$1"
    local expected_perm="644"

    if [ -f "$file" ] && [ "$(stat -c %a "$file")" = "$expected_perm" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung der Verzauberung (Dateien mit korrekten Rechten)
check_verzauberung() {
    local garden_dir="/home/Garten"
    local z1="Zauberblume1.txt"
    local z2="Zauberblume2.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$garden_dir" ]; then
        echo "false"
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$garden_dir"

    # Überprüfen, ob beide Dateien mit den richtigen Rechten existieren
    check_file_with_permissions "$z1"
    check_file_with_permissions "$z2"
}

# Führe die Hauptüberprüfung aus
check_verzauberung
