package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.controller.front.vo.CourseFroutVo;
import com.atguigu.eduservice.controller.front.vo.CourseWebVo;
import com.atguigu.eduservice.entity.*;
import com.atguigu.eduservice.entity.order.EduCourseOrder;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //简介
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;


    //章节
@Autowired
    private EduChapterService chapterService;

    //小节
@Autowired
    private EduVideoService videoService;
    @Override
    public void saveaddCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();

        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert= baseMapper.insert(eduCourse);
        if(insert!=1){
            throw new GuliException(20001,"课程插入失败！！");
        }

        EduCourseDescription courseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);

        courseDescription.setId(eduCourse.getId());
        boolean b= courseDescriptionService.save(courseDescription);
        if(!b){
            throw new GuliException(20001,"课程简介插入失败！！");
        }

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse eduCourse = new EduCourse();
//
//        eduCourse.setId(courseId);
//        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
//        wrapper.eq("id",courseId);
//        eduCourse=baseMapper.selectOne(wrapper);
        eduCourse = baseMapper.selectById(courseId);
        if (eduCourse != null){
            BeanUtils.copyProperties(eduCourse, courseInfoVo);

        }else{
            throw new GuliException(20001,"没找到此ID数据");
        }


        EduCourseDescription courseDescription = new EduCourseDescription();
//        QueryWrapper<EduCourseDescription> wrapper1=new QueryWrapper<>();
//        wrapper.eq("id",courseId);
//        courseDescription=courseDescriptionService.getOne(wrapper1);
        courseDescription = courseDescriptionService.getById(courseId);
        if (courseDescription != null){

            BeanUtils.copyProperties(courseDescription, courseInfoVo);

        }else{
            throw new GuliException(20001,"没找到此ID数据");
        }


        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse=new EduCourse();

        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert= baseMapper.updateById(eduCourse);
        if(insert!=1){
            throw new GuliException(20001,"课程更新失败！！");
        }

        EduCourseDescription courseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,courseDescription);

        //courseDescription.setId(eduCourse.getId());
        boolean b= courseDescriptionService.updateById(courseDescription);
        if(!b){
            throw new GuliException(20001,"课程简介更新失败！！");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishVo(String courseId) {
        return baseMapper.getCoursePublishVo(courseId);
    }

    @Override
    public boolean removeCourseById(String id) {

//        QueryWrapper<EduVideo> eduVideoQueryWrapper=new QueryWrapper<>();
//        eduVideoQueryWrapper.eq("course_id ",id);
//        videoService.remove(eduVideoQueryWrapper);

        videoService.removeCourseById(id);


//        QueryWrapper<EduChapter> eduChapterQueryWrapper=new QueryWrapper<>();
//        eduChapterQueryWrapper.eq("course_id ",id);
//        chapterService.remove(eduChapterQueryWrapper);
        chapterService.removeCourseById(id);

        //courseDescriptionService.removeById(id);
        courseDescriptionService.removeCourseById(id);

        int byId = baseMapper.deleteById(id);
        if (byId==0)
        {
            throw new GuliException(20001,"删除失败");
        }
        return true;
    }

    @Cacheable(value = "Course",key ="'indexCourselist'" )
    @Override
    public List<EduCourse> indexlist() {
        QueryWrapper<EduCourse> wrapper1=new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 8");

        List<EduCourse> courseList = baseMapper.selectList(wrapper1);
        return courseList;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> teacherPage, CourseFroutVo courseFroutVo) {

        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();

        //判断条件分割
        if(!StringUtils.isEmpty( courseFroutVo.getTitle())){
            wrapper.like("title",courseFroutVo.getTitle());
        }
        if(!StringUtils.isEmpty( courseFroutVo.getTeacherId())){
            wrapper.eq("teacher_id",courseFroutVo.getTeacherId());
        }
        if(!StringUtils.isEmpty( courseFroutVo.getTeacherId())){
            wrapper.like("subject_id",courseFroutVo.getSubjectId());
        }
        if(!StringUtils.isEmpty( courseFroutVo.getTeacherId())){
            wrapper.like("subject_parent_id",courseFroutVo.getSubjectParentId());
        }

        if(!StringUtils.isEmpty( courseFroutVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }

        if(!StringUtils.isEmpty( courseFroutVo.getPriceSort())){
            wrapper.orderByDesc("price");
        }

        if(!StringUtils.isEmpty( courseFroutVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_modified");
        }

        baseMapper.selectPage(teacherPage,wrapper);
        List<EduCourse> records = teacherPage.getRecords();//数据
        long current = teacherPage.getCurrent();//当前页
        long total = teacherPage.getTotal();//总数
        long size = teacherPage.getSize();//每页记录数
        long pages = teacherPage.getPages(); //总页数

        boolean next = teacherPage.hasNext();//是否有下一页
        boolean previous = teacherPage.hasPrevious();//是否有上一页

        Map<String,Object> map=new HashMap<>();
        map.put("records",records);
        map.put("current",current);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("next",next);
        map.put("previous",previous);

        return map;
    }

    @Override
    public CourseWebVo getCourseWebVo(String id) {
        CourseWebVo courseWebVo= baseMapper.getCourseWebVo(id);
        return courseWebVo;
    }


    @Override
    public EduCourseOrder getCourseOrder(String id) {
        EduCourseOrder courseOrder= baseMapper.getCourseOrder(id);
        return courseOrder;
    }
}
