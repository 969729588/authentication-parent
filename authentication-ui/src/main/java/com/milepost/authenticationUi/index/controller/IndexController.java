package com.milepost.authenticationUi.index.controller;

import com.milepost.api.enums.MilepostApplicationType;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.core.multipleTenant.MultipleTenantProperties;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/19.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClient eurekaClient;

    //获取当前租户
    @Autowired
    private MultipleTenantProperties multipleTenantProperties;

    /**
     * 认证UI的服务名称
     */
    private final static String AUTHENTICATION_UI = "authentication-ui";
    /**
     * 认证Service的服务名称
     */
    private final static String AUTHENTICATION_SERVICE = "authentication-service";

    /**
     *
     */
//    @ResponseBody
//    @GetMapping("/serviceInstance")
//    public Response<List<Map<String, String>>> getServiceInstance(){
//        Response<List<Map<String, String>>> response = null;
//        try {
//            List<Map<String, String>> instanceInfoList = new ArrayList<>();
//            String tenant = multipleTenantProperties.getTenant();
//
//            Applications applications = eurekaClient.getApplications();
//            List<Application> registeredApplications = applications.getRegisteredApplications();
//            for(Application application : registeredApplications){
//                String appName = application.getName();
//                //不显示认证UI和认证Service
//                if(appName.equalsIgnoreCase(AUTHENTICATION_UI) || appName.equalsIgnoreCase(AUTHENTICATION_SERVICE)){
//                    continue;
//                }
//                List<InstanceInfo> instancesAsIsFromEureka = application.getInstancesAsIsFromEureka();
//                for(InstanceInfo instanceInfo : instancesAsIsFromEureka){
//                    Map<String, String> metadata = instanceInfo.getMetadata();
//                    String thisTenant = metadata.get("tenant");
//                    if(StringUtils.isBlank(thisTenant)){
//                        //分布式事务服务端
//                        continue;
//                    }
//                    //过滤出当前租户下的（租户是大小写不敏感的）
//                    if(thisTenant.equalsIgnoreCase(tenant)){
//                        Map<String, String> map = new HashMap<>();
//                        map.put("instanceId", instanceInfo.getInstanceId());//实例id
//                        map.put("appName", instanceInfo.getAppName());//服务(应用)名称，spring.application.name
//                        String appType = metadata.get("milepost-type");//应用类型
//                        map.put("appType", appType);
//                        map.put("name", metadata.get("name"));//info.app.name
//                        map.put("description", metadata.get("description"));//info.app.description
//                        map.put("version", metadata.get("version"));//版本
//                        if(appType.equalsIgnoreCase(MilepostApplicationType.UI.getValue())){
//                            map.put("url", instanceInfo.getHomePageUrl() + "/index");//首页url
//                        }else{
//                            map.put("url", instanceInfo.getStatusPageUrl());//info页面
//                        }
//                        map.put("contextPath", metadata.get("context-path"));//服务context-path
//                        map.put("tenant", thisTenant);//租户
//
//                        instanceInfoList.add(map);
//                    }
//                }
//            }
//            response = ResponseHelper.createSuccessResponse(instanceInfoList);
//        }catch (Exception e){
//            logger.error(e.getMessage(), e);
//            response = ResponseHelper.createFailResponse();
//        }
//        return response;
//    }

    /**
     * 使用discoveryClient和eurekaClient获取服务都有延时，
     * 最大延时时间为：
     * EurekaServer端的eureka.server.response-cache-update-interval-ms与
     * EurekaClient端的eureka.client.registry-fetch-interval-seconds之和
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/serviceInstance")
    public Response<List<Map<String, String>>> getServiceInstance(){
        Response<List<Map<String, String>>> response = null;
        try {
            List<Map<String, String>> instanceInfoList = new ArrayList<>();
            String tenant = multipleTenantProperties.getTenant();

            //获取所有服务
            List<String> serviceList = discoveryClient.getServices();
            for(String service : serviceList){
                //不显示认证UI和认证Service
                if(service.equalsIgnoreCase(AUTHENTICATION_UI) || service.equalsIgnoreCase(AUTHENTICATION_SERVICE)){
                    continue;
                }
                //根据服务Id(就是应用名称)获取所有的服务实例
                List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(service);
                for(ServiceInstance serviceInstance : serviceInstanceList){
                    InstanceInfo instanceInfo = ((EurekaDiscoveryClient.EurekaServiceInstance) serviceInstance).getInstanceInfo();
                    Map<String, String> metadata = instanceInfo.getMetadata();
                    String thisTenant = metadata.get("tenant");
                    //过滤出当前租户下的（租户是大小写不敏感的）
                    if(thisTenant.equalsIgnoreCase(tenant)){
                        Map<String, String> map = new HashMap<>();
                        map.put("instanceId", instanceInfo.getInstanceId());//实例id
                        map.put("appName", instanceInfo.getAppName());//服务(应用)名称，spring.application.name
                        String appType = metadata.get("milepost-type");//应用类型
                        map.put("appType", appType);
                        map.put("name", metadata.get("name"));//info.app.name
                        map.put("description", metadata.get("description"));//info.app.description
                        map.put("version", metadata.get("version"));//版本
                        if(appType.equalsIgnoreCase(MilepostApplicationType.UI.getValue())){
                            map.put("url", instanceInfo.getHomePageUrl() + "/index");//首页url
                        }else{
                            map.put("url", instanceInfo.getStatusPageUrl());//info页面
                        }
                        map.put("contextPath", metadata.get("context-path"));//服务context-path
                        map.put("tenant", thisTenant);//租户
                        map.put("weight", metadata.get("weight"));//权重
                        if(metadata.containsKey("label-or")){
                            map.put("labelOr", metadata.get("label-or"));//或标签
                        }else{
                            map.put("labelOr", "未设置");//或标签
                        }
                        if(metadata.containsKey("label-and")){
                            map.put("labelAnd", metadata.get("label-and"));//与标签
                        }else{
                            map.put("labelAnd", "未设置");//与标签
                        }
                        map.put("trackSampling", metadata.get("track-sampling"));//跟踪采样率

                        instanceInfoList.add(map);
                    }
                }
            }
            response = ResponseHelper.createSuccessResponse(instanceInfoList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
