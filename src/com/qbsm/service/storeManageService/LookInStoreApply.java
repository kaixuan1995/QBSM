package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.LookInStoreApplyFormBean;

public class LookInStoreApply {
	private GladiolusDao dao = new GladiolusDaoImpl();

	public static void main(String[] args) {
		LookInStoreApply apply = new LookInStoreApply();
		LookInStoreApplyFormBean applyFormBean = new LookInStoreApplyFormBean();
		applyFormBean.setApply_id_fk(3);
		Filters filters = new Filters();
		filters.setSord("asc");
		List<LookInStoreApplyFormBean> list = apply.queryLookInStoreApply(applyFormBean,1,10,filters);
	
		for(LookInStoreApplyFormBean l:list){
			System.out.println(l);
		}
		List<Integer> listc =  apply.queryCount(applyFormBean, filters);
		System.out.println(listc.get(0));
	}
	/**
	 * 查看申请中的物资详细信息
	 * @param apply_id
	 * @return
	 */
	protected List<LookInStoreApplyFormBean> queryLookInStoreApply(LookInStoreApplyFormBean lookInStoreApplyFormBean,int pc,int ps,Filters filters){
    	StringBuffer sql = new StringBuffer();
		sql.append("select go.goods_id as goods_id,go.goods_name as goods_name," +
				"go.goods_unit as goods_unit,go.goods_standard as goods_standard," +
				"gt.type_name as type_name,go.goods_brand as goods_brand,go.goods_cas as goods_cas," +
				"bar.barcode_validity as barcode_validity,apg.apply_goods_count as apply_goods_count " +
				"from  t_apply ap,t_apply_goods apg,t_goods go,t_barcode bar,t_goodstype gt " +
				"where go.type_id_fk=gt.type_id and ap.apply_id=apg.apply_id_fk and apg.goods_id_fk=go.goods_id" +
				" and bar.goods_id_fk=go.goods_id and ap.apply_id=?");
		List<LookInStoreApplyFormBean> StoreApplylist = dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{lookInStoreApplyFormBean.getApply_id_fk()}, LookInStoreApplyFormBean.class);
        return StoreApplylist;
	 }
	
	/**
	 * 统计查询出来的数量
	 * @param lookInStoreApplyFormBean
	 * @param pc
	 * @param ps
	 * @param filters
	 * @return
	 */
	protected List<Integer> queryCount(LookInStoreApplyFormBean lookInStoreApplyFormBean,Filters filters){
		List<Integer> list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)" +
				"from  t_apply ap,t_apply_goods apg,t_goods go,t_barcode bar,t_goodstype gt " +
				"where go.type_id_fk=gt.type_id and ap.apply_id=apg.apply_id_fk and apg.goods_id_fk=go.goods_id" +
				" and bar.goods_id_fk=go.goods_id and ap.apply_id=?" + filters.toAndSql()
				);
		Object count = dao.queryForObject(sql.toString(), new Object[]{lookInStoreApplyFormBean.getApply_id_fk()});
		list.add(Integer.parseInt(String.valueOf(count)));
		return list;
	 }
}
