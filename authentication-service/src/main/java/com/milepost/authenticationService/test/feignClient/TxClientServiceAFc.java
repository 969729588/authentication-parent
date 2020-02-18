package com.milepost.authenticationService.test.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/2/18.
 */
@FeignClient("tx-client-service-a")
public interface TxClientServiceAFc {
    @RequestMapping("tx-client-service-a/testSeata/test2")
    String callA(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);
}
