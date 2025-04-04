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

check_magische_blumen() {
    cd /home/Garten || { echo "Verzeichnis /home/Garten konnte nicht betreten werden"; exit 1; }

    check_file "Zauberblume1.txt"
    check_file "Zauberblume2.txt"
}

check_magische_blumen
