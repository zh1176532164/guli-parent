package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: guli-parent
 * @description: Excel读取监听器
 * @author: Mr.Wang
 * @create: 2021-06-05 15:32
 **/
public class ExcelListener extends AnalysisEventListener<DemoExcel> {

    List<DemoExcel> list = new ArrayList<DemoExcel>();

    //一行一行读取数据
    @Override
    public void invoke(DemoExcel demoExcel, AnalysisContext analysisContext) {
        System.out.println(demoExcel);
        list.add(demoExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext){
        System.out.println("结束");
    }
    //读取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //super.invokeHeadMap(headMap, context);
        System.out.println("表头"+headMap);
    }
}
