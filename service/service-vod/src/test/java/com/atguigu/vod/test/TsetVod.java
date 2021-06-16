package com.atguigu.vod.test;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

import static com.atguigu.vod.test.InitVod.initVodClient;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-08 14:09
 **/
public class TsetVod {
//    public static void main(String[] args) {
//        //根据视频的ID上传
//
//    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("18d93cb8bcd946cba69636526f811962");
        return client.getAcsResponse(request);
    }

    /*以下为调用示例*/
    public static void main(String[] argv) throws Exception {
        //String PlayAuth=PlayAuth();

        String accessKeyId="LTAI5tN1hcuVmn1zczKRScTb";
        String accessKeySecret="0AuXUHKZMUxSFtitC54IiQgM2WhoZx";

        String title="123.mp4";
        String fileName="E:\\123.mp4";


        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);

        /*可指定分片上传时每个分片的大小，默认为1M字节*/
        request.setPartSize(1 * 1024 * 1024L);
        /*可指定分片上传时的并发线程数，默认为1（注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /*是否开启断点续传，默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" +  response.getRequestId()  + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId="+   response.getVideoId()  + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId="  + response.getVideoId()  + "\n");
            System.out.print("ErrorCode=" +  response.getCode()  + "\n");
            System.out.print("ErrorMessage="+   response.getMessage()   +"\n");
        }
    }

    //获取路径
    private static void extracted() {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        try {
            DefaultAcsClient client = initVodClient("LTAI5tN1hcuVmn1zczKRScTb", "0AuXUHKZMUxSFtitC54IiQgM2WhoZx");
            request.setVideoId("18d93cb8bcd946cba69636526f811962");
            response= client.getAcsResponse(request);

//            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            //https://outin-c9970fafc81911eb88e400163e1c8dba.oss-cn-shanghai.aliyuncs.com/sv/aa4849d-179ea150244/aa4849d-179ea150244.mp4?Expires=1623136819&OSSAccessKeyId=LTAI4FfD63zoqnm6ckiBFfXZ&Signature=F%2BmCrPoFZItaQCjTUs2uAFER778%3D
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    //获取凭证
    public static String PlayAuth() throws ClientException {
        String PlayAuth="";
        DefaultAcsClient client = initVodClient("LTAI5tN1hcuVmn1zczKRScTb", "0AuXUHKZMUxSFtitC54IiQgM2WhoZx");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId("18d93cb8bcd946cba69636526f811962");
            response= client.getAcsResponse(request);
            //response = getVideoPlayAuth(client);
            //播放凭证
             PlayAuth=response.getPlayAuth();
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        return PlayAuth;
    }
}
