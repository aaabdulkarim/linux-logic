#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "[OK] $1 existiert."
    else
        echo "[FEHLER] $1 fehlt!"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    if [ -d "$1" ]; then
        echo "[OK] $1 existiert."
    else
        echo "[FEHLER] $1 fehlt!"
    fi
}

# 1. Den königlichen Kommunikationskanal sichern
echo "Überprüfung: Kommunikationskanal..."
check_file "/home/Kommunikation/nachricht_geheim.txt.gpg"

# 2. Verdächtige Aktivitäten überwachen
echo "Überprüfung: Sicherheitsprotokolle..."
if [ ! -f "/home/Sicherheit/blacklist_ip.txt" ]; then
    echo "[OK] Blacklist wurde entfernt."
else
    echo "[FEHLER] Blacklist existiert noch!"
fi

# 3. Das königliche Hauptquartier wiederherstellen
echo "Überprüfung: Hauptquartier..."
check_file "/home/Hauptquartier/bericht.txt"

# 4. Die königliche Schatzkammer sichern
echo "Überprüfung: Schatzkammer..."
check_file "/home/Sicherheit/schatzkammer_backup.tar.gz"

# 5. Die Burgmauern auf Exploits prüfen
echo "Überprüfung: Burgmauern..."
if [ ! -f "/home/Burgmauer/exploit.txt" ]; then
    echo "[OK] Exploit wurde entfernt."
else
    echo "[FEHLER] Exploit existiert noch!"
fi

# 6. Den königlichen Geheimdienst aktivieren
echo "Überprüfung: Geheimdienst..."
check_file "/home/Geheimdienst/ergebnisse.txt"

# 7. Das Versorgungssystem reparieren
echo "Überprüfung: Versorgungssystem..."
check_file "/home/Versorgung/config_neu.txt"

# 8. Das königliche Archiv retten
echo "Überprüfung: Archiv..."
check_directory "/mnt/externer_speicher/wichtige_daten"

echo "Überprüfung abgeschlossen!"