#!/bin/sh -l

java -jar /bin/codacy-coverage-reporter-assembly.jar report -l Kotlin -r "$REPORT_PATH" --commit-uuid "$COMMIT_ID"
