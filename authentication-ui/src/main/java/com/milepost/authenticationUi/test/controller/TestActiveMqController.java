package com.milepost.authenticationUi.test.controller;

import com.milepost.core.mq.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2020/2/8.
 */
@Controller
@RequestMapping("/testActiveMq")
public class TestActiveMqController {

    public TestActiveMqController() {
        System.out.println("TestActiveMqController...");
    }

    @Autowired
    private ActiveMqService activeMqService;

    @JmsListener(destination = "queue1", containerFactory = "jmsListenerContainerFactoryQueue")
    public void queue1(String message) {
        System.out.println("queue1接收到消息：【" + message + "】");
    }

    @JmsListener(destination = "queue2", containerFactory = "jmsListenerContainerFactoryQueue")
    public void queue2(String message) {
        System.out.println("queue2接收到消息：【" + message + "】");
    }

    @JmsListener(destination = "topic1", containerFactory = "jmsListenerContainerFactoryTopic")
    public void topic1(String message) {
        System.out.println("topic1接收到消息：【" + message + "】");
    }

    @JmsListener(destination = "topic2", containerFactory = "jmsListenerContainerFactoryTopic")
    public void topic2(String message) {
        System.out.println("topic2接收到消息：【" + message + "】");
    }

    @ResponseBody
    @RequestMapping("/testSendQueue")
    public String testSendQueue(@RequestParam("queueName") String queueName, @RequestParam("message") String message){
        activeMqService.sendQueue(activeMqService.getQueue(queueName), message);
        return message + "发送成功";
    }

    @ResponseBody
    @RequestMapping("/testReceiveQueue")
    public String testReceiveQueue(@RequestParam("queueName") String queueName){
        String message = activeMqService.receiveQueue(activeMqService.getQueue(queueName));
        return "从"+ queueName +"中接收到" + message;
    }

    @ResponseBody
    @RequestMapping("/testSendTopic")
    public String testSendTopic(@RequestParam("topicName") String topicName, @RequestParam("message") String message){
        activeMqService.sendTopic(activeMqService.getTopic(topicName), message);
        return message + "发送成功";
    }

    @ResponseBody
    @RequestMapping("/testReceiveTopic")
    public String testReceiveTopic(@RequestParam("topicName") String topicName){
        String message = activeMqService.receiveTopic(activeMqService.getTopic(topicName));
        return "从"+ topicName +"中接收到" + message;
    }






}
