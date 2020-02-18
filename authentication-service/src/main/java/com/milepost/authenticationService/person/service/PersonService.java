package com.milepost.authenticationService.person.service;

import com.milepost.api.util.DataUUIDUtil;
import com.milepost.authenticationService.person.dao.PersonMapper;
import com.milepost.authenticationService.person.entity.Person;
import com.milepost.authenticationService.person.entity.PersonExample;
import com.milepost.authenticationService.test.feignClient.TxClientServiceAFc;
import com.milepost.service.mybatis.service.BaseService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@Service
@Transactional
public class PersonService extends BaseService<Person, PersonExample>{

    @Autowired
    private TxClientServiceAFc txClientServiceAFc;

    @Autowired
    private PersonMapper personMapper;

    //只需要一个注解，标注在service的方法上即可
    @GlobalTransactional
    public void test1(String param) {
        System.out.println("收到参数：" + param);

        txClientServiceAFc.callA(param);
        System.out.println("-----------");

        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("000");
        personMapper.insert(person);

        int i = 1/0;
    }

}
