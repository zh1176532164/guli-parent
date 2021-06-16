package com.atguigu.oss.util;

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
public class OssGlobalNamespace implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        KEY_ID=keyid;
        KEY_SECRET=keysecret;
        BUCKET_NAME=bucketname;
    }
}
