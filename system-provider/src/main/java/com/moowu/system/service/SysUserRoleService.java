package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysUserRoleDao;
import com.moowu.system.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysUserRoleService  extends BaseService {



    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysUserRole sysUserRole){
            startPage();
            List<SysUserRole> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysUserRole selectSysUserRoleById(Integer id){
            return sysUserRoleDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysUserRole(SysUserRole sysUserRole){
            return sysUserRoleDao.save(sysUserRole);
    }

    /**
     * 修改
     */
    public boolean updateSysUserRole(SysUserRole sysUserRole){
            return sysUserRoleDao.updateById(sysUserRole);
    }

    /**
     * 删除
     */
    public boolean deleteSysUserRoleByIds(List<Integer> ids){
         return sysUserRoleDao.removeByIds(ids);
    }


}
