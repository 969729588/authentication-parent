package com.milepost.authenticationService.user.controller;

import com.milepost.authenticationApi.entity.user.User;
import com.milepost.authenticationService.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Ruifu Hua on 2020/1/31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getUserByUsernameAndPassword")
    public User getUserByUsernameAndPassword(@RequestParam("username") String username,
                                             @RequestParam("password") String password){
        User user = null;
        try {
            user = userService.getUserByUsernameAndPassword(username, password);
            System.out.println(new Date());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return user;
    }
}
