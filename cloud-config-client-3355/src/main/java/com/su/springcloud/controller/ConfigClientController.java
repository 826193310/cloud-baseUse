package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ConfigClientController
 * @Description 测试 ConfigClient
 * @Date 2020/9/20 0:22
 * @Created by SGZ
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /**
    *
    *@Description: http://localhost:3355/configInfo
    *@param: null
    *@Author: SGZ
    *@Date: 2020/9/20
    *@return:
    *
    **/
    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
