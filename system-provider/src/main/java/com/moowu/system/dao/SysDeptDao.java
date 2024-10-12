package com.moowu.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moowu.common.util.StringUtils;
import com.moowu.system.entity.SysDept;
import com.moowu.system.mapper.SysDeptMapper;
import com.moowu.system.vo.input.SysDeptQueryInput;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author weifw
 * @since 2024-10-12
 */
@Repository
public class SysDeptDao extends ServiceImpl<SysDeptMapper, SysDept> {

    public List<SysDept> findList(SysDeptQueryInput input){

        LambdaQueryWrapper<SysDept> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysDept::getDelFlag, "0");
        if(input!=null){
            if(StringUtils.isNotEmpty(input.getDeptName())){
                queryWrapper.like(SysDept::getDeptName, input.getDeptName());
            }
        }
        return baseMapper.selectList(queryWrapper);

    }


}
