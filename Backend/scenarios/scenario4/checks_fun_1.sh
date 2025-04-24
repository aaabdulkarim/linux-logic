#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung der Dateiberechtigungen (sollte 700 sein)
check_permissions() {
    local file="$1"
    local perms
    perms=$(stat -c %a "$file") # Hole die Berechtigungen der Datei

    if [ "$perms" -eq 700 ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung der Datei und Berechtigungen
check_file_and_permissions() {
    local msg_dir="/home/Kommunikation"
    local encrypted_file="nachricht_geheim.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$msg_dir" ]; then
        echo "false"
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$msg_dir"

    # Überprüfen, ob die Datei existiert
    if [ ! -f "$encrypted_file" ]; then
        echo "false"
        return 1
    fi

    # Überprüfen der Berechtigungen der Datei (sollte 700 sein)
    check_permissions "$encrypted_file"
}

# Führe die Hauptüberprüfung aus
check_file_and_permissions
