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

# Funktion zur Überprüfung, ob das Gartenverzeichnis existiert
check_gartenverzeichnis() {
    check_directory "/home/MagischerGarten"
}

# Funktion zur Überprüfung, ob die Heilrezepte gesichert wurden
check_heilrezepte() {
    check_file "/home/MagischerGarten/rezepte_backup.txt"
}

# Funktion zur Überprüfung, ob vergiftete Pflanzen dokumentiert wurden
check_heilung() {
    check_file "/home/MagischerGarten/heilung.txt"
}

# Funktion zur Überprüfung, ob Zaubertränke existieren
check_zaubertraenke() {
    check_file "/home/MagischerGarten/zaubertraenke.txt"
}

# Funktion zur Überprüfung, ob die Zaubertränke geschützt wurden
check_zauberschutz() {
    if [ -f "/home/MagischerGarten/zaubertraenke.txt" ] && [ "$(stat -c %a /home/MagischerGarten/zaubertraenke.txt)" = "600" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob der Fortschritt dokumentiert wurde
check_fortschritt() {
    check_file "/home/MagischerGarten/fortschritt.txt"
}