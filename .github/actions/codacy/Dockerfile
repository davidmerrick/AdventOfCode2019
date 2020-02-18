FROM openjdk:11.0.1-jdk-slim-stretch

COPY entrypoint.sh /bin/entrypoint.sh
RUN chmod +x /bin/entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
