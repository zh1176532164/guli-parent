package com.atguigu.vod.uitl;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description: 全局变量
 * @author: Mr.Wang
 * @create: 2021-06-05 11:14
//继承InitializingBean是变量初始化后执行方法
 **/
@Data
@Component
public class VodGlobalNamespace implements InitializingBean {

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;


    public static String KEY_ID;
    public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {

        KEY_ID=keyid;
        KEY_SECRET=keysecret;

    }
}
