# Szenario 2: Der verzauberte Garten des Königs

Der königliche Garten ist ein Ort von magischer Schönheit, 
doch er wird von Unkraut und Chaos bedroht. Deine Aufgabe ist es,
den Garten zu reinigen und seine magischen Eigenschaften wiederherzustellen.

## Subszenarien

### 1. Wechsel in den Gartenbereich

!! Betrete den Garten und entferne den Unkraut.
\_- Navigiere zum Gartenverzeichnis mit `cd /home/Garten` und rm Umkraut.
`cd /home/Garten && rm Unkraut*.txt`

### 2. Pflanze magische Blumen

!! Setze neue magische Blumen, Zauberblume1.txt und Zauberblume2.txt.
\_- Erstelle neue magische Blumen mit touch.
`touch Zauberblume1.txt Zauberblume2.txt`

### 3. Verzaubere den Garten

!! Gib den magischen Blumen besondere Eigenschaften, damit ihre Kraft zurückkehren kann. Die magische Nummer 644
\_- Verändere die Rechte der magischen Blumen mit chmod 644
`chmod 644 Zauberblume*.txt`

# EOF
