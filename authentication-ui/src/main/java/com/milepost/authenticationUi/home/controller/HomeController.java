package com.milepost.authenticationUi.home.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.authenticationUi.auth.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/6.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private LoginController loginController;

    /**
     * 显示首页，
     * @return
     */
    @GetMapping("/")
    public String toHome_(){
        return "redirect:/home";
    }

    /**
     * 显示首页，
     * 系统在使用过程中，浏览器地址栏始终保持这一个地址，永不改变，
     * @return
     */
    @GetMapping("")
    public String toHome(){
        return "forward:/home/home.html";
    }

    /**
     * 暴露实例元数据
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public Response<Map<String, Object>> exposeMetadata(){
        return loginController.exposeMetadata();
    }
}
