package com.moowu.system.vo.output;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifw
 * @date 2022/7/12
 */
@Data
public class SysDeptQueryOutput {

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 父部门id
     */
    private Integer parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer orderNum;


    /**
     * 子部门
     */
    private List<SysDeptQueryOutput> children = new ArrayList<>();

}
