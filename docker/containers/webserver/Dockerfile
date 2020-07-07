FROM ubuntu:16.04

LABEL maintainer="Henry Post, resplendent.falconeer@gmail.com, http://henryfbp.me/"

RUN apt-get update

RUN apt-get install -y python

COPY scripts/ /app/docker/scripts/

RUN bash /app/docker/scripts/init.sh

RUN bash /app/docker/scripts/install-general.sh

ENTRYPOINT [ "python" ] 

CMD [ "-m","SimpleHTTPServer","5001" ] 
