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

docker build -t springcloud-reservation-service:${version} .

docker tag springcloud-reservation-service:${version} reg-cnsh-nf.cloud.alipay.com/library/springcloud-reservation-service:${version}
docker tag springcloud-reservation-service:${version} ms.docker.hub.com:6003/library/springcloud-reservation-service:${version}

echo ms.docker.hub.com:6003/library/springcloud-reservation-service:${version}