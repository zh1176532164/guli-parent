package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Api(description = "课程操作")
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public Re addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.saveaddCourseInfo( courseInfoVo);
        return Re.ok();
    }

    @ApiOperation("获取课程信息")
    @GetMapping("getChapterVoid/{courseId}")
    public Re getCourseInfo(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable(value = "courseId") String courseId){
        CourseInfoVo courseInfoVo= eduCourseService.getCourseInfo( courseId);
        if(courseInfoVo==null)
            return Re.error().message("每次此ID的数据");
        return Re.ok().data("itme",courseInfoVo);
    }

    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public Re updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo( courseInfoVo);
        return Re.ok();
    }

    @ApiOperation("获取课程信息")
    @GetMapping("getCoursePublishVo/{courseId}")
    public Re getCoursePublishVo(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable(value = "courseId") String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVo( courseId);
        if(coursePublishVo==null)
            return Re.error().message("没有此ID的数据");
        return Re.ok().data("itme",coursePublishVo);
    }

    @ApiOperation("修改课程发布状态")
    @GetMapping("setPublishCourse/{courseId}")
    public Re setPublishCourse(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable(value = "courseId") String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");//设置发布
        boolean b = eduCourseService.updateById(course);
        if(!b)
            return Re.error().message("更新失败");
        return Re.ok();
    }

    @ApiOperation("获取分页所有课程")
    @PostMapping("getCourseAll/{pageCurrent}/{pageNumber}")
    public Re getCourseAll( @ApiParam(name = "pageCurrent",value = "当前页",required = true)
                                 @PathVariable Long pageCurrent,
                             @ApiParam(name = "pageNumber",value = "当前页数量",required = true)
                                 @PathVariable Long pageNumber,
                            @ApiParam(name = "QueryCourse",value = "课程查询条件")
                                @RequestBody(required = false) EduCourse QueryCourse
                            ){

        Page<EduCourse> page=new Page<>(pageCurrent,pageNumber);
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        if(QueryCourse!=null) {
            if (!StringUtils.isEmpty(QueryCourse.getTitle())) {
                wrapper.like("title", QueryCourse.getTitle());//模糊查询
            }
            if (!StringUtils.isEmpty(QueryCourse.getStatus())) {
                wrapper.eq("status", QueryCourse.getStatus());
            }
        }

        eduCourseService.page(page,wrapper);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("records",records);
        if(records!=null) {
            return Re.ok().data(map);
        }
        else
            return Re.error();

    }

    @ApiOperation(value = "根据课程ID删除课程")
    @DeleteMapping("{id}")
    public Re removeCourse(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable String id) {

        boolean b = eduCourseService.removeCourseById(id);
        if (b) {
            return Re.ok();
        } else
            return Re.error();
    }


}

