#!/bin/sh -l

java -jar codacy-coverage-reporter-assembly.jar report -l Kotlin -r "$REPORT_PATH" --commit-uuid "$COMMIT_ID"
