package com.atguigu.vod.controller;

import com.atguigu.commonutils.Re;
import com.atguigu.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-08 15:40
 **/
@Api(description = "阿里云点播")
@RestController
@RequestMapping("/educod/voide")
public class VodContorller {

    @Autowired
    private VodService vodService;
    @ApiOperation("上传视频")
    @PostMapping("uploadVoide")
    public Re uploadVoide(MultipartFile file){
        String VideoId= vodService.uploadVoide(file);
        return Re.ok().data("VideoId",VideoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("deleteVoide/{ID}")
    public Re deleteVoide(@PathVariable String ID){
        vodService.daleteVoide(ID);
        return Re.ok();
    }


    @ApiOperation("获取视频凭证")
    @GetMapping("getPlayAuth/{ID}")
    public Re getPlayAuth(@PathVariable String ID){
        String playAuth=vodService.getPlayAuth(ID);
        return Re.ok().data("playAuth",playAuth);
    }
}
