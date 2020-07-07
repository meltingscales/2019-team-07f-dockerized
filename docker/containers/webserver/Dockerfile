FROM ubuntu:16.04

LABEL maintainer="Henry Post, resplendent.falconeer@gmail.com, http://henryfbp.me/"

RUN apt-get update

WORKDIR /app/docker/scripts/

# Install dependencies
COPY scripts/init.sh /app/docker/scripts/init.sh
RUN bash init.sh

COPY scripts/install-netdata.sh /app/docker/scripts/install-netdata.sh
RUN bash install-netdata.sh

COPY scripts/install-java.sh /app/docker/scripts/install-java.sh
RUN bash install-java.sh

COPY scripts/install-tomcat.sh /app/docker/scripts/install-tomcat.sh
RUN bash install-tomcat.sh

COPY scripts/install-general.sh /app/docker/scripts/install-general.sh
RUN bash install-general.sh

COPY scripts/install-ffmpeg.sh /app/docker/scripts/install-ffmpeg.sh
RUN bash install-ffmpeg.sh

COPY scripts/install-maven.sh /app/docker/scripts/install-maven.sh
RUN bash install-maven.sh

COPY scripts/install-mysql-client.sh /app/docker/scripts/install-mysql-client.sh
RUN bash install-mysql-client.sh

COPY scripts/remove-mysql-server.sh /app/docker/scripts/remove-mysql-server.sh
RUN bash remove-mysql-server.sh

COPY scripts/install-python.sh /app/docker/scripts/install-python.sh
RUN bash install-python.sh

# This is not the real app. Simple webserver on port 5001.
RUN apt install -y python

RUN echo 'Visit http://127.0.0.1:5001/ in your browser.'

RUN echo '<body><h1><marquee>wowie! demo app!</marquee></h1></body>' > index.html

ENTRYPOINT [ "python2" ]
CMD [ "-m","SimpleHTTPServer","5001" ] 
