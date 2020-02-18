package com.milepost.txClientServiceA.test.controller;

import com.milepost.api.util.DataUUIDUtil;
import com.milepost.txClientServiceA.person.entity.Person;
import com.milepost.txClientServiceA.person.service.PersonService;
import com.milepost.txClientServiceA.test.feignClient.TestBFc;
import com.milepost.txClientServiceA.test.feignClient.TestCFc;
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
@RequestMapping("/testSeata")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    private final PersonService personService;

    @Autowired
    public TestController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseBody
    @RequestMapping("/test1")
    public String test1(@RequestParam("param") String param, Principal principal){

        personService.test1(param);
        return "收到参数：" + param;
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(@RequestParam("param") String param, Principal principal){

        personService.test2(param);
        return "收到参数：" + param;
    }

}
