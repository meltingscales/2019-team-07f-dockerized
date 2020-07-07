FROM ubuntu:16.04

LABEL maintainer="Henry Post, http://henryfbp.me/"

RUN apt-get update

RUN apt-get install -y python

#EXPOSE 5001 

ENTRYPOINT [ "python" ] 

CMD [ "-m","SimpleHTTPServer","5001" ] 
