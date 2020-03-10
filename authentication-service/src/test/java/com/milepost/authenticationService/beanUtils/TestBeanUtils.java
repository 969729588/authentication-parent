package com.milepost.authenticationService.beanUtils;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.milepost.api.util.ReadAppYml;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/2/13.
 */
public class TestBeanUtils {

    @Test
    public void test2(){
        Map<String, String> envMap = System.getenv();
        //java.lang.UnsupportedOperationException
        envMap.put("test", "123");

        System.out.println(System.getenv("test"));
    }


    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException {
        String key0 = "spring.datasource.druid.one";
        String key1 = "spring.datasource.druid.one.password";
        String key2 = "spring.datasource.druid";
        Map<String, Object> one = ReadAppYml.getMap(key0);
        Map<String, Object> druid = ReadAppYml.getMap(key2);

        System.out.println(one);
        System.out.println(ReadAppYml.getValue(key1));
        List<String> multipleDsKeys = new ArrayList<>();
        for(Map.Entry<String, Object> entry : druid.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof Map){
                multipleDsKeys.add(key);
            }
        }
        System.out.println(multipleDsKeys);

        for(String multipleDsKey : multipleDsKeys){
            Map<String, Object> multipleDsPro = ReadAppYml.getMap("spring.datasource.druid." + multipleDsKey);
            DataSource dataSource = DruidDataSourceBuilder.create().build();
            BeanUtils.populate(dataSource, multipleDsPro);
            System.out.println(dataSource);
        }
    }
}
