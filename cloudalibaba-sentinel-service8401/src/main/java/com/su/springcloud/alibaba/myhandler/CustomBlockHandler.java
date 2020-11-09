package com.su.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;

/**
 * @Classname CustomBlockHandler
 * @Description 自定义流控处理类，相关方法必须是 static
 * @Date 2020/11/9 23:41
 * @Created by SGZ
 */

public class CustomBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(8888, "按客户自定义，global handlerException----1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(8888, "按客户自定义，global handlerException----2");
    }
}
