#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Kommunikationskanal..."

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "✔ Datei existiert: $file"
        echo "true"
    else
        echo "✖ Datei fehlt: $file"
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    local dir="$1"
    if [ -d "$dir" ]; then
        echo "✔ Verzeichnis existiert: $dir"
        echo "true"
    else
        echo "✖ Verzeichnis fehlt: $dir"
        echo "false"
    fi
}

# Einzelne Überprüfungen
check_gaesteliste() { check_file "/home/Einladungen/gaesteliste.txt"; }
check_roter_teppich() { check_file "/home/Veranstaltung/roter_teppich.txt"; }
check_banner() { check_file "/home/Veranstaltung/banner.txt"; }
check_menue() { check_file "/home/Kueche/menue.txt"; }
check_musik() { check_file "/home/Musik/musikliste.txt"; }
check_beleuchtung() { check_file "/home/Beleuchtung/licht1.txt"; }
check_reinigung() { check_file "/home/Reinigung/boden_sauber.txt"; }
check_uhr() { check_file "/home/Uhr/zeit.txt"; }

# Hauptüberprüfung
echo "Starte Überprüfungen..."
check_gaesteliste
check_roter_teppich
check_banner
check_menue
check_musik
check_beleuchtung
check_reinigung
check_uhr
echo "Überprüfung abgeschlossen."
