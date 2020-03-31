#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building with image-version: $version"
fi

mvn clean package -pl springcloud-reservation-client,springcloud-reservation-service -am

cd springcloud-reservation-client

docker build -t springcloudreservationclient:${version} .

docker tag springcloudreservationclient:${version} reg-cnsh-nf.cloud.alipay.com/library/springcloudreservationclient:${version}

cd ../springcloud-reservation-service/

docker build -t springcloudreservationservice:${version} .

docker tag springcloudreservationservice:${version} reg-cnsh-nf.cloud.alipay.com/library/springcloudreservationservice:${version}