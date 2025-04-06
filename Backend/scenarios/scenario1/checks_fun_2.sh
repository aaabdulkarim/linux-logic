#!/bin/bash

set -e # Beende das Skript bei Fehlern

# Funktion zur Überprüfung, ob eine Datei eine bestimmte Zeile enthält (gibt nur true/false zurück)
check_file_contains_line() {
    local file="$1"
    local line="$2"
    if grep -qF "$line" "$file"; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung der Gästeliste (fokusiert auf den Inhalt)
check_guest_list() {
    local invite_dir="/home/Einladungen"
    local guest_list="$invite_dir/gaesteliste.txt"
    local expected_line="ich"

    # Prüfen, ob das Verzeichnis existiert
    if [ ! -d "$invite_dir" ]; then
        echo "false"
        return 1
    fi

    # Überprüfen, ob die Gästeliste die erwartete Zeile enthält
    check_file_contains_line "$guest_list" "$expected_line"
}

# Führe die Hauptüberprüfung aus
check_guest_list