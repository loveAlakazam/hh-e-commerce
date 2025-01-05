FROM ubuntu:latest
LABEL authors="eunkangchoi"

ENTRYPOINT ["top", "-b"]