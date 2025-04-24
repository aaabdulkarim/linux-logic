#!/bin/bash

set -e

check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        return 0
    else
        return 1
    fi
}

# Überprüfung starten
if cd /home/Archiv 2>/dev/null && check_file "/home/Archiv/geheime_nachricht.txt.gpg"; then
    # Entschlüsseln und Hinweis in artefakt_hinweis.txt speichern
    gpg -d geheime_nachricht.txt.gpg && echo "geheime_nachricht.txt.gpg" >> artefakt_hinweis.txt
    if check_file "/home/Archiv/artefakt_hinweis.txt"; then
        echo "true"
        exit 0
    else
        echo "Fehler: artefakt_hinweis.txt wurde nicht aktualisiert."
        exit 1
    fi
else
    echo "Die Datei geheime_nachricht.txt.gpg existiert nicht."
    exit 1
fi
