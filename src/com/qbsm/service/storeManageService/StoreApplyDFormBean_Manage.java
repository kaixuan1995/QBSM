package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Goods;
import com.qbsm.bean.Type;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;


@SuppressWarnings("all")
public class StoreApplyDFormBean_Manage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	protected List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean,Filters filters){
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_apply where apply_isdel = 0 " );
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
//		Goods goods = new Goods();
//		Type type = new Type();
//		Apply apply = new Apply();
//		Apply_Goods apply_Goods = new Apply_Goods();
//		ResetBeanUtils.copy(storeApplyDFormBean, goods);
//		ResetBeanUtils.copy(storeApplyDFormBean, type);
//		ResetBeanUtils.copy(storeApplyDFormBean, apply);
//		ResetBeanUtils.copy(storeApplyDFormBean, apply_Goods);
//		list.add(dao.getDataCount(goods,type,apply,apply_Goods));
		return list;
	}
	
	
	/**
	 * 查看特定的申请单据数量
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	protected List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean,int apply_type,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_apply where apply_type = ? ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{apply_type}));
//		list.add(dao.getDataCount("select count(*) from t_apply where apply_type = "+apply_type, null));
		return list;
	}
	
	/**
	 * 通过分页查询申请单信息
	 */
	protected List<StoreApplyDFormBean> queryStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_goods as g,t_type as t, t_apply as a,t_apply_goods as ag " +
				" where g.type_id_fk = t.type_id and g.goods_id = ag.goods_id_fk and a.apply_id = ag.apply_id_fk ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_goods as g,t_type as t, t_apply as a,t_apply_goods as ag " +
//				" where g.type_id_fk = t.type_id and g.goods_id = ag.goods_id_fk and a.apply_id = ag.apply_id_fk " +
//				" limit "+(pc-1)*ps +" , "+ps, StoreApplyDFormBean.class);
	}
	
	/**
	 * 通过条件更新物资申请详细视图信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	protected List<String> updateStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean){
		list = new ArrayList<String>(1);
		if(dao.updateByVo(storeApplyDFormBean)){
			list.add(SUCCESS);
		}else {
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 保存物资申请详细视图信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	protected List<String> saveStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean){
		list = new ArrayList<String>(1);
		if(dao.save(storeApplyDFormBean)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 查询物资申请详细视图信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	protected List<String> deleteStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean){
		list = new ArrayList<String>(1);
		if(dao.delete(storeApplyDFormBean)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}
	
	/**
	 * 查询申请单详细信息
	 * 		步骤：
	 *			1：通过apply_id查询t_apply_goods表得到goods_id
	 *			2：通过goods_id查询t_goods表得到详细信息
	 *			3：通过goods_id查询t_barcode得到供应商user_id
	 *			4：通过user_id得到供应商信息		
	 * @param storeApplyDFormBean
	 * @param apply_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryStoreApplyDFormBeanByApply_id(StoreApplyDFormBean storeApplyDFormBean,int apply_id,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply_goods,t_goods,t_barcode,t_user" +
				" where t_apply_goods.apply_id_fk = ?" +
				" and t_apply_goods.goods_id_fk = t_goods.goods_id" +
				" and t_goods.goods_id = t_barcode.goods_id_fk" +
				" and t_barcode.user_id_fk = t_user.user_id ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_apply_goods,t_goods,t_barcode,t_user" +
//				" where t_apply_goods.apply_id_fk = ?" +
//				" and t_apply_goods.goods_id_fk = t_goods.goods_id" +
//				" and t_goods.goods_id = t_barcode.goods_id_fk" +
//				" and t_barcode.user_id_fk = t_user.user_id" +
//				" limit ? , ?",new Object[]{apply_id,(pc-1)*ps,ps}, StoreApplyDFormBean.class);
	}
	
	
	
	/**
	 * 查看特定的申请单
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean,
			int pc, int ps, int apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " +
				" t_goods AS tg,t_apply AS ta,t_type AS tt, t_apply_goods AS tag " +
				" WHERE tg.goods_id = tag.goods_id_fk " +
				" AND tag.apply_id_fk = ta.apply_id " +
				" AND tg.type_id_fk = tt.type_id" +
				" AND ta.apply_type = ?");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{apply_type}, StoreApplyDFormBean.class);
//		return dao.queryForList("SELECT * FROM " +
//				" t_goods AS tg,t_apply AS ta,t_type AS tt, t_apply_goods AS tag " +
//				" WHERE tg.goods_id = tag.goods_id_fk " +
//				" AND tag.apply_id_fk = ta.apply_id " +
//				" AND tg.type_id_fk = tt.type_id" +
//				" AND ta.apply_type = "+apply_type
//				+ " limit " + ps * (pc - 1) + " , " + ps,
//				StoreApplyDFormBean.class);
	}


	/**
	 * 查询检测人员已经领用的物资信息的总数
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryLingYongCount(StoreApplyDFormBean storeApplyDFormBean,int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_apply where apply_state='已领用' AND user_id_fk= ? ");
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{user_id}));
//		list.add(dao.getDataCount("select count(*) from t_apply where apply_state='已领用' AND user_id_fk="+user_id, null));
		return list;
	}


	/**
	 * 查询分页的检测人员已经领用的物资信息
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryLingYongGoods(StoreApplyDFormBean storeApplyDFormBean,
			int pc, int ps, int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " +
				" t_goods AS tg,t_apply AS ta,t_type AS tt, t_apply_goods AS tag ,t_user AS user,t_barcode AS bar" +
				" WHERE tg.goods_id = tag.goods_id_fk " +
				" AND tg.goods_id = bar.goods_id_fk " +
				" AND tag.apply_id_fk = ta.apply_id " +
				" AND tg.type_id_fk = tt.type_id" +
				" AND ta.user_id_fk = user.user_id" +
				" AND user.user_id = ?"+
				" AND ta.apply_state ='已领用'");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{user_id}, StoreApplyDFormBean.class);
//		return dao.queryForList("SELECT * FROM " +
//				" t_goods AS tg,t_apply AS ta,t_type AS tt, t_apply_goods AS tag ,t_user AS user,t_barcode AS bar" +
//				" WHERE tg.goods_id = tag.goods_id_fk " +
//				" AND tg.goods_id = bar.goods_id_fk " +
//				" AND tag.apply_id_fk = ta.apply_id " +
//				" AND tg.type_id_fk = tt.type_id" +
//				" AND ta.user_id_fk = user.user_id" +
//				" AND user.user_id = "+user_id+
//				" AND ta.apply_state ='已领用'"+
//				" limit " + ps * (pc - 1) + " , " + ps,
//				StoreApplyDFormBean.class);
	}
}
