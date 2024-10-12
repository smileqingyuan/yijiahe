package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysRoleMenuDao;
import com.moowu.system.entity.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysRoleMenuService  extends BaseService {



    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysRoleMenu sysRoleMenu){
            startPage();
            List<SysRoleMenu> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysRoleMenu selectSysRoleMenuById(Integer id){
            return sysRoleMenuDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysRoleMenu(SysRoleMenu sysRoleMenu){
            return sysRoleMenuDao.save(sysRoleMenu);
    }

    /**
     * 修改
     */
    public boolean updateSysRoleMenu(SysRoleMenu sysRoleMenu){
            return sysRoleMenuDao.updateById(sysRoleMenu);
    }

    /**
     * 删除
     */
    public boolean deleteSysRoleMenuByIds(List<Integer> ids){
         return sysRoleMenuDao.removeByIds(ids);
    }


}
