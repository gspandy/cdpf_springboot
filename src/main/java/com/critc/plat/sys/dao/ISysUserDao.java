package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysUser;
import com.critc.plat.sys.vo.SysUserSearchVO;
import com.critc.plat.util.model.ComboboxVO;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysUserDao {
    int add(SysUser sysUser);

    int update(SysUser sysUser);

    int updatePass(int id, String newPass, String randowmcode);

    int updateInfo(SysUser sysUser);

    int updateStatus(int id, int status);

    int delete(int id);

    SysUser get(int id);

    SysUser getByUsername(String username);

    List<SysUser> list(SysUserSearchVO sysUserSearchVO);

    List<SysUser> listAll();

    int listCount(SysUserSearchVO sysUserSearchVO);

    List<ComboboxVO> listAllUser();
}
