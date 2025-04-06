#!/bin/bash
set -e  # Beende das Skript bei Fehlern

# Wechsel in das Mauer-Verzeichnis
cd /home/Burgmauer || { echo "❌ Verzeichnis /home/Burgmauer konnte nicht betreten werden"; exit 1; }

# Überprüfen, ob die Datei existiert
file="sicherung_sicher.txt"
if [ ! -f "$file" ]; then
    echo "false"
    exit 1
fi

# Überprüfen, ob die Datei die Berechtigung 600 hat
perm=$(stat -c %a "$file")
if [ "$perm" != "600" ]; then
    echo "❌ Datei $file hat nicht die Berechtigung 600 (aktuell: $perm)!"
    exit 1
fi

echo "Datei $file existiert und hat die Berechtigung 600."
