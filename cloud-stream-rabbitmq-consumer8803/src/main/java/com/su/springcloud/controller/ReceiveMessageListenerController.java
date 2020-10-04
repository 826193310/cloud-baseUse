package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Classname ReceiveMessageController
 * @Description TODO
 * @Date 2020/10/4 12:54
 * @Created by SGZ
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String>message) {
        System.out.println("消费者 1 号， 接收到的消息：" + message.getPayload() + "\t port:" + serverPort);
    }
}
