#!/bin/bash

set -e  # Beende das Skript bei Fehlern


# Funktion zur Überprüfung, ob eine Datei existiert
check_file() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "true"
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob der Inhalt einer Datei dem erwarteten Inhalt entspricht
check_file_content() {
    local file="$1"
    local expected_content="$2"
    if [ -f "$file" ]; then
        local file_content=$(cat "$file")
        if [ "$file_content" == "$expected_content" ]; then
            echo "true"
        else
            echo "false"
        fi
    else
        echo "false"
    fi
}

# Funktion zur Überprüfung, ob die Zeit in der Datei 'zeit.txt' auf 12:00 eingestellt wurde
check_time_updated() {
    local file="$1"
    if [ -f "$file" ]; then
        local file_content=$(cat "$file")
        if [ "$file_content" == "12:00" ]; then
            echo "true"
        else
            echo "false"
        fi
    else
        echo "false"
    fi
}

# Einzelne Überprüfungen
check_uhr() { 
    check_file "/home/Uhr/zeit.txt"
    check_time_updated "/home/Uhr/zeit.txt"
}

# Hauptüberprüfung
check_uhr