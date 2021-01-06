#!/bin/bash

version="$1"

if [[ -z "$version" ]]
then
    echo "Usage: build.sh image-version"
    exit 1;
else
    echo "Start building dubbo, sofa, springcloud images with image-version: $version"
fi

cd dubbo-samples-springboot2
sh ./build.sh $version

cd ../sofa-samples-springboot2
sh ./build.sh $version

cd ../springcloud-samples-springboot2
sh ./build.sh $version