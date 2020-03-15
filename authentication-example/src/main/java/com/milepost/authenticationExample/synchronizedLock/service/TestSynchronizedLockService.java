package com.milepost.authenticationExample.synchronizedLock.service;

import com.milepost.core.lock.SynchronizedLock;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Service
public class TestSynchronizedLockService {

    /**
     * 实现跨实例的同步方法，在多个实例中，被@SynchronizedLock注解标注的方法，在同一时刻只能有一个实例的一个线程能执行该方法。
     * @param flag
     * @param sleep
     * @return
     * @throws InterruptedException
     */
    @Transactional
    @SynchronizedLock
    public String testSynchronizedLock(String flag, Integer sleep) throws InterruptedException {
        System.out.println("TestSynchronizedLockService.test1--1" + ", flag="+flag);
        Thread.sleep(sleep*1L);
        System.out.println("TestSynchronizedLockService.test1--2" + ", flag="+flag);
        return DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
    }
}
