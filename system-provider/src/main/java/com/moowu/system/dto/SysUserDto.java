package com.moowu.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author weifw
 * @date 2022/7/6
 */
@Data
public class SysUserDto {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private Short sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Short status;


    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    /**
     * 角色对象
     */
    private List<SysRoleDto> roles;

    /**
     * 角色组
     */
    private List<Integer> roleIds;

    /**
     * 用户类型 0系统用户 1公司管理员 2公司员工
     */
    private Short userType;

    private String remark;

    /**
     * 用户部门名称
     */
    private String deptName;


    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Integer userId) {
        return userId != null && 1L == userId;
    }


}
