package com.atguigu.oss.controller;

import com.atguigu.commonutils.Re;
import com.atguigu.oss.service.OssServive;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 11:34
 **/
@Api(description = "阿里云数据服务")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssServive ossServive;

    @PostMapping ("uploadFiles")
    public Re uploadFilesAvatar(MultipartFile file){
        String url= ossServive.uploadFilesAvatar(file);
        return Re.ok().data("url",url);
    }
}
