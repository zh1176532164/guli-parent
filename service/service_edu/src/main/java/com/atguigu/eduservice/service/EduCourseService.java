package com.atguigu.eduservice.service;

import com.atguigu.eduservice.controller.front.vo.CourseFroutVo;
import com.atguigu.eduservice.controller.front.vo.CourseWebVo;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.order.EduCourseOrder;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveaddCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVo(String courseId);

    boolean removeCourseById(String id);

    List<EduCourse> indexlist();

    Map<String, Object> getCourseFrontList(Page<EduCourse> teacherPage, CourseFroutVo courseFroutVo);

    CourseWebVo getCourseWebVo(String id);

    EduCourseOrder getCourseOrder(String id);
}
