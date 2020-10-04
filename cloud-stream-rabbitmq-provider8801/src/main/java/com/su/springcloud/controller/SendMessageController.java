package com.su.springcloud.controller;

import com.su.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SendMessageController
 * @Description TODO
 * @Date 2020/10/4 12:30
 * @Created by SGZ
 */
@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    /**
    *
    *@Description: http://localhost:8801/sendMsg
    *@param: null
    *@Author: SGZ
    *@Date: 2020/10/4
    *@return:
    *
    **/
    @GetMapping("/sendMsg")
    public String sendMsg() {
        return messageProvider.send();
    }
}
