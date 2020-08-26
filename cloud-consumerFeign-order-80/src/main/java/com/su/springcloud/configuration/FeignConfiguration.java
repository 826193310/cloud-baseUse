package com.su.springcloud.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname GiteeFeignConfiguration
 * @Description 配置 Feign 日志级别
 * @Date 2020/8/17 17:59
 * @Created by SGZ
 */
@Configuration
public class FeignConfiguration {

    /**
     * 配置 Feign 日志级别
     * <p>
     * NONE：没有日志
     * BASIC：基本日志
     * HEADERS：header
     * FULL：全部
     * <p>
     * 配置为打印全部日志，可以更方便的查看 Feign 的调用信息
     *
     * @return Feign 日志级别
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}