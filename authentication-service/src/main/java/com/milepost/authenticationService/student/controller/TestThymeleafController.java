package com.milepost.authenticationService.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by Ruifu Hua on 2018-12-11.
 */
@Controller
@RequestMapping("testThymeleaf")
public class TestThymeleafController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/test1")
    public String  test1(Model model){
        //放在请求域中
        model.addAttribute("data1", "value1");
        model.addAttribute("data2", "value2");
        model.addAttribute("data3", new Date());
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "testThymeleaf/test1";
    }
}
