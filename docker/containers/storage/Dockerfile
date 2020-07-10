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

COPY scripts/setup/install-nfs-server.sh /app/docker/scripts/setup/install-nfs-server.sh
RUN bash install-nfs-server.sh
