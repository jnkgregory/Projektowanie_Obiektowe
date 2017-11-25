#!/bin/bash

CURRENT_DIR=$(pwd)

JODA_JAR="joda-time-2.9.9.jar"
SUFFIX_OPTIONS=":"
COMPILER="java8 -cp"
MAIN_FILE="Main"

FULL_COMPILE_CMD='${COMPILER} "${CURRENT_DIR}/${JODA_JAR}${SUFFIX_OPTIONS}" ${MAIN_FILE}'

eval echo "[ compile.sh ] INFO: Running ${FULL_COMPILE_CMD}"

eval ${FULL_COMPILE_CMD}
