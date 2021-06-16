package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-09 13:36
 **/
@Component
public class VodClientImpl implements VodClient {
    @Override
    public Re deleteVoide(String ID) {
        return Re.error().message("删除视频出错了！！");
    }
}
