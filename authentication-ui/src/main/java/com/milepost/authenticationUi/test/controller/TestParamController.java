package com.milepost.authenticationUi.test.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationApi.entity.person.Person;
import com.milepost.authenticationUi.test.feignClient.TestParamFc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */
@Controller
@RequestMapping("/testParam")
public class TestParamController {

    @Autowired
    private TestParamFc testParamFc;

    private Logger logger = LoggerFactory.getLogger(TestParamController.class);

    private static final String CALL_SUCCESS = "调用成功";

    /**
     * @param paramList
     * @return
     */
    @ResponseBody
    @PostMapping("/list")
    public Response<List<String>> list(@RequestParam("paramList[]")  List<String> paramList) {
        Response<List<String>> response = null;
        try {
            for(String param : paramList){
                System.out.println(param);
            }
            response = ResponseHelper.createSuccessResponse(paramList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param paramArray
     * @return
     */
    @ResponseBody
    @PostMapping("/array")
    public Response<String[]> array(@RequestParam("paramArray[]") String[] paramArray) {
        Response<String[]> response = null;
        try {
            for(String param : paramArray){
                System.out.println(param);
            }
            response = ResponseHelper.createSuccessResponse(paramArray);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param mapList
     * @return
     */
    @ResponseBody
    @PostMapping("/listMap")
    public Response<List<Map<String, Object>>> listMap(@RequestBody List<Map<String, Object>> mapList) {
        Response<List<Map<String, Object>>> response = null;
        try {
            for(Map map : mapList){
                System.out.println(map);
            }
            response = testParamFc.listMap(mapList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param personList
     * @return
     */
    @ResponseBody
    @PostMapping("/listPojo")
    public Response<List<Person>> listPojo(@RequestBody List<Person> personList) {
        Response<List<Person>> response = null;
        try {
            for(Person person : personList){
                System.out.println(person);
            }
            response = testParamFc.listPojo(personList);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param person
     * @return
     */
    @ResponseBody
    @PostMapping("/complexPojo2")
    public Response<Person> complexPojo2(@RequestBody Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = testParamFc.complexPojo(person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param person
     * @return
     */
    @ResponseBody
    @PostMapping("/complexPojo1")
    public Response<Person> complexPojo(Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = testParamFc.complexPojo(person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/map2")
    public Response<Map<String, Object>> map2(@RequestBody Map<String, Object> map) {
        Response<Map<String, Object>> response = null;
        try {
            System.out.println("map: " + map);
            response = testParamFc.map(map);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * map前面要加@RequestParam注解
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/map1")
    public Response<Map<String, Object>> map1(@RequestParam Map<String, Object> map) {
        Response<Map<String, Object>> response = null;
        try {
            System.out.println("map: " + map);
            response = testParamFc.map(map);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * 入参当中，不能有多余一个参数被标@RequestBody注解，因为只有一个请求体
     * @param person
     * @return
     */
    @ResponseBody
    @PostMapping("/pojo2")
    public Response<Person> pojo2(@RequestBody Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = testParamFc.pojo(person);
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
    @PostMapping("/pojo1")
    public Response<Person> pojo1(Person person) {
        Response<Person> response = null;
        try {
            System.out.println("person: " + person);
            response = testParamFc.pojo(person);
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
            response = testParamFc.basicTypes(name, count, age);
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
            response = testParamFc.pathVariable(id);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @GetMapping("/forward")
    public String forward(){
        System.out.println("forward");
        return "forward:/testParam/testRedirect";
    }

    @PostMapping("/redirect")
    public String redirect(){
        System.out.println("redirect");
        return "redirect:/testParam/testRedirect";
    }

    @ResponseBody
    @GetMapping("/testRedirect")
    public Response<String> testRedirect(){
        System.out.println("testRedirect");
        Response<String> response = ResponseHelper.createSuccessResponse("testRedirect");
        return response;
    }

    @ResponseBody
    @PostMapping("/cookieValue")
    public Response<String> cookieValue(@CookieValue("JSESSIONID") String sessionId){
        Response<String> response = null;
        try {
            System.out.println("sessionId: " + sessionId);
            response = testParamFc.cookieValue(sessionId);
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
            response = testParamFc.requestHeader();
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
            response1 = testParamFc.servlet();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response1 = ResponseHelper.createExceptionResponse(e);
        }
        return response1;
    }
}
