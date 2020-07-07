FROM ubuntu:16.04

LABEL maintainer="Henry Post, resplendent.falconeer@gmail.com, http://henryfbp.me/"

RUN apt-get update

WORKDIR /app/docker/scripts/setup/

# Install dependencies
COPY scripts/setup/init.sh /app/docker/scripts/setup/init.sh
RUN bash init.sh

COPY scripts/setup/install-general.sh /app/docker/scripts/setup/install-general.sh
RUN bash install-general.sh

COPY scripts/setup/install-netdata.sh /app/docker/scripts/setup/install-netdata.sh
RUN bash install-netdata.sh

# TODO: These should NOT be in this file. Figure out how .env files work ASAP.
# TODO: Try K8s secrets. Maybe deploy to K8s cluster.
ENV MYSQL_PASSWORD="uhohstinky"
ENV TEST="Uh oh, stinky!"

# should print out test message in .env file
RUN echo Test: $TEST

COPY scripts/setup/install-mysql.sh /app/docker/scripts/setup/install-mysql.sh
RUN bash install-mysql.sh 