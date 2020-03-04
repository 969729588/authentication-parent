package com.milepost.authenticationExample.schedulerLock.scheduler;

import com.milepost.core.lock.InstanceRoleService;
import com.milepost.core.lock.SchedulerLock;
import com.milepost.core.lock.SchedulerLockModel;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ruifu Hua on 2020/3/4.
 */
@Component
public class TestSchedulerLock {

    private static Logger logger = LoggerFactory.getLogger(TestSchedulerLock.class);

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
    @SchedulerLock(model = SchedulerLockModel.master)
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

    @SchedulerLock(model = SchedulerLockModel.slave)
    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    public void testSchedulerLockSlave() {
        logger.info("所有slave运行这个定时任务");
    }

    @SchedulerLock(model = SchedulerLockModel.master)
    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void testSchedulerLockMaster() {
        logger.info("只有master运行这个定时任务");
    }
}
