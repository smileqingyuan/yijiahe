package com.moowu.system.vo.input;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author weifw
 * @since 2022-07-07
 */
@Data
public class SysOperLogQueryInput {

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型;（0其它 1新增 2修改 3删除）
     */
    private List<Short> businessTypes;

    /**
     * 操作人员
     */
    private String operName;


    /**
     * 操作状态;（0正常 1异常）
     */
    private Short status;



}
