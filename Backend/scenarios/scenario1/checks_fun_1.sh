#!/bin/bash

set -e

check_file() {
    local file="$1"
    echo "Überprüfe Datei: '$file'"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

check_roter_teppich() {
    local dir="/home/Veranstaltung"
    echo "Wechsle in Verzeichnis: '$dir'"
    cd "$dir" || { echo "Verzeichnis $dir konnte nicht betreten werden"; exit 1; }

    echo "Aktuelles Verzeichnis: $(pwd)"
    echo "Dateien im aktuellen Verzeichnis:"
    ls -la

    check_file "roter_teppich.txt"
}

check_roter_teppich
