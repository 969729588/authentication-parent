package com.milepost.authenticationExample.getServiceInEureka.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.core.lock.InstanceRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
@RequestMapping("/testDiscoveryClient")
public class TestDiscoveryClientController {

    private Logger logger = LoggerFactory.getLogger(TestDiscoveryClientController.class);

    /**
     * 用于在EurekaClient端获取注册到EurekaServer上的所有服务实例，
     * discoveryClient不适合用在EurekaServer端，因为EurekaServer配置了eureka.client.fetch-registry=false，
     * 禁止向注册中心获取服务列表，所以获取到的服务列表为空，但是这种方法可以用到EurekaClient端，
     *
     * EurekaServer端应该使用PeerAwareInstanceRegistry获取注册到注册中心的所有服务，是服务ID的集合，
     * 当没有配置服务ID时，其默认值是应用名称，即spring.application.name。
     *
     * 认证UI页面，使用DiscoveryClient获取实例信息即可，把server.servlet.context-path放到metadata中即可。
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private InstanceRoleService instanceRoleService;

    @ResponseBody
    @PostMapping("")
    public Response<List<Map<String, Object>>> test(){
        Response<List<Map<String, Object>>> response = null;
        try {
            List<Map<String, Object>> list = new ArrayList<>();

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
                    Map<String, Object> map = new HashMap<>();
                    map.put("appName", service.toUpperCase());
                    map.put("instanceId", instanceId);
                    list.add(map);
                }
            }
            response = ResponseHelper.createSuccessResponse(list);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
