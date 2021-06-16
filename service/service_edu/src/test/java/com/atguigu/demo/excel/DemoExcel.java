package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 15:12
 **/
@Data
public class DemoExcel {

    //设置表头的名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer suo;
    @ExcelProperty(value = "学生姓名",index = 1)
    private  String sname;
}
