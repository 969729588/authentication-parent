package com.milepost.txClientServiceC.person.dao;

import com.milepost.service.mybatis.dao.BaseMapper;
import com.milepost.txClientServiceC.person.entity.Person;
import com.milepost.txClientServiceC.person.entity.PersonExample;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/1/17.
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person, PersonExample>{

}
