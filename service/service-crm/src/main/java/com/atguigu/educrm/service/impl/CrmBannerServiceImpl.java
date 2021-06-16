package com.atguigu.educrm.service.impl;

import com.atguigu.educrm.entity.CrmBanner;
import com.atguigu.educrm.mapper.CrmBannerMapper;
import com.atguigu.educrm.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-09
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Cacheable(key = "'getAllBanner'",value = "banner")
    @Override
    public List<CrmBanner> getAllBanner() {

        //根据ID进行降序排列 ，显示排列之后前的两条记录
        QueryWrapper<CrmBanner> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //拼接语句
        wrapper.last("limit 2");

        List<CrmBanner> crmBanners = baseMapper.selectList(wrapper);


        return crmBanners;
    }
}
