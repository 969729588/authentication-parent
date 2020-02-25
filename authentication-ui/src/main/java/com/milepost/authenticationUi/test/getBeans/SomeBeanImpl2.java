package com.milepost.authenticationUi.test.getBeans;

import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/2/26.
 */
@Component
public class SomeBeanImpl2 implements SomeBean{

    @Override
    public void doSomething(String input) {
        System.out.println("SomeBeanImpl2 " + input);
    }

    @SomeAnnotation(pro = "pro2")
    @Override
    public String getSomething(String input) {
        return "SomeBeanImpl2 " + input;
    }
}
