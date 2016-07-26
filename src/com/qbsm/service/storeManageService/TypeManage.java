package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.bean.Role;
import com.qbsm.bean.Type;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;


@SuppressWarnings("all")
public class TypeManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	private final String SYSTEM_ERROR = MessageEnum.E11111+"";
	
	/**
	 * 查询角色总数
	 * @param place
	 * @return
	 */
	protected List<Integer> queryCount(Type type,Filters filters){
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from type where type_isdel = 0");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
//		list.add(dao.getDataCount("select count(*) from type where type_isdel = 0", null));
		return list;
	}
	
	/**
	 * 通过角色查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Place> queryType(Type type,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_type where type_isdel = 0");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),  Role.class);
//		return dao.queryForList("select * from t_type where type_isdel = 0 limit "+(pc-1)*ps +" , "+ps, Role.class);
	}
	
	/**
	 * 查询角色信息
	 * @param place
	 * @return
	 */
	protected List<Place> queryType(Type type) {
		type.setType_isdel(0);
		return dao.queryForListByPo(type);
	}
	
	
	/**
	 * 更新角色信息
	 * @param place
	 * @return
	 */
	protected List<String> updateType(Type type) {
		list = new ArrayList<String>(1);
		if(dao.update(type)){
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
	protected List<String> deleteType(Type type) {
		list = new ArrayList<String>(1);
		type.setType_isdel(1);
		if(list.add(dao.update(type))){
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
	protected List<String> saveType(Type type) {
		list = new ArrayList<String>(1);
		if(dao.save(type)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
}
