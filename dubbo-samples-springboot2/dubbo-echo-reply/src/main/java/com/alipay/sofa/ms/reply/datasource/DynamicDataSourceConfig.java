package com.alipay.sofa.ms.reply.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:config/jdbc-${spring.profiles.active:dev}.properties")
@MapperScan(basePackages = "com.alipay.sofa.ms.reply.mapper")
public class DynamicDataSourceConfig {

    @Bean(DataSourceKey.PROD_DATABASE)
    @ConfigurationProperties(prefix = "spring.datasource.prod")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DataSourceKey.TEST_DATABASE)
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceKey.PROD_DATABASE, prodDataSource());
        dataSourceMap.put(DataSourceKey.TEST_DATABASE, testDataSource());

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(prodDataSource());

        return dynamicDataSource;
    }

}