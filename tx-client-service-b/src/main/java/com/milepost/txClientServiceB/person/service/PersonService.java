package com.milepost.txClientServiceB.person.service;

import com.milepost.service.mybatis.service.BaseService;
import com.milepost.txClientServiceB.person.dao.PersonMapper;
import com.milepost.txClientServiceB.person.entity.Person;
import com.milepost.txClientServiceB.person.entity.PersonExample;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@Service
public class PersonService extends BaseService<Person, PersonExample>{

    @Autowired
    private PersonMapper personMapper;

    public void test1(Person person) {
        personMapper.insert(person);
    }

}
