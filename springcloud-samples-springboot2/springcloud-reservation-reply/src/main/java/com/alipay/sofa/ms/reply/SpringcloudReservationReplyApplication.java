package com.alipay.sofa.ms.reply;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yiji@apache.org
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ImportResource("spring/aop-config.xml")
public class SpringcloudReservationReplyApplication {
  public static void main(String[] args) {
    new SpringApplicationBuilder(SpringcloudReservationReplyApplication.class)
            .run(args);
  }
}
