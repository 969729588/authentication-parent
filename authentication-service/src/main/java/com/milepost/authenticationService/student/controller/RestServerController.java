package com.milepost.authenticationService.student.controller;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.api.util.DataUUIDUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationService.student.entity.Student;
import com.milepost.authenticationService.student.service.StudentService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;

/**
 * Created by Ruifu Hua on 2018-12-07.
 */
@Controller
@RequestMapping("/restServer")
@Api(description = "RestServer")
public class RestServerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "列表", notes = "获取学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stuNo", value = "学号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "remark", value = "评价", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classesId", value = "班级id", required = false, paramType = "query", dataType = "String")
    })
    @ResponseBody
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

    @ApiOperation(value = "修改", notes = "修改学生")
    @ResponseBody
    @PutMapping
    public Response<Student> update(@ApiParam(value = "学生") @RequestBody Student record){
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

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "stuNo", value = "学号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "分数", required = false, paramType = "query", dataType = "Float"),
            @ApiImplicitParam(name = "remark", value = "评价", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "classesId", value = "班级id", required = false, paramType = "query", dataType = "String")
    })
    @ResponseBody
    @PostMapping("/file")
    public Response<Student> uploadFile(Student record,
                                 @RequestParam(value="name", required=true) String name,
                                 @ApiParam(name = "file", value = "上传文件", required = true)
                                 @RequestParam(value="file", required=true) MultipartFile multipartFile) throws IOException {
        Response<Student> response = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
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

            String riginalFilename = multipartFile.getOriginalFilename();
            inputStream = multipartFile.getInputStream();

            outputStream = new FileOutputStream(new File("F:\\TestPicture\\" +  riginalFilename.replace(".", "_"+ name +".")));
            byte[] buf = new byte[1024];
            int len;
            len = inputStream.read(buf);
            while (-1 != len) {
                outputStream.write(buf, 0, len);// 把字符ch写到fw所关联的文件中。
                len = inputStream.read(buf);
            }
            outputStream.flush();// 刷新输出流缓冲区，即将输出流中缓冲的数据全部写到目的地。

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }finally {
            inputStream.close();
            outputStream.close();
        }
        return response;
    }

    @ApiOperation(value = "添加", notes = "添加学生")
    @ResponseBody
    @PostMapping
    public Response<Student> add(@ApiParam(value = "学生") @RequestBody Student record, HttpServletRequest request){
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

        //打印请求头
        printHeaders(request);
        return response;
    }

    /**
     * 打印请求头
     * @param request
     */
    private void printHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.print(headerName + " : ");
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while (headerValues.hasMoreElements()){
                String headerValue = headerValues.nextElement();
                System.out.print(headerValue + "、");
            }
            System.out.println("");
        }
    }

    @ApiOperation(value = "删除", notes = "删除学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @ResponseBody
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
    @ResponseBody
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

    @PostMapping(value = "/login")
    public String login(@RequestBody Student student){
        try{
            System.out.println(student);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "redirect:/index.html";
    }
}
