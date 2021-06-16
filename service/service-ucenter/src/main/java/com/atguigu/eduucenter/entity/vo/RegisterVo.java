package com.atguigu.eduucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-11 16:59
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="注册对象", description="注册表")
public class RegisterVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "验证码")
    private String code;

}
