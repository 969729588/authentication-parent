package com.milepost.authenticationService;

import com.milepost.core.lock.SchedulerLock;
import com.milepost.core.lock.SchedulerLockModel;
import com.milepost.service.MilepostServiceApplication;
import io.seata.core.lock.LockMode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients	//service也可能调用其他service
@SpringBootApplication
@EnableAspectJAutoProxy
public class AuthenticationServiceApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		run(AuthenticationServiceApplication.class, args);
	}

	@SchedulerLock(model = SchedulerLockModel.slave)
	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
	public void testSchedulerLockSlave() {
		System.out.println("所有slave运行这个定时任务");
	}
}
