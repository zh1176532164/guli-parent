package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.client.impl.VodClientImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//连接注册的名称
//出错运行 fallback里面的属性  VodClientImpl
@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {

    //定义方法路径
    @DeleteMapping("/educod/voide/deleteVoide/{ID}")
    Re deleteVoide(@PathVariable("ID") String ID);
}
