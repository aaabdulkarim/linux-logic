#!/bin/bash

set -e

ERGEBNIS="/home/Geheimdienst/ergebnisse.txt"

if [ -f "$ERGEBNIS" ]; then
    echo "true"
else
    exit 1
fi
