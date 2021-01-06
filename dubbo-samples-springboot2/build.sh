#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building dubbo image with image-version: $version"
fi

mvn clean package -pl dubbo-echo-client,dubbo-echo-server -am

cd dubbo-echo-client

docker build -t dubbo-echo-client:${version} .

docker tag dubbo-echo-client:${version} reg.docker.alibaba-inc.com/lxd/dubbo-echo-client:${version}

cd ../dubbo-echo-server/

docker build -t dubbo-echo-server:${version} .

docker tag dubbo-echo-server:${version} reg.docker.alibaba-inc.com/lxd/dubbo-echo-server:${version}