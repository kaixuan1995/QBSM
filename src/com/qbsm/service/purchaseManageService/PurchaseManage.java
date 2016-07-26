package com.qbsm.service.purchaseManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Barcode;
import com.qbsm.bean.Goods_number;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.Voucher;
import com.qbsm.bean.Voucher_Goods;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.PurchaseManageFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;
import com.qbsm.web.formbean.VoucherDetailFormBean;

//采购人员的申请管理
public class PurchaseManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List<Integer> list = null;
	private final String DELETE_ERROR = MessageEnum.E30004 + "";
	private final String UPDATE_ERROR = MessageEnum.E30003 + "";
	private final String SAVE_ERROR = MessageEnum.E30002 + "";
	private final String SUCCESS = MessageEnum.S00000 + "";
	private final String SYSTEM_ERROR = MessageEnum.E11111 + "";

	/**
	 * 采购人员查询 采购申请总表（分页） 根据标记queryRang 值为 all---->查询所有的未审批和审批的采购申请
	 * notCheck----->未审批的采购申请 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 6仓库之间的调度
	 * 
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryCaiGouApply(
			StoreApplyFormBean storeApplyFormBean, int pc, int ps,
			String queryRang, Filters filters) {
		List<StoreApplyFormBean> storeApplyFormBeans = new ArrayList<StoreApplyFormBean>();
		StringBuffer sql = new StringBuffer();
		if (queryRang != null && queryRang.equals("all")) {
			sql.append("SELECT * FROM "
					+ " t_apply AS apply, t_office AS off,t_user AS user"
					+ " WHERE apply.user_id_fk= user.user_id"
					+ " AND off.office_id = user.office_id_fk "
					+ " AND apply.apply_type='2'");
		} else if (queryRang.equals("notCheck")) {
			sql.append("SELECT * FROM "
					+ " t_apply AS apply, t_office AS off,t_user AS user"
					+ " WHERE apply.user_id_fk= user.user_id"
					+ " AND off.office_id = user.office_id_fk "
					+ " AND apply.apply_type='2'"
					+ " AND apply.apply_state='待审核'");
		}
		storeApplyFormBeans = dao.queryForList(filters.toLimitSql(sql, pc, ps),
				StoreApplyFormBean.class);
		return storeApplyFormBeans;
	}

	/**
	 * 采购人员查询 采购申请总表（分页）总数 根据标记queryRang 值为 all---->查询所有的未审批和审批的采购申请
	 * 
	 * @param storeApplyFormBean
	 * @param queryRang
	 * @return
	 */
	protected List<Integer> queryCaiGouApplyCount(
			StoreApplyFormBean storeApplyFormBean, String queryRang,
			Filters filters) {
		list = new ArrayList<Integer>(1);
		String sql = null;
		if (queryRang != null && queryRang.equals("all")) {
			sql = "select count(*) from t_apply where apply_type='2'"
					+ filters.toAndSql();
		} else if (queryRang.equals("notCheck")) {
			sql = "select count(*) from t_apply where apply_type='2' and apply_state='待审核'"
					+ filters.toAndSql();
		}
		list.add(dao.getDataCount(sql, null));
		return list;
	}

	/**
	 * 采购人员查询 采购申请详细表（分页)
	 * 
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryDetailApply(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps,
			int apply_id, Filters filters) {
		List<StoreApplyDFormBean> storeApplyDFormBeans = new ArrayList<StoreApplyDFormBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_apply_goods AS ag, t_goodstype AS gt,t_goods AS goods"
				+ " WHERE ag.goods_id_fk= goods.goods_id"
				+ " AND goods.type_id_fk = gt.type_id "
				+ " AND ag.apply_id_fk= " + apply_id);
		storeApplyDFormBeans = dao.queryForList(
				filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
		return storeApplyDFormBeans;
	}

	/**
	 * 采购人员查询 采购申请详细表 总数
	 * 
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryDetailApplyCount(
			StoreApplyDFormBean storeApplyDFormBean, int apply_id,
			Filters filters) {
		list = new ArrayList<Integer>(1);
		String sql = "select count(*) from t_apply_goods where apply_id_fk ="
				+ apply_id + filters.toAndSql();
		list.add(dao.getDataCount(sql, null));
		return list;
	}

	/**
	 * 通过apply_id查询采购申请单详细的物资信息（给自己调用）
	 * 
	 * @param storeApplyDFormBean
	 * @param apply_id
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryDetailApplyById(int apply_id) {
		List<StoreApplyDFormBean> list = dao
				.queryForList(
						"SELECT * FROM "
								+ " t_apply_goods AS ag, t_goodstype AS gt,t_goods AS goods"
								+ " WHERE ag.goods_id_fk= goods.goods_id"
								+ " AND goods.type_id_fk = gt.type_id "
								+ " AND ag.apply_id_fk= " + apply_id,
						StoreApplyDFormBean.class);
		return list;
	}
	
	/**
	 * 通过good_id查询条形码id（给自己调用）
	 * 
	 * @param storeApplyDFormBean
	 * @param apply_id
	 * @return
	 */
	protected List<Barcode> queryBarcodeBygoodId(int good_id) {
		List<Barcode> list = dao
				.queryForList("SELECT * FROM t_barcode"
								+ " WHERE goods_id_fk="+good_id,Barcode.class);
		return list;
	}
	

	/**
	 * 采购人员审核采购申请单 check 1 代表 通过，0代表不通过
	 * 
	 * @param apply_ids
	 * @param check
	 * @return
	 */
	protected List<String> checkCaiGouApply(String apply_ids[], int check,
			int user_id) {
		List<Boolean> boos = new ArrayList<Boolean>();
		List<String> list = new ArrayList<String>();
		String sql = null;
		dao.beginTransaction();
		if (apply_ids != null) {
			for (int i = 0; i < apply_ids.length; i++) {
				if (check == 1) {
					sql = "update t_apply set apply_state = '审核通过'"
							+ " where apply_id = "
							+ Integer.parseInt(apply_ids[i]);

				} else if (check == 0) {
					sql = "update t_apply set apply_state = '审核不通过'"
							+ " where apply_id = "
							+ Integer.parseInt(apply_ids[i]);

				}
				System.out.println(sql);
				boos.add(dao.update(sql, null));
				/*
				 * 流程：检测人员申请采购生成采购申请单交给采购人员审核，审核通过则改变状态为申请通过并生成个人凭证单， 生成个人采购凭证单
				 * 1.先生成Voucher总表 2在生成Voucher_Goods
				 */
				// 1.先生成Voucher总表
				Voucher voucher = new Voucher();
				voucher.setUser_id_fk(user_id);
				voucher.setVoucher_createtime(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				// 2个人采购凭证表
				voucher.setVoucher_type(2);
				voucher.setVoucher_isdel(0);
				// 供应商----->个人采购申请凭证不需要指定向哪个供应商----->统一由采购人员指定（数据库里的凭证表的供应商id可以为空）
				// voucher.setVoucher_brokerage(1);
				// 仓库id----->个人采购申请凭证不需要指定向哪个仓库
				int voucherId = dao.getGeneratedKeyByInsert(voucher);
				if (voucherId != -1) {
					boos.add(true);
				} else {
					boos.add(false);

				}
				// 2在生成Voucher_Goods
				List<StoreApplyDFormBean> storeApplyDFormBeans = queryDetailApplyById(Integer.parseInt(apply_ids[i]));
				if (storeApplyDFormBeans != null) {
					for (StoreApplyDFormBean storeApplyDFormBean : storeApplyDFormBeans) {
						Voucher_Goods voucher_Goods = new Voucher_Goods();
						voucher_Goods.setVoucher_goods_isdel(0);
						voucher_Goods.setVoucher_id_fk(voucherId);
						voucher_Goods.setGoods_id_fk(storeApplyDFormBean
								.getGoods_id());
						voucher_Goods.setVoucher_goods_count(Integer
								.parseInt(storeApplyDFormBean
										.getApply_goods_count()));
						// Barcode_id_fk我如何让得到？
						List<Barcode>barcodes =queryBarcodeBygoodId(storeApplyDFormBean.getGoods_id());
						if(barcodes!=null){
							voucher_Goods.setBarcode_id_fk(barcodes.get(0).getBarcode_id());
						}else{
							throw new RuntimeException(storeApplyDFormBean.getGoods_id()+"所对应的条形码id没有");
						}
						System.out.println(storeApplyDFormBean.getGoods_id());
						boos.add(dao.save(voucher_Goods));
					}
				} else {
					boos.add(false);
				}

			}
		} else {
			throw new RuntimeException("请选择要审核的采购申请单");
		}
		for (Boolean b : boos) {
			if (!b) {
				list.add(SYSTEM_ERROR);
				dao.rollbackTransaction();
				System.out.println(list);
				return list;
			}
		}
		list.add(SUCCESS);
		dao.commitTransaction();
		System.out.println(list);
		return list;
	}

	public static void main(String[] args) {
		PurchaseManage purchaseManage = new PurchaseManage();
		// printList(purchaseManage.queryNotCheckApply(new StoreApplyFormBean(),
		// 1, 10, "notCheck"));
		// printList(purchaseManage
		// .queryNotCheckApplyCount(new StoreApplyFormBean(),"notCheck"));
		// printList(purchaseManage.queryDetailApply(new StoreApplyDFormBean(),
		// 1,
		// 10, 3));
		// printList(purchaseManage.queryDetailApplyCount(
		// new StoreApplyDFormBean(), 3));
		// int apply_ids[] = { 2, 3 };
		// purchaseManage.checkCaiGouApply(apply_ids, 1);
		// printList(purchaseManage.queryPurchasePlan(new
		// PurchaseManageFormBean(), 1, 10, new Filters()));
//		printList(purchaseManage.queryPurchasePlan(
//				new PurchaseManageFormBean(), 1, 10, new Filters()));
//		printList(purchaseManage.queryPurchasePlanCount(new PurchaseManageFormBean(), new Filters()));
//		printList(purchaseManage.queryDetailVoucher(new VoucherDetailFormBean(), 1, 5, 1, new Filters()));
	}

	// 打印list集合测试
	public static void printList(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	/**
	 * 查询采购计划（分页）
	 * 
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	protected List<PurchaseManageFormBean> queryPurchasePlan(
			PurchaseManageFormBean purchaseManageFormBean, int pc, int ps,
			Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_voucher AS vo,t_voucher_goods AS vg , t_goods AS g,t_goodstype AS ty,t_user AS u, t_office AS off"
				+ " WHERE vo.voucher_id = vg.voucher_id_fk"
				+ " AND vg.goods_id_fk = g.goods_id "
				+ " AND u.user_id = vo.user_id_fk "
				+ " AND g.type_id_fk = ty.type_id "
				+ " AND off.office_id = u.office_id_fk"
				+ " AND vo.voucher_type = 2" 
				+ " AND vo.voucher_isdel = 0");
		return dao.queryForList(sql.toString(), PurchaseManageFormBean.class);
	}

	/**
	 * 查询采购计划总数）
	 * 
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	protected List<Integer> queryPurchasePlanCount(
			PurchaseManageFormBean purchaseManageFormBean,
			Filters filters) {
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount(
				"select count(*) from t_voucher AS vo,t_voucher_goods as vg" +
				" where vo.voucher_id= vg.voucher_id_fk " +
				" AND vo.voucher_type=2 " +
				" AND vo.voucher_isdel = 0", null));
		return list;
	}
	
//
//	/**
//	 * 采购人员点击生成采购申请单
//	 * @param shopping_ids
//	 * @param apply
//	 * @return
//	 */
//	protected List<String> saveApply(String[] shopping_ids, Apply apply) {
//		List <String>list = new ArrayList<String>(1);
//		int user_id = -1;
//		int apply_id = -1;
//		Apply_Goods apply_goods = null;
//		boolean boo = true;
//		Goods_number goods_number = null;
//		dao.beginTransaction();
//
//		// 添加申请总表信息
//		apply.setApply_isdel(0);
//		apply.setApply_state("待审核");
//		apply.setApply_time(new SimpleDateFormat("yyyy-MM-dd")
//				.format(new Date()));
//		apply.setApply_type(apply.getApply_type());
//
//		// 解决默认值的问题
//		if (apply.getApply_urgent() == null
//				|| apply.getApply_urgent().equals("")) {
//			apply.setApply_urgent("否");
//		} else {
//			apply.setApply_urgent(apply.getApply_urgent());
//		}
//
//		apply.setStorehouse_id_fk(1);
//		apply.setStorehouse_id_fk(Integer.valueOf(apply.getStorehouse_id_fk()));
//
//		// 通过shopping_ids得到user_id
//		Shopping shopping = new Shopping();
//		if (shopping_ids != null && shopping_ids[0] != null) {
//			shopping.setShopping_id(Integer.valueOf(shopping_ids[0]));
//		}
//		shopping = dao.queryForObject(shopping);
//		if (shopping != null) {
//			user_id = shopping.getUser_id_fk();
//		}
//
//		// 添加user_id到
//		apply.setUser_id_fk(user_id);
//		// 添加一个
//		apply_id = dao.getGeneratedKeyByInsert(apply);
//		if (apply_id != -1) {
//			// 添加申请单详细信息,遍历shopping_ids得到详细信息
//			for (int i = 0; i < shopping_ids.length && boo; i++) {
//				shopping = new Shopping();
//				shopping.setShopping_id(Integer.valueOf(shopping_ids[i]));
//				shopping = dao.queryForObject(shopping);
//
//				// 添加申请单详细信息
//				apply_goods = new Apply_Goods();
//
//				if (shopping.getApply_goods_count() == null
//						|| shopping.getApply_goods_count() == 0) {
//					apply_goods.setApply_goods_count(0);
//					//list.add(APPLYCOUNTERROR);// 申请数量为0
//				} else {
//					apply_goods.setApply_goods_count(shopping
//							.getApply_goods_count());
//				}
//				apply_goods.setApply_goods_isdel(0);
//				apply_goods.setApply_id_fk(apply_id);
//				apply_goods.setGoods_id_fk(shopping.getGoods_id_fk());
//				boo = dao.save(apply_goods);
//
//				// 更改库存表
//				if (boo) {
//					boo = dao.delete(shopping);
//				}
//
//			}
//
//		}
//		if (boo) {
//			dao.commitTransaction();
//			list.add(SUCCESS);
//		} else {
//			if (list.size() < 1) {
//				list.add(SYSTEM_ERROR);
//			}
//		}
//		System.out.println(list);
//		return list;
//	}

	

	/**
	 * 采购人员查询所有申请采购的数据
	 * 
	 * @param purchaseManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<PurchaseManageFormBean> queryPurchaseManageFormBean(
			PurchaseManageFormBean purchaseManageFormBean, int pc, int ps,
			String apply_type, Filters filters) {
		StringBuffer sql = new StringBuffer();
		if ("".equals(apply_type)) {
			sql.append("SELECT * FROM  " + " t_apply,t_user,t_office "
					+ " WHERE t_apply.user_id_fk = t_user.user_id "
					+ " AND t_user.office_id_fk = t_office.office_id "
					+ " AND t_apply.apply_state = '待审核' and ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps),
					PurchaseManageFormBean.class);
		} else {
			sql.append("SELECT * FROM  " + " t_apply,t_user,t_office "
					+ " WHERE t_apply.user_id_fk = t_user.user_id "
					+ " AND t_user.office_id_fk = t_office.office_id "
					+ " AND t_apply.apply_type = " + apply_type
					+ " AND t_apply.apply_state = '待审核' and ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps),
					PurchaseManageFormBean.class);
		}
	}

	/**
	 * 采购人员查询所有申请采购的信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @return
	 */
	protected List<Integer> queryCount(
			PurchaseManageFormBean purchaseManageFormBean, String apply_type,
			Filters filters) {
		list = new ArrayList<Integer>(1);
		if ("".equals(apply_type)) {
			list.add(dao
					.getDataCount(
							"select count(*) from t_apply where t_apply.apply_state = ?",
							new Object[] { "待审核" }));
		} else {
			list.add(dao
					.getDataCount(
							"select count(*) from t_apply where t_apply.apply_type = ? AND t_apply.apply_state = ?",
							new Object[] { "2", "待审核" }));
			StringBuffer sql = new StringBuffer();
			if ("".equals(apply_type)) {
				sql.append("select count(*) from t_apply where t_apply.apply_state = ? ");
				list.add(dao.getDataCount(sql.append(filters.toAndSql()),
						new Object[] { "待审核" }));
			} else {
				sql.append("select count(*) from t_apply where t_apply.apply_type = ? AND t_apply.apply_state = ? ");
				list.add(dao.getDataCount(sql.append(filters.toAndSql()),
						new Object[] { "2", "待审核" }));
			}
			return list;
		}
		return list;
	}

	/**
	 * 查询详细的申请采购的物资信息
	 * 
	 * @param purchaseManageFormBean
	 * @param pc
	 * @param ps
	 * @param apply_state
	 * @param apply_id
	 * @return
	 */
	protected List<PurchaseManageFormBean> queryPurchaseManageFormBean(
			PurchaseManageFormBean purchaseManageFormBean, int pc, int ps,
			String apply_type, int apply_id) {
		if ("".equals(apply_type)) {
			return dao.queryForList("select * from"
					+ " t_apply,t_apply_goods,t_goods,t_type"
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_goods.type_id_fk = t_type.type_id"
					+ " AND t_apply.apply_id = " + apply_id + " limit "
					+ (pc - 1) * ps + " , " + ps, PurchaseManageFormBean.class);
		} else {
			return dao.queryForList("select * from"
					+ " t_apply,t_apply_goods,t_goods,t_type"
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_goods.type_id_fk = t_type.type_id"
					+ " AND t_apply.apply_type = " + apply_type
					+ " AND t_apply.apply_id = " + apply_id + " limit "
					+ (pc - 1) * ps + " , " + ps, PurchaseManageFormBean.class);
		}
	}

	protected List<PurchaseManageFormBean> queryPurchaseManageFormBean(
			PurchaseManageFormBean purchaseManageFormBean, int pc, int ps,
			String apply_type, int apply_id, Filters filters) {
		StringBuffer sql = new StringBuffer();
		if ("".equals(apply_type)) {
			sql.append("select * from"
					+ " t_apply,t_apply_goods,t_goods,t_type"
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_goods.type_id_fk = t_type.type_id"
					+ " AND t_apply.apply_id = " + apply_id + " and ");
		} else {
			sql.append("select * from"
					+ " t_apply,t_apply_goods,t_goods,t_type"
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_goods.type_id_fk = t_type.type_id"
					+ " AND t_apply.apply_type = " + apply_type
					+ " AND t_apply.apply_id = " + apply_id + " and ");
		}
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),
				PurchaseManageFormBean.class);
	}

	/**
	 * 查询申请单详细物资信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @param apply_type
	 * @param apply_id
	 * @return
	 */
	protected List<Integer> queryCount(
			PurchaseManageFormBean purchaseManageFormBean, String apply_type,
			int apply_id, Filters filters) {
		list = new ArrayList<Integer>(1);
		if ("".equals(apply_type)) {
			list.add(dao.getDataCount("select count(*) from "
					+ " t_apply,t_apply_goods,t_goods "
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_apply.apply_id = ?", new Object[] { apply_id }));
		} else {
			list.add(dao.getDataCount("select count(*) from "
					+ " t_apply,t_apply_goods,t_goods "
					+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
					+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
					+ " AND t_apply.apply_id = ? AND t_apply.apply_type = ?",
					new Object[] { apply_id, apply_type }));
			StringBuffer sql = new StringBuffer();
			if ("".equals(apply_type)) {
				sql.append("select count(*) from "
						+ " t_apply,t_apply_goods,t_goods "
						+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
						+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
						+ " AND t_apply.apply_id = ? ");
				list.add(dao.getDataCount(sql.append(filters.toAndSql()),
						new Object[] { apply_id }));
			} else {
				sql.append("select count(*) from "
						+ " t_apply,t_apply_goods,t_goods "
						+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
						+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
						+ " AND t_apply.apply_id = ? AND t_apply.apply_type = ? ");
				list.add(dao.getDataCount(sql.append(filters.toAndSql()),
						new Object[] { apply_id, apply_type }));
			}
			return list;
		}
		return list;
	}
	
	//显示单据
	public List<VoucherDetailFormBean> queryDetailVoucher(
			VoucherDetailFormBean voucherDetailFormBean,int voucher_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_voucher AS vo,t_voucher_goods AS vg , t_goods AS g,t_user AS u"
				+ " WHERE vo.voucher_id = vg.voucher_id_fk"
				+ " AND vg.goods_id_fk = g.goods_id "
				+ " AND u.user_id = vo.applyuser_id_fk "
				+ " AND vo.voucher_id = "+ voucher_id
				+ " AND vo.voucher_isdel = 0");
		return dao.queryForList(sql.toString(),
				VoucherDetailFormBean.class);
	}

	
	
}
