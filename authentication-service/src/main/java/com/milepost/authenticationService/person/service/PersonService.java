package com.milepost.authenticationService.person.service;

import com.milepost.authenticationService.person.entity.Person;
import com.milepost.authenticationService.person.entity.PersonExample;
import com.milepost.service.mybatis.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@Service
@Transactional
public class PersonService extends BaseService<Person, PersonExample>{

}
