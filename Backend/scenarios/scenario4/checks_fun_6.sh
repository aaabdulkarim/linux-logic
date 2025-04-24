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

# Funktion zur Überprüfung, ob die ergebnisse.txt Datei die erwartete Ausgabe enthält
check_ergebnisse() {
    local ergebnisse_file="/home/Geheimdienst/ergebnisse.txt"

    # Prüfen, ob die Datei existiert
    if [ "$(check_file_exists "$ergebnisse_file")" = "false" ]; then
        echo "false: Die Datei ergebnisse.txt existiert nicht!"
        return 1
    fi

    # Prüfen, ob die Datei den erwarteten Inhalt hat
    if grep -q "Starte Bedrohungsprüfung..." "$ergebnisse_file" && \
       grep -q "Untersuche systemlog.txt nach möglichen Bedrohungen." "$ergebnisse_file" && \
       grep -q "Keine Bedrohungen gefunden. Alles in Ordnung." "$ergebnisse_file"; then
        echo "true"
    else
        echo "false"
        return 1
    fi
}

# Führe die Überprüfung aus
check_ergebnisse
