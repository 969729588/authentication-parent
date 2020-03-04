package com.milepost.authenticationExample.schedulerLock.controller;

import com.milepost.core.lock.InstanceRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Controller
@RequestMapping("/testInstanceRoleServiceController")
public class TestInstanceRoleServiceController {

    /**
     * 注入InstanceRoleService
     */
    @Autowired
    private InstanceRoleService instanceRoleService;

    @ResponseBody
    @GetMapping("/isMaster")
    public Boolean isMaster(@RequestParam(value = "appName", required = false) String appName,
                                       @RequestParam(value = "instanceId", required = false) String instanceId) {
        Boolean isMaster = null;
        if(StringUtils.isNotBlank(appName) && StringUtils.isNotBlank(instanceId)){
            //获取指定服务，指定实例的角色
            isMaster = instanceRoleService.isMaster(appName, instanceId);
        }else{
            //获取当前服务，当前实例的角色
            isMaster = instanceRoleService.isMaster();
        }
        return isMaster;
    }
}
