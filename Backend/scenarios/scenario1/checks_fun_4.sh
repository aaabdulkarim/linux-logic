#!/bin/bash

set -e  # Beende das Skript bei Fehlern

echo "Überprüfung: Küche..."

# Funktion zur Überprüfung, ob eine Datei existiert und Inhalt hat
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        if [ -s "$file" ]; then
            echo "✔ Datei existiert und enthält Inhalt: $file"
            echo "true"
        else
            echo "✖ Datei existiert, aber ist leer: $file"
            echo "false"
        fi
    else
        echo "✖ Datei fehlt: $file"
        echo "false"
    fi
}

# Gehe ins Verzeichnis der Küche und füge ein neues Gericht hinzu
add_dish() {
    # Wechsel ins Küchenverzeichnis
    cd /home/Kueche
    
    # Überprüfen, ob die Datei menue.txt existiert und Inhalt hat
    check_file "/home/Kueche/menue.txt"
    
    # Füge ein neues Gericht zum Menü hinzu
    if [ -s "/home/Kueche/menue.txt" ]; then
        echo "Das Gericht '' wurde zur Datei hinzugefügt."
    else
        echo "Die Datei menue.txt ist leer oder fehlt."
    fi
}

# Hauptüberprüfung
echo "Starte Überprüfung der Küche..."
add_dish
echo "Überprüfung abgeschlossen."
