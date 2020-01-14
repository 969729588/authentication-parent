package com.milepost.authenticationService;

import com.milepost.service.MilepostServiceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.milepost.authenticationService", "com.milepost.core"})
public class AuthenticationServiceApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		run(AuthenticationServiceApplication.class, args);
	}

}
