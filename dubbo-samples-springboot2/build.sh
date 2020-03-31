#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building with image-version: $version"
fi

mvn clean package -pl dubbo-echo-client,dubbo-echo-server -am

cd dubbo-echo-client

docker build -t dubbo-echo-client:${version} .

docker tag dubbo-echo-client:${version} reg-cnsh-nf.cloud.alipay.com/library/dubbo-echo-client:${version}

cd ../dubbo-echo-server/

docker build -t dubbo-echo-server:${version} .

docker tag dubbo-echo-server:${version} reg-cnsh-nf.cloud.alipay.com/library/dubbo-echo-server:${version}