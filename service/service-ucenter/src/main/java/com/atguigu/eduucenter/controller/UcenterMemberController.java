package com.atguigu.eduucenter.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.eduucenter.entity.UcenterMember;
import com.atguigu.eduucenter.entity.vo.RegisterVo;
import com.atguigu.eduucenter.entity.vo.UcenterMemberOrder;
import com.atguigu.eduucenter.entity.vo.UcenterMemberVo;
import com.atguigu.eduucenter.service.UcenterMemberService;
import com.atguigu.jwt.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/eduucenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    public UcenterMemberService ucenterMemberService;

    @PostMapping("login")
    public Re login(@RequestBody UcenterMember member){

        String token= ucenterMemberService.login(member);
        //System.out.println(eduUser.getUsername()+"---"+eduUser.getPassword());
        return Re.ok().data("token",token);
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
    @PostMapping("register")
    public Re register(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return Re.ok();
    }

    @GetMapping("getMemberInfo")
    public Re getMemberInfo(HttpServletRequest request){

        //响应体中获取token信息 得到ID信息
        String id = JwtUtils.getMemberIdByJwtToken(request);

        //查询数据可获得用户信息
        UcenterMember member = ucenterMemberService.getById(id);

        return Re.ok()
                .data("Info",member);
    }

    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberVo getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberVo memeber = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,memeber);
       return memeber;
    }

    //根据token字符串获取用户信息
    @PostMapping("getInfoOrder/{id}")
    public UcenterMemberOrder getInfoOrder(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,memberOrder);
        return memberOrder;
    }
}

