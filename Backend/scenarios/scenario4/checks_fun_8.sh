#!/bin/bash

set -e

ARCHIVZIEL="/mnt/externer_speicher/wichtige_daten"
SCHUTZORDNER="/home/Archiv"

if [ -d "$ARCHIVZIEL" ]; then
    PERM=$(stat -c %a "$SCHUTZORDNER")
    if [ "$PERM" = "700" ]; then
        echo "true"
        exit 0
    fi
fi

echo "false"
exit 1
