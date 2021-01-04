FROM openjdk:8-jre-alpine
MAINTAINER yiji <yiji@apache.org>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ADD target/sofa-echo-server-web-1.0-SNAPSHOT-executable.jar /home/admin/release/sofa-echo-server-web-1.0-SNAPSHOT-executable.jar

ADD start.sh /home/admin/release/start.sh

RUN chmod a+x /home/admin/release/start.sh

ENTRYPOINT ["/bin/sh", "/home/admin/release/start.sh"]
