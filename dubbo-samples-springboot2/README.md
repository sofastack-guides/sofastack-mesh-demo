# dubbo mesh demo

setp1:
    start zookeeper first on mac.

step2:
    start dubbo-echo-server with jvm arguments: -Dspring.profiles.active=dev
    
step3:
    start dubbo-echo-client with jvm arguments: -Dspring.profiles.active=dev