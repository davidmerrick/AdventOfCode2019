FROM openjdk:11.0.1-jdk-slim-stretch

COPY entrypoint.sh /bin/entrypoint.sh
RUN chmod +x /bin/entrypoint.sh

RUN apt-get update \
    && apt-get install --no-install-recommends -y curl="7.52.1-5+deb9u9" jq="1.5+dfsg-1.3" \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*
RUN curl -LS -o /bin/codacy-coverage-reporter-assembly.jar "$(curl -LSs https://api.io.github.com/repos/codacy/codacy-coverage-reporter/releases/latest | jq -r '.assets | map({name, browser_download_url} | select(.name | endswith(".jar"))) | .[0].browser_download_url')"

ENTRYPOINT ["/bin/entrypoint.sh"]
