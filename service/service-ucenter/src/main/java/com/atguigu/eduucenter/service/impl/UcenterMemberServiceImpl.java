package com.atguigu.eduucenter.service.impl;

import com.atguigu.eduucenter.entity.UcenterMember;
import com.atguigu.eduucenter.entity.vo.RegisterVo;
import com.atguigu.eduucenter.mapper.UcenterMemberMapper;
import com.atguigu.eduucenter.service.UcenterMemberService;
import com.atguigu.jwt.JwtUtils;
import com.atguigu.jwt.MD5;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String password = member.getPassword();
        String mobile = member.getMobile();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"用户或者密码为空");
        }

        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
//                .eq("password",password)
//                .ne("is_disabled",0);
        UcenterMember ucenterMember=baseMapper.selectOne(wrapper);
        if(ucenterMember==null){
            throw new GuliException(20001,"用户不存在");
        }

        //网络一般密码都不是明文 是加密存储的
        //一般使用MD5 无法逆向的加密方式 将得到的密码进行加密都与数据库进行比对

        System.out.println(MD5.encrypt(password));
        if(!MD5.encrypt(password) .equals(ucenterMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }else if(ucenterMember.getIsDisabled()){
            throw new GuliException(20001,"用户禁用");
        }
         //生成token
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getMobile());


        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        if(StringUtils.isEmpty(registerVo.getPassword())||StringUtils.isEmpty(registerVo.getPassword())||StringUtils.isEmpty(registerVo.getPassword()))
        {
            throw new GuliException(20001,"数据有空值");
        }
        String code = redisTemplate.opsForValue().get(registerVo.getMobile());
        if(!code.equals(registerVo.getCode())){
            throw new GuliException(20001,"验证码错误");
        }

        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",registerVo.getMobile());
        Integer integer = baseMapper.selectCount(wrapper);
        if(integer>0){
            throw new GuliException(20001,"用户存在");
        }


        UcenterMember member=new UcenterMember();
        //加密密码
        registerVo.setPassword(MD5.encrypt(registerVo.getPassword()));

        BeanUtils.copyProperties(registerVo,member);

        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        int insert = baseMapper.insert(member);
        if(insert!=1){
            throw new GuliException(20001,"注册失败");
        }

    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }
}
