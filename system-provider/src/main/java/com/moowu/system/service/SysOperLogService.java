package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysOperLogDao;
import com.moowu.system.entity.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysOperLogService  extends BaseService {



    @Autowired
    private SysOperLogDao sysOperLogDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysOperLog sysOperLog){
            startPage();
            List<SysOperLog> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysOperLog selectSysOperLogById(Integer id){
            return sysOperLogDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysOperLog(SysOperLog sysOperLog){
            return sysOperLogDao.save(sysOperLog);
    }

    /**
     * 修改
     */
    public boolean updateSysOperLog(SysOperLog sysOperLog){
            return sysOperLogDao.updateById(sysOperLog);
    }

    /**
     * 删除
     */
    public boolean deleteSysOperLogByIds(List<Integer> ids){
         return sysOperLogDao.removeByIds(ids);
    }


}
