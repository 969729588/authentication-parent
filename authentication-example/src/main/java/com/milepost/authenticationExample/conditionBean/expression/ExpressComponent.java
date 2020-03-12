package com.milepost.authenticationExample.conditionBean.expression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/3/11.
 */
@Component
@ConditionalOnExpression("#{'true'.equals(environment['scheduler-lock.enabled'])}")
public class ExpressComponent {
    public ExpressComponent() {
        System.out.println("init ExpressComponent...");
    }
}
