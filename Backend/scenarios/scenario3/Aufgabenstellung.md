# Szenario 3: Die Verteidigung der königlichen Mauern

Die Mauern des Schlosses sind das Rückgrat seiner Verteidigung. Sie müssen überprüft, repariert und mit Schutzzaubern verstärkt werden.

## Subszenarien

### 1. Wechsel in das Mauer-Verzeichnis

!!Gehe zu /home/Burgmauer, um Schwachstellen zu identifizieren. Nun kopier `sicherung.txt  als sicherung_sicher.txt`
\_- Navigiere zum Mauerverzeichnis mit `cd /home/Burgmauer` und Kopiere die Datei `sicherung.txt`
`cp sicherung.txt sicherung_sicher.txt`

### 2. Repariere beschädigte Mauern

!!Dokumentiere die notwendigen Reparaturen, indem du eine Reparatur Datei erstellsts. `reparatur.txt`
\_- Erstelle eine Datei für Reparaturdetails mit 'touch'.
`touch reparatur.txt`

### 3. Füge Schutzzauber hinzu

!!Erhöhe die Sicherheit der gespeicherten Pläne durch spezielle Schutzmaßnahmen 600.
\_- Ändere die Datei-Eigenschaften für höhere Sicherheit mit `chmod 600`.
`chmod 600 sicherung_sicher.txt`

# EOF
