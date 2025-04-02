#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Gästeliste..."

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

# Funktion zur Überprüfung, ob das Verzeichnis existiert
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

# Funktion zur Überprüfung der Gästeliste
check_gaesteliste() {
    # Verzeichnis wechseln
    cd /home/Einladungen
    
    # Überprüfen, ob die Datei "gaesteliste.txt" existiert
    check_file "/home/Einladungen/gaesteliste.txt"
    
    # Prüfen, ob der Text "ich" bereits in der Datei vorhanden ist
    if ! awk '/ich/ {found=1} END {if(found) exit 0; else exit 1}' /home/Einladungen/gaesteliste.txt; then
        # Text "ich" wird zur Datei hinzugefügt, wenn er noch nicht vorhanden ist
        echo "Text 'ich' wird zur Datei gaesteliste.txt hinzugefügt..."
        echo "ich" >> /home/Einladungen/gaesteliste.txt
    else
        echo "Der Text 'ich' ist bereits in der Datei."
    fi
}

# Hauptüberprüfung
echo "Starte Überprüfung der Gästeliste..."
check_gaesteliste
echo "Überprüfung abgeschlossen."