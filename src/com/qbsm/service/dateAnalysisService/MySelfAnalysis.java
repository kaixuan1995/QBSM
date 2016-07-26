package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.ApplyGoodsInfo;

@SuppressWarnings("all")
public class MySelfAnalysis {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String NOTFOUND = MessageEnum.E40004 + "";

	/**
	 * 查询出某段时间内某人对某物资（小柱）的请购数量
	 * 
	 * @param user_name
	 * @param goods_id_fk
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	protected List<String> queryMySelfApplyBuyByGoods_id(int user_id,
			int goods_id_fk, String beform_Date, String after_Date) {
		list = new ArrayList<String>(1);
		String number = "";
		Object count = dao.queryForObject("select sum(apg.apply_goods_count) "
				+ " from t_apply ap ,t_apply_goods apg,t_user u "
				+ " where ap.user_id_fk=u.user_id "
				+ " and ap.Apply_id=apg.apply_id_fk " + " and u.user_id=? "
				+ " and ap.apply_type=2 and apg.goods_id_fk=? "
				+ " and ap.Apply_time between ? " + " and ?", new Object[] {
				user_id, goods_id_fk, beform_Date, after_Date });

		if (count != null) {
			number = count + "";

		} else {
			number = NOTFOUND;
		}
		list.add(number);
		return list;
	}

	/**
	 * 查询出某段时间内某人请购的物资信息
	 * 
	 * @param user_name
	 * @param goods_id_fk
	 * @param beform_Dates
	 * @param after_Date
	 * @return
	 */
	protected List<ApplyGoodsInfo> queryApplyGoodsInfo(int user_id, String beform_Date,
			String after_Date,int pc,int ps) {
		StringBuffer sql = new StringBuffer();
		sql.append("select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas from t_apply ap ,t_apply_goods apg,t_user u ,t_goods go where ap.user_id_fk=u.user_id and ap.Apply_id=apg.apply_id_fk and apg.goods_id_fk=go.goods_id and u.user_id= ? and ap.apply_type=2 and ap.Apply_time between ? and ?");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		return dao.queryForList(sql.toString(), new Object[]{user_id, beform_Date, after_Date}, ApplyGoodsInfo.class);
	}

	/**
	 * 查询出某段时间内某人请购的物资信息（查询出来的结果的数量）
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(int user_id, String beform_Date,String after_Date){
		String sql = "select count(*) from (select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas from t_apply ap ,t_apply_goods apg,t_user u ,t_goods go where ap.user_id_fk=u.user_id and ap.Apply_id=apg.apply_id_fk and apg.goods_id_fk=go.goods_id and u.user_id= ? and ap.apply_type=2 and ap.Apply_time between ? and ?) t";
		Object requestnum = dao.queryForObject(sql, new Object[]{user_id, beform_Date, after_Date});
	    list = new ArrayList();
		list.add(Integer.parseInt(String.valueOf(requestnum)));
		return list;
	}
	/**
	 * 查询出某段时间内某人(人员角色诠释检测员)对于某物资(小柱)的领用数量
	 * 
	 * @param user_name
	 * @param goods_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	protected List<String> queryMyselfApplyUseBygo(int user_id, int goods_id,
			String beform_Date, String after_Date) {
		list = new ArrayList<String>(1);
		String number = "";
		Object count = dao.queryForObject("select sum(vg.voucher_goods_count) "
				+ " from t_voucher vo,t_voucher_goods vg,t_user u,t_goods go "
				+ " where vo.voucher_id=vg.voucher_id_fk "
				+ " and vo.user_id_fk=u.user_id "
				+ " and go.goods_id=vg.goods_id_fk "
				+ " and vo.voucher_type= '领用凭证单' " + " and u.user_id=? "
				+ " and vg.goods_id_fk=? " + " and vo.voucher_createtime "
				+ " BETWEEN ? and ?", new Object[] { user_id, goods_id,
				beform_Date, after_Date });
		if (count != null) {
			number = count + "";

		} else {
			number = NOTFOUND;
		}
		list.add(number);
		return list;
	}

	/**
	 * 查询出某段时间内某人(人员角色诠释检测员)领用物资信息
	 * 
	 * @param user_name
	 * @param goods_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	protected List<ApplyGoodsInfo> queryApplyGoodsInfo(String beform_Date, String after_Date,int user_id,int pc,int ps) {
		StringBuffer sql = new StringBuffer();
		sql.append("select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas "
				+ " from t_voucher vo,t_voucher_goods vg,t_user u,t_goods go "
				+ " where vo.voucher_id=vg.voucher_id_fk "
				+ " and vo.user_id_fk=u.user_id "
				+ " and go.goods_id=vg.goods_id_fk "
				+ " and vo.voucher_type= '领用凭证单' " + " and u.user_id=? "
			    + " and vo.voucher_createtime "
				+ " BETWEEN ? and ?");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);

		return dao.queryForList(sql.toString(),new Object[]{user_id,beform_Date,after_Date},ApplyGoodsInfo.class);
	}
	
	/**
	 * 查询出某段时间内某人(人员角色诠释检测员)领用物资信息（查询出来的结果的数量）
	 * @param beform_Date
	 * @param after_Date
	 * @param user_id
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(String beform_Date, String after_Date,int user_id){
		String sql = "select count(*) from (select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas "
				+ " from t_voucher vo,t_voucher_goods vg,t_user u,t_goods go "
				+ " where vo.voucher_id=vg.voucher_id_fk "
				+ " and vo.user_id_fk=u.user_id "
				+ " and go.goods_id=vg.goods_id_fk "
				+ " and vo.voucher_type= '领用凭证单' " + " and u.user_id=? "
			    + " and vo.voucher_createtime "
				+ " BETWEEN ? and ?) t";
		Object getnum = dao.queryForObject(sql, new Object[]{user_id,beform_Date,after_Date});
	    list.add(Integer.parseInt(String.valueOf(getnum)));
		return list;
	}
	
}
