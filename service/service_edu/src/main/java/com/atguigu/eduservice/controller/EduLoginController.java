package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.entity.EduUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: guli-parent
 * @description: 用户登录
 * @author: Mr.Wang
 * @create: 2021-06-03 16:55
 **/
@Api(description = "登录画面")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

//    @Autowired
    //private EduUser eduUser;

    @PostMapping("login")
    public Re loginByUsername(@RequestBody EduUser eduUser){

        System.out.println(eduUser.getUsername()+"---"+eduUser.getPassword());
        return Re.ok().data("token","admin");
    }
    @GetMapping("info")
    public Re info(){

        return Re.ok()
                .data("roles","[admin]")
                .data("name","mack")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .data("introduction","一级讲师");
    }
    @PostMapping("logout")
    public Re logout() {
        return Re.ok();
    }


}
