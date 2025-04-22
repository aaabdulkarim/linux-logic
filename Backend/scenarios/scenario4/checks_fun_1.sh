#!/bin/bash

set -e

# Zielpfad zur verschlüsselten Datei
DATEI="/home/Kommunikation/nachricht_geheim.txt.gpg"

# 1. Überprüfen, ob die verschlüsselte Datei existiert
if [! -f "$DATEI" ]; then
    exit 1
fi

# Optional: Überprüfen, ob die Datei eine GPG-Datei ist (Magic-Bytes Check)
if file "$DATEI" | grep -qi "GPG encrypted"; then
    echo "true"
else
    exit 1
fi
