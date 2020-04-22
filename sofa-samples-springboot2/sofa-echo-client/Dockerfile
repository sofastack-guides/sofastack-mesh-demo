FROM centos:7
FROM maven:3.5-jdk-8-alpine
MAINTAINER yiji <yiji@apache.org>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ADD target/sofa-echo-client-web-1.0-SNAPSHOT-executable.jar /home/admin/release/sofa-echo-client-web-1.0-SNAPSHOT-executable.jar

ADD start.sh /home/admin/release/start.sh

RUN chmod a+x /home/admin/release/start.sh

ENTRYPOINT ["/bin/bash", "/home/admin/release/start.sh"]
