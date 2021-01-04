package com.alipay.sofa.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yiji@apache.org
 */
@ImportResource({"classpath*:META-INF/sofa-samples-client/*.xml"})
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SOFABootClientSpringApplication {

    // init the logger
    private static final Logger logger = LoggerFactory.getLogger(SOFABootClientSpringApplication.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SOFABootClientSpringApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);

        if (logger.isInfoEnabled()) {
            logger.info("application start");
        }

    }
}
