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
check_file_content() {
    local file="$1"
    local expected_content="$2"
    if [ -f "$file" ]; then
        if grep -Fxq "$expected_content" "$file"; then
            echo "✔ Datei existiert und enthält den erwarteten Inhalt: $file"
            echo "true"
        else
            echo "⚠ Datei existiert, aber der Inhalt stimmt nicht: $file"
            echo "false"
        fi
    else
        echo "❌ Datei fehlt: $file"
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
# Spezielle Funktion zur Überprüfung, ob die Datei 'zeit.txt' exakt auf 12:00 geändert wurde
check_time_updated() {
    local file="$1"
    if [ -f "$file" ]; then
        local file_content=$(cat "$file")
        if [ "$file_content" == "12:00" ]; then
            echo "✔ Die Datei $file wurde auf 12:00 geändert."
            echo "true"
        else
            echo "⚠ Die Datei $file wurde nicht auf 12:00 geändert. Aktueller Inhalt: $file_content"
            echo "false"
        fi
    else
        echo "❌ Datei fehlt: $file"
        echo "false"
    fi
}


# Einzelne Überprüfungen
check_gaesteliste() { check_file "/home/Einladungen/gaesteliste.txt"; }
check_roter_teppich() { check_file "/home/Veranstaltung/roter_teppich.txt"; }
check_banner() { check_file "/home/Veranstaltung/banner.txt"; }
check_menue() { check_file_content "/home/Kueche/menue.txt"; }
check_musik() { check_file "/home/Musik/musikliste.txt"; }
check_beleuchtung() { check_file "/home/Beleuchtung/licht1.txt"; }
check_reinigung() { check_file "/home/Reinigung/boden_sauber.txt"; }
check_uhr() { check_time_updated "/home/Uhr/zeit.txt"; }

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
