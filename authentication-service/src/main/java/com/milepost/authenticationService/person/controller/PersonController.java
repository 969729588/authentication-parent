package com.milepost.authenticationService.person.controller;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationApi.entity.person.Person;
import com.milepost.authenticationApi.entity.person.PersonExample;
import com.milepost.authenticationService.person.service.PersonService;
import com.milepost.authenticationService.student.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
    public Response<PageInfo<Person>> list(PageParameter pageParameter, @ApiIgnore Student example) {
        Response<PageInfo<Person>> response = null;
        try{
            PersonExample personExample = new PersonExample();
            personExample.or().andFirstNameLike("%%");
            PageInfo<Person> pageInfo = personService.selectByExampleForPageInfo(personExample, pageParameter);
            response = ResponseHelper.createSuccessResponse(pageInfo);
        } catch (Exception e) {
            response = ResponseHelper.createExceptionResponse(e);
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @PutMapping
    public Response<Person> update(Person record) {
        Response<Person> response = null;
        try{
            int effectRow = personService.updateByPrimaryKey(record);
            if(effectRow > 0){
                response = ResponseHelper.createSuccessResponse(record);
            }else {
                response = ResponseHelper.createFailResponse();
            }
        } catch (Exception e) {
            response = ResponseHelper.createExceptionResponse(e);
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
