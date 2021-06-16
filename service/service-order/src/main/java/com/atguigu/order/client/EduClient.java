package com.atguigu.order.client;

import com.atguigu.order.client.impl.EduClientImpl;
import com.atguigu.order.client.impl.UcenterClientImpl;
import com.atguigu.order.entity.vo.EduCourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-14 10:59
 **/
//连接注册的名称
//出错运行 fallback里面的属性  VodClientImpl
@FeignClient(value = "service-edu",fallback = EduClientImpl.class)
@Component
public interface EduClient {

    //根据用户id获取用户信息
    @GetMapping("/edufront/course/getCourseOrder/{id}")
    EduCourseOrder getCourseOrder(String id);
}
