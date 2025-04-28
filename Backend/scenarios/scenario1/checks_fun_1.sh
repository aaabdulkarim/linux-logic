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

check_file "/home/Veranstaltung/roter_teppich.txt"
