package com.moowu.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moowu.common.constant.DeleteConstant;
import com.moowu.system.entity.SysUser;
import com.moowu.system.mapper.SysUserMapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author weifw
 * @since 2024-10-12
 */
@Repository
public class SysUserDao extends ServiceImpl<SysUserMapper, SysUser> {

    public SysUser selectUserByUserName(String username) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getDelFlag, DeleteConstant.unDeleted);
        queryWrapper.eq(SysUser::getUserName, username);

        return baseMapper.selectOne(queryWrapper);
    }


}
