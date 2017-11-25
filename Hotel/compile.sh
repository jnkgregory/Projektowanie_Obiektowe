#!/bin/bash

CURRENT_DIR=$(pwd)

JODA_JAR="joda-time-2.9.9.jar"
SUFFIX_OPTIONS=":lib/*;."
COMPILER="javac8 -cp"
COMPILED_FILES="*java"

FULL_COMPILE_CMD='${COMPILER} "${CURRENT_DIR}/${JODA_JAR}${SUFFIX_OPTIONS}" ${COMPILED_FILES}'

eval echo "[ compile.sh ] INFO: Running ${FULL_COMPILE_CMD}"

eval ${FULL_COMPILE_CMD}
