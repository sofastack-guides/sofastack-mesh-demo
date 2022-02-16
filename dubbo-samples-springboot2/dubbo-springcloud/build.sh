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

docker build -t dubbo-springcloud:${version} .

docker tag dubbo-springcloud:${version} reg-cnsh-nf.cloud.alipay.com/library/dubbo-springcloud:${version}
docker tag dubbo-springcloud:${version} ms.docker.hub.com:6003/library/dubbo-springcloud:${version}

echo ms.docker.hub.com:6003/library/dubbo-springcloud:${version}