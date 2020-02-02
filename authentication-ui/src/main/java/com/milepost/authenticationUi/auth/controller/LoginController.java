package com.milepost.authenticationUi.auth.controller;

import com.milepost.api.util.EncryptionUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationApi.entity.auth.Jwt;
import com.milepost.authenticationApi.entity.user.User;
import com.milepost.authenticationUi.auth.feignClient.AuthFc;
import com.milepost.authenticationUi.user.feignClient.UserFc;
import com.milepost.core.multipleTenant.MultipleTenantProperties;
import com.sun.javafx.tk.TKClipboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/1/29.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthFc authFc;

    @Autowired
    private UserFc userFc;

    @Autowired
    private MultipleTenantProperties multipleTenantProperties;

    @PostMapping("")
    public Response<Map<String, Object>> login(@RequestParam("username") String username,
                                               @RequestParam("password") String password){
        Response<Map<String, Object>> response = null;
        try {
            Map<String, Object> result = new HashMap<>();
            //获取token
            String tenant = multipleTenantProperties.getTenant();
            String clientId = "client_id_" + tenant;
            String clientSecret = "client_secret_" + tenant;
            String basicAuthorization = "Basic " + EncryptionUtil.encodeWithBase64(clientId + ":" + clientSecret);//Basic Auth，即 client_id + ":" + client_secret 的base64编码
            String grantType = "password";//授权方式
            Jwt jwt = authFc.getToken(basicAuthorization, grantType, username, password);
            result.put("jwt", jwt);
            result.put("jwt", jwt);
            String token = jwt.getAccess_token();
            String tokenType = jwt.getToken_type();
            tokenType = tokenType.substring(0,1).toUpperCase() + tokenType.substring(1) + " ";
            String bearerAuthorization = tokenType + token;//Bearer
            //获取user
            User user = userFc.getUserByUsernameAndPassword(bearerAuthorization, username, password);
            user.setPassword(password);
            result.put("user", user);

            response = ResponseHelper.createSuccessResponse(result);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

}
