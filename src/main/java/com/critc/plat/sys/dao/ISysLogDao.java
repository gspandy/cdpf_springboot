package com.critc.plat.sys.dao;

import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.vo.SysLogSearchVO;

import java.util.List;

/**
 * @Description:
 * @Author: 靳磊
 * @Date: 2017/6/22:21
 */
public interface ISysLogDao {
    int add(SysLog sysLog);

    List<SysLog> list(SysLogSearchVO sysLogSearchVO);

    List<SysLog> listAll(SysLogSearchVO sysLogSearchVO);

    int listCount(SysLogSearchVO sysLogSearchVO);
}
