package com.atguigu.servicebase.exception;

import com.atguigu.commonutils.Re;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: guli-parent
 * @description: 统一异常类
 * @author: Mr.Wang
 * @create: 2021-06-02 21:27
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Re error(Exception e){
        e.printStackTrace();
        return  Re.error().message("自定义全局异常");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Re error(ArithmeticException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return  Re.error().message("除数为0异常");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Re error(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return  Re.error().message(e.getMsg()).code(e.getCode());
    }

}
