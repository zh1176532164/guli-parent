package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.subject.OneEduSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //得到上传图片
    @PostMapping("addSubject")
    public Re addSubject(MultipartFile file)
    {
        eduSubjectService.addSubject(file,eduSubjectService);
        return Re.ok();
    }
    @GetMapping("seletsAll")
    public Re seletsAll()
    {
        List<OneEduSubject> oneEduSubjects= eduSubjectService.seletsAll();
        return  Re.ok().data("itme",oneEduSubjects);
    }
}

