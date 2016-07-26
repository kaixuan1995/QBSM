package com.qbsm.service.systemManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.bean.Role;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;

@SuppressWarnings("all")
public class RoleManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 查询角色总数
	 * @param place
	 * @return
	 */
	protected List<Integer> queryCount(Role role){
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_role where role_isdel = 1", null));
		return list;
	}
	
	/**
	 * 通过角色查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Place> queryRole(Role role,int pc,int ps) {
		return dao.queryForList("select * from t_role where role_isdel = 1 limit "+(pc-1)*ps +" , "+ps, Role.class);
	}
	
	/**
	 * 查询角色信息
	 * @param place
	 * @return
	 */
	protected List<Place> queryRole(Role role) {
		role.setRole_isdel(0);
		return dao.queryForListByPo(role);
	}
	
	
	/**
	 * 更新角色信息
	 * @param place
	 * @return
	 */
	protected List<String> updateRole(Role role) {
		list = new ArrayList<String>(1);
		if(dao.update(role)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 删除角色信息
	 * @param place
	 * @return
	 */
	protected List<String> deleteRole(Role role) {
		list = new ArrayList<String>(1);
		role.setRole_isdel(1);
		if(list.add(dao.update(role))){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
		
	}
	
	/**
	 * 新增角色信息
	 * @param place
	 * @return
	 */
	protected List<String> saveRole(Role role) {
		list = new ArrayList<Boolean>(1);
		role.setRole_createdata(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(dao.save(role)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
}
