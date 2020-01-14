package com.milepost.authenticationUi.test.controller;

import com.milepost.authenticationUi.test.feignClient.TestFc;
import com.milepost.authenticationUi.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    //如果配置文件中没有这个属性，则启动会报错，
//    @Value("${test.aaa}")
//    private String aaa;
//    @Value("${eureka.instance.instance-id}")
//    private String instanceId;

    @Resource
    private Environment environment;

    @Autowired
    private TestFc testFc;

    /**
     * https://192.168.223.1:9999/authentication-ui/test/test3?param=123
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/test3")
    public String test3(@RequestParam("param") String param){
        System.out.println(param);
        return testFc.test3(param);
    }

    /**
     * http://localhost:9998/test/test2?key=test.aaa
     * http://localhost:9998/test/test2?key=eureka.instance.hostname
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/test2")
    public String test2(@RequestParam("key") String key){
        System.out.println(environment.getProperty(key));
        return environment.getProperty(key);
    }

    /**
     * 测试分布式同步锁
     * http://localhost:9997/test/test1?sleep=10000&flag=1
     * @param sleep
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/test1")
    public String test1(@RequestParam("flag") String flag, @RequestParam("sleep") Integer sleep) throws InterruptedException {
        System.out.println("TestController.test1--1" + ", flag="+flag);
        String result = testService.test1(flag, sleep);
        System.out.println("TestController.test1--2" + ", flag="+flag);
        return result;
    }


}
