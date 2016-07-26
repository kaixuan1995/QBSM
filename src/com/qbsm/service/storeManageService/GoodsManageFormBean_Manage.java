package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.qbsm.bean.Barcode;
import com.qbsm.bean.Goods;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.GoodsManageFormBean;

@SuppressWarnings("all")
public class GoodsManageFormBean_Manage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;

//	/**
//	 * 通过vo对象进行物资信息查询
//	 * 
//	 * @param goodsManageFormBean
//	 * @return
//	 */
//	protected List<GoodsManageFormBean> queryGoodsFormBean(
//			GoodsManageFormBean goodsManageFormBean) {
//		return dao.queryForListByVo(goodsManageFormBean);
//	}
	
	
	
	

	/**
	 * 查询分页的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		if (goodsManageFormBean.getGoods_id() == null) {
			sql.append("SELECT * FROM "
				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
				+ " WHERE t_goods.goods_id = t_barcode.goods_id_fk "
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
				+ " AND t_barcode.user_id_fk = t_user.user_id ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//			return dao.queryForList("SELECT * FROM "
//				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
//				+ " WHERE t_goods.goods_id = t_barcode.goods_id_fk "
//				+ " AND t_goods.type_id_fk = t_type.type_id"
//				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
//				+ " AND t_barcode.user_id_fk = t_user.user_id"
//				+ " limit " + (pc - 1) * ps + "," + ps,
//				GoodsManageFormBean.class);
		} else {
			
			sql.append("SELECT * FROM "+ 
				" t_goods,t_type,t_barcode,t_user,t_inventory"+
			    " WHERE t_goods.goods_id= t_barcode.goods_id_fk " +
				" AND t_goods.type_id_fk = t_type.type_id"+
				" AND t_inventory.goods_id_fk = t_goods.goods_id " +
				" AND t_barcode.user_id_fk = t_user.user_id "+
				" AND t_goods.goods_id = ? ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{goodsManageFormBean.getGoods_id()}, GoodsManageFormBean.class);
//			return dao.queryForList("SELECT * FROM "+ 
//				" t_goods,t_type,t_barcode,t_user,t_inventory"+
//			    " WHERE t_goods.goods_id= t_barcode.goods_id_fk " +
//				" AND t_goods.type_id_fk = t_type.type_id"+
//				" AND t_inventory.goods_id_fk = t_goods.goods_id " +
//				" AND t_barcode.user_id_fk = t_user.user_id "+
//				" AND t_goods.goods_id = "+goodsManageFormBean.getGoods_id()+
//				" limit " + (pc - 1) * ps + "," + ps,
//				GoodsManageFormBean.class);
		}
	}
	
	/**
	 * 通过仓库查找物资信息
	 * @param goodsManageFormBean
	 * @param storehouse_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean,String storehouse_id, int pc, int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_inventory,t_goods,t_type" +
				" where t_inventory.storehouse_id_fk = "+storehouse_id +
				" AND t_inventory.goods_id_fk = t_goods.goods_id" +
				" AND t_goods.type_id_fk = t_type.type_id");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_inventory,t_goods,t_type" +
//				" where t_inventory.storehouse_id_fk = "+storehouse_id +
//				" AND t_inventory.goods_id_fk = t_goods.goods_id" +
//				" AND t_goods.type_id_fk = t_type.type_id", GoodsManageFormBean.class);
	}
	
	
	/**
	 * 通过仓库id查找物资信息总数
	 * @param goodsManageFormBean
	 * @param storehouse_id
	 * @return
	 */
	protected List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,String storehouse_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from " +
				" t_inventory" +
				" where t_inventory.storehouse_id_fk = ?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{storehouse_id}));
		
