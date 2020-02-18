#!/bin/sh -l

export REVIEWDOG_GITHUB_API_TOKEN=$1

ktlint --reporter=checkstyle | reviewdog -f=checkstyle -name="ktlint" --reporter=github-pr-check
