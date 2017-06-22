package com.critc.plat.sys.dao.mysql;

import com.critc.plat.core.dao.BaseDao;
import com.critc.plat.sys.dao.ISysLogDao;
import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.vo.SysLogSearchVO;
import com.critc.plat.util.page.PageUtil;
import com.critc.plat.util.string.StringUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统日志dao
 *
 * @author 孔垂云
 */
@Repository
@Profile("dev")
public class SysLogDao extends BaseDao<SysLog, SysLogSearchVO> implements ISysLogDao {

    public int add(SysLog sysLog) {
        String sql = "insert into t_sys_log(user_id,opera_date,opera_ip,module_name,opera_name,opera_url,opera_params)"
                + " values(:userId,sysdate,:operaIp,:moduleName,:operaName,:operaUrl,:operaParams)";
        return insert(sql, sysLog);
    }

    /**
     * 查询
     *
     * @return
     */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        String sql = "select l.*,u.realname realname  from t_sys_log l,t_sys_user u where l.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        sql = PageUtil.createOraclePageSQL(sql, sysLogSearchVO.getPageIndex());
        return list(sql, sysLogSearchVO);
    }

    /**
     * 查询所有，不分页
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        String sql = "select l.*,u.code user_code,u.realname realname  from t_sys_log l,t_sys_user u where l.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        List<SysLog> list = list(sql, sysLogSearchVO);
        return list;
    }

    /**
     * 查询
     *
     * @param sysLogSearchVO
     * @return
     */
    public int listCount(SysLogSearchVO sysLogSearchVO) {
        String sql = "select count(*) from t_sys_log where 1=1 ";
        sql += createSearchSql(sysLogSearchVO);
        return listCount(sql, sysLogSearchVO);
    }

    private String createSearchSql(SysLogSearchVO sysLogSearchVO) {
        String sql = "";
        if (sysLogSearchVO.getUserId() != null) {
            sql += " and user_id=:userId";
        } else {
            sql += " and user_id=0";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getStartDate())) {
            sql += " and to_char(opera_date,'yyyy-mm-dd')>=:startDate";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getEndDate())) {
            sql += " and to_char(opera_date,'yyyy-mm-dd')<=:endDate";
        }
        return sql;
    }
}
