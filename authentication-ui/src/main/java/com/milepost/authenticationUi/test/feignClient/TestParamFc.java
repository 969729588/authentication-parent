package com.milepost.authenticationUi.test.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.authenticationApi.entity.person.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/3/15.
 */
@FeignClient(value = "${info.app.service.name}")//获取配置文件中的service服务名称
@RequestMapping("${info.app.service.prefix}/testParam")
public interface TestParamFc {

    @PostMapping("/servlet")
    Response<String> servlet();

    @PostMapping("/requestHeader")
    Response<String> requestHeader();

    @PostMapping("/cookieValue")
    Response<String> cookieValue(@RequestParam("JSESSIONID") String sessionId);

    @GetMapping("/pathVariable/{id}")
    Response<String> pathVariable(@PathVariable("id") Integer id);

    @PostMapping("/basicTypes")
    Response<String> basicTypes(@RequestParam("name") String name, @RequestParam("count") int count, @RequestParam("age") Integer age);

    /**
     * Feign传递pojo时，只能使用@RequestBody注解，不能像SpringMVC那样不使用任何注解传递pojo。
     * @param person
     * @return
     */
    @PostMapping("/pojo")
    Response<Person> pojo(@RequestBody Person person);

    @PostMapping("/map")
    Response<Map<String,Object>> map(@RequestBody Map<String, Object> map);

    @PostMapping("/complexPojo")
    Response<Person> complexPojo(@RequestBody Person person);

    @PostMapping("/listPojo")
    Response<List<Person>> listPojo(@RequestBody List<Person> personList);

    @PostMapping("/listMap")
    Response<List<Map<String,Object>>> listMap(@RequestBody List<Map<String, Object>> mapList);

}
