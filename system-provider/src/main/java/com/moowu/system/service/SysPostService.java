package com.moowu.system.service;

import com.moowu.common.core.page.TableDataInfo;
import com.moowu.common.core.service.BaseService;
import com.moowu.system.dao.SysPostDao;
import com.moowu.system.entity.SysPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;


/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author weifw
 * @since 2024-10-11
 */
@Service
public class SysPostService  extends BaseService {



    @Autowired
    private SysPostDao sysPostDao;

    /**
     * 查询列表
     */
    public TableDataInfo list(SysPost sysPost){
            startPage();
            List<SysPost> list = new ArrayList();
            return getDataTable(list);
    }


    /**
     * 获取详细信息
     */
    public SysPost selectSysPostById(Integer id){
            return sysPostDao.getById(id);
    }

    /**
     * 新增
     */
    public boolean insertSysPost(SysPost sysPost){
            return sysPostDao.save(sysPost);
    }

    /**
     * 修改
     */
    public boolean updateSysPost(SysPost sysPost){
            return sysPostDao.updateById(sysPost);
    }

    /**
     * 删除
     */
    public boolean deleteSysPostByIds(List<Integer> ids){
         return sysPostDao.removeByIds(ids);
    }


}
