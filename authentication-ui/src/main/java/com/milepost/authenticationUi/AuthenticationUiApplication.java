package com.milepost.authenticationUi;

import com.milepost.ui.MilepostUiApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableHystrix
public class AuthenticationUiApplication extends MilepostUiApplication{

	public static void main(String[] args) {
//		SpringApplication.run(AuthenticationUiApplication.class, args);
		run(AuthenticationUiApplication.class, args);
	}
}
