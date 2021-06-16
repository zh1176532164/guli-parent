package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * easy Excel 读取表格数据
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 15:57
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为是new的 不能交给Spring进行 管理 进行有参构造传递参数
    private EduSubjectService eduSubjectService;
    private Map<String,String> oneSubjectmap=new HashMap<>();
    private Map<String,String> tmoSubjectmap=new HashMap<>();

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    //判断一级分裂不能重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id",0);
        EduSubject eduSubject=subjectService.getOne(wrapper);
        return  eduSubject;
    }
    //判断二级分裂不能重复
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id",pid);
        EduSubject eduSubject=subjectService.getOne(wrapper);
        return  eduSubject;
    }
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
        String oneSubjectName = subjectData.getOneSubjectName();
        String tmoSubjectName = subjectData.getTmoSubjectName();
        //一级分类不重复
        EduSubject eduSubject=new EduSubject();
        if(this.existOneSubject(eduSubjectService,oneSubjectName)==null)//
        {
            eduSubject.setTitle(oneSubjectName);
            eduSubject.setParentId("0");
            eduSubjectService.save(eduSubject);
        }
        String pid=eduSubject.getId();
        if(this.existTwoSubject(eduSubjectService,tmoSubjectName,pid)==null)//
        {
            eduSubject=new EduSubject();
            eduSubject.setTitle(tmoSubjectName);
            eduSubject.setParentId(pid);
            eduSubjectService.save(eduSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }
}
