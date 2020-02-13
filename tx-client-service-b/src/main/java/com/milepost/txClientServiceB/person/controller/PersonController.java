package com.milepost.txClientServiceB.person.controller;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.txClientServiceB.person.entity.Person;
import com.milepost.txClientServiceB.person.entity.PersonExample;
import com.milepost.txClientServiceB.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonService personService;

    @GetMapping
    public Response<PageInfo<Person>> list(PageParameter pageParameter) {
        Response<PageInfo<Person>> response = null;
        try{
            PersonExample personExample = new PersonExample();
            personExample.or().andFirstNameLike("%%");
            PageInfo<Person> pageInfo = personService.selectByExampleForPageInfo(personExample, pageParameter);
            response = ResponseHelper.createSuccessResponse(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
