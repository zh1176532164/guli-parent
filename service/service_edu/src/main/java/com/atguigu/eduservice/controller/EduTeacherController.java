package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-02
 */
//交给Spring进行管理 返回数据
@Api(description ="讲师管理" )
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin //解决跨域
public class EduTeacherController {

    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    @Autowired
    private EduTeacherService eduTeacherService;
    //讲师对的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Re findAllTeacher(){

        try {
            List<EduTeacher> eduTeachers = eduTeacherService.list(null);
            //int a = 1 / 0;
            if (eduTeachers != null) {
                return Re.ok().data("itme", eduTeachers);
            } else {
                return Re.error();
            }
        } catch (Exception e) {
            throw new GuliException(20001,"查询全部出现异常");
        }
    }

    @ApiOperation(value = "根据讲师ID删除讲师")
    @DeleteMapping("{id}")
    public Re removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true,defaultValue = "1")
            @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return Re.ok();
        } else
            return Re.error();
    }

    @ApiOperation(value = "分页查找讲师资料")
    @GetMapping("pageTeacher/{pageCurrent}/{pageNumber}")
    public Re PageTeacher(
            @ApiParam(name = "pageCurrent",value = "当前页",required = true)
            @PathVariable Long pageCurrent,
            @ApiParam(name = "pageNumber",value = "当前页数量",required = true)
            @PathVariable Long pageNumber){
        Page<EduTeacher> eduTeacherPage=new Page<>(pageCurrent,pageNumber) ;
        eduTeacherService.page(eduTeacherPage);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("records",records);
        if(records!=null) {
            return Re.ok().data(map);
        }
        else
            return Re.error();

    }

    @ApiOperation(value = "分页带条件查找讲师资料")
    @PostMapping("pageTeacherQuery/{pageCurrent}/{pageNumber}")
    public Re PageTeacherQuery(
            @ApiParam(name = "pageCurrent",value = "当前页",required = true)
            @PathVariable Long pageCurrent,
            @ApiParam(name = "pageNumber",value = "当前页数量",required = true)
            @PathVariable Long pageNumber,
            @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> eduTeacherPage=new Page<>(pageCurrent,pageNumber) ;
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        //多条件组合查询 动态spl
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();

        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);//模糊查询
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);//等于
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);//大于等于
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.lt("gmt_create",end);//小于等于
        }

        eduTeacherService.page(eduTeacherPage,wrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("records",records);
        if(records!=null) {
            return Re.ok().data(map);
        }
        else
            return Re.error();

    }

    @ApiOperation(value = "添加教师")
    @PostMapping("addTeacher")
    public Re addTeacher(@RequestBody EduTeacher eduTeacher)
    {
        boolean b = eduTeacherService.save(eduTeacher);
        if (b) {
            return Re.ok();
        } else
            return Re.error();
    }


    @ApiOperation(value = "根据讲师ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public Re getTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        if(teacher!=null) {
            return Re.ok().data("itme",teacher);
        }else{
            return Re.error();
        }
    }
    @ApiOperation(value = "更新讲师")
    @PostMapping ("updateTeacher")
    public Re updateTeacher(
            @ApiParam(name = "eduTeacher",value = "讲师信息",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return Re.ok();
        } else
            return Re.error();
    }


}

