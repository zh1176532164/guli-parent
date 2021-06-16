package com.atguigu.order.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.jwt.JwtUtils;
import com.atguigu.order.client.EduClient;
import com.atguigu.order.client.UcenterClient;
import com.atguigu.order.entity.TOrder;
import com.atguigu.order.entity.vo.EduCourseOrder;
import com.atguigu.order.entity.vo.UcenterMemberOrder;
import com.atguigu.order.service.TOrderService;
import com.atguigu.order.utils.OrderNoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-14
 */
@RestController
@RequestMapping("/order/t-order")
public class TOrderController {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Autowired
    private TOrderService orderService;

    @ApiOperation(value = "添加订单")
    @PostMapping("order/save/{CourseId}")
    public Re save(@PathVariable String CourseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return Re.error().code(28004).message("请登录");
        }

        String orderNo = OrderNoUtil.getOrderNo();

        TOrder order=new TOrder();
        order.setOrderNo(orderNo);
        order.setCourseId(CourseId);

        EduCourseOrder courseOrder=eduClient.getCourseOrder(CourseId);
        //获取客户信息
        order.setCourseCover(courseOrder.getCover());
        order.setCourseTitle(courseOrder.getTitle());
        order.setTotalFee(courseOrder.getPrice());
        order.setTeacherName(courseOrder.getTeacherName());

        UcenterMemberOrder ucenterInfo = ucenterClient.getInfoOrder(memberId);

        order.setNickname(ucenterInfo.getNickname());
        order.setMobile(ucenterInfo.getMobile());

        order.setStatus(0);
        order.setPayType(1);//支付类型

        orderService.save(order);
        return Re.ok().data("orderNo",orderNo);
    }


    @ApiOperation(value = "查询订单")
    @PostMapping("order/save/{order_no}")
    public Re save(@PathVariable String order_no){

        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",order_no);

        TOrder order = orderService.getOne(wrapper);

        return Re.ok().data("order",order);
    }
}

