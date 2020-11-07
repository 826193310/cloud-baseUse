package com.su.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Classname FlowLimitController
 * @Description sentinel demo 项目
 * @Date 2020/11/3 13:11
 * @Created by SGZ
 */
@RestController
public class FlowLimitController {

    /**
    *
    *@Description: http://localhost:8401/testA
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/3
    *@return:
    *
    **/
    @GetMapping("/testA")
    public String testA() {
        //System.out.println("testA");
        return "----testA";
    }

    /**
     *
     *@Description: http://localhost:8401/testB
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }

    /**
     *
     *@Description: 测试降级规则 http://localhost:8401/testC
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/testC")
    public String testC() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        return "----testC";
    }

    /**
     *
     *@Description: 测试降级规则 http://localhost:8401/testD
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/testD")
    @SentinelResource(value = "testD")
    public String testD() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        return "----testD";
    }

    /**
     *
     *@Description: 测试服务降级之异常比例 http://localhost:8401/fwjj/ycbl
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/fwjj/ycbl")
    public String ycbl() {
        int a = 10/0;
        return "----ycbl";
    }

    /**
     *
     *@Description: 测试服务降级之异常数 http://localhost:8401/fwjj/ycs
     * 如果一分钟之内，异常数超过配置的异常数，时间窗口要大于等于60秒，否则可能造成一直都是熔断状态的处理
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/fwjj/ycs")
    public String ycs() {
        int a = 10/0;
        return "----ycs";
    }
}
