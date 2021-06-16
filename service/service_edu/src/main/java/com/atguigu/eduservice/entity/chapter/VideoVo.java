package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-07 16:32
 **/
@Data
public class VideoVo {

    @ApiModelProperty(value = "小节ID")
    private String id;


    @ApiModelProperty(value = "小节名称")
    private String title;
}
