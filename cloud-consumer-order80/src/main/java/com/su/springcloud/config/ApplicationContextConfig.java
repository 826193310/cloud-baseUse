package com.su.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname ApplicationContextConfig
 * @Description TODO
 * @Date 2020/8/2 17:01
 * @Created by SGZ
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getTestTemplate() {
        return new RestTemplate();
    }
}
