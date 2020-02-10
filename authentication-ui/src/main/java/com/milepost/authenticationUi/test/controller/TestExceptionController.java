package com.milepost.authenticationUi.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/2/9.
 */
@RestController
@RequestMapping("/testException")
public class TestExceptionController {

    @RequestMapping("/test500")
    public void test500(){
        System.out.println(1/0);
    }

}
