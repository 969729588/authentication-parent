package com.milepost.txClientServiceB.test.controller;

import com.milepost.api.util.DataUUIDUtil;
import com.milepost.txClientServiceB.person.entity.Person;
import com.milepost.txClientServiceB.person.service.PersonService;
import com.milepost.txClientServiceB.test.feignClient.TestBFc;
import com.milepost.txClientServiceB.test.feignClient.TestCFc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@Controller
@RequestMapping("/testLcn")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestBFc testBFc;

    @Autowired
    private TestCFc testCFc;

    @Autowired
    private PersonService personService;

    @ResponseBody
    @RequestMapping("/test1")
    public String test1(@RequestParam("param") String param, Principal principal){
        System.out.println(principal);
        System.out.println(principal.getName());
        System.out.println("收到参数：" + param);

//        testBFc.callB(param);
//        System.out.println("-----------");
//        testCFc.callC(param);

        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("bbb");
        personService.test1(person);

        return "收到参数：" + param;
    }

}
