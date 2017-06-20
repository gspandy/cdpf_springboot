package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysUserLoginDao;
import com.critc.plat.sys.model.SysUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

    /**
     * 登录时新增登录信息
     *
     * @param sysUserLogin
     * @return
     */
    @Async
    @com.critc.plat.core.annotation.Tx
    public void add(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

}
