
- doc : https://help.aliyun.com/document_detail/91225.html
- console: http://127.0.0.1:8080/#/serviceManagement?dataId=&group=&appName=&namespace=&serverId=


排除!alipay-cloud-server@public,!edas-oss-central,!edas-oss-plugin-central仓库镜像配置

```
<mirror>
<id>mirror-all</id>
  <mirrorOf>*,!alipay-cloud-server@public,!edas-oss-central,!edas-oss-plugin-central</mirrorOf>
  <name>mirror</name>
  <url>http://mvn.cloud.alipay.com/nexus/content/groups/public</url>
</mirror>
```