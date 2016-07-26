package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Goods;
import com.qbsm.bean.Office;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.GoodsManageFormBean;


@SuppressWarnings("all")
public class GoodsManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	
	/**
	 * 通过分页查找物资信息
	 * @param goods
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Goods> queryGoods(Goods goods,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_goods where goods_isdel = 0  ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), Goods.class);
//		return dao.queryForList("select * from t_goods where goods_isdel = 0 limit "+(pc-1)*ps +" , "+ps, Goods.class);
	}
	
	/**
	 * 查找物资总数
	 * @param goods
	 * @return
	 */
	protected List<Integer> queryCount(Goods goods,Filters filters){
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_goods where goods_isdel = ?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{0}));
//		list.add(dao.getDataCount("select count(*) from t_goods where goods_isdel = ?",new Object[]{0}));
		return list;
	}

	/**
	 * 增加物资信息
	 * @param goods
	 * @return
	 */
	protected List<String> saveGoods(Goods goods){
		list = new ArrayList<String>(1);
		if(dao.save(goods)) {
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 删除物资信息
	 * @param officeId
	 * @return
	 */
	protected List<String> deleteGoods(Goods goods){
		list = new ArrayList<String>(1);
		goods.setGoods_isdel(1);
		if(dao.update(goods)){
			list.add(SUCCESS);
		}else {
			list.add(DELETE_ERROR);
		}
		return list;
		
	}
	
	/**
	 * 修改物资信息
	 * @param office
	 * @return
	 */
	protected List<String> updateGoods(Goods goods){
		list = new ArrayList<String>(1);
		if(dao.update(goods)){
			list.add(SUCCESS);
		}else {
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 通过goods信息查找goods
	 * @param office
	 * @return
	 */
	protected List<Office> queryGoods(Goods goods,Filters filters) {
		goods.setGoods_isdel(0);
		return dao.queryForListByPo(goods);
	}
	
	
	
}
