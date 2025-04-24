#!/bin/bash

set -e

# Überprüfung starten
if [ -d "/home/GeheimeKammer" ]; then
    
        # Überprüfen, ob die Datei offen.txt existiert
        if [ -f "/home/GeheimeKammer/offen.txt" ]; then
            echo "true"
            exit 0
        else
            echo "Fehler: Die Datei offen.txt existiert nicht."
            exit 1
        fi
else
    echo "false"
    exit 1
fi
