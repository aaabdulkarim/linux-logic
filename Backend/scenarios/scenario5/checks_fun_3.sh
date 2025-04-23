#!/bin/bash

set -e

# Überprüfung starten
if [ -d "/home/GeheimeKammer" ]; then
    echo "true"
    exit 0
else
    echo "false"
    exit 1
fi
