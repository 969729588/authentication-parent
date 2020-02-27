package com.milepost.authenticationExample.getBeans;

import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 */
@Component
public class SomeBeanImpl1 implements SomeBean{

    @Override
    public void doSomething(String input) {
        System.out.println("SomeBeanImpl1 " + input);
    }

    @SomeAnnotation(pro = "pro1")
    @Override
    public String getSomething(String input) {
        return "SomeBeanImpl1 " + input;
    }
}
