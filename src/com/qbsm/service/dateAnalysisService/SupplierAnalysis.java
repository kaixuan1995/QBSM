package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.formbean.SupplierAnalysisFromBean;

public class SupplierAnalysis {

	private GladiolusDao dao = new GladiolusDaoImpl();

	/**
	 * 查询某个供应商的异常供应记录
	 * 
	 * @param user_id
	 *            //用户id
	 * @param start_Date
	 *            //开始时间
	 * @param end_Date
	 *            //结束时间
	 * @return
	 */
	protected List<SupplierAnalysisFromBean> querySupplierAnalysisFromBean(
			int user_id, String start_Date, String end_Date, int pc, int ps) {

		StringBuffer sql = new StringBuffer();
		sql.append("select "
				+ " 	 vo.voucher_id as voucher_id,u.user_name as user_name,"
				+ " 	 go.goods_name as goods_name,go.goods_brand as goods_brand,"
				+ " 	 go.goods_cas as goods_cas,go.goods_standard as goods_standard ,"
				+ "      vg.voucher_goods_count as voucher_goods_count,"
				+ "      go.goods_cas as goods_cas,"
				+ " 	 go.goods_unit as goods_unit ,vo.voucher_createtime as voucher_createtime ,"
				+ " 	 vo.voucher_remark as voucher_remark,api.Apply_picture_url as apply_picture_url "
				+ " from "
				+ "	 t_apply_picture api,t_voucher vo,t_voucher_goods vg, "
				+ " 	 t_user u,t_goods go " + " where "
				+ "	 api.voucher_id_fk=vo.voucher_id "
				+ "	 and vg.voucher_goods_id=go.goods_id "
				+ "	 and u.user_id=vo.voucher_brokerage "
				+ "	 and vo.voucher_id=vg.voucher_id_fk "
				+ "	 and vo.voucher_type='退货凭证单' " + "	 and u.user_id=? "
				+ "	 and vo.voucher_createtime" + " BETWEEN ? and ?");
		sql.append(" limit " + (pc - 1) * ps + " , " + ps);
		return dao.queryForList(sql.toString(), new Object[] { user_id,
				start_Date, end_Date }, SupplierAnalysisFromBean.class);
	}

	/**
	 * 查询某个供应商的异常供应记录（所查询出的结果的数量）
	 * @param user_id
	 * @param start_Date
	 * @param end_Date
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(int user_id, String start_Date,String end_Date) {
		String sql = "select count(*) from (select "
				+ " 	 vo.voucher_id as voucher_id,u.user_name as user_name,"
				+ " 	 go.goods_name as goods_name,go.goods_brand as goods_brand,"
				+ " 	 go.goods_cas as goods_cas,go.goods_standard as goods_standard ,"
				+ " 	 go.goods_unit as goods_unit ,vo.voucher_createtime as voucher_createtime ,"
				+ " 	 vo.voucher_remark as voucher_remark,api.Apply_picture_url as apply_picture_url "
				+ " from "
				+ "	 t_apply_picture api,t_voucher vo,t_voucher_goods vg, "
				+ " 	 t_user u,t_goods go " + " where "
				+ "	 api.voucher_id_fk=vo.voucher_id "
				+ "	 and vg.voucher_goods_id=go.goods_id "
				+ "	 and u.user_id=vo.voucher_brokerage "
				+ "	 and vo.voucher_id=vg.voucher_id_fk "
				+ "	 and vo.voucher_type='退货凭证单' " + "	 and u.user_id=? "
				+ "	 and vo.voucher_createtime" + " BETWEEN ? and ?) t";
		Object suppliererrornum = dao.queryForObject(sql, new Object[] {
				user_id, start_Date, end_Date });
		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(String.valueOf(suppliererrornum)));
		return list;
	}
}
