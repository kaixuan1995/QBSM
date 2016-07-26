package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.formbean.StoreWaringFormBean;

public class StoreWaring {
	private GladiolusDao dao = new GladiolusDaoImpl();

	/**
	 * 查询出仓库中在某日期前两个月内过期的物资信息
	 * @param waringDate
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreWaringFormBean> queryStoreWaringFormBean(String waringDate, int pc,int ps) {
		StringBuffer sql = new StringBuffer();
		sql.append("select go.goods_id as goods_id,go.goods_name as goods_name,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_brand as goods_brand,go.goods_cas as goods_cas,bar.barcode_validity as barcode_validity,st.storehouse_name as storehouse_name from t_inventory inv,t_barcode bar,t_goods go,t_storehouse st where inv.goods_id_fk=go.goods_id and st.storehouse_id=inv.storehouse_id_fk and inv.barcode_id_fk=bar.barcode_id and bar.barcode_validity BETWEEN TIMESTAMPADD(MONTH,-2,?) and ?");
		sql.append(" limit "+(pc-1)*ps+" , "+ps);
		return dao.queryForList(sql.toString(), new Object[] {
				waringDate, waringDate }, StoreWaringFormBean.class);
	}
	
	/**
	 * 查询出仓库中在某日期前两个月内过期的物资信息(所查询出的结果的数量)
	 * @param waringDate
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(String waringDate) {
		String sql = "select count(*) from (select go.goods_id as goods_id,go.goods_name as goods_name,go.goods_unit as goods_unit,go.goods_standard as goods_standard,go.goods_brand as goods_brand,go.goods_cas as goods_cas,bar.barcode_validity as barcode_validity,st.storehouse_name as storehouse_name from t_inventory inv,t_barcode bar,t_goods go,t_storehouse st where inv.goods_id_fk=go.goods_id and st.storehouse_id=inv.storehouse_id_fk and inv.barcode_id_fk=bar.barcode_id and bar.barcode_validity BETWEEN TIMESTAMPADD(MONTH,-2,?) and ?) t";
		Object outgoods = dao.queryForObject(sql, new Object[] {
				waringDate, waringDate });
		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.parseInt(String.valueOf(outgoods)));
		return list;
	}
}
