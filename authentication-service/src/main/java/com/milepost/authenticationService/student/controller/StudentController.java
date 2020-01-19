package com.milepost.authenticationService.student.controller;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.api.util.DataUUIDUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationService.student.entity.Student;
import com.milepost.authenticationService.student.service.StudentService;
import com.milepost.core.spring.ApplicationContextProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Ruifu Hua on 2018-11-26.
 */
@RestController
@RequestMapping("/student")
@Api(description = "学生")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentService studentService;

    @GetMapping("/testLog")
    public String testLog(String p1){

        logger.trace("接收到p1=" + p1);
        logger.debug("接收到p1=" + p1);
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("接收到p1=" + p1);
        logger.warn("接收到p1=" + p1);
        logger.error("接收到p1=" + p1);

        return  p1+"123";
    }

    /**
     * http://192.168.223.1:9995/authentication-service/student/
     * @param pageParameter
     * @param example
     * @return
     */
    @ApiOperation(value = "列表", notes = "获取学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stuNo", value = "学号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "remark", value = "评价", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classesId", value = "班级id", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping
    public Response<PageInfo<Student>> list(PageParameter pageParameter, @ApiIgnore Student example) {
        Response<PageInfo<Student>> response = null;
        try{
            PageInfo<Student> pageInfo = studentService.list(pageParameter, example);
            response = ResponseHelper.createSuccessResponse(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @ApiOperation(value = "添加", notes = "添加学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "stuNo", value = "学号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "分数", required = false, paramType = "query", dataType = "Float"),
            @ApiImplicitParam(name = "remark", value = "评价", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classesId", value = "班级id", required = false, paramType = "query", dataType = "String")
    })
    @PostMapping
    public Response<Student> add(@ApiIgnore Student record){
        Response<Student> response = null;
        try{

            record.setId(DataUUIDUtil.randomUUID());
            int affectedRow = studentService.insertSelective(record);
            if(affectedRow > 0){
                response = ResponseHelper.createSuccessResponse(record);
                response.setMsg("添加成功");
            }else{
                response = ResponseHelper.createFailResponse();
                response.setMsg("添加失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ApiOperation(value = "修改", notes = "修改学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "stuNo", value = "学号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "分数", required = false, paramType = "query", dataType = "Float"),
            @ApiImplicitParam(name = "remark", value = "评价", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classesId", value = "班级id", required = false, paramType = "query", dataType = "String")
    })
    @PutMapping
    public Response<Student> update(@ApiIgnore Student record){
        Response<Student> response = null;
        try{
            int affectedRow = studentService.updateByPrimaryKeySelective(record);
            if(affectedRow > 0){
                response = ResponseHelper.createSuccessResponse(record);
                response.setMsg("修改成功");
            }else{
                response = ResponseHelper.createFailResponse();
                response.setMsg("修改失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ApiOperation(value = "删除", notes = "删除学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @DeleteMapping(value = "/{id}")
    public Response<Student> delete(@PathVariable("id") String id){
        Response<Student> response = null;
        try{
            int affectedRow = studentService.deleteByPrimaryKey(id);
            if(affectedRow > 0){
                response = ResponseHelper.createSuccessResponse();
                response.setMsg("删除成功");
            }else{
                response = ResponseHelper.createFailResponse();
                response.setMsg("删除失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/{id}")
    public Response<Student> get(@PathVariable("id") String id){
        Response<Student> response = null;
        try{
            Student student = studentService.selectByPrimaryKey(id);
            response = ResponseHelper.createSuccessResponse(student);
            response.setMsg("查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ApiOperation(value = "测试SpringContextUtil", notes = "测试SpringContextUtil")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/testSpringContextUtil")
    public Response<?> testSpringContextUtil(){
        Response<?> response = null;
        try{
            System.out.println(studentService.toString());
            StudentService studentService2 = ApplicationContextProvider.getBean("studentService");
            System.out.println(studentService2.toString());
            response = ResponseHelper.createSuccessResponse(studentService.toString() + "," + studentService2.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @GetMapping(value = "/testTransactional")
    public Response<?> testTransactional(){
        Response<?> response = null;
        try{
            Student record1 = new Student();
            record1.setId(DataUUIDUtil.randomUUID());
            record1.setName("11");

            Student record2 = new Student();
            record2.setId(DataUUIDUtil.randomUUID());
            record2.setName("22");

            studentService.testTransactional(record1, record2);
            response = ResponseHelper.createSuccessResponse("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

}
