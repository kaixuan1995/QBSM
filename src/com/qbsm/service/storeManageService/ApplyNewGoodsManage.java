package com.qbsm.service.storeManageService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Goods;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;


@SuppressWarnings("all")
public class ApplyNewGoodsManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";

	/**
	 * 查询审核物资新增信息
	 * @param applyNewGoods
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<ApplyNewGoods> queryApplyNewGoods(ApplyNewGoods applyNewGoods,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_applynewgoods,t_type" +
				" where t_applynewgoods.type_id_fk = t_type.type_id" +
				" AND t_applynewgoods.apply_state = '待审核' " +
				" AND apply_isdel = 0 ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), ApplyNewGoods.class);
		
//		return dao.queryForList("select * from " +
//				" t_applynewgoods,t_type" +
//				" where t_applynewgoods.type_id_fk = t_type.type_id" +
//				" AND t_applynewgoods.apply_state = '待审核' " +
//				" AND apply_isdel = 0 limit "+(pc-1)*ps+" , "+ps, ApplyNewGoods.class);
	}
	
	
	/**
	 * 查询审核物资新增信息
	 * @param applyNewGoods
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Integer> queryCount(ApplyNewGoods applyNewGoods,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select * from t_applynewgoods where apply_state = '待审核'");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		return list;
	}

	/**
	 * 审核新增物资申请信息
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	protected List<String> updateApplyNewGoods(String[] applygoods_ids,String apply_state) {
		list = new ArrayList<String>(1);
		ApplyNewGoods applyNewGoods = null;
		Goods goods = null;
		boolean boo = true;
		dao.beginTransaction();
		for(int i = 0;i<applygoods_ids.length && boo;i++) {
			applyNewGoods = new ApplyNewGoods();
			goods = new Goods();
			applyNewGoods.setApplygoods_id(Integer.valueOf(applygoods_ids[i]));
			applyNewGoods = dao.queryForObject(applyNewGoods);
			applyNewGoods.setApply_state(apply_state);
			boo = dao.update(applyNewGoods);
			if("审核通过".equals(apply_state)) {
				if(boo) {
					ResetBeanUtils.copy(applyNewGoods, goods);
					goods.setType_id_fk(applyNewGoods.getType_id_fk());
					goods.setGoods_isdel(0);
					boo = dao.save(goods);
				}
			}
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else{
			dao.rollbackTransaction();
			list.add(UPDATE_ERROR);
		}
		return list;
	}
}
