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

# Hauptüberprüfung: Datei darf nicht existieren
check_exploit_absence() {
    local wall_dir="/home/Burgmauer"
    local exploit_file="exploit.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$wall_dir" ]; then
        return 0  # Wenn das Verzeichnis nicht existiert, existiert die Datei auch nicht → alles gut
    fi

    # Wechsel ins Verzeichnis
    cd "$wall_dir"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$exploit_file")" = "false" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur "true" aus, wenn die Datei **nicht** existiert
if check_exploit_absence; then
    echo "true"
fi
