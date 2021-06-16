package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.controller.front.vo.CourseFroutVo;
import com.atguigu.eduservice.controller.front.vo.CourseWebVo;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.order.EduCourseOrder;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.eduservice.service.EduVideoService;
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
@RequestMapping("/edufront/course")
@CrossOrigin
public class CourseFrontController {


    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;


    //不成功
    @ApiOperation("前台显示")
    //查询前8条热门教师
    @PostMapping("getCourseFrontList/{limit}/{page}")
    public Re getCourseFrontList(
            @ApiParam(name = "limit",value = "数量",defaultValue = "2")
            @PathVariable(required = false) long limit,
            @ApiParam(name = "page",value = "页",defaultValue = "1")
            @PathVariable(required = false) long page,
            @RequestBody(required = false) CourseFroutVo courseFroutVo
    ){

        Page<EduCourse> teacherPage=new Page<>(page,limit);
        Map<String,Object> map=courseService.getCourseFrontList(teacherPage,courseFroutVo);


        return Re.ok().data(map);
    }

    //讲师详情
    @ApiOperation(value = "根据课程ID查询讲师")
    @GetMapping("getCourseFront/{id}")
    public Re getCourseFront(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable String id){
        CourseWebVo courseWebVo=courseService.getCourseWebVo(id);

        List<ChapterVo> chapterVos = chapterService.getChapterVoid(id);

       return Re.ok().data("courseWebVo",courseWebVo).data("chapterVos",chapterVos);
    }

    //讲师详情
    @ApiOperation(value = "根据课程ID查询订单需要的信息")
    @GetMapping("getCourseOrder/{id}")
    public EduCourseOrder getCourseOrder(
            @ApiParam(name = "id",value = "课程ID",required = true)
            @PathVariable String id){
        EduCourseOrder courseOrder=courseService.getCourseOrder(id);

        return courseOrder;
    }
}
