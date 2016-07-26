package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.GoodsType;
import com.qbsm.bean.Place;
import com.qbsm.bean.Role;
import com.qbsm.bean.Type;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;


@SuppressWarnings("all")
public class GoodsTypeManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 查询物资类别总数
	 * @param place
	 * @return
	 */
	protected List<Integer> queryCount(GoodsType type,Filters filters){
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_goodstype where type_isdel = 0");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
//		list.add(dao.getDataCount("select count(*) from t_goodstype where type_isdel = 0", null));
		return list;
	}
	
	/**
	 * 通过物资类别（分页）
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsType> queryGoodsType(GoodsType type,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_goodstype where type_isdel = 0 ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsType.class);
//		return dao.queryForList("select * from t_goodstype where type_isdel = 0 limit "+(pc-1)*ps +" , "+ps, GoodsType.class);
	}
	
	/**
	 * 查询物资类别
	 * @param place
	 * @return
	 */
	protected List<GoodsType> queryGoodsType(GoodsType type) {
		StringBuffer sql = new StringBuffer();
		type.setType_isdel(0);
		return dao.queryForListByPo(type);
	}
	
	
	/**
	 * 更新物资类别
	 * @param place
	 * @return
	 */
	protected List<String> updateGoodsType(GoodsType type) {
		list = new ArrayList<String>(1);
		if(dao.update(type)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 删除物资类别
	 * @param place
	 * @return
	 */
	protected List<String> deleteGoodsType(GoodsType type) {
		list = new ArrayList<String>(1);
		type.setType_isdel(1);
		if(dao.update(type)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
		
	}
	
	/**
	 * 新增物资类别
	 * @param place
	 * @return
	 */
	protected List<String> saveGoodsType(GoodsType type) {
		list = new ArrayList<String>(1);
		if(dao.save(type)) {
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
}
