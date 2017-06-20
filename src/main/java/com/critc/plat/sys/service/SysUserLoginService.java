package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.SysUserLoginDao;
import com.critc.plat.sys.model.SysUserLogin;
import com.critc.plat.sys.vo.SysUserloginSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

    /**
     * 登录时新增登录信息
     *
     * @param sysUserLogin
     * @return
     */
    @Async
    public void addTx(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

    /**
     * 取得最后登录信息
     *
     * @param user_id
     * @return
     */
    public SysUserLogin getLastLogin(int user_id) {
        return sysUserLoginDao.getLastLogin(user_id);
    }

    public List<SysUserLogin> list(SysUserloginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.list(sysUserloginSearchVO);
    }

    /**
     * 查询用户登录总数
     *
     * @param sysUserloginSearchVO
     * @return
     */
    public int listCount(SysUserloginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.listCount(sysUserloginSearchVO.getUserId());
    }

}
