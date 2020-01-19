package com.milepost.authenticationService.student.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2018-12-11.
 */
@RestController
@RequestMapping("/testGlobalExceptionHandler")
@Api(description = "测试全局的异常处理")
public class TestGlobalExceptionHandlerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/test1")
    public Response<?> test1(Model model){
        int i = 1/0;
        return ResponseHelper.createSuccessResponse();
    }

    @GetMapping("/test2")
    public Response<?> test2(Model model){

        try {
            int i = 1/0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ResponseHelper.createSuccessResponse();
    }
}
