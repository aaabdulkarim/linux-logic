#!/bin/bash

set -e

DATEI="/home/Versorgung/config_neu.txt"

if [ -f "$DATEI" ]; then
    echo "true"
else
    exit 1
fi

