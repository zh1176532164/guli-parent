package com.atguigu.msm.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface MsmService {
    Boolean Send(Map<String, Object> map,String phone);
}
