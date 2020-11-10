package com.su.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.su.springcloud.alibaba.service.PaymentService;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import com.sun.deploy.security.BlockedException;
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

    @Autowired
    private PaymentService paymentService;

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
    //@SentinelResource(value = "fallback") // 没有配置异常捕获和限流自定义处理
    //@SentinelResource(value = "fallback", fallback = "handlerFallback") // fallback 只负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler 只负责 sentinel 控制台配置违规
    //@SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback")
    // 当捕获到某些异常的时候，服务降级不进行处理，比如 IllegalArgumentException
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVER_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult(888, "兜底异常 handlerFallback, exception内容:" + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception) {
        Payment payment = new Payment(id, null);
        return new CommonResult(889, "blockHandler-sentinel限流，无此流水, blockException:" + exception.getMessage(), payment);
    }

    /**
    *
    *@Description: sentinel 整合 feigin http://localhost:84/consumer/sentinel/feign/1
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/10
    *@return:
    *
    **/
    @RequestMapping("/consumer/sentinel/feign/{id}")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> getPaymentFeign(@PathVariable("id") Long id) {
        CommonResult<Payment> result = paymentService.getPayment(id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该ID没有对应记录，空指针异常");
        }
        return result;
    }
}
