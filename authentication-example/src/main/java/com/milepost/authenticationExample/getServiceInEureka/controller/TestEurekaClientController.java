package com.milepost.authenticationExample.getServiceInEureka.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/5.
 */
@RestController
@RequestMapping("/testEurekaClient")
public class TestEurekaClientController {

    private Logger logger = LoggerFactory.getLogger(TestEurekaClientController.class);

    /**
     * 用于获取当前应用的appName和instanceId，
     * 这里最好不要使用@Value读取配置文件，因为在不配置这个属性时，@Value注解报错，而这个方法返回UNKNOWN
     * appName:spring.application.name
     * instanceId:eureka.instance.instance-id
     *
     * 实际上是com.netflix.discovery.DiscoveryClient 和 org.springframework.cloud.netflix.eureka.CloudEurekaClient
     * 在支撑着这里的EurekaClient
     */
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired


    @ResponseBody
    @PostMapping("getMySelf")
    public Response<Map<String, Object>> getMySelf(){
        Response<Map<String, Object>> response = null;
        try {
            Map<String, Object> result = new HashMap<>();
            InstanceInfo instanceInfo = eurekaClient.getApplicationInfoManager().getInfo();
            result.put("instanceId", instanceInfo.getInstanceId());
            result.put("appName", instanceInfo.getAppName());//注意，无论配置如何，这里都会获取到大写的，所以在DiscoveryClient中获取到小写的需要转换成大写的
            response = ResponseHelper.createSuccessResponse(result);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * 测试eurekaClient.shutdown
     */
    @ResponseBody
    @PostMapping("/shutdown")
    public void shutdown(){
        System.out.println("---------");
        //把自己从EurekaServer中注销，从服务列表中消失，
        eurekaClient.shutdown();//进入了com.netflix.discovery.DiscoveryClient.shutdown()
        System.out.println("---------");
    }

    /**
     * EurekaClient也能获取注册到EurekaServer的所有服务实例，但是依然推荐使用
     * org.springframework.cloud.client.discovery.DiscoveryClient
     * @return
     */
    @ResponseBody
    @PostMapping("getAll")
    public Response<List<String>> getAll(){
        Response<List<String>> response = null;
        try {
            List<String> result = new ArrayList<>();
            Applications applications = eurekaClient.getApplications();
            List<Application> registeredApplications = applications.getRegisteredApplications();
            for(Application application : registeredApplications){
                System.out.println(application.getName());
                List<InstanceInfo> instancesAsIsFromEureka = application.getInstancesAsIsFromEureka();
                for(InstanceInfo instanceInfo1 : instancesAsIsFromEureka){
                    System.out.println(instanceInfo1.getInstanceId());
                    result.add(instanceInfo1.getInstanceId());
                }
            }
            response = ResponseHelper.createSuccessResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }

        return response;
    }

}
