package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Role_Privilege;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;




/*角色权限的管理*/
@SuppressWarnings("all")
public class Role_privilegeManage {
	
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 删除角色权限
	 * 
	 * @param role_Privilege
	 * @return
	 */
	protected List<String> deletePrivilege(Role_Privilege role_Privilege){
		list = new ArrayList<String>(1);
		role_Privilege.setRole_privilege_isdel(1);
		if(dao.update(role_Privilege)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}

	/**
	 * 添加角色权限
	 * @param role_Privilege
	 * @return
	 */
	protected List<String> savePrivilege(Role_Privilege role_Privilege){
		list = new ArrayList<String>(1);
		if(dao.save(role_Privilege)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}

	/**
	 * 修改角色权限信息
	 * 
	 * @param role_Privilege
	 * @param newPrivilegeId
	 *            ：新的权限id
	 * @return
	 */
	protected List<String> updatePrivilege(Role_Privilege role_Privilege){
		list = new ArrayList<Boolean>(1);
		if(dao.update(role_Privilege)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}

	/**
	 * 查找角色权限表
	 * @param role_id
	 *            ：角色id
	 * @return
	 */
	protected List<Role_Privilege> queryPrivilege(Role_Privilege role_Privilege){
		role_Privilege.setRole_privilege_isdel(0);
		list = dao.queryForListByPo(role_Privilege);
		return list;
	}
	
}
