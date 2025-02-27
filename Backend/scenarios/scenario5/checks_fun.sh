#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "[OK] $1 existiert."
    else
        echo "[FEHLER] $1 fehlt!"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    if [ -d "$1" ]; then
        echo "[OK] $1 existiert."
    else
        echo "[FEHLER] $1 fehlt!"
    fi
}

# 1. Spurensuche beginnen
echo "Überprüfung: Archiv..."
check_file "/home/Archiv/artefakt_hinweis.txt"

# 2. Verschlüsselte Botschaften entschlüsseln
echo "Überprüfung: Entschlüsselung..."
check_file "/home/Archiv/geheime_nachricht.txt.gpg"

# 3. Geheime Kammer betreten
echo "Überprüfung: Geheime Kammer..."
check_directory "/home/GeheimeKammer"

# 4. Artefakt bergen
echo "Überprüfung: Artefakt..."
check_file "/home/Tresor/artefakt.txt"

# 5. Sicherheitsmaßnahmen erhöhen
echo "Überprüfung: Sicherheitsmaßnahmen..."
check_file "/home/Tresor/artefakt.txt"
check_file "/home/GeheimeKammer/sicherheitsprotokoll.txt"
check_file "/home/GeheimeKammer/aktivieren_alarm.sh"

echo "Überprüfung abgeschlossen!"
