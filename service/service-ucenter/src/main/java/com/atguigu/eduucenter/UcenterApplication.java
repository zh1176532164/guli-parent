package com.atguigu.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-02 14:30
 **/
@SpringBootApplication
@EnableDiscoveryClient//在Nacos中注册
//@EnableFeignClients//消费者开启 调用Nacos 中注册生产者 信息
@ComponentScan(basePackages = {"com.atguigu"})
//Mapper扫描放在配置类或者直接放在启动类上
@MapperScan("com.atguigu.eduucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
