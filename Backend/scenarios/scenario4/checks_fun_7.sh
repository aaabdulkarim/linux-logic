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

# Hauptüberprüfung der Konfigurationsdatei
check_config_exists() {
    local versorgungs_dir="/home/Versorgung"
    local config_file="config_neu.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$versorgungs_dir" ]; then
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$versorgungs_dir"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$config_file")" = "true" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur ein "true" aus, wenn die Datei existiert
if check_config_exists; then
    echo "true"
fi
