[![Build Status](https://travis-ci.org/HenryFBP/2019-team-07f-dockerized.svg?branch=master)](https://travis-ci.org/HenryFBP/2019-team-07f-dockerized)

# 2019-team-07f-dockerized

A Dockerized school project.

See <http://github.com/HenryFBP/2019-team-07f/> for the non-Dockerized version, which used Packer and Vagrant.

## Building

    cd docker
    docker-compose build

## Running

    cd docker
    docker-compose up

## Using the app

Go to <http://localhost:5001/>

## Misc

<https://www.portainer.io/>

<https://docs.docker.com/develop/develop-images/dockerfile_best-practices/>

## Removing all containers

This will cause you to have to rebuild ALL containers.

Only use if you need to.

Run `docker ps -a -q` to see all Docker containers.

    docker stop $(docker ps -a -q)
    docker rm $(docker ps -a -q)
