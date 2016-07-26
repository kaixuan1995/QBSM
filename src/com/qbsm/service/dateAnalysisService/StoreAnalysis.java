package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.formbean.StoreAnalysisFormBean;

public class StoreAnalysis {

	private GladiolusDao dao = new GladiolusDaoImpl();
	
	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量
	 * @param storehouse_id		仓库id
	 * @param user_id			用户id
	 * @param voucher_type		凭证类别
	 * @param start_Date		开始时间
	 * @param end_Date			结束时间
	 * @return
	 */
	protected List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int storehouse_id,int user_id,String voucher_type,String start_Date,String end_Date,int pc,int ps) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT u.user_name as user_name,go.goods_name as goods_name," +
				" 				go.goods_brand as goods_brand,go.goods_cas as goods_cas," +
				" 				go.goods_standard as goods_standard,go.goods_unit as goods_unit," +
				" 				SUM(vg.voucher_goods_count) as voucher_goods_count ,vo.voucher_createtime as voucher_createtime" +
				" FROM t_voucher vo,t_voucher_goods vg,t_goods go,t_user u,t_storehouse st " +
				" WHERE st.storehouse_id=vo.storehouse_id_fk " +
				" AND vo.user_id_fk=u.user_id " +
				" AND go.goods_id=vg.goods_id_fk " +
				" AND vo.voucher_id=vg.voucher_id_fk " +
				" AND vo.storehouse_id_fk= ? " +
				" AND u.user_id= ? " +
				" AND vo.voucher_type=? " +
				" AND vo.voucher_createtime " +
				" BETWEEN ? AND ? GROUP BY go.goods_id");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		return dao.queryForList(sql.toString(),new Object[]{storehouse_id,user_id,voucher_type,start_Date,end_Date}, StoreAnalysisFormBean.class);
	}
	
	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量（所查询出的结果的数量）
	 * @param storehouse_id
	 * @param user_id
	 * @param voucher_type
	 * @param start_Date
	 * @param end_Date
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(int storehouse_id,int user_id,String voucher_type,String start_Date,String end_Date){
		String sql = "select count(*) from (SELECT u.user_name as user_name,go.goods_name as goods_name," +
				" go.goods_brand as goods_brand,go.goods_cas as goods_cas," +
				" go.goods_standard as goods_standard,go.goods_unit as goods_unit," +
				" SUM(vg.voucher_goods_count) as voucher_goods_count ,vo.voucher_createtime as voucher_createtime" +
				" FROM t_voucher vo,t_voucher_goods vg,t_goods go,t_user u,t_storehouse st " +
				" WHERE st.storehouse_id=vo.storehouse_id_fk " +
				" AND vo.user_id_fk=u.user_id " +
				" AND go.goods_id=vg.goods_id_fk " +
				" AND vo.voucher_id=vg.voucher_id_fk " +
				" AND vo.storehouse_id_fk= ? " +
				" AND u.user_id= ? " +
				" AND vo.voucher_type=? " +
				" AND vo.voucher_createtime " +
				" BETWEEN ? AND ? GROUP BY go.goods_id) t";
		Object storenum = dao.queryForObject(sql, new Object[]{storehouse_id,user_id,voucher_type,start_Date,end_Date});
	    List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(String.valueOf(storenum)));
		return list;
	}
}
