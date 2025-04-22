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

# Hauptüberprüfung der Backup-Datei
check_backup() {
    local wall_dir="/home/Burgmauer"
    local backup_file="sicherung_sicher.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$wall_dir" ]; then
        echo "false"
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$wall_dir"

    # Überprüfen, ob die Backup-Datei existiert
    check_file_exists "$backup_file"
}

# Führe die Hauptüberprüfung aus
check_backup
