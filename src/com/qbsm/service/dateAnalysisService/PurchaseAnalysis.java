package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.formbean.StoreAnalysisFormBean;

public class PurchaseAnalysis {
private GladiolusDao dao = new GladiolusDaoImpl();
	
	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别物资的信息及其数量
	 * @param user_id		//用户id
	 * @param goods_type_id		//物资列别id
	 * @param start_Date		//开始时间
	 * @param end_Date			//结束时间
	 * @return
	 */
	protected List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int user_id,int goods_type_id,String start_Date,String end_Date,int pc,int ps) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " +
				"	 u.user_name as user_name,go.goods_name as goods_name," +
				"	 go.goods_standard as goods_standard ,go.goods_unit as goods_unit ," +
				"	 go.goods_brand as goods_brand ,go.goods_cas as goods_cas," +
				"    go.type_id_fk as type_id_fk,"+
				"	 vo.voucher_createtime as voucher_createtime,SUM(vg.voucher_goods_count) " +
				"	 as voucher_goods_count " +
				" from t_voucher vo,t_voucher_goods vg,t_goods go,t_user u " +
				" where vg.goods_id_fk=go.goods_id " +
				" and u.user_id=vo.user_id_fk " +
				" and vo.voucher_id=vg.voucher_id_fk " +
				" and u.user_id= ? " +
				" and vo.voucher_type='采购单凭证单'  " +
				" and vg.voucher_goods_id in " +
				"	 (" +
				"		 select go.goods_id " +
				"		 from t_goods go,t_goodstype gt " +
				"		 where go.type_id_fk=gt.type_id " +
				"		 and gt.type_id= ?" +
				"	 ) " +
				" and u.role_id_fk=1 " +
				" and vo.voucher_createtime " +
				" between ? and ? GROUP BY go.goods_id");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		return dao.queryForList(sql.toString(),new Object[]{user_id,goods_type_id,start_Date,end_Date}, StoreAnalysisFormBean.class);
	}
	
	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别物资的信息及其数量(所查询出的结果的数量)
	 * @param user_id
	 * @param goods_type_id
	 * @param start_Date
	 * @param end_Date
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(int user_id,int goods_type_id,String start_Date,String end_Date){
		String sql = "select count(*) from (select " +
				"	 u.user_name as user_name,go.goods_name as goods_name," +
				"	 go.goods_standard as goods_standard ,go.goods_unit as goods_unit ," +
				"	 go.goods_brand as goods_brand ,go.goods_cas as goods_cas," +
				"	 vo.voucher_createtime as voucher_createtime,SUM(vg.voucher_goods_count) " +
				"	 as voucher_goods_count " +
				" from t_voucher vo,t_voucher_goods vg,t_goods go,t_user u " +
				" where vg.goods_id_fk=go.goods_id " +
				" and u.user_id=vo.user_id_fk " +
				" and vo.voucher_id=vg.voucher_id_fk " +
				" and u.user_id= ? " +
				" and vo.voucher_type='采购单凭证单'  " +
				" and vg.voucher_goods_id in " +
				"	 (" +
				"		 select go.goods_id " +
				"		 from t_goods go,t_goodstype gt " +
				"		 where go.type_id_fk=gt.type_id " +
				"		 and gt.type_id= ?" +
				"	 ) " +
				" and u.role_id_fk=1 " +
				" and vo.voucher_createtime " +
				" between ? and ? GROUP BY go.goods_id) t";
		Object playnum = dao.queryForObject(sql, new Object[]{user_id,goods_type_id,start_Date,end_Date});
	    List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(String.valueOf(playnum)));
		return list;
	}
}
