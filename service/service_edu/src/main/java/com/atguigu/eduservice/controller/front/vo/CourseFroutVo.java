package com.atguigu.eduservice.controller.front.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-13 20:15
 **/
@Data
public class CourseFroutVo {

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;


    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "销售排序")
    private String buyCountSort;


    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;


}
