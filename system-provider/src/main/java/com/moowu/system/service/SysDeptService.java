package com.moowu.system.service;

import com.moowu.system.dao.SysDeptDao;
import com.moowu.system.dao.SysRoleDao;
import com.moowu.system.dao.SysUserDao;
import com.moowu.system.entity.SysDept;
import com.moowu.system.vo.input.SysDeptQueryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weifw
 * @date 2024/10/12
 */
@Service
public class SysDeptService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysDeptDao sysDeptDao;


    /**
     * 查询部门管理数据
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDeptQueryInput queryInput) {

        List<SysDept> dbItemList = sysDeptDao.findList(queryInput);

        return  null;
    }




}
