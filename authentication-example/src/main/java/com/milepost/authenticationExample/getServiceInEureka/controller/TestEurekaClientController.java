package com.milepost.authenticationExample.getServiceInEureka.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     */
    @Autowired
    private EurekaClient eurekaClient;

    @ResponseBody
    @PostMapping("")
    public Response<Map<String, Object>> test(){
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
}
