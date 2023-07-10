#!/bin/sh

# build project and create docker image leobi/web-content-picker locally
gradle :svc-web-content-picker:build && sudo docker build -t leobi/web-content-picker ./svc-web-content-picker

# run docker
# docker run --net grid -p 8080:8080 leobi/web-content-picker
