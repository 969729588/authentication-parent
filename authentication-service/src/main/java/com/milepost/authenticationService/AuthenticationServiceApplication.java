package com.milepost.authenticationService;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.milepost.service.MilepostServiceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients	//service也可能调用其他service
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDistributedTransaction
@EnableHystrix
public class AuthenticationServiceApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		run(AuthenticationServiceApplication.class, args);
	}

}
