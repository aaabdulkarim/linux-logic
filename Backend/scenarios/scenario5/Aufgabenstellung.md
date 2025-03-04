# Szenario 5: Die Rettung des königlichen Artefakts

Das wertvolle königliche Artefakt wurde gestohlen und versteckt. Es ist von Schutzmechanismen umgeben, die nur mit präzisen Maßnahmen überwunden werden können. Deine Aufgabe ist es, das Artefakt wiederzuerlangen und es sicher im Schloss zu verwahren.

## Subszenarien

### 1. Spurensuche beginnen

Um das Artefakt zu finden, müssen erste Hinweise gesammelt und analysiert werden.

\_- Wechsel in das Verzeichnis des königlichen Archivs mit `cd /home/Archiv`.
\_- Suche nach Hinweisen in Dokumenten mit `grep "Artefakt" *`.

### 2. Verschlüsselte Botschaften entschlüsseln

Die Hinweise sind in einem geheimen Code verfasst und müssen entschlüsselt werden.

\_- Entschlüssele die Nachricht mit `gpg -d geheime_nachricht.txt.gpg`.
\_- Speichere den entschlüsselten Inhalt in `artefakt_hinweis.txt`.

### 3. Geheime Kammer betreten

Das Artefakt ist in einer verborgenen Kammer eingeschlossen. Zugang erhält nur, wer die richtigen Schalter betätigt.

\_- Wechsle in das Kammer-Verzeichnis mit `cd /home/GeheimeKammer`.
\_- Aktiviere den geheimen Mechanismus mit `bash schalter_aktivieren.sh`.

### 4. Artefakt bergen

Nachdem der Mechanismus deaktiviert wurde, kann das Artefakt entnommen und gesichert werden.

\_- Verschiebe das Artefakt in den königlichen Tresor mit `mv artefakt.txt /home/Tresor`.
\_- Stelle sicher, dass das Artefakt gesperrt ist mit `chmod 400 /home/Tresor/artefakt.txt`.

### 5. Sicherheitsmaßnahmen erhöhen

Um zukünftige Diebstähle zu verhindern, müssen neue Schutzmaßnahmen implementiert werden.

\_- Erstelle eine Protokolldatei über den Vorfall mit `nano sicherheitsprotokoll.txt`.
\_- Aktiviere das Alarmsystem mit `bash aktivieren_alarm.sh`.

Das königliche Artefakt ist nun sicher verwahrt, und das Schloss wurde mit neuen Schutzmaßnahmen verstärkt!
# EOF