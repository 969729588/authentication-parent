package com.milepost.authenticationService.multipleDs;

import com.milepost.authenticationService.AuthenticationServiceApplication;
import com.milepost.authenticationService.student.entity.Student;
import com.milepost.authenticationService.student.service.StudentService;
import com.milepost.service.config.dynamicDs.DataSourceContextHolder;
import com.milepost.test.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/2/13.
 */
public class TestMultipleDs extends BaseTest<AuthenticationServiceApplication> {

    private StudentService studentService;
    private DataSource mainDs;
    private DataSource milepostRoutingDataSource;

    /**
     * 获取要测试的bean
     */
    @Before
    public void before(){
        mainDs = getBean("mainDs", DataSource.class);
        milepostRoutingDataSource = getBean(DataSource.class);
        studentService = getBean(StudentService.class);
    }

    /**
     * 测试后的收尾
     */
    @After
    public void after(){
        System.out.println("测试后的收尾");
    }

    @Test
    public void testMultiDataSource(){
        System.out.println(mainDs);
        System.out.println(milepostRoutingDataSource);

        getStudent();
        DataSourceContextHolder.setDataSource("one");
        getStudent();
        DataSourceContextHolder.clearDataSource();
        DataSourceContextHolder.setDataSource("two");
        getStudent();
        DataSourceContextHolder.clearDataSource();
        getStudent();
    }

    public Student getStudent() {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("id", "11");
        //paramsMap.put("name", "1");
        paramsMap.put("score", 11f);
        Student student = studentService.testMapUnderscoreToCamelCase(paramsMap);
        System.out.println(student.getName());

        return student;
    }

    /**
     * 一定要在调用service方法之前设置数据源，service方法内设置无效。
     */
    @Test
    public void testMultiDataSourceAndTransactional(){
        Student student = new Student();
        student.setId("11");
        student.setRemark("03");
        DataSourceContextHolder.setDataSource("one");
        studentService.testMultiDataSourceAndTransactional(student);
    }
}
