package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Api(description ="课程" )
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "根据课程ID，获取章节信息")
    @GetMapping("getChapterVoid/{courseId}")
    public Re getChapterVoid(
            @ApiParam(name = "courseId",value = "课程ID",required = true)
            @PathVariable(value = "courseId") String courseId){

        List<ChapterVo>  chapterVo =chapterService.getChapterVoid(courseId);
        return Re.ok().data("itme",chapterVo);
    }

    @ApiOperation(value = "添加章节信息")
    @PostMapping("addChapter")
    public Re addChapter(
            @RequestBody(required = false) EduChapter chapter
            ){
        chapterService.save(chapter);
        return Re.ok();
    }

    @ApiOperation(value = "更新章节信息")
    @GetMapping("updateChapter")
    public Re updateChapter(
            @RequestBody(required = false) EduChapter chapter
    ){
        chapterService.updateById(chapter);
        return Re.ok();
    }


    @ApiOperation(value = "删除章节信息")
    @DeleteMapping ("daleteChapter/{Id}")
    public Re daleteChapter(
            @ApiParam(name = "Id",value = "章节ID",required = true)
            @PathVariable(value = "Id") String Id
    ){

        chapterService.removeChapterById(Id);
        return Re.ok();
    }
}

