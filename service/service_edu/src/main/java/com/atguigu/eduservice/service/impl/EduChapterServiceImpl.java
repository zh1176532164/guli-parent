package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVoid(String courseId) {
        return baseMapper.seleteChapterVo(courseId);
    }

    @Override
    public void removeCourseById(String id) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper=new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id ",id);
        baseMapper.delete(eduChapterQueryWrapper);
    }

    @Override
    public void removeChapterById(String id) {
        videoService.removeChapterById(id);
        baseMapper.deleteById(id);
    }
}
