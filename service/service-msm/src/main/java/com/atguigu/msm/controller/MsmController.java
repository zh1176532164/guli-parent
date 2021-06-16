package com.atguigu.msm.controller;

import com.atguigu.commonutils.Re;
import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.utils.RandomUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-11 09:21
 **/
@Api(produces = "短信实现")
@RestController
@RequestMapping("edumsm/msm")
@CrossOrigin//跨域
public class MsmController {
    @Autowired
    private MsmService msmService;

    //spring 整合好的Redis方法
    //向Redis设置值
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public Re SendMsm(@PathVariable String phone) {

        //判断Redis里面有没有验证码 有的话直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return Re.ok().data("code",code);
        }


        //生成4位长度的随机数
        code = RandomUtil.getFourBitRandom();
        //生成随机数 发送阿里云 map传值方便
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);

        //正常发送短信验证 目前测试屏蔽 直接返回验证码 正常不返回在短信中
        //Service中实现短信发送 也就是发送随机生成的验证码
        Boolean isSend=true;
//         isSend = msmService.Send(map,phone);

        if (isSend){
            //发送成功，吧发送成功的验证码放到Redis中
            //并设置其有效时间 5分钟 后加带数据与时间单位  10s
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Re.ok().data("code",code);
        }
        else
            return Re.error();

    }
}
