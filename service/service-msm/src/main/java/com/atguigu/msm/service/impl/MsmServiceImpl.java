package com.atguigu.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msm.service.MsmService;
import com.atguigu.servicebase.exception.GuliException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-11 09:23
 **/

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean Send(Map<String, Object> map,String phone) {

        //有效时间通过redis解决
        //判断手机号是否为空
        if (StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5tN1hcuVmn1zczKRScTb", "0AuXUHKZMUxSFtitC54IiQgM2WhoZx");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关的固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送的相关参数
        request.putQueryParameter("PhoneNumbers", phone);//手机号
        request.putQueryParameter("SignName", "申请阿里云那签名");
        request.putQueryParameter("TemplateCode", "申请阿里云模板Code");
        //需要json格式 map 可以直接转成json格式 阿里云只认识json
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response=new CommonResponse();
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"发送信息异常");
        }

    }
}
