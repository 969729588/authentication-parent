package com.milepost.authenticationUi.activeMq;

import com.milepost.authenticationUi.AuthenticationUiApplication;
import com.milepost.test.BaseTest;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by Ruifu Hua on 2020/2/8.
 * 本机mq地址
 * http://192.168.223.129:8161/
 * admin/admin
 *
 */
public class TestActiveMq extends BaseTest<AuthenticationUiApplication> {

    private JmsTemplate jmsTemplate;

    @Before
    public void init(){
        jmsTemplate = getBean(JmsTemplate.class);
    }

    /**
     * 发送消息
     */
    @Test
    public void test1(){
        jmsTemplate.convertAndSend(new ActiveMQQueue("dd"), "--123");
        jmsTemplate.convertAndSend(new ActiveMQTopic("ee"), "--456");
    }

    /**
     * 收取消息，当没有消息存在时，收取消息方法会阻塞等待。
     */
    @Test
    public void test2(){
        String dd = (String) jmsTemplate.receiveAndConvert(new ActiveMQQueue("dd"));
        String ee = (String) jmsTemplate.receiveAndConvert(new ActiveMQTopic("ee"));
        System.out.println("-------------"+dd);
        System.out.println("-------------"+ee);
    }
}
