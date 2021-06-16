package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.vod.into.VodInto;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.uitl.VodGlobalNamespace;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-08 15:42
 **/
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVoide(MultipartFile file) {
        String VideoId="";
        try {
            String accessKeyId= VodGlobalNamespace.KEY_ID;
            String accessKeySecret=VodGlobalNamespace.KEY_SECRET;

            String fileName=file.getOriginalFilename();
            String title=fileName.substring(0,fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();


            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title,fileName, inputStream);
//            UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);

            UploadVideoImpl uploader = new UploadVideoImpl();

            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            VideoId=response.getVideoId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return VideoId;
    }

    @Override
    public void daleteVoide(String id) {

        try {
            DefaultAcsClient client = VodInto.initVodClient(VodGlobalNamespace.KEY_ID, VodGlobalNamespace.KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(id);
            client.getAcsResponse(request);

           // System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败异常");
        }
    }

    @Override
    public String getPlayAuth(String id) {
        try {
            DefaultAcsClient client = VodInto.initVodClient(VodGlobalNamespace.KEY_ID, VodGlobalNamespace.KEY_SECRET);

            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            String playAuth = response.getPlayAuth();
            return playAuth;
            // System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001,"获取视频凭证失败");
        }
    }
}
