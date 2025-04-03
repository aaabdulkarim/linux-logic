# Szenario 1: Die königliche Eröffnung

Der König plant eine große Eröffnung für das neue Schloss. Deine Aufgabe ist es, sicherzustellen, dass alles perfekt vorbereitet ist.

## Subszenarien

### 1. Den roten Teppich ausrollen

!! Gehe zum Veranstaltungsraum und lege den roten Teppich aus.
\_- Wechsle zum Veranstaltungsordner und erstelle eine Datei namens roter_teppich.txt.
`cd /home/Veranstaltung && touch roter_teppich.txt`

### 2. Die Einladungen überprüfen

!! Stelle sicher, dass die Gästeliste vollständig ist.
\_- Gehe in das Einladungs-Verzeichnis und prüfe, ob die Datei gaesteliste.txt existiert.
`cd /home/Einladungen && ls && echo "ich" >> gaesteliste.txt`

### 3. Das königliche Banner aufhängen

!! Bringe das königliche Banner zum Veranstaltungsort.
\_- Kopiere die Datei banner.txt aus dem Dekorationsverzeichnis zum Veranstaltungsort.
`cd /home/Dekoration && cp banner.txt /home/Veranstaltung/`

### 4. Das königliche Menü prüfen

!! Füge ein neues Gericht zur Speisekarte hinzu.
\_- Wechsle in die Küche und bearbeite die Datei menue.txt.
`cd /home/Kueche && echo "Neues Gericht" >> menue.txt`

### 5. Die Musik vorbereiten

!! Stelle sicher, dass eine Liste der Musikstücke vorhanden ist.
\_- Wechsle in das Musikverzeichnis und prüfe den Inhalt.
`cd /home/Musik && ls`

### 6. Die Beleuchtung überprüfen

!! Sorge dafür, dass genug Lichtquellen vorhanden sind.
\_- Wechsle ins Beleuchtungsverzeichnis und erstelle eine neue Lichtquelle.
`cd /home/Beleuchtung && touch licht1.txt`

### 7. Das Schloss reinigen

!! Stelle sicher, dass der Boden sauber ist.
\_- Gehe in das Reinigungsverzeichnis und erstelle eine Datei zur Dokumentation.
`cd /home/Reinigung && touch boden_sauber.txt`

### 8. Die königliche Uhr stellen

!! Stelle die königliche Uhr auf 12:00 ein.
\_- Bearbeite die Datei zeit.txt und prüfe den Inhalt.
`cd /home/Uhr && echo "12:00" > zeit.txt && cat zeit.txt`

# EOF