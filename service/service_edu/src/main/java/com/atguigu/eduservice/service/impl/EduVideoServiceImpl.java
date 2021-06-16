package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.Re;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public void removeCourseById(String id) {


        QueryWrapper<EduVideo> eduVideoQueryWrapper=new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id ",id);

        List<EduVideo> eduVideos=baseMapper.selectList(eduVideoQueryWrapper);


        if(eduVideos.size()>0) {
            String VideosId = "";
            for (int i = 0; i < eduVideos.size(); i++) {
                if (!StringUtils.isEmpty(eduVideos.get(i).getVideoSourceId())) {
                    if (!VideosId.equals("") )
                        VideosId = VideosId + "," + eduVideos.get(i).getVideoSourceId();
                    else
                        VideosId = eduVideos.get(i).getVideoSourceId();
                }
            }
            //org.apache.commons.lang.StringUtils.join(eduVideos)
            if (!VideosId.equals("") ){

               Re re= vodClient.deleteVoide(VideosId);
               if(re.getCode()!=20000){
                   throw new GuliException(20001,"删除视频失败，熔断器...");
               }
            }

        }


        baseMapper.delete(eduVideoQueryWrapper);

    }

    @Override
    public void removeChapterById(String id) {


        QueryWrapper<EduVideo> eduVideoQueryWrapper=new QueryWrapper<>();
        eduVideoQueryWrapper.eq("chapter_id ",id);

        List<EduVideo> eduVideos=baseMapper.selectList(eduVideoQueryWrapper);

        if(eduVideos.size()>0) {
            String VideosId = "";
            for (int i = 0; i < eduVideos.size(); i++) {
                if (StringUtils.isEmpty(eduVideos.get(i).getVideoSourceId())) {
                    if (VideosId != "")
                        VideosId = VideosId + "," + eduVideos.get(i).getVideoSourceId();
                    else
                        VideosId = eduVideos.get(i).getVideoSourceId();
                }
            }
            //org.apache.commons.lang.StringUtils.join(eduVideos)
            if (!VideosId.equals("") ){

                Re re= vodClient.deleteVoide(VideosId);
                if(re.getCode()!=20000){
                    throw new GuliException(20001,"删除视频失败，熔断器...");
                }
            }

        }


        baseMapper.delete(eduVideoQueryWrapper);

    }

    @Override
    public void daleteVideo(String id) {
        EduVideo video=baseMapper.selectById(id);

        if(!StringUtils.isEmpty(video.getVideoSourceId())){
//            vodClient.deleteVoide(video.getVideoSourceId());
            Re re= vodClient.deleteVoide(video.getVideoSourceId());
            if(re.getCode()!=20000){
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }

        baseMapper.deleteById(id);
    }
}
