package com.atguigu.eduservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-03 19:56
 **/
@Data
@Component
@ApiModel(value="EduUser对象", description="用户")
public class EduUser implements Serializable {
    @ApiModelProperty(value = "用户")
    private String username;
    @ApiModelProperty(value = "用户")
    private String password;
}
