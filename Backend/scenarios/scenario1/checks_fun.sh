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

# Funktion zur Überprüfung, ob die Gästeliste existiert
check_gaesteliste() {
    check_file "/home/Einladungen/gaesteliste.txt"
}

# Funktion zur Überprüfung, ob der rote Teppich ausgerollt wurde
check_roter_teppich() {
    check_file "/home/Veranstaltung/roter_teppich.txt"
}

# Funktion zur Überprüfung, ob das Banner aufgehängt wurde
check_banner() {
    check_file "/home/Veranstaltung/banner.txt"
}

# Funktion zur Überprüfung, ob das Menü vorbereitet wurde
check_menue() {
    check_file "/home/Kueche/menue.txt"
}

# Funktion zur Überprüfung, ob die Musikliste vorbereitet wurde
check_musik() {
    check_file "/home/Musik/musikliste.txt"
}

# Funktion zur Überprüfung, ob die Beleuchtung eingerichtet wurde
check_beleuchtung() {
    check_file "/home/Beleuchtung/licht1.txt"
}

# Funktion zur Überprüfung, ob das Schloss gereinigt wurde
check_reinigung() {
    check_file "/home/Reinigung/boden_sauber.txt"
}

# Funktion zur Überprüfung, ob die königliche Uhr gestellt wurde
check_uhr() {
    check_file "/home/Uhr/zeit.txt"
}
