package com.milepost.authenticationUi.activeMq;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ruifu Hua on 2020/2/8.
 */
@RunWith(SpringRunner.class)
//value属性配置属性，覆盖yml中的配置
@SpringBootTest(value = {"spring.main.allow-bean-definition-overriding=true",
        })
public class TestActiveMq {

//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    /**
//     * 发送消息
//     */
//    @Test
//    public void test1(){
//        jmsTemplate.convertAndSend(new ActiveMQQueue("dd"), "123");
//        jmsTemplate.convertAndSend(new ActiveMQTopic("ee"), "456");
//    }
//
//    /**
//     * 收取消息，当没有消息存在时，收取消息方法会阻塞等待。
//     */
//    @Test
//    public void test2(){
//        String dd = (String) jmsTemplate.receiveAndConvert(new ActiveMQQueue("dd"));
//        String ee = (String) jmsTemplate.receiveAndConvert(new ActiveMQTopic("ee"));
//        System.out.println("-------------"+dd);
//        System.out.println("-------------"+ee);
//    }
}
