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

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    local dir="$1"
    if [ -d "$dir" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Einzelne Überprüfung: Geheime Kammer
check_geheime_kammer() {
    check_directory "/home/GeheimeKammer"
}

# Hauptüberprüfung
check_geheime_kammer
