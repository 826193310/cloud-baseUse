package com.su.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
