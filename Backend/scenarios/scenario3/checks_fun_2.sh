#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Reparaturdokumentation im aktuellen Verzeichnis..."

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "✔ Datei existiert: $file"
        echo "true"
    else
        echo "✖ Datei fehlt: $file"
        echo "false"
    fi
}

# Hauptüberprüfung für die Reparaturdatei im aktuellen Verzeichnis
check_reparatur_dokument() {
    local file="reparatur.txt"

    echo "🔍 Suche nach $file im aktuellen Verzeichnis: $(pwd)"
    check_file "$file"
}

# Führe die Hauptüberprüfung aus
check_reparatur_dokument
