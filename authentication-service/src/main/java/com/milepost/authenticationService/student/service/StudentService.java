package com.milepost.authenticationService.student.service;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.authenticationService.student.dao.StudentMapper;
import com.milepost.authenticationService.student.entity.Student;
import com.milepost.authenticationService.student.entity.StudentExample;
import com.milepost.service.config.dynamicDs.DataSourceContextHolder;
import com.milepost.service.mybatis.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2018-11-27.
 */
@Service
public class StudentService extends BaseService<Student, StudentExample> {
    @Autowired
    private StudentMapper studentMapper;

    public void testTransactional1(Student record, Student record2) {
        System.out.println(insert(record));
        System.out.println(insert(record));
        //手动回滚，无论本事物对数据库做出任何操作，或者是否抛出异常，本事物中的所有操作都将回滚，还可以设置回滚点等，
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        System.out.println(insert(record2));
    }

    public void testTransactional(Student record, Student record2) {
        System.out.println(insert(record));
        @SuppressWarnings("unused")
        int i= 3/0;
        System.out.println(insert(record2));
    }

    public void testFirstLevelCache(String id) {
        Student student1 = studentMapper.selectByPrimaryKey(id);
        System.out.println(student1);
        Student student2 = studentMapper.selectByPrimaryKey(id);
        System.out.println(student2);
    }

    public Student testMapUnderscoreToCamelCase(Map<String, Object> paramsMap) {
        return studentMapper.testMapUnderscoreToCamelCase(paramsMap);
    }

    public List<Student> testMultiTableSelect(Student student){
        return studentMapper.testMultiTableSelect(student);
    }


    public PageInfo<Student> list(PageParameter pageParameter, Student example) {
        StudentExample studentExample = new StudentExample();

        StudentExample.Criteria criteria = studentExample.createCriteria();

        if(StringUtils.isNotEmpty(example.getName())){
            criteria.andNameLike("%"+ example.getName() +"%");
        }
        if(StringUtils.isNotEmpty(example.getRemark())){
            criteria.andRemarkLike("%"+ example.getRemark() +"%");
        }
        if(StringUtils.isNotEmpty(example.getStuNo())){
            criteria.andStuNoLike("%"+ example.getStuNo() +"%");
        }
        if(example.getBirth() != null){
            criteria.andBirthGreaterThan(example.getBirth());
        }
        if(example.getClassesId() != null){
            criteria.andClassesIdEqualTo(example.getClassesId());
        }

        studentExample.setOrderByClause(" BIRTH ASC ");

        return this.selectByExampleForPageInfo(studentExample, pageParameter);
    }

    public void testMultiDataSourceAndTransactional(Student record) {
        DataSourceContextHolder.setDataSource("one");
        System.out.println(updateByPrimaryKeySelective(record));

        DataSourceContextHolder.setDataSource("one");
        System.out.println(updateByPrimaryKeySelective(record));
        DataSourceContextHolder.clearDataSource();

        DataSourceContextHolder.setDataSource("two");
        System.out.println(updateByPrimaryKeySelective(record));
        DataSourceContextHolder.clearDataSource();

//        @SuppressWarnings("unused")
//        int i= 3/0;
    }
}
