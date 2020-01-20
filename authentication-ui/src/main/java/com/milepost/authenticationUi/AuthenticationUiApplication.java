package com.milepost.authenticationUi;

import com.milepost.core.lock.InstanceRoleService;
import com.milepost.core.lock.LockModel;
import com.milepost.core.lock.SchedulerLock;
import com.milepost.ui.MilepostUiApplication;
import com.netflix.discovery.EurekaClient;
import com.netflix.eureka.EurekaServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.List;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableAspectJAutoProxy
public class AuthenticationUiApplication extends MilepostUiApplication{

	public static void main(String[] args) {
//		SpringApplication.run(AuthenticationUiApplication.class, args);
		run(AuthenticationUiApplication.class, args);
	}


	private static Logger logger = LoggerFactory.getLogger(AuthenticationUiApplication.class);

	@Autowired
	private InstanceRoleService instanceRoleService;

	//用于在EurekaClient端获取注册到EurekaServer上的所有服务实例
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private EurekaClient eurekaClient;


	/**
	 * 为了测试的，没有实际作用
	 */
	// initialDelay：Spring IoC 容器初始化后，第一次延迟的时间，单位为ms，
	// fixedDelay：从上一个任务完成到下一个任务开始的间隔，单位为ms，
	@SchedulerLock(model = LockModel.master)
	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
	public void printAllServiceInstance() {
		//注意，下面的使用discoveryClient的方式不适合用在EurekaServer端，因为EurekaServer配置了eureka.client.fetch-registry=false，
		//禁止向注册中心获取服务列表，所以获取到的服务列表为空，但是这种方法可以用到EurekaClient端，EurekaServer端应该使用PeerAwareInstanceRegistry
		//获取注册到注册中心的所有服务，是服务ID的集合，当没有配置服务ID时，其默认值是应用名称，即spring.application.name。
		List<String> serviceList = discoveryClient.getServices();
		if(serviceList.size() == 0){
			logger.info("DiscoveryClient没有任何服务注册到注册中心。");
		}
		for(String service : serviceList){
			//根据服务Id获取所有的服务实例
			List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(service);
			for(ServiceInstance serviceInstance : serviceInstanceList){
				String instanceId = serviceInstance.getInstanceId();
				//注意，这里要转换成大写的，因为无论如何配置应用名称，都会被转换成大写的
				logger.info("DiscoveryClient获取到的服务--appName:" + service.toUpperCase() + "; instanceId:" + instanceId + "; instanceRole:" + (instanceRoleService.isMaster(service.toUpperCase(), instanceId)?"master":"slave"));
			}
		}

	}

	//测试@SchedulerLock
//	@SchedulerLock(model = LockModel.slave)
//	@Scheduled(initialDelay = 10000, fixedDelay = 5000)
//	public void testSchedulerLock() {
//		logger.info("测试@SchedulerLock");
//	}
}
