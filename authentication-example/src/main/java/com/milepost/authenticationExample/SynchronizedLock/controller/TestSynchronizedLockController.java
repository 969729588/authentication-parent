package com.milepost.authenticationExample.SynchronizedLock.controller;

import com.milepost.authenticationExample.SynchronizedLock.service.TestSynchronizedLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Controller
@RequestMapping("/testSynchronizedLock")
public class TestSynchronizedLockController {

    private Logger logger = LoggerFactory.getLogger(TestSynchronizedLockController.class);

    @Autowired
    private TestSynchronizedLockService testSynchronizedLock;

    /**
     * 测试分布式同步锁
     * @param sleep
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @GetMapping("")
    public String testSynchronizedLock(@RequestParam("flag") String flag,
                                       @RequestParam("sleep") Integer sleep) throws InterruptedException {
        System.out.println("TestSynchronizedLockService.test1--1" + ", flag="+flag);
        String result = testSynchronizedLock.testSynchronizedLock(flag, sleep);
        System.out.println("TestSynchronizedLockService.test1--2" + ", flag="+flag);
        return result;
    }
}
