package com.qbsm.service.systemManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;


@SuppressWarnings("all")
public class PlaceManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 查询分区总数
	 * @param place
	 * @return
	 */
	protected List<Integer> queryCount(Place place,Filters filters){
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_place where place_isdel = 0"+
				filters.toAndSql(),null));
		return list;
	}
	
	/**
	 * 通过分页查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Place> queryPlace(Place place,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer("");
		if(place.getStorehouse_id_fk()==null){
			sql.append("select * from t_place where place_isdel = 0");
		}else{
			sql.append("select * from t_place where place_isdel = 0 " +
					"and storehouse_id_fk = "+ place.getStorehouse_id_fk());
		}
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),Place.class);
	}
	
	/**
	 * 查询分区信息
	 * @param place
	 * @return
	 */
	protected List<Place> queryPlcae(Place place) {
		place.setPlace_isdel(0);
		return dao.queryForListByPo(place);
	}
	
	
	/**
	 * 更新分区信息
	 * @param place
	 * @return
	 */
	protected List<String> updatePlace(Place place) {
		list = new ArrayList<String>(1);
		if(dao.update(place)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 删除分区信息
	 * @param place
	 * @return
	 */
	protected List<String> deletePlace(Place place) {
		list = new ArrayList<String>(1);
		place.setPlace_isdel(1);
		if(dao.update(place)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}
	/**
	 * 新增分区信息
	 * @param place
	 * @return
	 */
	protected List<String> savePlace(Place place) {
		list = new ArrayList<String>(1);
		if(dao.save(place)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
}
