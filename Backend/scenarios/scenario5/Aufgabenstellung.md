# Szenario 5: Die Rettung des königlichen Artefakts

Das wertvolle königliche Artefakt wurde gestohlen und versteckt. Es ist von Schutzmechanismen umgeben, die nur mit präzisen Maßnahmen überwunden werden können. Deine Aufgabe ist es, das Artefakt wiederzuerlangen und es sicher im Schloss zu verwahren.

## Subszenarien

### 1. Spurensuche beginnen

!! Suche nach dem Artefakt im Archiv indem du infos ausliest und diese dann in info_artefakt abspeicherst
\_- In /home/Archiv cat die Info und schreibe diese in info_artefakt.txt
`cd /home/Archiv && cat info.txt > info_artefakt.txt`

### 2. Verschlüsselte Botschaften entschlüsseln

!! Die Hinweise sind in einem geheimen Code verfasst und müssen entschlüsselt(gpg -d) werden. 
\_- Mit `gpg -d geheime_nachricht.txt.gpg` speicher in der Hinweis Datei dies ab
`gpg -d geheime_nachricht.txt.gpg && echo geheime_nachricht.txt.gpg >> artefakt_hinweis.txt`

### 3. Geheime Kammer betreten

!! Das Artefakt ist in einer verborgenen Kammer eingeschlossen. Aktiviere den schalter
\_- In /home/GeheimeKammer bash den Schalter
`cd /home/GeheimeKammer && bash schalter_aktivieren.sh`

### 4. Artefakt bergen

!! Nachdem der Mechanismus deaktiviert wurde und zum Tresor bewegt wurde, kann das Artefakt entnommen und gesichert werden. (400)
\_- Verschiebe mit mv den artefakt zum tresor danach chmod 400 diesen.
`mv artefakt.txt /home/Tresor && chmod 400 /home/Tresor/artefakt.txt`

### 5. Sicherheitsmaßnahmen erhöhen

!! Um zukünftige Diebstähle zu verhindern, müssen neue Schutzmaßnahmen implementiert werden. sicherheitsprotokoll.txt erstellen und alarmsystem muss aktiviert werden
\_- Mit touch sicherheitsprotokoll.txt` erstellen und alarm aktivieren mit bash
`touch sicherheitsprotokoll.txt && bash aktivieren_alarm.sh`

Das königliche Artefakt ist nun sicher verwahrt, und das Schloss wurde mit neuen Schutzmaßnahmen verstärkt!
# EOF