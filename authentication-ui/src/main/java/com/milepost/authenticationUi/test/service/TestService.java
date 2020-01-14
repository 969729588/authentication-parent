package com.milepost.authenticationUi.test.service;

import com.milepost.core.lock.SynchronizedLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Service
public class TestService {

    @Transactional
    @SynchronizedLock
    public String test1(String flag, Integer sleep) throws InterruptedException {
        System.out.println("TestService.test1--1" + ", flag="+flag);
        Thread.sleep(sleep*1L);
        System.out.println("TestService.test1--2" + ", flag="+flag);
        return "123456";
    }
}
