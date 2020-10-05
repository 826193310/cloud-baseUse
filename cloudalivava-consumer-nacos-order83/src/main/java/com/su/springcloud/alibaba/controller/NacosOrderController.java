package com.su.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname NacosOrderController
 * @Description TODO
 * @Date 2020/10/5 12:09
 * @Created by SGZ
 */
@RestController
public class NacosOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    /**
    *
    *@Description: http://localhost:83/nacos/payment/nacos/1
    *@param: null
    *@Author: SGZ
    *@Date: 2020/10/5
    *@return:
    *
    **/
    @GetMapping("/nacos/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id, String.class);
    }
}
