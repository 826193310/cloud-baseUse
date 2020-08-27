package com.su.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Classname OrderHystrixServiceFallbackImpl
 * @Description hystrixService fabllback 实现类， 主要是为了 OrderHystrixService 调用一些异常的时候的服务降级和熔断处理
 * @Date 2020/8/27 12:11
 * @Created by SGZ
 */
@Component
public class OrderHystrixServiceFallbackImpl implements OrderHystrixService{

    @Override
    public String getInfo(Integer id) {
        return "服务调用异常，hystrix fallback 机制返回数据";
    }

    @Override
    public String timeOut() {
        return "服务调用异常，hystrix fallback 返回数据";
    }
}
