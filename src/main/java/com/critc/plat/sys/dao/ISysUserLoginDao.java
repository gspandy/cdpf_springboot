package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysUserLogin;
import com.critc.plat.sys.vo.SysUserloginSearchVO;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysUserLoginDao {
    void add(SysUserLogin sysUserLogin);

    SysUserLogin getLastLogin(int userId);

    List<SysUserLogin> list(SysUserloginSearchVO sysUserloginSearchVO);

    int listCount(int userId);
}
