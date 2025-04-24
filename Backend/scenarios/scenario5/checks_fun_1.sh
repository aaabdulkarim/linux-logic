#!/bin/bash

set -e

check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        return 0
    else
        return 1
    fi
}

# Überprüfung starten
if cd /home/Archiv 2>/dev/null && check_file "/home/Archiv/info_artefakt.txt"; then
    echo "true"
    exit 0
else
    echo "Die Datei info_artefakt.txt existiert nicht."
    exit 1
fi
