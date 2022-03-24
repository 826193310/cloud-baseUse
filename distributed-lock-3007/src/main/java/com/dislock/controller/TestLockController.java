package com.dislock.controller;

import com.dislock.service.DisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.rtf.RTFEditorKit;

/**
 * @Classname TestLockController
 * @Description 测试分布式锁的入口类
 * @Date 2020/9/18 23:15
 * @Created by SGZ
 */
@RestController
@RequestMapping("lock/")
public class TestLockController {

    @Autowired
    DisLockService disLockService;

    @GetMapping("jvmLock")
    public String jvmSyncLock() {
        return disLockService.miaosha();
    }

}
