package com.atguigu.eduservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient("service-oss")
@Component
public interface OssClient {
}
