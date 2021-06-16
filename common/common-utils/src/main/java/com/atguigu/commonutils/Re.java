package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: guli-parent
 * @description: 统一返回类
 * @author: Mr.Wang
 * @create: 2021-06-02 16:27
 **/
@Data
public class Re {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回代码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回信息")
    private Map<String,Object> data=new HashMap<>();

    public Re(){}

    public static Re ok(){
        Re r = new Re();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;

    }
    public static Re error(){
        Re r = new Re();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;

    }

    public Re success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public Re code(Integer code){
        this.setCode(code);
        return this;
    }
    public Re message(String message){
        this.setMessage(message);
        return this;
    }

    /** *
     *
     * @param key 键值
     * @param value 数据
     * @return com.atguigu.commonutils.Re
     * @author zyt
     * @creed: Talk is cheap,show me the code
     * @date 2021/6/5 12:00
     */
    public Re data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Re data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
