#!/bin/sh


java  -Dmesh_http_read_timeout=600000 ${JAVA_OPTS} -Duser.timezone=Asia/Shanghai -jar /home/admin/release/sofa-echo-server-web-1.0-SNAPSHOT-executable.jar
