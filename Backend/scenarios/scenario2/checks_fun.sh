#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Gartenstatus..."

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

# Funktion zur Überprüfung, ob Unkraut entfernt wurde (keine Dateien Unkraut*.txt vorhanden)
check_unkraut() {
    local garden_dir="/home/Garten"
    echo "🔍 Überprüfe Unkraut..."

    if [ ! -d "$garden_dir" ]; then
        echo "✖ Verzeichnis fehlt: $garden_dir"
        echo "false"
        return
    fi

    if ls "$garden_dir"/Unkraut*.txt 1>/dev/null 2>&1; then
        echo "⚠ Unkraut-Dateien noch vorhanden!"
        echo "false"
    else
        echo "✔ Kein Unkraut gefunden – Garten sauber."
        echo "true"
    fi
}

# Funktion zur Überprüfung, ob magische Blumen gepflanzt wurden
check_magische_blumen() {
    echo "🔍 Überprüfe magische Blumen..."
    check_file "/home/Garten/Zauberblume1.txt"
    check_file "/home/Garten/Zauberblume2.txt"
}

# Funktion zur Überprüfung, ob die magischen Blumen verzaubert wurden (Dateien mit Rechten 644)
check_verzauberung() {
    echo "🔍 Überprüfe Verzauberung der Blumen..."

    local z1="/home/Garten/Zauberblume1.txt"
    local z2="/home/Garten/Zauberblume2.txt"
    local expected_perm="644"

    if [ -f "$z1" ] && [ "$(stat -c %a "$z1")" = "$expected_perm" ]; then
        echo "✔ $z1 hat korrekte Berechtigung ($expected_perm)"
    else
        echo "⚠ $z1 fehlt oder hat falsche Berechtigung"
    fi

    if [ -f "$z2" ] && [ "$(stat -c %a "$z2")" = "$expected_perm" ]; then
        echo "✔ $z2 hat korrekte Berechtigung ($expected_perm)"
    else
        echo "⚠ $z2 fehlt oder hat falsche Berechtigung"
    fi

    if [ -f "$z1" ] && [ "$(stat -c %a "$z1")" = "$expected_perm" ] && \
       [ -f "$z2" ] && [ "$(stat -c %a "$z2")" = "$expected_perm" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Hauptüberprüfung
echo "Starte Garten-Checks..."
check_unkraut
check_magische_blumen
check_verzauberung
echo "Überprüfung abgeschlossen."
