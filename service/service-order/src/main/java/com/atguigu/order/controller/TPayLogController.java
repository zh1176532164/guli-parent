package com.atguigu.order.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.order.service.TPayLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-14
 */
@Api(description = "微信支付")
@RestController
@RequestMapping("/order/paylog")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;
    //生成支付二维码

    @GetMapping("createnative/{orderNo}")
    public Re createnative(@PathVariable String orderNo){
        //返回信息，包括二维码地址，还有其他的信息

        Map map =payLogService.createnative(orderNo);
        return Re.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public Re queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return Re.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return Re.ok().message("支付成功");
        }

        return Re.ok().code(25000).message("支付中");
    }
}

