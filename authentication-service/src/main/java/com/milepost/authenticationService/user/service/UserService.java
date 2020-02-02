package com.milepost.authenticationService.user.service;

import com.milepost.api.util.MD5Util;
import com.milepost.authenticationApi.entity.user.User;
import com.milepost.authenticationApi.entity.user.UserExample;
import com.milepost.authenticationService.user.dao.UserMapper;
import com.milepost.service.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2020/1/31.
 */
@Service
public class UserService extends BaseService<User, UserExample>{
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User getUserByUsernameAndPassword(String username, String password) throws Exception {
        UserExample ex = new UserExample();
        String md5Password = MD5Util.string2MD5(password);
        ex.or().andUsernameEqualTo(username).andPasswordEqualTo(md5Password);
        User user = super.selectOneByExample(ex);
        return user;
    }

}
