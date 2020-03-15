package com.milepost.authenticationUi.test.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient(value = "${info.app.service.name}")//获取配置文件中的service服务名称
public interface TestFc {

    @GetMapping("${info.app.service.prefix}/test/testManualToken")
    Response<String> testManualToken(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);

    @GetMapping("${info.app.service.prefix}/test/testDistTransaction")
    String testDistTransaction(@RequestParam("param") String param);
}
