package com.qbsm.service.systemManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Storehouse;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;


@SuppressWarnings("all")
public class StorehouseManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	
	/**
	 * 删除仓库信息
	 * @param storehouse
	 * @return
	 */
	protected List<String> deleteStorehouse(Storehouse storehouse) {
		list = new ArrayList<String>(1);
		if(dao.delete(storehouse)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}

	/**
	 * 更新仓库信息
	 */
	protected List<String> updateStorehouse(Storehouse storehouse) {
		list = new ArrayList<String>(1);
		if(dao.update(storehouse)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}

	/**
	 * 添加仓库信息
	 */
	protected List<String> saveStorehouse(Storehouse storehouse) {
		list = new ArrayList<String>(1);
		if(dao.save(storehouse)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}

	/**
	 * 查找仓库信息总数
	 */
	protected List<Integer> queryCount(Storehouse storehouse,Filters filters) {
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_storehouse where storehouse_isdel = 0"+
				filters.toAndSql(),null));
		return list;
	}

	/**
	 * 通过分页查找仓库信息
	 */
	protected List<Storehouse> queryStorehouse(Storehouse storehouse, int pc,
			int ps,Filters filters) {
		StringBuffer sql = new StringBuffer("select * from t_storehouse where storehouse_isdel = 0");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),Storehouse.class);
	}

	/**
	 * 查找仓库信息
	 */
	protected List<Storehouse> queryStorehouse(Storehouse storehouse) {
		return dao.queryForListByPo(storehouse);
	}
	/**
	 * 查找所有仓库信息
	 */
	public List<Storehouse> queryStorehouse() {
		return dao.queryForList("select * from t_storehouse", Storehouse.class);
	}
}
