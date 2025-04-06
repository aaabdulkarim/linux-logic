#!/bin/bash

set -e

check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

check_backup() {
    cd /home/Burgmauer || { echo "❌ Verzeichnis nicht gefunden"; exit 1; }
    check_file "sicherung_sicher.txt"
}

check_backup
