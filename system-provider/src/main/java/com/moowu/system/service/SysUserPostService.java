package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysUserPostDao;
import com.moowu.system.entity.SysUserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 用户与岗位关联表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysUserPostService  extends BaseService {



    @Autowired
    private SysUserPostDao sysUserPostDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysUserPost sysUserPost){
            startPage();
            List<SysUserPost> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysUserPost selectSysUserPostById(Integer id){
            return sysUserPostDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysUserPost(SysUserPost sysUserPost){
            return sysUserPostDao.save(sysUserPost);
    }

    /**
     * 修改
     */
    public boolean updateSysUserPost(SysUserPost sysUserPost){
            return sysUserPostDao.updateById(sysUserPost);
    }

    /**
     * 删除
     */
    public boolean deleteSysUserPostByIds(List<Integer> ids){
         return sysUserPostDao.removeByIds(ids);
    }


}
