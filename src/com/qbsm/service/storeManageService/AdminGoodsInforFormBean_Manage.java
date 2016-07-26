package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.AdminGoodsInforFormBean;


@SuppressWarnings("all")
public class AdminGoodsInforFormBean_Manage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";

	
	/**
	 * 用于显示管理员的信息列表
	 * @param adminGoodsInforFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<AdminGoodsInforFormBean> queryAll(AdminGoodsInforFormBean adminGoodsInforFormBean,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(t_goods_number.goods_number) AS goods_number,t_goods.*,t_user.*,t_barcode.*,t_type.*" +
				" FROM  t_goods LEFT OUTER JOIN t_type ON t_goods.type_id_fk = t_type.type_id" +
				" LEFT OUTER JOIN t_barcode ON  t_goods.goods_id = t_barcode.goods_id_fk" +
				" LEFT OUTER JOIN t_user ON t_barcode.user_id_fk = t_user.user_id" +
				" LEFT OUTER JOIN t_goods_number ON t_goods.goods_id = t_goods_number.goods_id_fk GROUP BY(t_goods.goods_id) ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), AdminGoodsInforFormBean.class);
	}
	
	/**
	 * 用于查询总共有多少条物资信息
	 * @param adminGoodsInforFormBean
	 * @return
	 */
	protected List<Integer> queryCount(AdminGoodsInforFormBean adminGoodsInforFormBean,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_goods where 1 = 1");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",null));
		return list;
	}
	
	
	
	
	/**
	 * 通过用户所属的科室，查找该科室的库存物资
	 * @param adminGoodsInforFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<AdminGoodsInforFormBean> queryAllGoodsByStorehouse_id(AdminGoodsInforFormBean adminGoodsInforFormBean,int user_id,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * " +
				" FROM " +
				" t_inventory " +
				" LEFT OUTER JOIN t_goods ON t_inventory.goods_id_fk = t_goods.goods_id" +
				" LEFT OUTER JOIN t_storehouse ON t_inventory.storehouse_id_fk = t_storehouse.storehouse_id" +
				" LEFT OUTER JOIN t_barcode ON t_barcode.goods_id_fk =  t_inventory.goods_id_fk" +
				" LEFT OUTER JOIN t_goods_number ON t_goods_number.goods_id_fk = t_inventory.goods_id_fk" +
				" LEFT OUTER JOIN t_user ON t_barcode.user_id_fk = t_user.user_id" +
				" LEFT OUTER JOIN t_type ON t_goods.type_id_fk = t_type.type_id" +
				" WHERE t_inventory.storehouse_id_fk IN (SELECT t_office.storehouse_id_fk FROM t_user,t_office WHERE t_user.office_id_fk = t_office.office_id AND t_user.user_id=?) ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{user_id}, AdminGoodsInforFormBean.class);
		
	}
	
	/**
	 * 通过科室id查找该科室总共有多少物资数目
	 * @param adminGoodsInforFormBean
	 * @return
	 */
	protected List<Integer> queryCount(AdminGoodsInforFormBean adminGoodsInforFormBean,int user_id,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(t_inventory.goods_id_fk) from t_inventory" +
				" WHERE " +
				"		t_inventory.storehouse_id_fk " +
				"		IN (" +
				"			SELECT " +
				"			t_office.storehouse_id_fk " +
				"			FROM t_user,t_office " +
				"			WHERE t_user.office_id_fk = t_office.office_id " +
				"			AND t_user.user_id=?" +
				"		)");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",new Object[]{user_id}));
		
		return list;
	}
	
	
	protected List<AdminGoodsInforFormBean> queryGoodsByGoods_name(AdminGoodsInforFormBean adminGoodsInforFormBean,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(t_goods_number.goods_number) AS goods_number,t_goods.*,t_user.*,t_barcode.barcode_validity AS barcode_validity,t_type.type_name AS type_name" +
				" FROM  t_goods LEFT OUTER JOIN t_type ON t_goods.type_id_fk = t_type.type_id" +
				" LEFT OUTER JOIN t_barcode ON  t_goods.goods_id = t_barcode.goods_id_fk" +
				" LEFT OUTER JOIN t_user ON t_barcode.user_id_fk = t_user.user_id" +
				" LEFT OUTER JOIN t_goods_number ON t_goods.goods_id = t_goods_number.goods_id_fk");
		return dao.queryForList(filters.toWhereLimitSql(sql, pc, ps)+" GROUP BY(t_goods.goods_id) ", AdminGoodsInforFormBean.class);
	}
	
	
	

}
