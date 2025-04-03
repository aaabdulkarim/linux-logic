#!/bin/bash

set -e  # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Gehe ins Verzeichnis der Einladungen und überprüfe die Gästeliste
check_guest_list() {
    local invite_dir="/home/Einladungen"
    local guest_list="$invite_dir/gaesteliste.txt"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$invite_dir" ]; then
        echo "false"
    fi

    # Prüfen, ob die Gästeliste existiert
    check_file "$guest_list"
}

# Hauptüberprüfung
check_guest_list