//		list.add(dao.getDataCount("select count(*) from " +
//				" t_inventory" +
//				" where t_inventory.storehouse_id_fk = ?", new Object[]{storehouse_id}));
		return list;
		
	}

	/**
	 * 查询分页的物资信息总数
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,
			HashSet<String> goods_ids) {
		list = new ArrayList<Integer>(1);
		int i = 0;
		Object[] objs = goods_ids.toArray();
		for (Object obj : objs) {
			String goods_id = obj + "";
			goods_id = goods_id.replace(",", "");
			if (goods_id != "") {
				i++;
			}
		}
		list.add(i);
		return list;
	}

	/**
	 * 查询物资信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,Filters filters) {
		if(goodsManageFormBean != null) {
			StringBuffer sql = new StringBuffer();
			Goods goods = new Goods();
			User user = new User();
			Barcode barcode = new Barcode();
			ResetBeanUtils.copy(goodsManageFormBean, user);
			ResetBeanUtils.copy(goodsManageFormBean, goods);
			ResetBeanUtils.copy(goodsManageFormBean, barcode);
			list = new ArrayList<Integer>(1);
			list.add(dao.getDataCount(user, goods, barcode));
			return list;
		}else{
			list.add(0);
			return list;
		}
		
	}

	/**
	 * 查询物资信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,
			String[] goods_ids) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		list.add(goods_ids.length);
		return list;
	}

	/**
	 * 查询分页的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryCheckGoodsFormBean(
			GoodsManageFormBean goodsManageFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		if (goodsManageFormBean.getGoods_id() == null) {
			sql.append("SELECT * FROM "
			+ " t_goods,t_type,t_barcode,t_user,t_inventory"
			+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk " 
			+ " AND t_goods.type_id_fk = t_type.type_id"
			+ " AND t_inventory.goods_id_fk = t_goods.goods_id " 
			+ " AND t_barcode.user_id_fk = t_user.user_id ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//			return dao.queryForList("SELECT * FROM "
//			+ " t_goods,t_type,t_barcode,t_user,t_inventory"
//			+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk " 
//			+ " AND t_goods.type_id_fk = t_type.type_id"
//			+ " AND t_inventory.goods_id_fk = t_goods.goods_id " 
//			+ " AND t_barcode.user_id_fk = t_user.user_id "
//			+ " ADN  limit " + (pc - 1) * ps + "," + ps,GoodsManageFormBean.class);
		} else {
			sql.append("SELECT * FROM "
			+ " t_goods,t_type,t_barcode,t_user,t_inventory"
			+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk" 
			+ " AND t_goods.type_id_fk = t_type.type_id"
			+ " AND ti.goods_id_fk = t_goods.goods_id " 
			+ " AND t_barcode.user_id_fk = t_user.user_id "
			+ " AND t_goods.goods_id = ? ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{goodsManageFormBean.getGoods_id()}, GoodsManageFormBean.class);
//			return dao.queryForList("SELECT * FROM "
//			+ " t_goods,t_type,t_barcode,t_user,t_inventory"
//			+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk" 
//			+ " AND t_goods.type_id_fk = t_type.type_id"
//			+ " AND ti.goods_id_fk = t_goods.goods_id " 
//			+ " AND t_barcode.user_id_fk = t_user.user_id "
//			+ " AND t_goods.goods_id = "+ goodsManageFormBean.getGoods_id()
//			+ " limit " + (pc - 1) * ps + "," + ps,GoodsManageFormBean.class);
		}
	}

	/**
	 * 查询分页的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryGoodsFormBean(
			GoodsManageFormBean goodsManageFormBean, int pc, int ps,
			Set<String> goods_ids,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<GoodsManageFormBean>();
		Object[] objs = goods_ids.toArray();
		String goods_id_str = "";
		for (int i = 0; i < objs.length; i++) {
			Object obj = objs[i] + "";
			String goods_id = obj + "";
			goods_id = goods_id.replace(",", "");
			if (goods_id != "") {
				if (i != objs.length - 1) {
					goods_id_str = goods_id_str + goods_id + " , ";
				} else {
					goods_id_str = goods_id_str + goods_id;
				}
			}
		}
		sql.append("SELECT * FROM "
				+ " t_goods,t_type,t_inventory"
				+ " WHERE t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id" 
				+ " AND t_goods.goods_isdel = 0"
				+ " AND t_goods.goods_id in (" + goods_id_str + ")  ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),  GoodsManageFormBean.class);
//		list = dao.queryForList("SELECT * FROM "
//				+ " t_goods,t_type,t_inventory"
//				+ " WHERE t_goods.type_id_fk = t_type.type_id"
//				+ " AND t_inventory.goods_id_fk = t_goods.goods_id" 
//				+ " AND t_goods.goods_isdel = 0"
//				+ " AND t_goods.goods_id in (" + goods_id_str + ") limit " + (pc - 1)
//				* ps + "," + ps, GoodsManageFormBean.class);
//		return list;
	}

	/**
	 * 查询物资信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryCheckCount(
			GoodsManageFormBean goodsManageFormBean,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(t_goods.goods_id) FROM "
				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk "
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
				+ " AND t_barcode.user_id_fk = t_user.user_id ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		return list;
	}

	/**
	 * 查询分页的库存中的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryGoodsManageFormBean(
			GoodsManageFormBean goodsManageFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk " 
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id " 
				+ " AND t_barcode.user_id_fk = t_user.user_id ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//		return dao.queryForList("SELECT * FROM "
//				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
//				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk " 
//				+ " AND t_goods.type_id_fk = t_type.type_id"
//				+ " AND t_inventory.goods_id_fk = t_goods.goods_id " 
//				+ " AND t_barcode.user_id_fk = t_user.user_id"
//				+ " limit " + (pc - 1) * ps + "," + ps,GoodsManageFormBean.class);
	}
	
	
	
	/**
	 * 查询所有的物资信息：
	 * 		使用范围：仓库管理人员里面的查询物资信息列表
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryAllGoods(GoodsManageFormBean goodsManageFormBean,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_goods left outer join t_type " +
				" on t_goods.type_id_fk = t_type.type_id" +
				" where t_goods.goods_isdel = 0");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_goods left outer join t_type " +
//				" on t_goods.type_id_fk = t_type.type_id" +
//				" and t_goods.goods_isdel = 0" +
//				" limit ?,?",new Object[]{(pc-1)*ps,ps}, GoodsManageFormBean.class);
	}
	
	/**
	 * 查询所有物资数目
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryAllGoodsCount(GoodsManageFormBean goodsManageFormBean,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(t_goods.goods_id) from t_goods" +
				" where t_goods.goods_isdel = 0 ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
//		list.add(dao.getDataCount("select count(t_goods.goods_id) from t_goods" +
//				" where t_goods.goods_isdel = 0", null));
		
		return list;
	}

}
