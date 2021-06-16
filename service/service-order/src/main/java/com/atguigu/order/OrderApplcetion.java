package com.atguigu.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-14 10:17
 **/

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient//在Nacos中注册
@EnableFeignClients//消费者的话开启
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.order.mapper")
public class OrderApplcetion {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplcetion.class,args);
    }
}
