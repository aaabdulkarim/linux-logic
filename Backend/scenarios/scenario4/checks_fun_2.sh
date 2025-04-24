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
check_blacklist_absence() {
    local sec_dir="/home/Sicherheit"
    local blacklist_file="blacklist_ip.txt"

    # Falls Verzeichnis nicht existiert → Blacklist existiert nicht
    if [ ! -d "$sec_dir" ]; then
        return 0
    fi

    # Wechsel ins Verzeichnis
    cd "$sec_dir"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$blacklist_file")" = "false" ]; then
        return 0
    else
        return 1
    fi
}

# Führe die Überprüfung aus und gib nur ein "true" aus, wenn alles passt
if check_blacklist_absence; then
    echo "true"
fi
