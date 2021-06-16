package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneEduSubject;
import com.atguigu.eduservice.entity.subject.TwoEduSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void addSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneEduSubject> seletsAll() {
        return this.baseMapper.SelectOenTwo();
//        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
//        wrapper.eq("parent_id",0);
//        List<EduSubject>  OneeduSubject=baseMapper.selectList(wrapper);
//        wrapper =new QueryWrapper<>();
//        wrapper.ne("parent_id",0);
//        List<EduSubject>  TwoeduSubject=baseMapper.selectList(wrapper);
//
//        List<OneEduSubject>  oneEduSubjects=new ArrayList<>();
//
//
//        for (EduSubject s:
//                OneeduSubject) {
//            OneEduSubject oneEduSubject=new OneEduSubject();
//            BeanUtils.copyProperties(s,oneEduSubject);
//            oneEduSubjects.add(oneEduSubject);
//        }
//        for (EduSubject s:
//                TwoeduSubject) {
//            for (int i = 0; i < oneEduSubjects.size(); i++) {
//                if(s.getParentId().equals(oneEduSubjects.get(i).getId()))
//                {
//                    TwoEduSubject EduSubject=new TwoEduSubject();
//                    BeanUtils.copyProperties(s,EduSubject);
//                    oneEduSubjects.get(i).getEduSubjects().add(EduSubject);
//                }
//            }
//        }
//
//
//        return  oneEduSubjects;

    }

}
