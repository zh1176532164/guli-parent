package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.ucenter.UcenterMemberPay;
import com.atguigu.servicebase.exception.GuliException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-13 22:48
 **/
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberPay getUcenterPay(String id) {
        throw new GuliException(20001,"查询用户出错");
    }

    //根据用户id获取用户信息
}
