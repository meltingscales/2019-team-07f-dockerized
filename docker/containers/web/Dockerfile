FROM ubuntu:16.04

MAINTAINER Henry Post "resplendent.falconeer@gmail.com"

RUN apt-get update

RUN apt-get install -y python

#EXPOSE 5001 

ENTRYPOINT [ "python" ] 

CMD [ "-m","SimpleHTTPServer","5001" ] 
