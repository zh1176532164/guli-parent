package com.atguigu.eduucenter.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description: 微信登录常量类
 * @author: Mr.Wang
 * @create: 2021-06-11 21:15
 **/
@Component
//@PropertySource("classpath:application.yml")可以指定对应的数据
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${wx.open.appid}")
    private String appId;

    @Value("${wx.open.appsecret}")
    private String appSecret;

    @Value("${wx.open.redirecturl}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {

        WX_OPEN_APP_ID=appId;
        WX_OPEN_APP_SECRET=appSecret;
        WX_OPEN_REDIRECT_URL=redirectUrl;
    }
}
