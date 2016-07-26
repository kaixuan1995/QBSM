package com.qbsm.service.purchaseManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Barcode;
import com.qbsm.bean.Goods_number;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.Voucher;
import com.qbsm.bean.Voucher_Goods;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.VoucherFormBean;

//采购人员的申请管理
@SuppressWarnings("all")
public class ApplyManage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004 + "";
	private final String UPDATE_ERROR = MessageEnum.E30003 + "";
	private final String SAVE_ERROR = MessageEnum.E30002 + "";
	private final String SUCCESS = MessageEnum.S00000 + "";
	private final String SYSTEM_ERROR = MessageEnum.E11111 + "";

	/**
	 * 通过用户id查找申请单据
	 * 
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryApply(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps, int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM "
				+ " t_goods,t_apply, t_type,t_apply_goods"
				+ " WHERE t_apply_goods.apply_id_fk = t_apply.apply_id "
				+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id "
				+ " AND t_goods.type_id_fk = t_type.type_id "
				+ " AND t_apply.user_id_fk="+ user_id +"  ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
	}

	/**
	 * 通过用户id查找申请单据总数
	 * 
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	protected List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean,
			int user_id,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_apply where user_id_fk=? ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",null));
		return list;
	}

	/***
	 * 新增物资信息功能
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	protected List<String> saveApplyNewGoods(ApplyNewGoods applyNewGoods) {
		list = new ArrayList<String>(1);
		applyNewGoods.setApply_time(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
		if (dao.save(applyNewGoods)) {
			list.add(SUCCESS);
		} else {
			list.add(SAVE_ERROR);
		}
		return list;
	}

	/**
	 * 查看自己的新增物资申请
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	protected List<ApplyNewGoods> queryOurselfApplyNewGoods(
			ApplyNewGoods applyNewGoods, int pc, int ps, int user_id,
			String apply_state,Filters filters) {
		StringBuffer sql = new StringBuffer();
		if (apply_state != null && "审核通过".equals(apply_state)) {
			sql.append("select * from "
					+ " t_applynewgoods,t_type,t_user"
					+ " where t_applynewgoods.user_id_fk = t_user.user_id"
					+ " AND t_applynewgoods.type_id_fk = t_type.type_id"
					+ " AND t_applynewgoods.user_id_fk = " + user_id
					+ " AND t_applynewgoods.apply_state = '审核通过' ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), ApplyNewGoods.class);
		} else {
			sql.append("select * from "
					+ " t_applynewgoods,t_type,t_user"
					+ " where t_applynewgoods.type_id_fk = t_type.type_id"
					+ " AND t_applynewgoods.user_id_fk = t_user.user_id"
					+ " AND t_applynewgoods.apply_state != '审核通过'"
					+ " ANd t_applynewgoods.user_id_fk = ? ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[] { user_id },
					ApplyNewGoods.class);
		}
	}

	/**
	 * 查看自己的新增物资申请总数
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	protected List<Integer> queryCount(ApplyNewGoods applyNewGoods,
			int user_id, String apply_state,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		if (apply_state != null && "审核通过".equals(apply_state)) {
			sql.append("select count(*) from t_applynewgoods where user_id_fk = ? and apply_state = ? ");
			list.add(dao.getDataCount(filters.toAndSql()+"",new Object[] { user_id, apply_state }));
		} else {
			sql.append("select count(*) from t_applynewgoods where user_id_fk = ? ");
			list.add(dao.getDataCount(filters.toAndSql()+"",new Object[] { user_id }));
		}
		return list;
	}

	/**
	 * 删除我的新增物资申请
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	protected List<String> deleteApplyNewGoods(ApplyNewGoods applyNewGoods) {
		list = new ArrayList<String>(1);
		if (dao.delete(applyNewGoods)) {
			list.add(SUCCESS);
		} else {
			list.add(DELETE_ERROR);
		}
		return list;
	}

	/**
	 * 查询单据凭证
	 * 
	 * @param type
	 *            凭证类型
	 * @return
	 */
	protected List<VoucherFormBean> getVoucherFormBean(
			VoucherFormBean voucherFormBean, int pc, int ps, int type,Filters filters) {
	 
		StringBuffer querySql=new StringBuffer();
		querySql.append("select * from " +
				"t_user,t_storehouse,t_voucher " +
				"where 1=1 and t_storehouse.storehouse_id=t_voucher.storehouse_id_fk " +
				"and t_user.user_id=t_voucher.user_id_fk " +
				"and voucher_type=?");
		
		return dao.queryForList(filters.toLimitSql(querySql, pc, ps).toString(),
				new Object[] { getTypeName(type) }, VoucherFormBean.class);
	}
	private String getTypeName(int type){
		 String typeName = null;
		 if(type==1){
			 typeName = "采购单凭证单";
		 }else if(type==2){
			 typeName = "个人采购凭证单";
		 }else if(type==3){
			 typeName = "入库凭证单";
		 }else if(type==4){
			 typeName = "出库凭证单";
		 }else if(type==5){
			 typeName = "领用凭证单";
		 }else if(type==6){
			 typeName = "退货凭证单";
		 }else if(type==7){
			 typeName = "仓库调用凭证单";
		 }else if(type==9){
			 typeName = "退用凭证单";
		 }
		return typeName;
	}
	public List<Integer> queryCount(VoucherFormBean voucherFormBean,
			int apply_id,Filters filters) {
		list = new ArrayList<Integer>(1);
		Object[] p = new Object[] { apply_id };
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from "
								+ " t_user,t_storehouse,t_voucher "
								+ " where t_storehouse.storehouse_id=t_voucher.storehouse_id_fk"
								+ " and t_user.user_id=t_voucher.user_id_fk"
								+ " and t_voucher.voucher_type=?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[] { apply_id }));
		return list;
//		list.add(dao
//				.getDataCount(
//						"select count(*) from "
//								+ " t_user,t_storehouse,t_voucher "
//								+ " where t_storehouse.storehouse_id=t_voucher.storehouse_id_fk"
//								+ " and t_user.user_id=t_voucher.user_id_fk"
//								+ " and t_voucher.voucher_type=?", p));
	}

	/**
	 * 查询所有的要入库的入库申请物资详细（分页）
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryYanShouGoods(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from"
				+ " t_apply ,t_apply_goods,t_goods,t_type,t_user,t_barcode"
				+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
				+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
				+ " AND t_apply.user_id_fk = t_user.user_id"
				+ " AND t_goods.type_id_fk = t_type.type_id"
				+ " AND t_goods.goods_id = t_barcode.goods_id_fk"
				+ " AND t_apply.apply_type = '1' "
				+ " AND t_apply.apply_state = '已到货' ");
		List<StoreApplyDFormBean> list = dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyDFormBean.class);
		return list;
	}

	/**
	 * 查询所有的要入库的入库申请物资详细(所有的记录)
	 * 
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyDFormBean> queryAllYanShouGoods(
			StoreApplyDFormBean storeApplyDFormBean) {
		List<StoreApplyDFormBean> list = dao
				.queryForList(
						"select * from"
								+ " t_apply ,t_apply_goods,t_goods,t_type,t_user,t_barcode"
								+ " where t_apply.apply_id = t_apply_goods.apply_id_fk"
								+ " AND t_apply_goods.goods_id_fk = t_goods.goods_id"
								+ " AND t_apply.user_id_fk = t_user.user_id"
								+ " AND t_goods.type_id_fk = t_type.type_id"
								+ " AND t_goods.goods_id = t_barcode.goods_id_fk"
								+ " AND t_apply.apply_state = '已到货'",
						StoreApplyDFormBean.class);
		System.out.println(list);
		return list;
	}

	/**
	 * 查询所有的要入库的入库申请物资详细数量（分页）
	 * 
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<Integer> queryYanShouGoodsCount(
			StoreApplyDFormBean storeApplyDFormBean,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) "
								+ " from t_apply ,t_apply_goods where t_apply.apply_id = t_apply_goods.apply_id_fk AND t_apply.apply_type = '1'");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		return list;
		//		list.add(dao
//				.getDataCount(
//						"select count(*) "
//								+ " from t_apply ,t_apply_goods where t_apply.apply_id = t_apply_goods.apply_id_fk AND t_apply.apply_type = '1'",
//						null));
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
				.queryForList("SELECT * FROM t_Barcode"
								+ " WHERE goods_id_fk="+good_id,Barcode.class);
		return list;
	}
	
	
	/**
	 * 验收入库 步骤：更改t_apply中的apply_type为1申请入库单的apply_state改为已到货
	 * @param id 
	 * 
	 * @param user_id
	 *            经手人id
	 * @return
	 */
	protected List<String> yanshouRuKu(String[] id, int user_id) {
		list = new ArrayList<String>(1);
		List<Boolean> boos = new ArrayList<Boolean>();
		List<StoreApplyDFormBean> storeApplyDFormBeans = queryAllYanShouGoods(new StoreApplyDFormBean());
		dao.beginTransaction();
		/* 更改t_apply中的apply_type为1申请入库单的apply_state改为已验收 */
		String sql = "update t_apply set apply_state='已验收' where apply_type='1'";
		boos.add(dao.update(sql, null));

		/* 生成到货凭证单 1.先生成Voucher总表 2在生成Voucher_Goods */
		// 1.先生成Voucher总表
		Voucher voucher = new Voucher();
		voucher.setUser_id_fk(user_id);
		voucher.setVoucher_createtime(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
		// 验收凭证表
		voucher.setVoucher_type(8);
		voucher.setVoucher_isdel(0);
		// 验收入库的供应商id---------->采购单里获取？
		voucher.setVoucher_brokerage(1);
		// 仓库id我如何让得到？
		voucher.setStorehouse_id_fk(1);
		int voucherId = dao.getGeneratedKeyByInsert(voucher);
		if (voucherId != -1) {
			boos.add(true);
			System.out.println("--------");
		} else {
			System.out.println("========");
			boos.add(false);

		}
		// 2在生成Voucher_Goods
		System.out.println(storeApplyDFormBeans);
		if (storeApplyDFormBeans != null) {
			for (StoreApplyDFormBean storeApplyDFormBean : storeApplyDFormBeans) {
				Voucher_Goods voucher_Goods = new Voucher_Goods();
				voucher_Goods.setVoucher_goods_isdel(0);
				voucher_Goods.setVoucher_id_fk(voucherId);
				voucher_Goods.setGoods_id_fk(storeApplyDFormBean.getGoods_id());
				voucher_Goods.setVoucher_goods_count(Integer
						.parseInt(storeApplyDFormBean.getApply_goods_count()));
				List<Barcode>barcodes =queryBarcodeBygoodId(storeApplyDFormBean.getGoods_id());
				if(barcodes!=null){
					voucher_Goods.setBarcode_id_fk(barcodes.get(0).getBarcode_id());
				}else{
					throw new RuntimeException(storeApplyDFormBean.getGoods_id()+"所对应的条形码id没有");
				}
				System.out.println("++++++++++++");
				boos.add(dao.save(voucher_Goods));
			}
		}else{
			boos.add(false);
		}
		
		for (Boolean b : boos) {
			System.out.println(b);
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
 
}
