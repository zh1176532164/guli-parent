package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-02
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    //缓存到redis中
    @Cacheable(value = "Teacher",key = "'indexTeacherlist'")
    @Override
    public List<EduTeacher> indexlist() {
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> teacherList=baseMapper.selectList(wrapper);
        return teacherList;
    }

    @Override
    public Map<String, Object> ingetTeacherFrontListdexlist(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");

        baseMapper.selectPage(teacherPage,wrapper);
        List<EduTeacher> records = teacherPage.getRecords();//数据
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
}
