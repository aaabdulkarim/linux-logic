#!/bin/bash

set -e

BACKUP="/home/Sicherheit/schatzkammer_backup.tar.gz"

if [ -f "$BACKUP" ]; then
    echo "true"
else
    exit 1
fi
