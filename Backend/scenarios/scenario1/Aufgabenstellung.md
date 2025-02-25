Hier stehen Beschreibungen, Sublevels und die unterkategorisierten Aufgaben

# Szenario 1: Die königliche Eröffnung

Der König plant eine große Eröffnung für das neue Schloss und hat dich mit einfachen, aber wichtigen Aufgaben beauftragt, um sicherzustellen, dass alles perfekt vorbereitet ist.

## Subszenarien

### 1. Den roten Teppich ausrollen

Begib dich zum Ort der Veranstaltungsvorbereitung mithilfe von cd "Name des Verzeichnisses" und stelle sicher, dass der rote Teppich für den Empfang bereitliegt.

\_- Navigiere zum Veranstaltungsverzeichnis mit `cd /home/Veranstaltung`.
\_- Erstelle eine Datei für den roten Teppich mit `touch roter_teppich.txt`.
\_- Überprüfe mit `ls`, ob die Datei korrekt erstellt wurde.

### 2. Die Einladungen überprüfen

Gehe zum Bereich der Einladungen und überprüfe, ob die Gästeliste vollständig ist.

\_- Wechsle ins Einladungsverzeichnis mit `cd /home/Einladungen`.
\_- Zeige die vorhandenen Dateien an mit `ls`.
\_- Stelle sicher, dass die Datei `gaesteliste.txt` vorhanden ist.

### 3. Das königliche Banner aufhängen

Finde das königliche Banner in der Dekorationssammlung und bringe es am Veranstaltungsort an. Mit cp kannst du die Sammlung kopieren.

\_- Wechsle in das Dekorationsverzeichnis mit `cd /home/Dekoration`.
\_- Kopiere die Datei `banner.txt` in das Veranstaltungsverzeichnis mit `cp banner.txt /home/Veranstaltung/`.

### 4. Das königliche Menü prüfen

Betrete die königliche Küche und erstelle eine Liste der geplanten Gerichte. Falls erforderlich, füge neue Speisen hinzu. Dafür gehe in die /Kueche und bearbeite das menue.txt. Anschließlich speichere alles ab.

\_- Gehe in das Verzeichnis der Küche mit `cd /home/Kueche`.
\_- Erstelle eine Datei für die Menüliste mit `touch menue.txt`.
\_- Öffne die Datei mit `nano menue.txt` und füge ein Gericht hinzu.
\_- Speichere die Datei mit `STRG + X`, dann `Y` und `Enter`.

### 5. Die Musik vorbereiten

Wechsle zur Musikabteilung und stelle sicher, dass eine Liste der gespielten Stücke existiert. Erstelle eine musikliste.txt Datei in einem schon erstelltem Musikverzeichniss

\_- Wechsle ins Musikverzeichnis mit `cd /home/Musik`.
\_- Zeige die vorhandenen Dateien mit `ls` an.
\_- Falls `musikliste.txt` nicht existiert, erstelle sie mit `touch musikliste.txt`.

### 6. Die Beleuchtung überprüfen

Überprüfe die Lichter in der Veranstaltungsstätte und ergänze zusätzliche Lichtquellen, falls notwendig. Finde /Beleuchtung und erstelle licht1.txt

\_- Navigiere ins Beleuchtungsverzeichnis mit `cd /home/Beleuchtung`.
\_- Zeige die vorhandenen Dateien mit `ls` an.
\_- Erstelle eine neue Datei für eine zusätzliche Lichtquelle mit `touch licht1.txt`.

### 7. Das Schloss reinigen

Stelle sicher, dass der Boden des Schlosses für die Eröffnung sauber ist. Wechsel in das Reinigungsverzeichnis und erstelle "boden_sauber.txt"

\_- Wechsle ins Reinigungsverzeichnis mit `cd /home/Reinigung`.
\_- Erstelle eine Datei zur Darstellung der Bodenreinigung mit `touch boden_sauber.txt`.

### 8. Die königliche Uhr stellen

Sorge dafür, dass die königliche Uhr korrekt eingestellt ist, damit die Veranstaltung pünktlich beginnt. Benutze dafür die "zeit.txt" und den Befehl echo. Sie muss auf 12:00 eingestellt sein.

\_- Wechsle ins Uhrenverzeichnis mit `cd /home/Uhr`.
\_- Stelle die Uhrzeit in der Datei `zeit.txt` mit `echo "12:00" > zeit.txt` ein.
\_- Überprüfe den Inhalt der Datei mit `cat zeit.txt`.
