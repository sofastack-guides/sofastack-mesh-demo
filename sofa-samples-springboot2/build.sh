#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building with image-version: $version"
fi

cd sofa-echo-server
#mvn clean package

cd ../sofa-echo-client/
#mvn clean package

cd ../sofa-echo-server
echo "building sofa-echo-server with image-version: $version"
docker build -t sofa-echo-server:${version} .
docker tag sofa-echo-server:${version} registry.cn-shanghai.aliyuncs.com/mesh-demo/sofa-echo-server:${version}

cd ../sofa-echo-client/
echo "building sofa-echo-client with image-version: $version"
docker build -t sofa-echo-client:${version} .

docker tag sofa-echo-client:${version} registry.cn-shanghai.aliyuncs.com/mesh-demo/sofa-echo-client:${version}