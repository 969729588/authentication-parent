package com.milepost.authenticationExample.conditionBean.conditional;

import com.milepost.authenticationExample.conditionBean.conditional.condition.RandBooleanCondition;
import com.milepost.authenticationExample.conditionBean.conditional.condition.RandIntCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Created by @author yihui in 22:05 18/10/17.
 */
@Configuration
public class ConditionalAutoConfig {

    @Bean
    //RandIntCondition.matches()方法返回true时才初始化这个bean
    @Conditional(RandIntCondition.class)
    public RandDataComponent<Integer> randIntComponent() {
        return new RandDataComponent<>(() -> {
            Random random = new Random();
            return random.nextInt(1024);
        });
    }

    @Bean
    @Conditional(RandBooleanCondition.class)
    public RandDataComponent<Boolean> randBooleanComponent() {
        return new RandDataComponent<>(() -> {
            Random random = new Random();
            return random.nextBoolean();
        });
    }
}
