package com.atguigu.order.service;

import com.atguigu.order.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-14
 */
public interface TPayLogService extends IService<TPayLog> {

    //生成支付二维码
    Map createnative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
