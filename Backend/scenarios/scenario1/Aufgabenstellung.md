Hier stehen Beschreibungen, Sublevels und die unterkategorisierten Aufgaben

# Szenario 1: Die königliche Eröffnung

Der König plant eine große Eröffnung für das neue Schloss und hat dich mit einfachen, aber wichtigen Aufgaben beauftragt, um sicherzustellen, dass alles perfekt vorbereitet ist.

## Subszenarien

### 1. Den roten Teppich ausrollen

!!Gehe zum Veranstaltungsordner mit cd "/home/Veranstaltung" und lege den roten Teppich aus. (Erstelle die Datei roter_teppich.txt)

\_- Wechsle in den Veranstaltungsordner mit cd /home/Veranstaltung.
\_- Erstelle die Datei für den roten Teppich mit touch roter_teppich.txt.
\_- Prüfe mit ls, ob die Datei vorhanden ist.

### 2. Die Einladungen schreiben

!!Gehe zum Verzeichnis „Einladungen“ und prüfe, ob die Gästeliste vollständig ist. Wechsel dazu zunächst in das Verzeichnis „Einladungen“. Wenn die Datei existiert, lade einen Ritter ein, indem du eine neue Datei ritter.txt im gleichen Verzeichnis erstellst.

\_- Wechsel in das Einladungsverzeichnis mit cd /home/Einladungen
\_- Zeige alle Dateien im Verzeichnis an mit ls /home/Einladungen
\_- Erstelle eine Datei ritter.txt, um einen Ritter zur Gästeliste hinzuzufügen

### 3. Das königliche Banner aufhängen

!!Hole das königliche Banner aus der Dekorationssammlung und bringe es zum Veranstaltungsort. Du kannst es mit cp kopieren.

\_- Wechsle in das Dekorationsverzeichnis mit `cd /home/Dekoration`.
\_- Kopiere die Datei `banner.txt` in das Veranstaltungsverzeichnis mit `cp banner.txt /home/Veranstaltung/`.

### 4. Das königliche Menü prüfen

!!Gehe in die königliche Küche und erstelle eine Liste der Gerichte. Füge neue Speisen hinzu.

\_- Gehe in das Verzeichnis der Küche mit `cd /home/Kueche`.
\_- Öffne die Datei `menue.txt` mit `echo` und füge ein Gericht hinzu.
\_- Stelle sicher, dass die Änderungen in der Datei gespeichert werden.

### 5. Die Musik vorbereiten

!!Wechsle zur Musikabteilung und stelle sicher, dass eine Liste der gespielten Stücke existiert. Gibt es Musik?

\_- Wechsle ins Musikverzeichnis mit `cd /home/Musik`.
\_- Zeige die vorhandenen Dateien mit `ls` an.

### 6. Die Beleuchtung überprüfen

!!Überprüfe die Lichter in der Veranstaltungsstätte und ergänze zusätzliche Lichtquellen, falls notwendig. Finde /Beleuchtung und erstelle licht1.txt

\_- Navigiere ins Beleuchtungsverzeichnis mit `cd /home/Beleuchtung`.
\_- Zeige die vorhandenen Dateien mit `ls` an.
\_- Erstelle eine neue Datei für eine zusätzliche Lichtquelle mit `touch licht1.txt`.

### 7. Das Schloss reinigen

!!Stelle sicher, dass der Boden des Schlosses für die Eröffnung sauber ist. Wechsel in das Reinigungsverzeichnis und erstelle "boden_sauber.txt"

\_- Wechsle ins Reinigungsverzeichnis mit `cd /home/Reinigung`.
\_- Erstelle eine Datei zur Darstellung der Bodenreinigung mit `touch boden_sauber.txt`.

### 8. Die königliche Uhr stellen

!!Sorge dafür, dass die königliche Uhr korrekt eingestellt ist, damit die Veranstaltung pünktlich beginnt. Benutze dafür die "zeit.txt" und den Befehl echo. Sie muss auf 12:00 eingestellt sein.

\_- Wechsle ins Uhrenverzeichnis mit `cd /home/Uhr`.
\_- Stelle die Uhrzeit in der Datei `zeit.txt` mit `echo "12:00" > zeit.txt` ein.
\_- Überprüfe den Inhalt der Datei mit `cat zeit.txt`.

# EOF
