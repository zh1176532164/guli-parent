package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.controller.front.vo.CourseWebVo;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.order.EduCourseOrder;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getCoursePublishVo(@Param(value = "ID") String ID);

    CourseWebVo getCourseWebVo(@Param(value = "ID") String id);

    EduCourseOrder getCourseOrder(@Param(value = "ID") String id);
}
