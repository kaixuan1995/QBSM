package com.qbsm.service.purchaseManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Supplier_Goodstype;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.UserFormBean;

@SuppressWarnings("all")
public class ApplyerManage {
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 查询供应商
	 * @param user
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<UserFormBean> queryApplyer(UserFormBean userForBean,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_user AS u,t_supplier_Goodstype AS tsg, t_type AS tt" +
				" WHERE u.user_id = tsg.user_id_fk AND tsg.type_id_fk = tt.type_id AND u.role_id_fk = 4 and u.user_isdel = 0 ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps) , UserFormBean.class);
	}
	
	
	
	/**
	 * 查询所有供应商总数
	 * @param userForBean
	 * @return
	 */
	protected List<Integer> queryCount(UserFormBean userForBean,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_user where role_id_fk = 4");
		list.add(dao.getDataCount(filters.toAndSql()+"", null));
		return list;
	}
	
	
	/**
	 * 添加供应商
	 * @param user
	 * @param type_ids
	 * @return
	 */
	protected List<String> saveApplyer(User user,String[] type_ids) {
		list = new ArrayList<String>(1);
		Supplier_Goodstype supplier_goodsType = null;
		boolean boo = true;
		int user_id_fk = -1;
		user.setRole_id_fk(4);
		user.setUser_createtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dao.beginTransaction();
		user_id_fk = dao.getGeneratedKeyByInsert(user);
		for(int i = 0;i<type_ids.length && boo && user_id_fk != -1;i++) {
			supplier_goodsType = new Supplier_Goodstype();
			supplier_goodsType.setUser_id_fk(user_id_fk);
			supplier_goodsType.setType_id_fk(Integer.valueOf(type_ids[i]));
			boo = dao.save(supplier_goodsType);
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else {
			dao.rollbackTransaction();
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	/**
	 * 删除供应商信息
	 * @param user
	 * @return
	 */
	protected List<String> deleteApplyer(User user) {
		list = new ArrayList<String>(1);
		boolean boo = false;
		Supplier_Goodstype supplier_GoodsType = new Supplier_Goodstype();
		dao.beginTransaction();
		user.setUser_isdel(1);
		boo = dao.update(user);
		if(boo) {
			boo = dao.update("update t_supplier_goodstype set supplier_goodstype_isdel=? where user_id_fk=?", new Object[]{1,user.getUser_id()});
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else {
			dao.rollbackTransaction();
			list.add(DELETE_ERROR);
		}
		return list;
	}
	
	/**
	 * 更新供应商供应类别信息
	 * @param aupplier_Goodstypes
	 * @return
	 */
	protected List<String> updateSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes) {
		list = new ArrayList<String>(1);
		if(dao.update(aupplier_Goodstypes)) {
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	/**
	 * 删除供应商供应类别信息
	 * @param aupplier_Goodstypes
	 * @return
	 */
	protected List<String> deleteSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes) {
		list = new ArrayList<Boolean>(1);
		aupplier_Goodstypes.setSupplier_goodstype_isdel(1);
		if(dao.update(aupplier_Goodstypes)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}
	
	
}
