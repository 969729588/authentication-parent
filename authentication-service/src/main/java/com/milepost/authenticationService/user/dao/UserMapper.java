package com.milepost.authenticationService.user.dao;

import com.milepost.authenticationApi.entity.user.User;
import com.milepost.authenticationApi.entity.user.UserExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/1/31.
 */
@Mapper
public interface UserMapper extends BaseMapper<User, UserExample>{

}
