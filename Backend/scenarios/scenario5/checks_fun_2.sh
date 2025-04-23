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
if cd /home/Archiv 2>/dev/null && check_file "/home/Archiv/geheime_nachricht.txt.gpg"; then
    echo "true"
    exit 0
else
    echo "false"
    exit 1
fi
