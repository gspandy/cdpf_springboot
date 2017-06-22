package com.critc.plat.sys.dao.mysql;

import com.critc.plat.core.dao.BaseDao;
import com.critc.plat.sys.dao.ISysUserDao;
import com.critc.plat.sys.model.SysUser;
import com.critc.plat.sys.vo.SysUserSearchVO;
import com.critc.plat.util.model.ComboboxVO;
import com.critc.plat.util.page.PageUtil;
import com.critc.plat.util.string.StringUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
@Repository
@Profile("dev")
public class SysUserDao extends BaseDao<SysUser, SysUserSearchVO> implements ISysUserDao {
    public int add(SysUser sysUser) {
        String sql = "insert into t_sys_user(username,password,randomcode,status,realname,mobile,created_at,created_by,role_id)";
        sql += " values(:username,:password,:randomcode,1,:realname,:mobile,sysdate,:createdBy,:roleId)";
        return insertForId(sql, sysUser, "id");
    }

    public int update(SysUser sysUser) {
        String sql = "update t_sys_user set realname=:realname,role_id=:roleId,mobile=:mobile,last_modified_by=:lastModifiedBy,last_modified_at=sysdate where id=:id ";
        return update(sql, sysUser);
    }


    /**
     * 修改密码
     *
     * @param id
     * @param newPass
     * @param randowmcode
     * @return
     */
    public int updatePass(int id, String newPass, String randowmcode) {
        String sql = "update t_sys_user set password=?,randomcode=?  where id=? ";
        return update(sql, newPass, randowmcode, id);
    }

    /**
     * 修改个人信息，用户自己操作
     *
     * @param sysUser
     * @return
     */
    public int updateInfo(SysUser sysUser) {
        String sql = "update t_sys_user set realname=:realname,telephone=:telephone where id=:id";
        return update(sql, sysUser);
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        String sql = "update t_sys_user set status=?  where id=?";
        return update(sql, status, id);
    }

    public int delete(int id) {
        String sql = "delete from t_sys_user where id=?";
        return delete(sql, id);
    }

    public SysUser get(int id) {
        String sql = "select * from t_sys_user where id=?";
        return get(sql, id);
    }

    /**
     * 根据username获取sysUser
     *
     * @param username
     * @return
     */
    public SysUser getByUsername(String username) {
        String sql = "select t.*,(select name from t_sys_role where id=role_id) roleName from t_sys_user t where username=?";
        return get(sql, username);
    }

    /**
     * 查询用户信息
     *
     * @param sysUserSearchVO
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        String sql = "select t.*,(select name from t_sys_role where id=t.role_id) roleName  from t_sys_user t where 1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        sql += " order by id asc";
        sql = PageUtil.createOraclePageSQL(sql, sysUserSearchVO.getPageIndex());
        return list(sql, sysUserSearchVO);
    }

    public List<SysUser> listAll() {
        String sql = "select t.*,(select name from t_sys_role where id=role_id) roleName  from t_sys_user t ";
        sql += " order by id asc";
        return list(sql);
    }

    /**
     * 查询用户总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int listCount(SysUserSearchVO sysUserSearchVO) {
        String sql = "select count(*) from t_sys_user where 1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        return listCount(sql, sysUserSearchVO);
    }

    private String createSearchSql(SysUserSearchVO sysUserSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername())) {
            sql += " and username=:username";
        }
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname())) {
            sql += " and realname like :realnameStr";
        }
        if (sysUserSearchVO.getRoleId() != null) {
            sql += " and role_id=:roleId";
        }
        if (sysUserSearchVO.getStatus() != null) {
            sql += " and status=:status";
        }
        return sql;
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        String sql = "select id value,username content from t_sys_user  order by id";
        return listCombobox(sql);
    }
}
