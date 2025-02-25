# koenigliche_eroeffnung.sh
#!/bin/bash

# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    if [ -f "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob ein Verzeichnis existiert
check_directory() {
    if [ -d "$1" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# 1. Den roten Teppich ausrollen
mkdir -p /home/Veranstaltung
cd /home/Veranstaltung

touch roter_teppich.txt
echo "Roter Teppich ausgerollt."
check_file roter_teppich.txt

# 2. Die Einladungen überprüfen
mkdir -p /home/Einladungen
cd /home/Einladungen

echo "Gästeliste prüfen:"
ls
echo "Existiert die Gästeliste?"
check_file gaesteliste.txt

# 3. Das königliche Banner aufhängen
mkdir -p /home/Dekoration
cd /home/Dekoration

touch banner.txt
cp banner.txt /home/Veranstaltung/
echo "Banner aufgehängt."
check_file /home/Veranstaltung/banner.txt

# 4. Das königliche Menü prüfen
mkdir -p /home/Kueche
cd /home/Kueche

touch menue.txt
echo "Gericht: Königlicher Festschmaus" > menue.txt
echo "Menü geprüft und aktualisiert."
check_file menue.txt

# 5. Die Musik vorbereiten
mkdir -p /home/Musik
cd /home/Musik

touch musikliste.txt
echo "Musikliste vorbereitet."
check_file musikliste.txt

# 6. Die Beleuchtung überprüfen
mkdir -p /home/Beleuchtung
cd /home/Beleuchtung

touch licht1.txt
echo "Beleuchtung überprüft."
check_file licht1.txt

# 7. Das Schloss reinigen
mkdir -p /home/Reinigung
cd /home/Reinigung

touch boden_sauber.txt
echo "Schloss gereinigt."
check_file boden_sauber.txt

# 8. Die königliche Uhr stellen
mkdir -p /home/Uhr
cd /home/Uhr

echo "12:00" > zeit.txt
echo "Uhr gestellt."
check_file zeit.txt
