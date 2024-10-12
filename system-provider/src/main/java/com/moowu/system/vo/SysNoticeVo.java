package com.moowu.system.vo;

import com.moowu.system.dto.SysNoticeUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 通知公告表 sys_notice
 * 
 * @author ruoyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysNoticeVo extends SysNoticeUserDto
{
    /** 公告标题 */
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    private String noticeType;

    /** 公告内容 */
    private String noticeContent;



}
