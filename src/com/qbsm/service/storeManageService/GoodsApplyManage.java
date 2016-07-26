package com.qbsm.service.storeManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.ApplyPicture;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Barcode;
import com.qbsm.bean.Goods;
import com.qbsm.bean.Goods_number;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.GoodsManageFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;

/*检测人员物资申请管理*/
@SuppressWarnings("all")
public class GoodsApplyManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String SAVE_ERROR = MessageEnum.E30002 + "";
	private final String SUCCESS = MessageEnum.S00000 + "";
	private final String SYSTEM_ERROR = MessageEnum.E11111 + "";
	private final String APPLYCOUNTERROR = MessageEnum.E40005 + "";

	/**
	 * 查询分页的库存中的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<GoodsManageFormBean> queryGoodsManageFormBean(
			GoodsManageFormBean goodsManageFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk "
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
				+ " AND t_barcode.user_id_fk = t_user.user_id ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), GoodsManageFormBean.class);
//		return dao.queryForList("SELECT * FROM "
//				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
//				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk "
//				+ " AND t_goods.type_id_fk = t_type.type_id"
//				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
//				+ " AND t_barcode.user_id_fk = t_user.user_id" + " limit "
//				+ (pc - 1) * ps + "," + ps, GoodsManageFormBean.class);
	}

	/**
	 * 查询库存物资信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(t_goods.goods_id) FROM "
				+ " t_goods,t_type,t_barcode,t_user,t_inventory"
				+ " WHERE t_goods.goods_id= t_barcode.goods_id_fk "
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_inventory.goods_id_fk = t_goods.goods_id "
				+ " AND t_barcode.user_id_fk = t_user.user_id ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		return list;
		
		
//		Goods goods = new Goods();
//		User user = new User();
//		Barcode barcode = new Barcode();
//		ResetBeanUtils.copy(goodsManageFormBean, user);
//		ResetBeanUtils.copy(goodsManageFormBean, goods);
//		ResetBeanUtils.copy(goodsManageFormBean, barcode);
//		
//		list.add(dao.getDataCount(user, goods, barcode));
	}

	/**
	 * 查询分页的检测人员已经领用的物资信息
	 * 
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryLingYongGoods(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps, int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM  "
				+ "t_apply LEFT OUTER JOIN t_apply_goods ON t_apply.apply_id = t_apply_goods.apply_id_fk "
				+ " LEFT OUTER JOIN t_goods ON t_apply_goods.goods_id_fk = t_goods.goods_id "
				+ " LEFT OUTER JOIN t_barcode ON t_barcode.goods_id_fk = t_goods.goods_id   "
				+ " LEFT OUTER JOIN t_user ON t_apply.user_id_fk = t_user.user_id  "
				+ " WHERE  t_user.user_id = " + user_id
				+ " AND t_apply.apply_state ='已领用' ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
//		return dao
//				.queryForList(
//						"SELECT * FROM  "
//								+ "t_apply LEFT OUTER JOIN t_apply_goods ON t_apply.apply_id = t_apply_goods.apply_id_fk "
//								+ " LEFT OUTER JOIN t_goods ON t_apply_goods.goods_id_fk = t_goods.goods_id "
//								+ " LEFT OUTER JOIN t_barcode ON t_barcode.goods_id_fk = t_goods.goods_id   "
//								+ " LEFT OUTER JOIN t_user ON t_apply.user_id_fk = t_user.user_id  "
//								+ " WHERE  t_user.user_id = " + user_id
//								+ " AND t_apply.apply_state ='已领用'" + " limit "
//								+ ps * (pc - 1) + " , " + ps,
//						StoreApplyDFormBean.class);
	}

	/**
	 * 查询检测人员已经领用的物资信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryLingYongCount(
			StoreApplyDFormBean storeApplyDFormBean, int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) "
				+ " from t_apply where apply_state='已领用' AND user_id_fk="
				+ user_id);
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		
//		list.add(dao.getDataCount("select count(*) "
//				+ " from t_apply where apply_state='已领用' AND user_id_fk="
//				+ user_id, null));
		return list;
	}

	/**
	 * 查询检测人员查看我的申请（总表） 根据user_id和物资申请的类型查询
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<StoreApplyFormBean> queryMyApply(
			StoreApplyFormBean storeApplyFormBean, int pc, int ps, int user_id,
			String applyType,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ "t_user AS user,t_apply AS apply,t_storehouse AS store"
				+ " where user.user_id = apply.user_id_fk "
				+ " and store.storehouse_id = apply.storehouse_id_fk"
				+ " and apply.user_id_fk = ?"
				+ " and apply.apply_type = ? " );
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{user_id,applyType},  StoreApplyFormBean.class);
//		return dao.queryForList("SELECT * FROM "
//				+ "t_user AS user,t_apply AS apply,t_storehouse AS store"
//				+ " where user.user_id = apply.user_id_fk "
//				+ " and store.storehouse_id = apply.storehouse_id_fk"
//				+ " and apply.user_id_fk =" + user_id
//				+ " and apply.apply_type = '" + applyType + "' limit "
//				+ (pc - 1) * ps + "," + ps, StoreApplyFormBean.class);
	}

	/**
	 * 查询检测人员我的申请信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	protected List<Integer> queryMyApplyCount(
			StoreApplyFormBean storeApplyFormBean, int user_id,
			String apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		
		sql.append("select count(*) "
				+ " from t_apply where  user_id_fk=" + user_id
				+ " and apply_type = '" + apply_type + "'");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		return list;
		
		
		
//		list.add(dao.getDataCount("select count(*) "
//				+ " from t_apply where  user_id_fk=" + user_id
//				+ " and apply_type = '" + apply_type + "'", null));
	}

	/**
	 * 通过分页特定的申请单详细信息
	 */
	protected List<StoreApplyDFormBean> queryStoreApplyDFormBean(int apply_id,
			int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "
				+ " t_goods,t_type,t_apply_goods "
				+ " where t_goods.type_id_fk = t_type.type_id "
				+ " and t_goods.goods_id = t_apply_goods.goods_id_fk "
				+ " and t_apply_goods.apply_id_fk =? ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{apply_id}, StoreApplyDFormBean.class);
		
//		return dao.queryForList("select * from "
//				+ " t_goods,t_type,t_apply_goods "
//				+ " where t_goods.type_id_fk = t_type.type_id "
//				+ " and t_goods.goods_id = t_apply_goods.goods_id_fk "
//				+ " and t_apply_goods.apply_id_fk =" + apply_id + " limit "
//				+ (pc - 1) * ps + " , " + ps, StoreApplyDFormBean.class);
	}


	/**
	 * 添加信息到购物车中
	 * 
	 * @param goods_ids
	 * @param user_id
	 * @return
	 */
	protected List<String> saveShopping(String[] goods_ids, int user_id,
			String storehouse_id) {
		list = new ArrayList<String>(1);
		Shopping shopping = null;
		User user = null;
		Goods goods = null;
		boolean boo = true;
		dao.beginTransaction();
		for (int i = 0; goods_ids != null && i < goods_ids.length && boo; i++) {
			String goods_id = goods_ids[i];
			shopping = new Shopping();
			shopping.setGoods_id_fk(Integer.valueOf(goods_id));
			shopping.setUser_id_fk(user_id);
			shopping = dao.queryForObject(shopping);
			if (shopping == null) {
				shopping = new Shopping();
				goods = new Goods();
				goods.setGoods_id(Integer.valueOf(goods_id));
				goods = dao.queryForObject(goods);

				// 讲查出来的goods复制到shopping中
				ResetBeanUtils.copy(goods, shopping);
				shopping.setGoods_id_fk(Integer.valueOf(goods_id));
				shopping.setStorehouse_id_fk(Integer.valueOf(storehouse_id));

				user = new User();
				user.setUser_id(user_id);
				user = dao.queryForObject(user);

				// 将查出来的user复制到shopping对象中
				ResetBeanUtils.copy(user, shopping);
				shopping.setUser_id_fk(user_id);

				// 添加shopping信息
				boo = dao.save(shopping);
			}
		}
		if (boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		} else {
			dao.rollbackTransaction();
			list.add(SAVE_ERROR);
		}
		return list;
	}

	/**
	 * 
	 * 添加申请单------>根据Apply 来决定是什么申请单
	 * 
	 * @return
	 */

	protected List<String> saveApply(String[] shopping_ids, Apply apply) {
		list = new ArrayList<String>(1);
		int user_id = -1;
		int apply_id = -1;
		Apply_Goods apply_goods = null;
		boolean boo = true;
		Goods_number goods_number = null;
		dao.beginTransaction();

		// 添加申请总表信息
		apply.setApply_isdel(0);
		apply.setApply_state("待审核");
		apply.setApply_time(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
		apply.setApply_type(apply.getApply_type());

		// 解决默认值的问题
		if (apply.getApply_urgent() == null
				|| apply.getApply_urgent().equals("")) {
			apply.setApply_urgent("否");
		} else {
			apply.setApply_urgent(apply.getApply_urgent());
		}

		apply.setStorehouse_id_fk(1);
		apply.setStorehouse_id_fk(Integer.valueOf(apply.getStorehouse_id_fk()));

		// 通过shopping_ids得到user_id
		Shopping shopping = new Shopping();
		if (shopping_ids != null && shopping_ids[0] != null) {
			shopping.setShopping_id(Integer.valueOf(shopping_ids[0]));
		}
		shopping = dao.queryForObject(shopping);
		if (shopping != null) {
			user_id = shopping.getUser_id_fk();
		}

		// 添加user_id到
		apply.setUser_id_fk(user_id);
		// 添加一个
		apply_id = dao.getGeneratedKeyByInsert(apply);
		if (apply_id != -1) {
			// 添加申请单详细信息,遍历shopping_ids得到详细信息
			for (int i = 0; i < shopping_ids.length && boo; i++) {
				shopping = new Shopping();
				shopping.setShopping_id(Integer.valueOf(shopping_ids[i]));
				shopping = dao.queryForObject(shopping);

				// 添加申请单详细信息
				apply_goods = new Apply_Goods();

				if (shopping.getApply_goods_count() == null
						|| shopping.getApply_goods_count() == 0) {
					apply_goods.setApply_goods_count(0);
					//list.add(APPLYCOUNTERROR);// 申请数量为0
				} else {
					apply_goods.setApply_goods_count(shopping
							.getApply_goods_count());
				}
				apply_goods.setApply_goods_isdel(0);
				apply_goods.setApply_id_fk(apply_id);
				apply_goods.setGoods_id_fk(shopping.getGoods_id_fk());
				boo = dao.save(apply_goods);

				// 更改库存表
				if (boo) {
					boo = dao.delete(shopping);
				}

			}

		}
		if (boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		} else {
			if (list.size() < 1) {
				list.add(SYSTEM_ERROR);
			}
		}
		System.out.println(list);
		return list;
	}

	/* 检测人员提交退用申请单,主要是退用申请要上传文件 */
	protected List<String> saveTuiYongApply(String[] shopping_ids,
			Apply apply, String fileUrl) {
		list = new ArrayList<String>(1);
		int user_id = -1;
		int apply_id = -1;
		Apply_Goods apply_goods = null;
		List<Boolean> boos = new ArrayList<Boolean>();
		Goods_number goods_number = null;
		dao.beginTransaction();

		// 添加申请总表信息
		apply.setApply_isdel(0);
		apply.setApply_state("待审核");
		apply.setApply_time(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));

		// 解决默认值的问题
		if (apply.getApply_urgent() == null
				|| apply.getApply_urgent().equals("")) {
			apply.setApply_urgent("否");
		} else {
			apply.setApply_urgent(apply.getApply_urgent());
		}

		apply.setStorehouse_id_fk(1);
		apply.setStorehouse_id_fk(Integer.valueOf(apply.getStorehouse_id_fk()));

		// 通过shopping_ids得到user_id
		Shopping shopping = new Shopping();
		if (shopping_ids != null && shopping_ids[0] != null) {
			shopping.setShopping_id(Integer.valueOf(shopping_ids[0]));
		}
		shopping = dao.queryForObject(shopping);
		if (shopping != null) {
			user_id = shopping.getUser_id_fk();
		}

		// 添加user_id到
		apply.setUser_id_fk(user_id);
		// 添加一个
		apply_id = dao.getGeneratedKeyByInsert(apply);
		if (apply_id != -1) {
			// 添加申请单详细信息,遍历shopping_ids得到详细信息
			for (int i = 0; i < shopping_ids.length; i++) {
				shopping = new Shopping();
				shopping.setShopping_id(Integer.valueOf(shopping_ids[i]));
				shopping = dao.queryForObject(shopping);

				// 添加申请单详细信息
				apply_goods = new Apply_Goods();

				if (shopping.getApply_goods_count() == null
						|| shopping.getApply_goods_count() == 0) {
					apply_goods.setApply_goods_count(0);
					list.add(APPLYCOUNTERROR);// 申请数量为0
					boos.add(false);
				} else {
					apply_goods.setApply_goods_count(shopping
							.getApply_goods_count());
				}
				apply_goods.setApply_goods_isdel(0);
				apply_goods.setApply_id_fk(apply_id);
				apply_goods.setGoods_id_fk(shopping.getGoods_id_fk());
				boos.add(dao.save(apply_goods));

				// 更改库存表
				boos.add(dao.delete(shopping));

			}
			// 添加上传的图片url
			if (fileUrl != null||!fileUrl.equals("")) {
				ApplyPicture applyPicture = new ApplyPicture();
				applyPicture.setApply_id(apply_id);
				applyPicture.setApply_picture_url(fileUrl);
				boos.add(dao.save(applyPicture));
			}
		}
		for(Boolean b :boos){
			if (!b) {
				list.add(SYSTEM_ERROR);
				dao.rollbackTransaction();
				return list;
			}
		}
		list.add(SUCCESS);
		dao.commitTransaction();
		
		return list;
	}
}
