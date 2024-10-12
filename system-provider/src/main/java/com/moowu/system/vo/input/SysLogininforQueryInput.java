package com.moowu.system.vo.input;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author weifw
 * @date 2021/6/16
 */
@Data
public class SysLogininforQueryInput {

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    private Short status;

    /**
     * 登录IP地址
     */
    private String ipaddr;


    private LocalDateTime beginTime;

    private LocalDateTime endTime;


}
