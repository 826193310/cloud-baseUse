package com.su.springcloud.alibaba;

import io.seata.config.springcloud.EnableSeataSpringConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname SeataOrderMainApp201
 * @Description TODO
 * @Date 2020/11/18 11:07
 * @Created by SGZ
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据源的自动创建
@EnableFeignClients
@EnableDiscoveryClient
@EnableSeataSpringConfig
@MapperScan("com.su.springcloud.alibaba.dao")
public class SeataOrderMainApp201 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp201.class, args);
    }
}
