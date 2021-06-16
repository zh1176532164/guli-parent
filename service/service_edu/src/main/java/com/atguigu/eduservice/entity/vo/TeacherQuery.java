package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: guli-parent
 * @description: 老师条件查询
 * @author: Mr.Wang
 * @create: 2021-06-02 18:58
 **/
@Data
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "讲师姓名,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2021-06-02 09:01:01")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2021-06-02 19:01:01")
    private String end;

}
