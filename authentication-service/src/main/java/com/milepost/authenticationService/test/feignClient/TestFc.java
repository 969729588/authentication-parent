package com.milepost.authenticationService.test.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient("other-service")//获取配置文件中的service服务名称
public interface TestFc {

    @RequestMapping("other-service/test/test3")
    String test3(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);

}
