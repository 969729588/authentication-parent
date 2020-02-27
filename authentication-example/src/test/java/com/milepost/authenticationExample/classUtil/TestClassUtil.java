package com.milepost.authenticationExample.classUtil;

import com.milepost.api.util.ClassFinderUtil;
import com.milepost.authenticationExample.getBeans.SomeBean;
import com.milepost.authenticationExample.getBeans.SomeBeanImpl3;
import org.apache.commons.lang.ClassUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ruifu Hua on 2020/2/26.
 */
public class TestClassUtil {

    @Test
    public void test1(){
        List allSuperclasses = ClassUtils.getAllSuperclasses(SomeBeanImpl3.class);
        for(Object object : allSuperclasses){
            System.out.println(object);
        }
        List allInterfaces = ClassUtils.getAllInterfaces(SomeBeanImpl3.class);
        for(Object object : allInterfaces){
            System.out.println(object);
        }

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        List<Class> subClass = ClassFinderUtil.getSubClass(SomeBean.class, new String[]{"com.milepost.authenticationExample.test.getBeans"});
        for(Class clazz : subClass){
            System.out.println(clazz.getName());
        }
        System.out.println("---");
        List<Class> subClass1 = ClassFinderUtil.getSubClass(SomeBean.class, new String[]{"com.milepost.authenticationExample.test"});
        for(Class clazz : subClass1){
            System.out.println(clazz.getName());
        }
    }
}
