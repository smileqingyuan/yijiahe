package com.moowu.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Duke_yzl
 * @date 20230629.01
 * @describe：消息下发用户
 */
@Data
public class NoticeSendUserVo {

    /**
     * 消息主键
     */
    private Long noticeId;

    /**
     * 下发类型 0 全部 1 按照部门 2 按照用户
     */
    private Long type;

    /**
     * 部门 id
     */
    private Long deptId;
    /**
     * 用户 id
     */
    private List<Long> userIds;
}