package com.atguigu.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-02 22:06
 **/
@Data
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
