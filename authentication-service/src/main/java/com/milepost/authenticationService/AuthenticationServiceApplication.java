package com.milepost.authenticationService;

import com.milepost.core.lock.SchedulerLock;
import com.milepost.core.lock.SchedulerLockModel;
import com.milepost.service.MilepostServiceApplication;
import io.seata.core.lock.LockMode;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients	//service也可能调用其他service
@SpringBootApplication
@EnableAspectJAutoProxy
public class AuthenticationServiceApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
//		List<String> argList = new ArrayList<>();
//		for(String arg : args){
//			argList.add(arg);
//		}
//		String argsKey = "eureka.client.service-url.defaultZone";
//		String envValue = "http://192.168.223.129:8761/eureka/";
//		argList.add("--" + argsKey + "=" + envValue);
//
//		String[] newArgs = new String[argList.size()];
//		argList.toArray(newArgs);
//		run(AuthenticationServiceApplication.class, newArgs);
		run(AuthenticationServiceApplication.class, args);
	}

	@SchedulerLock(model = SchedulerLockModel.slave)
	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
	public void testSchedulerLockSlave() {
		System.out.println("所有slave运行这个定时任务");
	}
}
