package com.milepost.authenticationUi.user.feignClient;

import com.milepost.authenticationApi.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/29.
 */
@FeignClient(contextId = "userFc", name = "${info.app.service.name}")
public interface UserFc {

    @GetMapping("${info.app.service.prefix}/user/getUserByUsernameAndPassword")
    User getUserByUsernameAndPassword(@RequestHeader(value = "Authorization") String authorization,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password);
}
