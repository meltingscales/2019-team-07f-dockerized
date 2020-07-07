FROM ubuntu:16.04

LABEL maintainer="Henry Post, resplendent.falconeer@gmail.com, http://henryfbp.me/"

RUN apt-get update

COPY scripts/ /app/docker/scripts/

# Install dependencies
WORKDIR /app/docker/scripts/
RUN bash init.sh
RUN bash install-general.sh
RUN bash install-netdata.sh
RUN bash install-java.sh
RUN bash install-ffmpeg.sh
RUN bash install-maven.sh
RUN bash install-mysql-client.sh
RUN bash remove-mysql-server.sh
RUN bash install-python.sh
RUN bash install-tomcat.sh

ENTRYPOINT [ "python2" ]

CMD [ "-m","SimpleHTTPServer","5001" ] 
