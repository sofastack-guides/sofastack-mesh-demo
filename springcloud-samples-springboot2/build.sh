#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building springcloud image with image-version: $version"
fi

mvn clean package -pl springcloud-reservation-client,springcloud-reservation-service -am

cd springcloud-reservation-client

docker build -t springcloudreservationclient:${version} .

docker tag springcloudreservationclient:${version} reg.docker.alibaba-inc.com/lxd/springcloud-client:${version}

docker push reg.docker.alibaba-inc.com/lxd/springcloud-client:${version}

cd ../springcloud-reservation-service/

docker build -t springcloudreservationservice:${version} .

docker tag springcloudreservationservice:${version} reg.docker.alibaba-inc.com/lxd/springcloud-server:${version}

docker push reg.docker.alibaba-inc.com/lxd/springcloud-server:${version}