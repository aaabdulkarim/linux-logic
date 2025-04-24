#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung der Verzeichnisberechtigungen
check_permissions() {
    local dir="$1"
    local expected_perm="$2"
    local perm=$(stat -c %a "$dir")
    if [ "$perm" = "$expected_perm" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung: Archivziel und Schutzordner
check_archive_permissions() {
    local archive_dir="/mnt/externer_speicher/wichtige_daten"
    local protection_dir="/home/Archiv"
    local required_perm="700"

    # Prüfen, ob das Archivziel existiert
    if [ ! -d "$archive_dir" ]; then
        return 1
    fi

    # Prüfen, ob der Schutzordner die richtigen Berechtigungen hat
    if [ "$(check_permissions "$protection_dir" "$required_perm")" = "true" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur "true" aus, wenn beide Bedingungen erfüllt sind
if check_archive_permissions; then
    echo "true"
else
    echo "false"
    exit 1
fi
