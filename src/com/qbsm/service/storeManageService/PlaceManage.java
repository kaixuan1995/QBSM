package com.qbsm.service.storeManageService;

import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;

public class PlaceManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	
	
	/**
	 * 通过申请单号查找仓库分区表
	 * @param place
	 * @param apply_id
	 * @return
	 */
	protected List<Place> queryPlace(Place place,String apply_id) {
		return dao.queryForList("select t_place.* from " +
				" t_apply,t_place" +
				" where t_apply.storehouse_id_fk = t_place.storehouse_id_fk" +
				" AND t_apply.apply_id = '"+apply_id+"'", Place.class);
	}
	
}
