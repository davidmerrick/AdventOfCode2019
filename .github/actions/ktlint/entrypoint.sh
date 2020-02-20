#!/bin/sh -l

ktlint --reporter=checkstyle | reviewdog -f=checkstyle -name="ktlint" --reporter=github-pr-check
