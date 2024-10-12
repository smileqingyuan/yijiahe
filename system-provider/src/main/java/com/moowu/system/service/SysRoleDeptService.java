package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysRoleDeptDao;
import com.moowu.system.entity.SysRoleDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 角色和部门关联表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysRoleDeptService  extends BaseService {



    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysRoleDept sysRoleDept){
            startPage();
            List<SysRoleDept> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysRoleDept selectSysRoleDeptById(Integer id){
            return sysRoleDeptDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysRoleDept(SysRoleDept sysRoleDept){
            return sysRoleDeptDao.save(sysRoleDept);
    }

    /**
     * 修改
     */
    public boolean updateSysRoleDept(SysRoleDept sysRoleDept){
            return sysRoleDeptDao.updateById(sysRoleDept);
    }

    /**
     * 删除
     */
    public boolean deleteSysRoleDeptByIds(List<Integer> ids){
         return sysRoleDeptDao.removeByIds(ids);
    }


}
