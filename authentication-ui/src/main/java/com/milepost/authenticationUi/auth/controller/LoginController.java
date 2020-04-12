package com.milepost.authenticationUi.auth.controller;

import com.milepost.api.util.EncryptionUtil;
import com.milepost.api.util.ImgCheckCodeUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationApi.entity.auth.Jwt;
import com.milepost.authenticationApi.entity.user.User;
import com.milepost.authenticationUi.auth.feignClient.AuthFc;
import com.milepost.authenticationUi.user.feignClient.UserFc;
import com.milepost.core.multipleTenant.MultipleTenantProperties;
import feign.FeignException;
import feign.RetryableException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/1/29.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthFc authFc;

    @Autowired
    private UserFc userFc;

    @Autowired
    private MultipleTenantProperties multipleTenantProperties;

    @Autowired
    private Environment environment;

    private final static String IMG_CHECK_CODE = "imgCheckCode";

    /**
     * 显示登录页面，
     * @return
     */
    @GetMapping("/")
    public String toLogin_(){
        return "redirect:/login";
    }

    /**
     * 显示登录页面，
     * @return
     */
    @GetMapping("")
    public String toLogin(){
        return "forward:/login/login.html";
    }

    /**
     * 暴露实例元数据，配置数据等 给前端
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public Response<Map<String, Object>> exposeMetadata(){
        Response<Map<String, Object>> response = null;
        try {
            Map<String, Object> metadataMap = new HashMap<>();

            //服务根目录
            String contextPath = environment.getProperty("server.servlet.context-path");
            metadataMap.put("contextPath", contextPath);

            //当前租户
            String tenant = multipleTenantProperties.getTenant();
            metadataMap.put("tenant", tenant);

            //登录 SBA Server 的用户信息
            String loginSbaServerUser = environment.getProperty("eureka.instance.metadata-map.login_sba_server.user");
            String loginSbaServerPassword = environment.getProperty("eureka.instance.metadata-map.login_sba_server.password");
            metadataMap.put("loginSbaServerUser", loginSbaServerUser);
            metadataMap.put("loginSbaServerPassword", loginSbaServerPassword);

            response = ResponseHelper.createSuccessResponse(metadataMap);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createFailResponse();
        }
        return response;
    }

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/doLogin")
    public Response<Map<String, Object>> doLogin(   @RequestParam("username") String username,
                                                    @RequestParam("password") String password,
                                                    @RequestParam(value = "imgCheckCode", required = false) String imgCheckCode,
                                                    HttpSession session){
        Response<Map<String, Object>> response = null;
        try {
            //如果前端传入了验证码就验证，否则忽略
            if(StringUtils.isNotBlank(imgCheckCode)){
                String imgCheckCodeFromSession = (String)session.getAttribute(IMG_CHECK_CODE);
                if(imgCheckCode.equalsIgnoreCase(imgCheckCodeFromSession)){
                    //验证码通过
                    response = login(username, password);
                }else{
                    //验证码不通过
                    response = ResponseHelper.createFailResponse();
                    response.setMsg("验证码错误");
                }
            }else{
                //忽略验证码
                response = login(username, password);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
            if(e instanceof RetryableException){
                response.setMsg("调用服务超时");
            }else if(e instanceof FeignException){
                int status = ((FeignException) e).status();
                switch(status) {
                    case 400:
                        response.setMsg("密码错误");
                        break;
                    case 401:
                        response.setMsg("用户名错误");
                        break;
                    default:
                        response.setMsg("登录信息错误");
                        break;
                }
            }
        }
        return response;
    }

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    private Response<Map<String, Object>> login(String username, String password) throws UnsupportedEncodingException {
        //验证码通过
        Map<String, Object> result = new HashMap<>();
        //获取token
        String tenant = multipleTenantProperties.getTenant();
        String clientId = "client_id_" + tenant;
        String clientSecret = "client_secret_" + tenant;
        String basicAuthorization = "Basic " + EncryptionUtil.encodeWithBase64(clientId + ":" + clientSecret);//Basic Auth，即 client_id + ":" + client_secret 的base64编码
        String grantType = "password";//授权方式
        //这里可以使用OpenFeign的feign.auth.BasicAuthRequestInterceptor实现，那样会代码会更多，但可以借鉴其如何实现拦截器
        Jwt jwt = authFc.getToken(basicAuthorization, grantType, username, password);
        jwt.setBorn_time_millis(Instant.now().toEpochMilli());
        result.put("jwt", jwt);
        String token = jwt.getAccess_token();
        String tokenType = jwt.getToken_type();
        tokenType = tokenType.substring(0,1).toUpperCase() + tokenType.substring(1) + " ";
        String bearerAuthorization = tokenType + token;//Bearer
        //获取user
        User user = userFc.getUserByUsernameAndPassword(bearerAuthorization, username, password);
        user.setPassword("******");//不返回密码
        result.put("user", user);
        return ResponseHelper.createSuccessResponse(result);
    }

    /**
     * 图片验证码
     * @param response
     */
    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) {
        try {
            //禁止缓存
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            OutputStream out = response.getOutputStream();
            String imgCheckCode = ImgCheckCodeUtil.createImage(out);
            session.setAttribute(IMG_CHECK_CODE, imgCheckCode);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 判断前端的access_token是否有效，如无效则不能进入此方法，返回401，
     */
    @RequestMapping(value = "/logined", method = RequestMethod.OPTIONS)
    public String logined(){
        return "";
    }
}
