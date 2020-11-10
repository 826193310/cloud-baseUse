package com.su.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname CricleBreakerController
 * @Description TODO
 * @Date 2020/11/10 0:31
 * @Created by SGZ
 */
@RestController
public class CricleBreakerController {

    public static final String SERVER_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    /**
    *
    *@Description: http://localhost:84/consumer/fallback/1
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/10
    *@return:
    *
    **/
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback") // 没有配置异常捕获和限流自定义处理
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录，空指针异常");
        }
        return result;
    }
}
