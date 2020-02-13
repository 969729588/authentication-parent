package com.milepost.txClientServiceC.test.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient("tx-client-service-b")//获取配置文件中的service服务名称
public interface TestBFc {

    @RequestMapping("tx-client-service-b/test/test1")
    String callB(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);
}
