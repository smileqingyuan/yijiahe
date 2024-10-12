package com.moowu.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moowu.common.util.StringUtils;
import com.moowu.system.entity.SysConfig;
import com.moowu.system.mapper.SysConfigMapper;
import com.moowu.system.vo.input.SysConfigQueryInput;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Repository
public class SysConfigDao extends ServiceImpl<SysConfigMapper, SysConfig> {

    public SysConfig selectConfigByKey(String configKey) {

        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConfig::getConfigKey, configKey);
        queryWrapper.last("limit 1");

        return getOne(queryWrapper);
    }

    public List<SysConfig> selectConfigList(SysConfigQueryInput config) {

        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<SysConfig>()
                .like(StringUtils.isNotBlank(config.getConfigName()), SysConfig::getConfigName, config.getConfigName())
                .eq(StringUtils.isNotBlank(config.getConfigType()), SysConfig::getConfigType, config.getConfigType())
                .like(StringUtils.isNotBlank(config.getConfigKey()), SysConfig::getConfigKey, config.getConfigKey());

        return list(queryWrapper);
    }

}

