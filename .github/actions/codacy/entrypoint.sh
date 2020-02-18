#!/bin/sh -l

export CODACY_PROJECT_TOKEN=$1
REPORT_PATH=$2
COMMIT_ID=$3

java -jar codacy-coverage-reporter-assembly.jar report -l Kotlin -r "$REPORT_PATH" --commit-uuid "$COMMIT_ID"
