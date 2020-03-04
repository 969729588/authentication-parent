package com.milepost.authenticationExample.activeMq.controller;

import com.milepost.core.mq.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2020/2/8.
 */
@Controller
@RequestMapping("/testActiveMq")
public class TestActiveMqController {

    @Autowired(required = false)
    private ActiveMqService activeMqService;

    public TestActiveMqController() {
        System.out.println("TestActiveMqController...");
    }

    /**
     * 监听名称为“queue1”的queue，containerFactory 要固定指定为 "jmsListenerContainerFactoryQueue"
     * @param message
     */
    @JmsListener(destination = "queue1", containerFactory = "jmsListenerContainerFactoryQueue")
    public void queue1Listener(String message) {
        System.out.println("queue1接收到消息：【" + message + "】");
    }

    /**
     * 监听名称为“queue2”的queue，containerFactory 要固定指定为 "jmsListenerContainerFactoryQueue"
     * @param message
     */
    @JmsListener(destination = "queue2", containerFactory = "jmsListenerContainerFactoryQueue")
    public void queue2Listener(String message) {
        System.out.println("queue2接收到消息：【" + message + "】");
    }

    /**
     * 监听名称为“topic1”topic，containerFactory 要固定指定为 "jmsListenerContainerFactoryTopic"
     * @param message
     */
    @JmsListener(destination = "topic1", containerFactory = "jmsListenerContainerFactoryTopic")
    public void topic1Listener(String message) {
        System.out.println("topic1接收到消息：【" + message + "】");
    }

    /**
     * 监听名称为“topic2”topic，containerFactory 要固定指定为 "jmsListenerContainerFactoryTopic"
     * @param message
     */
    @JmsListener(destination = "topic2", containerFactory = "jmsListenerContainerFactoryTopic")
    public void topic2Listener(String message) {
        System.out.println("topic2接收到消息：【" + message + "】");
    }

    /**
     * 使用activeMqService像queue发送消息。
     * @param queueName
     * @param message
     * @return
     */
    @ResponseBody
    @GetMapping("/testSendQueue")
    public String testSendQueue(@RequestParam("queueName") String queueName, @RequestParam("message") String message){
        activeMqService.sendQueue(activeMqService.getQueue(queueName), message);
        return message + "发送成功";
    }

    /**
     * 使用activeMqService收取queue消息。
     * @param queueName
     * @return
     */
    @ResponseBody
    @GetMapping("/testReceiveQueue")
    public String testReceiveQueue(@RequestParam("queueName") String queueName){
        String message = activeMqService.receiveQueue(activeMqService.getQueue(queueName));
        return "从"+ queueName +"中接收到" + message;
    }

    /**
     * 使用activeMqService像topic发送消息。
     * @param topicName
     * @param message
     * @return
     */
    @ResponseBody
    @GetMapping("/testSendTopic")
    public String testSendTopic(@RequestParam("topicName") String topicName, @RequestParam("message") String message){
        activeMqService.sendTopic(activeMqService.getTopic(topicName), message);
        return message + "发送成功";
    }

    /**
     * 使用activeMqService收取topic消息。
     * @param topicName
     * @return
     */
    @ResponseBody
    @GetMapping("/testReceiveTopic")
    public String testReceiveTopic(@RequestParam("topicName") String topicName){
        String message = activeMqService.receiveTopic(activeMqService.getTopic(topicName));
        return "从"+ topicName +"中接收到" + message;
    }
}