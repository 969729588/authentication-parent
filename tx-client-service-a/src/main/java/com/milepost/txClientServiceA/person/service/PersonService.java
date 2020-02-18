package com.milepost.txClientServiceA.person.service;

import com.milepost.api.util.DataUUIDUtil;
import com.milepost.service.mybatis.service.BaseService;
import com.milepost.txClientServiceA.person.dao.PersonMapper;
import com.milepost.txClientServiceA.person.entity.Person;
import com.milepost.txClientServiceA.person.entity.PersonExample;
import com.milepost.txClientServiceA.test.feignClient.TestBFc;
import com.milepost.txClientServiceA.test.feignClient.TestCFc;
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
    private TestBFc testBFc;

    @Autowired
    private TestCFc testCFc;

    @Autowired
    private PersonMapper personMapper;

    //只需要一个注解，标注在service的方法上即可
    @GlobalTransactional
    public void test1(String param) {
        System.out.println("收到参数：" + param);

        testBFc.callB(param);
        System.out.println("-----------");
        testCFc.callC(param);

        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("aaa");
        personMapper.insert(person);

        int i = 1/0;
    }

    public void test2(String param) {
        System.out.println("收到参数：" + param);
        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("111");
        personMapper.insert(person);
    }
}
