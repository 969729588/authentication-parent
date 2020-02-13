package com.milepost.txClientServiceB;

import com.milepost.service.MilepostServiceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients    //service也可能调用其他service
@SpringBootApplication
@EnableAspectJAutoProxy
public class TxClientServiceBApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		run(TxClientServiceBApplication.class, args);
	}

}
