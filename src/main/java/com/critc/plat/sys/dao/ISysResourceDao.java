package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysResource;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysResourceDao {
    int add(SysResource sysResource);

    int update(SysResource sysResource);

    int delete(int id);

    int deleteByParentId(int id);

    SysResource get(int id);

    List<SysResource> list();

    List<SysResource> listByType(int type);

    int getChildCount(int id);

    List<SysResource> listByParentId(int parentId);

    List<SysResource> listByRoleId(int roleId);

    SysResource getByModuleCode(String code);

    SysResource getByUrl(String url);
}
