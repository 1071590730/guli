package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //访问地址http://localhost:8001/eduservice/teacher/findAll
    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return  R.error();
        }
    }

    //访问地址http://localhost:8001/eduservice/teacher/findAll
    //分页讲师列表
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageList/{page}/{limit}")
    public R pageList(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,

        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit){

        //创建page对象
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，把分页所有封装数据封装到pageParam对象里面
        teacherService.page(pageParam, null);
        long total = pageParam.getTotal();//总数
        List<EduTeacher> records = pageParam.getRecords();//数据list集合

//        HashMap map = new HashMap();
//        map.put("total", total);
//        map.put("rows", records);
//        return R.ok().data(map);

        return  R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "条件查询分页讲师列表")
    @PostMapping("pageQuery/{page}/{limit}")
    public R pageQuery(
        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,

        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit,

        @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                TeacherQuery teacherQuery){
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

}

