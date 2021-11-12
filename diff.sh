#!/bin/bash

set -o errexit

TEMP_DIR="script_temp"
RESULT_FILE="diff.txt"

git clone $1 $TEMP_DIR;
cd $TEMP_DIR;
git diff --name-only $2 $3 > ../$RESULT_FILE;
cd ..;
rm -rf $TEMP_DIR
