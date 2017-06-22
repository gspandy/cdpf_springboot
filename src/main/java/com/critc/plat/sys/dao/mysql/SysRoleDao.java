package com.critc.plat.sys.dao.mysql;

import com.critc.plat.core.dao.BaseDao;
import com.critc.plat.sys.dao.ISysRoleDao;
import com.critc.plat.sys.model.SysRole;
import com.critc.plat.util.model.ComboboxVO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
@Repository
@Profile("dev")
public class SysRoleDao extends BaseDao<SysRole, SysRole> implements ISysRoleDao {

    /**
     * 新增
     *
     * @param sysRole
     * @return
     */
    public int add(SysRole sysRole) {
        String sql = "insert into t_sys_role(name,description,display_order,created_by,created_at)" +
                " values(:name,:description,:displayOrder,:createdBy,sysdate)";
        return insertForId(sql, sysRole, "id");
    }

    /**
     * 修改
     *
     * @param sysRole
     * @return
     */
    public int update(SysRole sysRole) {
        String sql = "update t_sys_role set name=:name,description=:description,display_order=:displayOrder,last_modified_by=:lastModifiedBy,last_modified_at=sysdate where id=:id";
        return update(sql, sysRole);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_sys_role where id=? ";
        return delete(sql, id);
    }

    public SysRole get(int id) {
        String sql = "select * from t_sys_role where id=? ";
        return get(sql, id);
    }

    /**
     * 角色列表
     *
     * @return
     */
    public List<SysRole> list() {
        String sql = "select t.* from t_sys_role t order by display_order asc ";
        return list(sql);
    }

    /**
     * 角色下拉框
     *
     * @return
     */
    public List<ComboboxVO> listCombo() {
        String sql = "select id value,name content from t_sys_role where 1=1 order by id ";
        return listCombobox(sql);
    }


}
