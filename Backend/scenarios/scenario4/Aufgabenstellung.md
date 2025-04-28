# Szenario 4: Der königliche Notfall

Das Königreich ist im Notfall! Die Sicherheit unter Angriff und seine Untertanen flehen ihn an Sicherheitsmaßnahmen festzustellen!

## Subszenarien

### 1. Den königlichen Kommunikationskanal sichern

!! Wechsle ins Kommunikationsverzeichnis, um die Sicherheit der Nachrichten zu gewährleisten. (Magische Nummer 700)
\_- In /home/Kommunikation suche nach geheimer nachricht in Dateien und versichere mit chmod diese
`cd /home/Kommunikation && chmod 700 nachricht_geheim.txt`

### 2. Verdächtige Aktivitäten überwachen

!! Überwache die Sicherheitsprotokolle, um verdächtige Aktivitäten zu erkennen. Analyse ..Fehler..
\_- In /home/Sicherheit grep nach Fehler in system.log danach rm die blackliste_ip
`cd /home/Sicherheit && grep "Fehler" system.log && rm blacklist_ip.txt`

### 3. Das königliche Hauptquartier wiederherstellen

!! Stelle gelöschte Dateien im königlichen Hauptquartier wieder her (Backup).
\_- In /home/Hauptquartier move den backup ins normale file
`cd /home/Hauptquartier && mv backup_bericht.txt bericht.txt`

### 4. Die königliche Schatzkammer sichern

!! Erstelle ein Backup der Schatzkammer in /home/Sicherheit, um wertvolle Daten zu schützen. (nutze tar)
\_- In /home/Schatzkammer, tarre also tar -czvf das backup gz file alles also mit Stern. mv danach das getarrtet in /home/Sicherheit
`cd /home/Schatzkammer && tar -czvf schatzkammer_backup.tar.gz * && mv schatzkammer_backup.tar.gz /home/Sicherheit`
### 5. Die Burgmauern auf Exploits prüfen

!! Überprüfe die Burgmauern auf potenzielle Sicherheitslücken oder etwas was für das System schlecht ist. LÖSCHE solch eine Datei
\_- In home/Burgmauer, finde eine schlechte Datei und lösche mit rm
`cd /home/Burgmauer && rm exploit.txt`
### 6. Den königlichen Geheimdienst aktivieren

!! Starte eine Pruefung in /home/Geheimdienst, um Bedrohungen zu scannen. Fuehre die sh datei aus. gib diese in ergebnisse.txt aus.
\_- in cd /home/Geheimdienst, bash scan_bedrohungen.sh und leite mit > an die ergebnisse textdatei.
`cd /home/Geheimdienst && bash scan_bedrohungen.sh >> ergebnisse.txt`

### 7. Das Versorgungssystem reparieren

!! Überprüfe die Netzwerkinfrastruktur bei der Versorgung und konfiguriere sie neu indem du eine neue config_neu.txt erstellst
\_- In /home/Versorgung und füge ein config_neu.txt
`cd /home/Versorgung && touch config_neu.txt`

### 8. Das königliche Archiv retten

!! Sichere die wichtigsten Dokumente im königlichen Archiv mit rsync zu /mnt/externer_speicher. Danach verschlüssel mit Magie(700) den Archiv
\_- In /home/Archiv, rsync -av wichtige_daten zu /mnt/externer_speicher. Und schütze mit chmod 700 den /home/Archiv
`cd /home/Archiv && rsync -av wichtige_daten /mnt/externer_speicher && chmod -R 700 /home/Archiv`

# EOF