package com.atguigu.order.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-13 23:02
 **/
@Data
@ApiModel(value="EduCourseVo", description="订单需要的课程信息")
public class EduCourseOrder {



    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "课程讲师名称")
    private String teacherName;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;


    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

}
