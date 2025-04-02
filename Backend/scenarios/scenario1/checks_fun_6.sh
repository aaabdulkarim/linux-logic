#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Beleuchtung..."

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

# Funktion zur Überprüfung und Erstellung der Datei "licht1.txt"
check_beleuchtung() {
    # Wechsel ins Beleuchtungsverzeichnis
    cd /home/Beleuchtung
    
    # Überprüfen, ob die Datei "licht1.txt" existiert
    check_file "/home/Beleuchtung/licht1.txt"
    
    # Falls die Datei nicht existiert, erstelle sie
    if [ ! -f "/home/Beleuchtung/licht1.txt" ]; then
        echo "Die Datei 'licht1.txt' existiert nicht. Sie wird nun erstellt..."
        touch /home/Beleuchtung/licht1.txt
    fi
}

# Hauptüberprüfung
echo "Starte Überprüfung der Beleuchtung..."
check_beleuchtung
echo "Überprüfung abgeschlossen."
