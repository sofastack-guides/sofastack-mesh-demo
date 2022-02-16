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

docker build -t springcloud-reservation-client:${version} .

docker tag springcloud-reservation-client:${version} reg-cnsh-nf.cloud.alipay.com/library/springcloud-reservation-client:${version}
docker tag springcloud-reservation-client:${version} ms.docker.hub.com:6003/library/springcloud-reservation-client:${version}

echo ms.docker.hub.com:6003/library/springcloud-reservation-client:${version}