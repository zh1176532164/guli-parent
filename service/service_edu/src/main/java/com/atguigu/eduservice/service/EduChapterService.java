package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVoid(String courseId);

    void removeCourseById(String id);

    void removeChapterById(String id);
}
