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

# Funktion zur Überprüfung, ob der Garten existiert
check_garten() {
    check_directory "/home/Garten"
}

# Funktion zur Überprüfung, ob Unkraut entfernt wurde
check_unkraut() {
    if ls /home/Garten/Unkraut*.txt 2>/dev/null; then
        echo "false"
    else
        echo "true"
    fi
}

# Funktion zur Überprüfung, ob magische Blumen gepflanzt wurden
check_magische_blumen() {
    check_file "/home/Garten/Zauberblume1.txt"
    check_file "/home/Garten/Zauberblume2.txt"
}

# Funktion zur Überprüfung, ob die magischen Blumen verzaubert wurden
check_verzauberung() {
    if [ -f "/home/Garten/Zauberblume1.txt" ] && [ "$(stat -c %a /home/Garten/Zauberblume1.txt)" = "644" ] && \
       [ -f "/home/Garten/Zauberblume2.txt" ] && [ "$(stat -c %a /home/Garten/Zauberblume2.txt)" = "644" ]; then
        echo "true"
    else
        echo "false"
    fi
}
