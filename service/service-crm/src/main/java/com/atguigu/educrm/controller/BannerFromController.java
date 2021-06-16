package com.atguigu.educrm.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.educrm.entity.CrmBanner;
import com.atguigu.educrm.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器 前台接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/educrm/bannerfrom")
@CrossOrigin
public class BannerFromController {

    @Autowired
    private CrmBannerService bannerService;



    @GetMapping("getAllBanner")
    public Re getAllBanner(){
        List<CrmBanner> crmBannerList=bannerService.getAllBanner();

        return Re.ok().data("items",crmBannerList);
    }
}

