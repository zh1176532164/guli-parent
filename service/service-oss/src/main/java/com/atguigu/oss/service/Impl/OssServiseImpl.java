package com.atguigu.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssServive;
import com.atguigu.oss.util.OssGlobalNamespace;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 11:34
 **/
@Service
public class OssServiseImpl implements OssServive {
    @Override
    public String uploadFilesAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = OssGlobalNamespace.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = OssGlobalNamespace.KEY_ID;
        String accessKeySecret = OssGlobalNamespace.KEY_SECRET;
        String bucketname = OssGlobalNamespace.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            String filesName= UUID.randomUUID()+ file.getOriginalFilename();
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = null;
            inputStream = file.getInputStream();
//            SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd");//显示20171027格式
//            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");//显示2017-10-27格式
//            SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//显示2017-10-27 10:00:00格式
//            SimpleDateFormat sdf4=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");//显示2017年10月27日10时00分00秒格式
//            //将格式应用于日期
//            System.out.println(sdf1.format(date));//20171027
            //Date date=new Date();//构建日期路径：avatar/2019/02/26/文件名
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            filesName =filePath+"/"+filesName;
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketname, filesName, inputStream);


            // 关闭OSSClient。
            ossClient.shutdown();
            //https://yantao-zhang.oss-cn-beijing.aliyuncs.com/%E4%BA%8C%E6%AC%A1P001-002-b08.jpg
            String url="https://"+bucketname+"."+endpoint+"/"+filesName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
