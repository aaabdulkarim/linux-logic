#!/bin/bash

set -e

check_verzauberung() {
    cd /home/Garten || { echo "Verzeichnis /home/Garten konnte nicht betreten werden"; exit 1; }

    local z1="Zauberblume1.txt"
    local z2="Zauberblume2.txt"

    if [ -f "$z1" ] && [ "$(stat -c %a "$z1")" = "644" ] && \
       [ -f "$z2" ] && [ "$(stat -c %a "$z2")" = "644" ]; then
        echo "true"
    else
        echo "false"
    fi
}

check_verzauberung
