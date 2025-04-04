#!/bin/bash

set -e

check_unkraut_entfernt() {
    cd /home/Garten || { echo "Verzeichnis /home/Garten konnte nicht betreten werden"; exit 1; }

    if ls Unkraut*.txt 1>/dev/null 2>&1; then
        echo "false"
    else
        echo "true"
    fi
}

check_unkraut_entfernt
