#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building sofa image with image-version: $version"
fi

mvn clean package -Dmaven.test.skip=true  -am

cd sofa-echo-client

docker build -t sofa-echo-client:${version} .

docker tag sofa-echo-client:${version} reg.docker.alibaba-inc.com/lxd/sofa-echo-client:${version}

docker push reg.docker.alibaba-inc.com/lxd/sofa-echo-client:${version}

cd ../dubbo-echo-server/

docker build -t sofa-echo-server:${version} .

docker tag sofa-echo-server:${version} reg.docker.alibaba-inc.com/lxd/sofa-echo-server:${version}

docker push reg.docker.alibaba-inc.com/lxd/sofa-echo-server:${version}