#!/bin/bash

set -e

# Artefakt-Check
if [ -f "/home/Tresor/artefakt.txt" ]; then
    echo "true"
    exit 0
else
    echo "false"
    exit 1
fi
