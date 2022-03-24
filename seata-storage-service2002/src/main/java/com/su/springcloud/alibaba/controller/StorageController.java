package com.su.springcloud.alibaba.controller;

import com.su.springcloud.alibaba.entity.CommonResult;
import com.su.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname StorageController
 * @Description TODO
 * @Date 2020/11/30 22:28
 * @Created by SGZ
 */
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    // 扣减库存
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decreate(productId, count);
        return new CommonResult(200, "操作成功");
    }
}
