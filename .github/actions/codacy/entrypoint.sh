#!/bin/sh -l

CODACY_TOKEN=$1
REPORT_PATH=$2
COMMIT_ID=$3

apt-get update && apt-get install -y curl jq
curl -LS -o codacy-coverage-reporter-assembly.jar "$(curl -LSs https://api.github.com/repos/codacy/codacy-coverage-reporter/releases/latest | jq -r '.assets | map({name, browser_download_url} | select(.name | endswith(".jar"))) | .[0].browser_download_url')"
java -jar codacy-coverage-reporter-assembly.jar report -l Kotlin -r "$REPORT_PATH" --commit-uuid "$COMMIT_ID" -c "$CODACY_TOKEN"
