package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysRole;
import com.critc.plat.util.model.ComboboxVO;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysRoleDao {
    int add(SysRole sysRole);

    int update(SysRole sysRole);

    int delete(int id);

    SysRole get(int id);

    List<SysRole> list();

    List<ComboboxVO> listCombo();
}
