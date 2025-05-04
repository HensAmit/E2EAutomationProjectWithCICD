FROM bellsoft/liberica-openjdk-alpine:21.0.7
RUN apk add curl jq
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
ENTRYPOINT sh runner.sh