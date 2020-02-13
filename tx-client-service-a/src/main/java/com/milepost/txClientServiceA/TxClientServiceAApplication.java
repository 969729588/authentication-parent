package com.milepost.txClientServiceA;

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
public class TxClientServiceAApplication extends MilepostServiceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TxClientServiceAApplication.class, args);
		run(TxClientServiceAApplication.class, args);
	}
}
