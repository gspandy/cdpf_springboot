package com.critc.plat.util.web;

import com.critc.plat.core.spring.SpringContextHolder;
import com.critc.plat.sys.service.SysRoleService;
import com.critc.plat.util.session.SessionUtil;
import com.critc.plat.util.session.UserSession;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面标签，用于直接处理页面数据，展示使用
 *
 * @author chykong
 */
public class WebTag {

    /**
     * 生成菜单
     *
     * @param request 请求
     * @return 菜单
     */
    public static String createMenu(HttpServletRequest request) {
        UserSession userSession = SessionUtil.getUserSession(request);
        SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
        return sysRoleService.createMenuStr(userSession.getRoleId());
    }

    /**
     * 获取当前用户的模块（菜单）列表
     *
     * @param request 请求
     * @return 当前用户的模块（菜单）列表
     *//*
      public static List<SysModule> getCurrentModule(HttpServletRequest request) {
          UserSession userSession = SessionUtil.getUserSession(request);
	  SysRoleService sysRoleService =
	  SpringContextHolder.getBean("sysRoleService");

	  return sysRoleService.listModuleByRoleId(userSession == null ? 1 :
	  userSession.getRole_id());
      }*/

    /**
     * 判断按钮权限
     *
     * @param buttonCode
     * @return
     */
    public static boolean isPrivilege(String buttonCode) {
        SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
        return sysRoleService.checkBtnPrivilege(buttonCode);
    }

    /**
     * 获取用户状态，系统管理-用户管理用到
     *
     * @param user_status
     * @return
     */
    public static String getUserStatus(Integer user_status) {
        if (user_status == 1)
            return "<span class=\"label label-success arrowed\">正常</span>";
        else if (user_status == 2)
            return "<span class=\"label label-warning arrowed\">已锁定</span>";
        else
            return "";
    }

}
