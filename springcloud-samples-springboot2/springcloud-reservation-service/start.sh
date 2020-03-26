#!/bin/sh


java  -Dmesh_http_read_timeout=600000 -Duser.timezone=Asia/Shanghai -jar /home/admin/release/springcloud-reservation-service-1.0-SNAPSHOT.jar
