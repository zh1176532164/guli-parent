package com.atguigu.eduservice.client;

import com.atguigu.eduservice.client.impl.UcenterClientImpl;
import com.atguigu.eduservice.entity.ucenter.UcenterMemberPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//连接注册的名称
//出错运行 fallback里面的属性  VodClientImpl
@FeignClient(value = "service-uenter",fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    //根据用户id获取用户信息
    @GetMapping("/eduucenter/member/getInfoUc/{id}")
    UcenterMemberPay getUcenterPay(@PathVariable("id") String id);
}
