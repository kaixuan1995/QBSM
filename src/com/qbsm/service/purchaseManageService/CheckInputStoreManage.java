package com.qbsm.service.purchaseManageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.formbean.CheckInputFormBean;


@SuppressWarnings("all")
public class CheckInputStoreManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;

	
	/**
	 * 通过扫描二维码查找物资详细信息
	 * @param sets
	 * @return
	 */
	protected List<CheckInputFormBean> queryCheckInputFormBean(HashSet<String> sets) {
		list = new ArrayList<CheckInputFormBean>();
		Iterator<String> iterator = sets.iterator();
		while(iterator.hasNext()){
			String barcode_id = iterator.next().replaceAll(",", "");
			list.add(dao.queryForObject("select * from " +
					" t_goods,t_type,t_user,t_barcode" +
					" where t_goods.type_id_fk = t_type.type_id" +
					" AND t_barcode.user_id_fk = t_user.user_id" +
					" AND t_barcode.goods_id_fk = t_goods.goods_id" +
					" AND t_barcode.barcode_id = ?",new Object[]{barcode_id}, CheckInputFormBean.class));
		}
 		return list;
	}

}
