package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysLogininforDao;
import com.moowu.system.entity.SysLogininfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysLogininforService  extends BaseService {



    @Autowired
    private SysLogininforDao sysLogininforDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysLogininfor sysLogininfor){
            startPage();
            List<SysLogininfor> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysLogininfor selectSysLogininforById(Integer id){
            return sysLogininforDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysLogininfor(SysLogininfor sysLogininfor){
            return sysLogininforDao.save(sysLogininfor);
    }

    /**
     * 修改
     */
    public boolean updateSysLogininfor(SysLogininfor sysLogininfor){
            return sysLogininforDao.updateById(sysLogininfor);
    }

    /**
     * 删除
     */
    public boolean deleteSysLogininforByIds(List<Integer> ids){
         return sysLogininforDao.removeByIds(ids);
    }


}
