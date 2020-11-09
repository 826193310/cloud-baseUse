package com.su.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname RateLimitController
 * @Description
 * @Date 2020/11/9 23:13
 * @Created by SGZ
 */
@RestController
public class RateLimitController {

    /**
    *
    *@Description: 测试自定义返回流量超出阀值的处理 http://localhost:8401/byResource
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/9
    *@return:
    *
    **/
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName()+"\t服务不可用");
    }

    /**
    *
    *@Description: 默认情况下，没有配置自定义限流处理(blockHandler)测试  http://localhost:8401/rateLimit/byUrl
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/9
    *@return:
    *
    **/
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试OK", new Payment(2020L, "serial002"));
    }
}
