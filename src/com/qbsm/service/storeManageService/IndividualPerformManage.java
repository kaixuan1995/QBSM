package com.qbsm.service.storeManageService;

import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.GoodsManageFormBean;

@SuppressWarnings("all")

/*检测人员个人绩效管理*/
public class IndividualPerformManage {
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;


	/**
	 *  查询分页的个人绩效信息
	 */
	protected List<GoodsManageFormBean> queryGoodsManageFormBean(
			GoodsManageFormBean goodsManageFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
								+ " t_goods AS g,t_type AS tt,t_barcode AS b,t_user AS u ,t_inventory AS ti "
								+ " WHERE g.goods_id= b.goods_id_fk AND g.type_id_fk = tt.type_id"
								+ " AND ti.goods_id_fk = g.goods_id AND b.user_id_fk = u.user_id ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//		return dao
//				.queryForList(
//						"SELECT * FROM "
//								+ " t_goods AS g,t_type AS tt,t_barcode AS b,t_user AS u ,t_inventory AS ti "
//								+ " WHERE g.goods_id= b.goods_id_fk AND g.type_id_fk = tt.type_id"
//								+ " AND ti.goods_id_fk = g.goods_id AND b.user_id_fk = u.user_id"
//								+ " limit " + (pc - 1) * ps + "," + ps,
//						GoodsManageFormBean.class);
	}

}
