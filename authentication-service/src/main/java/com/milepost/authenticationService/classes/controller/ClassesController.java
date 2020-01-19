package com.milepost.authenticationService.classes.controller;

import com.github.pagehelper.PageInfo;
import com.milepost.api.dto.request.PageParameter;
import com.milepost.api.util.DataUUIDUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.authenticationService.classes.entity.Classes;
import com.milepost.authenticationService.classes.service.ClassesService;
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
@RequestMapping("/classes")
@Api(description = "班级")
public class ClassesController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassesService classesService;

    @ApiOperation(value = "列表", notes = "获取班级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "String")
    })
    @GetMapping
    public Response<PageInfo<Classes>> list(PageParameter pageParameter, @ApiIgnore Classes example) {
        Response<PageInfo<Classes>> response = null;
        try{
            PageInfo<Classes> pageInfo = classesService.list(pageParameter, example);
            response = ResponseHelper.createSuccessResponse(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @ApiOperation(value = "添加", notes = "添加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping
    public Response<Classes> add(@ApiIgnore Classes record){
        Response<Classes> response = null;
        try{
            record.setId(DataUUIDUtil.randomUUID());
            int affectedRow = classesService.insertSelective(record);
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

    @ApiOperation(value = "修改", notes = "添加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "String")
    })
    @PutMapping
    public Response<Classes> update(@ApiIgnore Classes record){
        Response<Classes> response = null;
        try{
            int affectedRow = classesService.updateByPrimaryKeySelective(record);
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

    @ApiOperation(value = "删除", notes = "删除班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @DeleteMapping(value = "/{id}")
    public Response<Classes> delete(@PathVariable("id") String id){
        Response<Classes> response = null;
        try{
            int affectedRow = classesService.deleteByPrimaryKey(id);
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

    @ApiOperation(value = "根据id查询", notes = "根据id查询班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "/{id}")
    public Response<Classes> get(@PathVariable("id") String id){
        Response<Classes> response = null;
        try{
            Classes classes = classesService.selectByPrimaryKey(id);
            response = ResponseHelper.createSuccessResponse(classes);
            response.setMsg("查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
