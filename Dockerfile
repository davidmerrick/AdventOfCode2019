# Dockerfile for setting up reviewdog and ktlint

FROM ubuntu:16.04

RUN apt-get update && apt-get install --no-install-recommends -y curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install ktlint
RUN curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.35.0/ktlint && chmod a+x ktlint

# Install reviewdog
RUN curl -sfL https://raw.githubusercontent.com/reviewdog/reviewdog/master/install.sh| sh -s

WORKDIR /app
