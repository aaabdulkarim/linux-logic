#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei existiert und bestimmte Rechte hat
check_file_with_permissions() {
    local file="$1"
    local expected_perm="600"

    if [ -f "$file" ] && [ "$(stat -c %a "$file")" = "$expected_perm" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung der Backup-Datei mit spezifischen Berechtigungen
check_backup_permissions() {
    local wall_dir="/home/Burgmauer"
    local backup_file="sicherung_sicher.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$wall_dir" ]; then
        echo "false"
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$wall_dir"

    # Überprüfen, ob die Datei existiert und die richtigen Rechte hat
    check_file_with_permissions "$backup_file"
}

# Führe die Hauptüberprüfung aus
check_backup_permissions
