package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-09 16:33
 **/

@Api("前台接口")
@RestController
@RequestMapping("/edufront/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //不成功
    @ApiOperation("前台显示")
    //查询前8条热门教师
    @GetMapping("index/{TLimit}/{CLimit}")
    public Re index(

            @ApiParam(name = "TLimit",value = "教师数量",defaultValue = "4")
            @PathVariable(required = false) String TLimit,
            @ApiParam(name = "CLimit",value = "课程数量",defaultValue = "8")
            @PathVariable(required = false) String CLimit
    ){

        List<EduTeacher> teacherList=teacherService.indexlist();

        List<EduCourse> courseList=courseService.indexlist();

        return Re.ok().data("teacherList",teacherList).data("courseList",courseList);
    }
}
