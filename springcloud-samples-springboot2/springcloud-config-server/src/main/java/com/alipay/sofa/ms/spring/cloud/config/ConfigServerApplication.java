package com.alipay.sofa.ms.spring.cloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author yiji@apache.org
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ConfigServerApplication.class)
            .run(args);
  }

}
