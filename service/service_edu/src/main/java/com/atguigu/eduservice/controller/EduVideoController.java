package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Api(description = "小节")
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;



    @ApiOperation(value = "添加小节信息")
    @PostMapping("addVideo")
    public Re addVideo(
            @RequestBody  EduVideo video
    ){
        eduVideoService.save(video);
        return Re.ok();
    }
    @ApiOperation(value = "更新小节信息")
    @GetMapping("updateVideo")
    public Re updateVideo(
            @RequestBody EduVideo video
    ){
        eduVideoService.updateById(video);
        return Re.ok();
    }


    @ApiOperation(value = "删除小节信息")
    @DeleteMapping ("daleteVideo/{Id}")
    public Re daleteVideo(
            @ApiParam(name = "Id",value = "章节ID",required = true)
            @PathVariable(value = "Id") String Id
    ){
//        EduVideo video=eduVideoService.getById(Id);
//        if(!StringUtils.isEmpty(video.getVideoSourceId())){
//             vodClient.deleteVoide(video.getVideoSourceId());
//
//        }
//        eduVideoService.removeById(Id);
        eduVideoService.daleteVideo(Id);

        return Re.ok();
    }
}

