#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building with image-version: $version"
fi

mvn clean package

docker build -t dubbo-webservice:${version} .

docker tag dubbo-webservice:${version} reg-cnsh-nf.cloud.alipay.com/library/dubbo-webservice:${version}
docker tag dubbo-webservice:${version} ms.docker.hub.com:6003/library/dubbo-webservice:${version}

echo ms.docker.hub.com:6003/library/dubbo-webservice:${version}