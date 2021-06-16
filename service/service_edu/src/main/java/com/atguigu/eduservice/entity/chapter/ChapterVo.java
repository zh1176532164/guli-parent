package com.atguigu.eduservice.entity.chapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description: 章节类
 * @author: Mr.Wang
 * @create: 2021-06-07 16:31
 **/
@Data
public class ChapterVo {

    @ApiModelProperty(value = "章节ID")
    private String id;


    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "小节结合")
    private List<VideoVo> childer=new ArrayList<>();
}
