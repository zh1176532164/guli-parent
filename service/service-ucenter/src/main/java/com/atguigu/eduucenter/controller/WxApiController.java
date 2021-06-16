package com.atguigu.eduucenter.controller;

import com.atguigu.eduucenter.entity.UcenterMember;
import com.atguigu.eduucenter.service.UcenterMemberService;
import com.atguigu.eduucenter.util.ConstantPropertiesUtil;
import com.atguigu.eduucenter.util.HttpClientUtils;
import com.atguigu.jwt.JwtUtils;
import com.atguigu.servicebase.exception.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-11 21:18
 **/
//只是请求地址不需要返回数据
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
@CrossOrigin
public class WxApiController {


    @Autowired
    public UcenterMemberService memberService;

    @GetMapping("callback")
    public String callback(String code,String state){

        //得到授权临时票据code
//        System.out.println(code);
//        System.out.println(state);


        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
        "?appid=%s" +
        "&secret=%s" +
        "&code=%s" +
        "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);

        String result = null;

        //HttpClientUtils 不需要浏览器模拟数据访问
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            throw new GuliException(20001, "获取access_token失败");
        }

        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid");

        //查询数据库当前用用户是否曾经使用过微信登录
        UcenterMember member = memberService.getByOpenid(openid);
        if(member == null){
            System.out.println("新用户注册");

            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
            "?access_token=%s" +
            "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
                System.out.println("resultUserInfo==========" + resultUserInfo);
            } catch (Exception e) {
                throw new GuliException(20001, "获取用户信息失败");
            }

            //解析json
            HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String)mapUserInfo.get("nickname");
            String headimgurl = (String)mapUserInfo.get("headimgurl");

            //向数据库中插入一条记录
            member = new UcenterMember();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            memberService.save(member);
        }

        //TODO 登录
        //将jwt通过 member生成的字符串 token
        String token = JwtUtils.getJwtToken(member.getId(), member.getMobile());

        return "redirect:http://localhost:3000?token="+token;
    }


    @GetMapping("login")
    public String getWxCode(){

//        String url=" https://open.weixin.qq.com/connect/qrconnect?" +
//                "appid=" + ConstantPropertiesUtil.WX_OPEN_APP_ID+
//                "&redirect_uri="+ ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL+
//                "&response_type=" +ConstantPropertiesUtil.WX_OPEN_APP_SECRET+
//                "&scope=SCOPE" +
//                "&state=STATE#wechat_redirect";
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";
        //请求回调
        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001, e.getMessage());
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "atguigu";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        System.out.println("state = " + state);

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state);
        return "redirect:"+qrcodeUrl;
    }
}
