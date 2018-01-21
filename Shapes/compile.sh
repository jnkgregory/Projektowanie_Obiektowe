#!/bin/bash

CURRENT_DIR=$(pwd)

OPENCV_JAR="opencv-340.jar"
SUFFIX_OPTIONS=":*;."
COMPILER="javac -cp"
COMPILED_FILES="*java"

FULL_COMPILE_CMD='${COMPILER} "${CURRENT_DIR}/${OPENCV_JAR}${SUFFIX_OPTIONS}" ${COMPILED_FILES}'

eval echo "[ compile.sh ] INFO: Running ${FULL_COMPILE_CMD}"

eval ${FULL_COMPILE_CMD}
