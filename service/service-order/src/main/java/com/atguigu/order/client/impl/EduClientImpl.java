package com.atguigu.order.client.impl;

import com.atguigu.order.client.EduClient;
import com.atguigu.order.entity.vo.EduCourseOrder;
import com.atguigu.servicebase.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-13 22:48
 **/
@Component
public class EduClientImpl implements EduClient {

    public EduCourseOrder getCourseOrder(String id) {
        throw new GuliException(20001,"查询课程出错");
    }

    //根据用户id获取用户信息
}
