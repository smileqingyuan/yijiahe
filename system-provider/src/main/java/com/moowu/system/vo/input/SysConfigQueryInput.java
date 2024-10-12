package com.moowu.system.vo.input;

import lombok.Data;

/**
 * @author weifw
 * @date 2024/10/12
 */
@Data
public class SysConfigQueryInput {

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 系统内置（Y是 N否）
     */
    private String configType;

}
