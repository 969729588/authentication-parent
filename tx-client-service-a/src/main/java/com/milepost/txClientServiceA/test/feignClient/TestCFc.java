package com.milepost.txClientServiceA.test.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient("tx-client-service-c")//获取配置文件中的service服务名称
public interface TestCFc {

    @RequestMapping("tx-client-service-c/testLcn/test1")
    String callC(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);
}
