FROM openjdk:11.0.1-jdk-slim-stretch

RUN apt-get update && apt-get -y install curl
RUN curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.35.0/ktlint \
          && chmod a+x ktlint \
          && mv ktlint /bin

RUN curl -sfL https://raw.githubusercontent.com/reviewdog/reviewdog/master/install.sh | sh -s \
    && mv ./bin/reviewdog /bin/reviewdog

COPY entrypoint.sh /bin/entrypoint.sh
RUN chmod +x /bin/entrypoint.sh

ENTRYPOINT ["/bin/entrypoint.sh"]
