package com.milepost.authenticationService.test.controller;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationApi.entity.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */
@Controller
@RequestMapping("/testParam")
public class TestParamController {

    private Logger logger = LoggerFactory.getLogger(TestParamController.class);

    private static final String CALL_SUCCESS = "调用成功";

    @ResponseBody
    @PostMapping("/listMap")
    public Response<List<Map<String, Object>>> listMap(@RequestBody List<Map<String, Object>> mapList) {
        Response<List<Map<String, Object>>> response = null;
        try {
            for(Map map : mapList){
                System.out.println(map);
            }
            response = ResponseHelper.createSuccessResponse(mapList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/listPojo")
    public Response<List<Person>> listPojo(@RequestBody List<Person> personList) {
        Response<List<Person>> response = null;
        try {
            for(Person person : personList){
                System.out.println(person);
            }
            response = ResponseHelper.createSuccessResponse(personList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/complexPojo")
    public Response<Person> complexPojo(@RequestBody Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = ResponseHelper.createSuccessResponse(person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/map")
    public Response<Map<String, Object>> map(@RequestBody Map<String, Object> map) {
        Response<Map<String, Object>> response = null;
        try {
            System.out.println("map: " + map);
            response = ResponseHelper.createSuccessResponse(map);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * pojo前面不加@RequestParam注解
     * @param person
     * @return
     */
    @ResponseBody
    @PostMapping("/pojo")
    public Response<Person> pojo(@RequestBody Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = ResponseHelper.createSuccessResponse(person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }


    @ResponseBody
    @PostMapping("/basicTypes")
    public Response<String> basicTypes(@RequestParam("name") String name, @RequestParam("count") int count, @RequestParam Integer age) {
        Response<String> response = null;
        try {
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("age: " + age);
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @GetMapping("/pathVariable/{id}")
    public Response<String> pathVariable(@PathVariable("id") Integer id){
        Response<String> response = null;
        try {
            System.out.println("id: " + id);
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/cookieValue")
    public Response<String> cookieValue(@RequestParam("JSESSIONID") String sessionId){
        Response<String> response = null;
        try {
            System.out.println("sessionId: " + sessionId);
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/requestHeader")
    public Response<String> requestHeader(@RequestHeader(value="accept") String accept){
        Response<String> response = null;
        try {
            System.out.println("accept: " + accept);
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }


    @ResponseBody
    @PostMapping("/servlet")
    public Response<String> servlet(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        Response<String> response1 = null;
        try {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                System.out.println(headerName + ":" + request.getHeader(headerName));
            }

            System.out.println(response.toString());


            System.out.println(session.getId());
            response1 = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response1 = ResponseHelper.createExceptionResponse(e);
        }
        return response1;
    }
}
