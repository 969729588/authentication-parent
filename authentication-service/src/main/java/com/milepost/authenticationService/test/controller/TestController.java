package com.milepost.authenticationService.test.controller;

import com.milepost.authenticationService.test.feignClient.TestFc;
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
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestFc testFc;

    @ResponseBody
    @RequestMapping("/test3")
    public String test3(@RequestParam("param") String param, Principal principal){
        System.out.println(principal);
        System.out.println(principal.getName());
        System.out.println("收到参数：" + param);

        testFc.test3(param);


        return "收到参数：" + param;
    }

}
