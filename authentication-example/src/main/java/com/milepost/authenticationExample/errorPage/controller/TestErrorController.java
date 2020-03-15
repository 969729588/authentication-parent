package com.milepost.authenticationExample.errorPage.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */
@RestController
@RequestMapping("/testError")
public class TestErrorController {

    @GetMapping("/test1")
    @ResponseBody
    public void test1(@RequestParam("param") int param){
        System.out.println("--------");
        System.out.println(1/param);
        System.out.println("--------");
    }
}
