package com.milepost.authenticationService.config.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by Ruifu Hua on 2020/1/15.
 */
@Configuration
public class DruidMultipleDsConfig {

    /**
     * 多数据源仅仅到此为止，不要设计mybatis，因为在微服务架构下，分布式事物已经令人很烦恼了，
     * 而且多数据源可以通过服务查分来代替，
     * 而且需要用到多数据源的设计本身就不是很合理。
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

}
