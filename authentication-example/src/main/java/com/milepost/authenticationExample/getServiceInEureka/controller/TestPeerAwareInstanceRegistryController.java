package com.milepost.authenticationExample.getServiceInEureka.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/testPeerAwareInstanceRegistry")
public class TestPeerAwareInstanceRegistryController {

    private Logger logger = LoggerFactory.getLogger(TestPeerAwareInstanceRegistryController.class);


    /**
     * PeerAwareInstanceRegistry用于在EurekaServer端获取注册到注册中心的所有服务，获取的服务与Eureka Dashboard显示的一致。
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public Response<List<Map<String, Object>>> test(){
        Response<List<Map<String, Object>>> response = null;
        try {
            List<Map<String, Object>> list = new ArrayList<>();

            //用于EurekaServer端，这里不能正常运行
            PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications = registry.getApplications();
            List<Application> registeredApplications = applications.getRegisteredApplications();
            if(registeredApplications.size() == 0){
                logger.info("PeerAwareInstanceRegistry没有发现任何服务注册到注册中心。");
            }
            for(Application application : registeredApplications){
                List<InstanceInfo> instanceInfoList = application.getInstances();
                for(InstanceInfo instanceInfo : instanceInfoList){
                    String appName = instanceInfo.getAppName();
                    String instanceId = instanceInfo.getInstanceId();
                    logger.info("PeerAwareInstanceRegistry获取到的服务--服务名称:" + appName + "; 实例ID:" + instanceId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("appName", appName);
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
