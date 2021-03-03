package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Jianbiao
 * @since 2021-03-03
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //访问地址http://localhost:8001/eduservice/teacher/findAll
    //查询所有讲师数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher() {
        return teacherService.list(null);
    }

    //访问地址http://localhost:8001/eduservice/teacher/findAll
    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public boolean removeById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        return teacherService.removeById(id);
    }

}

