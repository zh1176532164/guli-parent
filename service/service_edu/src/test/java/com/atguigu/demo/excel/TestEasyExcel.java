package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: guli-parent
 * @description:
 * @author: Mr.Wang
 * @create: 2021-06-05 15:16
 **/
public class TestEasyExcel {

    public static void main(String[] args) {
        //实现写的操作

        String fileName="E:\\easyExcel.xlsx";

        EasyExcel.write(fileName,DemoExcel.class).sheet("学生列表").doWrite(getDate());
    }
    @Test
    public void ReadExcel()
    {
        String fileName="E:\\easyExcel.xlsx";
        EasyExcel.read(fileName,DemoExcel.class,new ExcelListener()).doReadAll();
    }

    private static List<DemoExcel> getDate()
    {
        List<DemoExcel> demoExcels=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoExcel demoExcel=new DemoExcel();
            demoExcel.setSuo(i);
            demoExcel.setSname("qwe"+i);
            demoExcels.add(demoExcel);
        }
        return demoExcels;
    }
}
