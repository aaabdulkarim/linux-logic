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

# Funktion zur Überprüfung, ob die Nachricht verschlüsselt wurde
check_kommunikation() {
    check_file "/home/Kommunikation/nachricht_geheim.txt.gpg"
}

# Funktion zur Überprüfung, ob die Blacklist zurückgesetzt wurde
check_sicherheit() {
    if [ ! -f "/home/Sicherheit/blacklist_ip.txt" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob das Hauptquartier wiederhergestellt wurde
check_hauptquartier() {
    check_file "/home/Hauptquartier/bericht.txt"
}

# Funktion zur Überprüfung, ob das Schatzkammer-Backup existiert
check_schatzkammer() {
    check_file "/home/Sicherheit/schatzkammer_backup.tar.gz"
}

# Funktion zur Überprüfung, ob Exploits entfernt wurden
check_burgmauer() {
    if [ ! -f "/home/Burgmauer/exploit.txt" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob der Geheimdienst aktiviert wurde
check_geheimdienst() {
    check_file "/home/Geheimdienst/ergebnisse.txt"
}

# Funktion zur Überprüfung, ob das Versorgungssystem repariert wurde
check_versorgung() {
    check_file "/home/Versorgung/config_neu.txt"
}

# Funktion zur Überprüfung, ob das Archiv gesichert wurde
check_archiv() {
    check_directory "/mnt/externer_speicher/wichtige_daten"
}
