package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.subject.OneEduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
@Repository
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    public List<OneEduSubject> SelectOenTwo();
}
