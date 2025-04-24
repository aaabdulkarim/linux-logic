#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei existiert
check_file_exists() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung der Backup-Datei
check_backup_exists() {
    local backup_dir="/home/Sicherheit"
    local backup_file="schatzkammer_backup.tar.gz"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$backup_dir" ]; then
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$backup_dir"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$backup_file")" = "true" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur ein "true" aus, wenn die Datei existiert
if check_backup_exists; then
    echo "true"
fi
