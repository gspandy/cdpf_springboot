package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysRoleResource;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysRoleresourceDao {
    List<SysRoleResource> listRoleResourceByType(int roleId, int type);

    List<SysRoleResource> listRoleResource(int roleId);

    void deleteRoleResource(int roleId);

    void addRoleResource(int roleId, int resourceId);
}
