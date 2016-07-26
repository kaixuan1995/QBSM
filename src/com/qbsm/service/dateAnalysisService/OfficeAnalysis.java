package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.ApplyGoodsInfo;
import com.qbsm.web.formbean.UseGoodsInfo;

@SuppressWarnings("all")
public class OfficeAnalysis {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String NOTFOUND = MessageEnum.E40004 + "";

	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资(小柱)的请购数量
	 * 
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	protected List<String> queryOfficeApplyBuy(int goods_id,
			String before_Date, String after_Date, int office_id) {
		list = new ArrayList<String>(1);
		String number = "";

		Object count = dao.queryForObject("select sum(apg.apply_goods_count) "
				+ " from t_apply ap ,t_apply_goods apg "
				+ " where ap.Apply_id=apg.apply_id_fk "
				+ " and ap.user_id_fk in" + "   (" + "		select u.user_id "
				+ "		from t_user u,t_office o "
				+ "		where u.office_id_fk=o.office_id "
				+ "		and u.role_id_fk=2 " + "		and o.office_id= ? " + "	) "
				+ " and ap.apply_type=2 " + " and apg.goods_id_fk=? "
				+ " and ap.Apply_time " + " between ? and ? ", new Object[] {
				office_id, goods_id, before_Date, after_Date });
		if (count != null) {
			number = count + "";

		} else {
			number = NOTFOUND;
		}
		list.add(number);
		return list;
	}

	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资的领用数量
	 * 
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	protected List<String> queryOfficeApplyUse(int goods_id,
			String before_Date, String after_Date, int office_id) {
		list = new ArrayList<String>(1);
		String number = "";
		Object count = dao.queryForObject("select sum(vg.voucher_goods_count) "
				+ " from t_voucher vo,t_voucher_goods vg "
				+ " where vo.voucher_id=vg.voucher_id_fk "
				+ " and vo.user_id_fk in" + "	 (" + "		 select u.user_id "
				+ "		 from t_user u,t_office o "
				+ "		 where u.office_id_fk=o.office_id "
				+ "		 and u.role_id_fk= 2 " + "		 and o.office_id= ? " + "	 ) "
				+ " and vo.voucher_type='领用凭证单' " + " and vg.goods_id_fk= ? "
				+ " and vo.voucher_createtime " + " between ? and ?;",
				new Object[] { office_id, goods_id, before_Date, after_Date });
		if (count != null) {
			number = count + "";

		} else {
			number = NOTFOUND;
		}
		list.add(number);
		return list;
	}

	/**
	 * 查询某段时间某个科室的请购物资信息
	 * 
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	protected List<UseGoodsInfo> queryUseGoodsInfo(String before_Date,
			String after_Date, int office_id,int pc,int ps) {

		List<UseGoodsInfo> appList = dao
				.queryForList(
						"select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas,ap.Apply_time as apply_time"
								+ " from t_apply ap ,t_apply_goods apg, t_goods go"
								+ " where ap.Apply_id=apg.apply_id_fk "
								+ " and go.goods_id = apg.goods_id_fk"
								+ " and ap.user_id_fk in"
								+ "   ("
								+ "		select u.user_id "
								+ "		from t_user u,t_office o "
								+ "		where u.office_id_fk=o.office_id "
								+ "		and u.role_id_fk=2 "
								+ "		and o.office_id= ? "
								+ "	) "
								+ " and ap.apply_type=2 "
								+ " and ap.Apply_time " + " between ? and ? ",new Object[]{office_id,before_Date,after_Date},UseGoodsInfo.class
						);
		return appList;
	}

	/**
	 * 查询某段时间某个科室的请购物资信息(所查询出结果的数量)
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(String before_Date, int office_id,String after_Date){
		list = new ArrayList();
		String sql = "select count(*) from (select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas "
								+ " from t_apply ap ,t_apply_goods apg, t_goods go"
								+ " where ap.Apply_id=apg.apply_id_fk "
								+ " and go.goods_id = apg.goods_id_fk"
								+ " and ap.user_id_fk in"
								+ "   ("
								+ "		select u.user_id "
								+ "		from t_user u,t_office o "
								+ "		where u.office_id_fk=o.office_id "
								+ "		and u.role_id_fk=2 "
								+ "		and o.office_id= ? "
								+ "	) "
								+ " and ap.apply_type=2 "
								+ " and ap.Apply_time " + " between ? and ?) t";
		Object roomrequestnum = dao.queryForObject(sql, new Object[]{office_id,before_Date,after_Date});
	    list.add(Integer.parseInt(String.valueOf(roomrequestnum)));
		return list;
	}
	
	/**
	 *  查询某段时间某个科室的领用物资信息
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	protected List<UseGoodsInfo> queryUseGoodsInfo(int office_id,String before_Date,
			String after_Date, int pc,int ps) {
		StringBuffer sql = new StringBuffer();
		sql.append("select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas from t_voucher vo,t_voucher_goods vg ,t_goods go where vg.goods_id_fk=go.goods_id and vo.voucher_id=vg.voucher_id_fk and vo.user_id_fk in( select u.user_id from t_user u,t_office o where u.office_id_fk=o.office_id and u.role_id_fk= 2 and o.office_id= ? ) and vo.voucher_type='领用凭证单' and vo.voucher_createtime between ? and ?");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);

		return dao.queryForList(sql.toString(),new Object[] {office_id, before_Date, after_Date },UseGoodsInfo.class);
	}
	
	/**
	 * 查询某段时间某个科室的领用物资信息(所查询出的结果的数量)
	 * @param office_id
	 * @param before_Date
	 * @param after_Date
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(int office_id,String before_Date,String after_Date){
		list = new ArrayList();
		String sql = "select count(*) from (select go.goods_name as goods_name,go.goods_brand as goods_brand,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_cas as goods_cas from t_voucher vo,t_voucher_goods vg ,t_goods go where vg.goods_id_fk=go.goods_id and vo.voucher_id=vg.voucher_id_fk and vo.user_id_fk in( select u.user_id from t_user u,t_office o where u.office_id_fk=o.office_id and u.role_id_fk= 2 and o.office_id= ? ) and vo.voucher_type='领用凭证单' and vo.voucher_createtime between ? and ?) t";
		Object roomgetnum = dao.queryForObject(sql, new Object[]{office_id, before_Date, after_Date});
	    list.add(Integer.parseInt(String.valueOf(roomgetnum)));
		return list;
	}
}
