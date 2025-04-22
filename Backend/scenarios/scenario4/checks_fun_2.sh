#!/bin/bash

set -e

BLACKLIST="/home/Sicherheit/blacklist_ip.txt"

if [ ! -f "$BLACKLIST" ]; then
    echo "true"
else
    exit 1
fi

