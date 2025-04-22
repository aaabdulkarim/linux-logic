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

    # Gewünschte Berechtigungen – z.B. 600 (rw-------)
    local required_perms="-rw-------"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$garden_dir" ]; then
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$garden_dir" || return 1

    # Prüfen, ob beide Dateien existieren und die richtigen Rechte haben
    if [ -f "$z1" ] && [ -f "$z2" ]; then
        local perms1 perms2
        perms1=$(stat -c "%A" "$z1")
        perms2=$(stat -c "%A" "$z2")

        if [ "$perms1" = "$required_perms" ] && [ "$perms2" = "$required_perms" ]; then
            echo "true"
            return 0
        fi
    fi

    return 1
}

# Führe die Hauptüberprüfung aus
check_verzauberung
