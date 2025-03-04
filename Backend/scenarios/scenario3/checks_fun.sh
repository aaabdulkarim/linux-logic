#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    if [ -d "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob das Mauerverzeichnis existiert
check_mauerverzeichnis() {
    check_directory "/home/Burgmauer"
}

# Funktion zur Überprüfung, ob die Pläne gesichert wurden
check_sicherung() {
    check_file "/home/Burgmauer/sicherung_sicher.txt"
}

# Funktion zur Überprüfung, ob Reparaturen dokumentiert wurden
check_reparaturen() {
    check_file "/home/Burgmauer/reparatur.txt"
}

# Funktion zur Überprüfung, ob der Schutzzauber aktiv ist
check_schutzzauber() {
    if [ -f "/home/Burgmauer/sicherung_sicher.txt" ] && [ "$(stat -c %a /home/Burgmauer/sicherung_sicher.txt)" = "600" ]; then
        echo "true"
    else
        echo "false"
    fi
}
