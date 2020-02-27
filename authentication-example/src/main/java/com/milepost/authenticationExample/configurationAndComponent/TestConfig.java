package com.milepost.authenticationExample.configurationAndComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 测试@Configuration和@Component的区别
 * Created by Ruifu Hua on 2020/2/27.
 */
@Component
public class TestConfig {
    @Autowired
    private Car car;

    @Autowired
    private Driver driver1;

    @Autowired
    private Driver driver2;

    @EventListener
    public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
        Car car1 = driver1.getCar();
        if(car1 == car){
            System.out.println("同一个car");
        }else{
            System.out.println("不同的car");
        }


        Car car2 = driver2.getCar();
        if(car2 == car){
            System.out.println("同一个car");
        }else{
            System.out.println("不同的car");
        }
    }
}
