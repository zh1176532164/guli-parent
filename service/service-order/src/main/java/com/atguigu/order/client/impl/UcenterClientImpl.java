package com.atguigu.order.client.impl;

import com.atguigu.order.client.UcenterClient;
import com.atguigu.order.entity.vo.UcenterMemberOrder;
import com.atguigu.servicebase.exception.GuliException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-13 22:48
 **/
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
//    })
    public UcenterMemberOrder getInfoOrder(String id) {
        throw new GuliException(20001,"查询用户出错");
    }

    //根据用户id获取用户信息
}
