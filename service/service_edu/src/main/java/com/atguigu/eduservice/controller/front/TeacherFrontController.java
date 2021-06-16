package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-12 00:53
 **/
@RestController
@RequestMapping("/edufront/teacher")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;


    //不成功
    @ApiOperation("前台显示")
    //查询前8条热门教师
    @GetMapping("getTeacherFrontList/{limit}/{page}")
    public Re getTeacherFrontList(
            @ApiParam(name = "limit",value = "课程数量",defaultValue = "8")
            @PathVariable(required = false) long limit,
            @ApiParam(name = "page",value = "课程页",defaultValue = "8")
            @PathVariable(required = false) long page
    ){

        Page<EduTeacher> teacherPage=new Page<>(page,limit);
        Map<String,Object> map=teacherService.ingetTeacherFrontListdexlist(teacherPage);


        return Re.ok().data(map);
    }

    //讲师详情
    @ApiOperation(value = "根据讲师ID查询讲师")
    @GetMapping("getTeacherFront/{id}")
    public Re getTeacherFront(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);

        QueryWrapper<EduCourse> wrapper =new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList=courseService.list(wrapper);

        if(teacher!=null) {
            return Re.ok().data("teacher",teacher).data("courseList",courseList);
        }else{
            return Re.error();
        }
    }
}
