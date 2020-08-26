package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname OrderConsulController
 * @Description TODO
 * @Date 2020/8/7 17:13
 * @Created by SGZ
 */
@RestController
public class OrderConsulController {

    private final static String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    /**
    *
    *@Description: 通过consul 注册中心调用服务 http://localhost/consumer/payment/consul
    *@Author: SGZ
    *@Date: 2020/8/7
    *@return:
    *
    **/
    @GetMapping("consumer/payment/consul")
    public String consulOrder() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
    }

}
