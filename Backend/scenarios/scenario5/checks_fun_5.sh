#!/bin/bash

set -e

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    [ -f "$file" ]
}

# Hauptüberprüfung: Sicherheitsmaßnahmen
check_sicherheitsmassnahmen() {
    if check_file "/home/Tresor/artefakt.txt" && \
       check_file "/home/GeheimeKammer/sicherheitsprotokoll.txt" && \
       check_file "/home/GeheimeKammer/aktivieren_alarm.sh"; then
        echo "true"
        return 0
    else
        echo "false"
        return 1
    fi
}

check_sicherheitsmassnahmen
