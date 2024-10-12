package com.moowu.system.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.moowu.common.annotation.Excel;
import com.moowu.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通知公告对象 sys_notice_user
 * 
 * @author Duke_yzl
 * @date 2023-06-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
//链式调用
@Accessors(chain = true)
@TableName("sys_notice_user")
public class SysNoticeUserDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    @TableId
    private Long noticeId;
    /** 用户 id */
    @Excel(name = "用户 id")
    private Long userId;
    /** 是否读取 0 未读 1 已读 */
    @Excel(name = "是否读取 0 未读 1 已读")
    private String isRead;


}
