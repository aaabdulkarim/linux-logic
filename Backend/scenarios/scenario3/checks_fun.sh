#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "🔍 Starte Überprüfung der Burgmauer-Sicherheit..."

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

# Überprüfung des Mauerverzeichnisses
check_mauerverzeichnis() {
    check_directory "/home/Burgmauer"
}

# Überprüfung, ob die Pläne gesichert wurden
check_sicherung() {
    check_file "/home/Burgmauer/sicherung_sicher.txt"
}

# Überprüfung, ob Reparaturen dokumentiert wurden
check_reparaturen() {
    check_file "/home/Burgmauer/reparatur.txt"
}

# Überprüfung, ob der Schutzzauber (Berechtigung) aktiv ist
check_schutzzauber() {
    local file="/home/Burgmauer/sicherung_sicher.txt"
    if [ -f "$file" ]; then
        local perm
        perm=$(stat -c %a "$file")
        if [ "$perm" = "600" ]; then
            echo "✔ Schutzzauber aktiv: $file hat Berechtigung 600"
            echo "true"
        else
            echo "⚠ $file hat falsche Berechtigung (erwartet: 600, aktuell: $perm)"
            echo "false"
        fi
    else
        echo "✖ Datei fehlt: $file"
        echo "false"
    fi
}

# Ausführung der Prüfungen
check_mauerverzeichnis
check_sicherung
check_reparaturen
check_schutzzauber

echo "✅ Überprüfung abgeschlossen."
