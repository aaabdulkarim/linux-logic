#!/bin/bash

set -e

# Artefakt-Check
ARTIFACT_PATH="/home/Tresor/artefakt.txt"

# Überprüfen, ob das Artefakt verschoben wurde
if [ -f "$ARTIFACT_PATH" ]; then
    # Überprüfen, ob die Datei die richtigen Berechtigungen hat (400)
    PERMS=$(stat -c %a "$ARTIFACT_PATH")

    if [ "$PERMS" -eq 400 ]; then
        echo "true"
        exit 0
    else
        echo "false: Berechtigungen nicht korrekt (erforderlich: 400)"
        exit 1
    fi
else
    echo "false: Artefakt nicht gefunden"
    exit 1
fi
