package com.critc.plat.sys.dao.oracle;

import com.critc.plat.core.dao.BaseDao;
import com.critc.plat.sys.dao.ISysRoleresourceDao;
import com.critc.plat.sys.model.SysRoleResource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色资源dao
 * Author  孔垂云
 * Date  2017/6/11.
 */
@Repository
@Profile("prod")
public class SysRoleresourceDao extends BaseDao<SysRoleResource, SysRoleResource> implements ISysRoleresourceDao {
    /**
     * 根据角色id获取所有资源
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleResource> listRoleResourceByType(int roleId, int type) {
        String sql = "select r.role_id,r.resource_id,s.code resourceCode,s.url from t_sys_roleresource r,t_sys_resource s where r.resource_id=s.id and r.role_id=? and type=? ";
        return list(sql, roleId, type);
    }

    /**
     * 根据角色id获取所有资源
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleResource> listRoleResource(int roleId) {
        String sql = "select s.url,nvl((select id from t_sys_roleresource where role_id=? and resource_id=s.id),0) resourceId " +
                " from t_sys_resource s  where s.url!='#'";
        return list(sql, roleId);
    }

    /**
     * 删除角色对应的资源
     *
     * @param roleId
     */
    @Override
    public void deleteRoleResource(int roleId) {
        String sql = "delete from t_sys_roleresource where role_id=?";
        delete(sql, roleId);
    }

    /**
     * 新增角色对应资源
     *
     * @param roleId
     * @param resourceId
     */
    @Override
    public void addRoleResource(int roleId, int resourceId) {
        String sql = "insert into t_sys_roleresource(id,role_id,resource_id) values(seq_t_sys_resource.nextval,?,?)";
        update(sql, roleId, resourceId);
    }


}
