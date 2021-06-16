package com.atguigu.educrm.controller;


import com.atguigu.commonutils.Re;
import com.atguigu.educrm.entity.CrmBanner;
import com.atguigu.educrm.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器 后台接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/educrm/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation("分页获取信息")
    @GetMapping("pageBanner/{page}/{limit}")
    public Re pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> crmBannerPage=new Page<>(page,limit);
        bannerService.page(crmBannerPage,null);
        return Re.ok().data("items",crmBannerPage.getRecords()).data("total",crmBannerPage.getTotal());
    }
    @ApiOperation("增加Banner")
    @PostMapping("addBanner")
    public Re addBanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return Re.ok();
    }

    @ApiOperation("更新Banner")
    @PostMapping("updateBanner")
    public Re updateBanner( @RequestBody CrmBanner banner){
        bannerService.updateById(banner);
        return Re.ok();
    }


    @ApiOperation("删除Banner")
    @PostMapping("deleteBanner/{ID}")
    public Re deleteBanner(@PathVariable String ID,@RequestBody CrmBanner banner){
        bannerService.removeById(ID);
        return Re.ok();
    }


    @ApiOperation("获取Banner")
    @PostMapping("getBannerById/{ID}")
    public Re getBannerById(@PathVariable String ID,@RequestBody CrmBanner banner){
        CrmBanner crmBanner = bannerService.getById(ID);
        return Re.ok().data("items",crmBanner);
    }
}

