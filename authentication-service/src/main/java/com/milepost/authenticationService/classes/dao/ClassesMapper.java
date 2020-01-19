package com.milepost.authenticationService.classes.dao;

import com.milepost.authenticationService.classes.entity.Classes;
import com.milepost.authenticationService.classes.entity.ClassesExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2018-11-28.
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes, ClassesExample> {

}
