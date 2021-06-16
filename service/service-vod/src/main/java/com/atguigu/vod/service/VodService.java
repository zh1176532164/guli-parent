package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVoide(MultipartFile file);

    void daleteVoide(String id);

    String getPlayAuth(String id);
}
