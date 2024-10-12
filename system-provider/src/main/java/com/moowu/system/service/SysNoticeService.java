package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysNoticeDao;
import com.moowu.system.entity.SysNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysNoticeService  extends BaseService {



    @Autowired
    private SysNoticeDao sysNoticeDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysNotice sysNotice){
            startPage();
            List<SysNotice> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysNotice selectSysNoticeById(Integer id){
            return sysNoticeDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysNotice(SysNotice sysNotice){
            return sysNoticeDao.save(sysNotice);
    }

    /**
     * 修改
     */
    public boolean updateSysNotice(SysNotice sysNotice){
            return sysNoticeDao.updateById(sysNotice);
    }

    /**
     * 删除
     */
    public boolean deleteSysNoticeByIds(List<Integer> ids){
         return sysNoticeDao.removeByIds(ids);
    }


}
