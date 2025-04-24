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

# Hauptüberprüfung der Bericht-Datei
check_report_exists() {
    local hq_dir="/home/Hauptquartier"
    local report_file="bericht.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$hq_dir" ]; then
        return 1
    fi

    # Wechsel ins Verzeichnis
    cd "$hq_dir"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$report_file")" = "true" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur ein "true" aus, wenn die Datei existiert
if check_report_exists; then
    echo "true"
fi
