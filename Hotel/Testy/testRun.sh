#!/bin/bash

CURRENT_DIR=$(pwd)

JODA_JAR="joda-time-2.9.9.jar:junit-4.12.jar:hamcrest-core-1.3.jar"
SUFFIX_OPTIONS=":"
COMPILER="java8 -cp"
MAIN_FILE="TestRunner"

FULL_COMPILE_CMD='${COMPILER} "${CURRENT_DIR}/${JODA_JAR}${SUFFIX_OPTIONS}" ${MAIN_FILE}'

eval echo "[ compile.sh ] INFO: Running ${FULL_COMPILE_CMD}"

eval ${FULL_COMPILE_CMD}
