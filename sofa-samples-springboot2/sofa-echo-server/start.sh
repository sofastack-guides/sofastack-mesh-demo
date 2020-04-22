#!/bin/bash


java  -DMOSN_ENABLE=${MOSN_ENABLE} -Dcom.alipay.instanceid=${SOFA_INSTANCE_ID} -Dcom.antcloud.antvip.endpoint=${SOFA_ANTVIP_ENDPOINT} -Drpc_bind_network_interface=eth0 -Dcom.alipay.env=shared -Duser.timezone=Asia/Shanghai -Drpc_tr_port=${RPC_TR_PORT} -Dserver.auto.start=${server_auto_start} -DSOFA_INSTANCE_ID=${SOFA_INSTANCE_ID} -DSOFA_ANTVIP_ENDPOINT=${SOFA_ANTVIP_ENDPOINT} -Dmesh_http_read_timeout=600000 -Duser.timezone=Asia/Shanghai -jar /home/admin/release/sofa-echo-server-web-1.0-SNAPSHOT-executable.jar
