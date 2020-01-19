package com.milepost.authenticationService.person.dao;

import com.milepost.authenticationService.person.entity.Person;
import com.milepost.authenticationService.person.entity.PersonExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/1/17.
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person, PersonExample>{

}
