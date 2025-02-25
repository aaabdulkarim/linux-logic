#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "$1 existiert."
    else
        echo "$1 fehlt!"
    fi
}

# 1. Den königlichen Kommunikationskanal sichern
cd /home/Kommunikation || mkdir -p /home/Kommunikation && cd /home/Kommunikation
echo "Kommunikationsverzeichnis betreten."
grep -r "geheim" *
echo "Sensible Dateien gesucht."
gpg -c nachricht_geheim.txt
echo "Nachricht verschlüsselt."

# 2. Verdächtige Aktivitäten überwachen
cd /home/Sicherheit || mkdir -p /home/Sicherheit && cd /home/Sicherheit
echo "Sicherheitsverzeichnis betreten."
grep "Fehler" system.log
echo "Verdächtige Aktivitäten überprüft."
rm -f blacklist_ip.txt
echo "Blacklist zurückgesetzt."

# 3. Das königliche Hauptquartier wiederherstellen
cd /home/Hauptquartier || mkdir -p /home/Hauptquartier && cd /home/Hauptquartier
echo "Hauptquartier-Verzeichnis betreten."
mv -f backup_bericht.txt bericht.txt
echo "Gelöschte Datei wiederhergestellt."

# 4. Die königliche Schatzkammer sichern
cd /home/Schatzkammer || mkdir -p /home/Schatzkammer && cd /home/Schatzkammer
echo "Schatzkammer-Verzeichnis betreten."
tar -czvf schatzkammer_backup.tar.gz *
echo "Backup erstellt."
mv schatzkammer_backup.tar.gz /home/Sicherheit/
echo "Backup in Sicherheitsverzeichnis verschoben."

# 5. Die Burgmauern auf Exploits prüfen
cd /home/Burgmauer || mkdir -p /home/Burgmauer && cd /home/Burgmauer
echo "Burgmauer-Verzeichnis betreten."
find . -perm 777
echo "Unsichere Dateien gesucht."
rm -f exploit.txt
echo "Exploit entfernt."

# 6. Den königlichen Geheimdienst aktivieren
cd /home/Geheimdienst || mkdir -p /home/Geheimdienst && cd /home/Geheimdienst
echo "Geheimdienst-Verzeichnis betreten."
bash scan_bedrohungen.sh > ergebnisse.txt
echo "Bedrohungsscan durchgeführt und gespeichert."

# 7. Das Versorgungssystem reparieren
cd /home/Versorgung || mkdir -p /home/Versorgung && cd /home/Versorgung
echo "Versorgungs-Verzeichnis betreten."
ping -c 4 192.168.1.1
echo "Verbindungsprüfung abgeschlossen."
touch config_neu.txt
echo "Neue Konfiguration erstellt."

# 8. Das königliche Archiv retten
cd /home/Archiv || mkdir -p /home/Archiv && cd /home/Archiv
echo "Archiv-Verzeichnis betreten."
rsync -av wichtige_daten /mnt/externer_speicher/
echo "Wichtige Daten gesichert."
chmod -R 700 /home/Archiv
echo "Zugriffsrechte angepasst."

echo "Szenario 'Der königliche Notfall' erfolgreich abgeschlossen!"
