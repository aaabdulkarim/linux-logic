#!/bin/bash

set -e


EXPLOIT="/home/Burgmauer/exploit.txt"

if [ ! -f "$EXPLOIT" ]; then
    echo "true"
else
    exit 1
fi
