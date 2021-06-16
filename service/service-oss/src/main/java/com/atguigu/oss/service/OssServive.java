package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssServive {
    String uploadFilesAvatar(MultipartFile file);
}
