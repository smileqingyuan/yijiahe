package com.moowu.system.dto;


import lombok.Data;

import java.util.List;

/**
 * @author weifw
 * @date 2022/7/6
 */
@Data
public class SysRoleDto {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    private boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    private boolean deptCheckStrictly;

    /**
     * 角色状态;（0正常1停用）
     */
    private Short status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单组
     */
    private List<Integer> menuIds;


}
