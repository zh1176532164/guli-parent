package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    public List<ChapterVo> seleteChapterVo(@Param("courseId") String courseId);
}
