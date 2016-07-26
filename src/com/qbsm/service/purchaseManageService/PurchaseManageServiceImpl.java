package com.qbsm.service.purchaseManageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Supplier_Goodstype;
import com.qbsm.bean.User;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.CheckInputFormBean;
import com.qbsm.web.formbean.PurchaseManageFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;
import com.qbsm.web.formbean.UserFormBean;
import com.qbsm.web.formbean.VoucherDetailFormBean;
import com.qbsm.web.formbean.VoucherFormBean;

public class PurchaseManageServiceImpl implements PurchaseManageService {

	private ApplyerManage applyerManage = new ApplyerManage();
	private ApplyManage applyManage = new ApplyManage();
	private PurchaseManage purchaseManage = new PurchaseManage();
	private CheckInputStoreManage checkInputStoreManage = new CheckInputStoreManage();

	/**
	 * 分页查询供应商信息
	 */
	public List<UserFormBean> queryApplyer(UserFormBean userForBean, int pc, int ps,Filters filters) {
		return applyerManage.queryApplyer(userForBean, pc, ps,filters);
	}

	/**
	 * 查询供应商信息总数
	 */
	public List<Integer> queryCount(UserFormBean userForBean,Filters filters) {
		return applyerManage.queryCount(userForBean,filters);
	}

	/**
	 * 添加供应商信息
	 */
	public List<String> saveApplyer(User user, String[] type_ids) {
		return applyerManage.saveApplyer(user, type_ids);
	}

	/**
	 * 删除供供应商信息
	 */
	public List<String> deleteApplyer(User user) {
		return applyerManage.deleteApplyer(user);
	}

	/**
	 * 更新供应商供应类别信息
	 */
	public List<String> updateSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes) {
		return applyerManage.updateSupplier_Goodstype(aupplier_Goodstypes);
	}

	/**
	 * 删除供应商供应类别信息
	 */
	public List<String> deleteSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes) {
		return applyerManage.deleteSupplier_Goodstype(aupplier_Goodstypes);
	}

	/*------------------------------------------------------------------------*/
	/**
	 * 分页查询采购人员申请信息
	 */
	public List<StoreApplyDFormBean> queryApply(StoreApplyDFormBean storeApplyDFormBean, int pc, int ps, int user_id,Filters filters) {
		return applyManage.queryApply(storeApplyDFormBean, pc, ps, user_id,filters);
	}

	/**
	 * 查找采购人员申请总数
	 */
	public List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean, int user_id,Filters filters) {
		return applyManage.queryCount(storeApplyDFormBean, user_id,filters);
	}

	/**
	 * 通过用户id查找生成的采购计划
	 * 
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	@Override
	public List<PurchaseManageFormBean> queryPurchasePlan(PurchaseManageFormBean purchaseManageFormBean, int pc, int ps, Filters filters) {
		return purchaseManage.queryPurchasePlan(purchaseManageFormBean, pc, ps,filters);
	}

	/**
	 * 查询采购计划总数）
	 * 
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryPurchasePlanCount(
			PurchaseManageFormBean purchaseManageFormBean,
			Filters filters) {
		return purchaseManage.queryPurchasePlanCount(purchaseManageFormBean, filters);
	}

	/***
	 * 新增物资信息功能
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	public List<String> saveApplyNewGoods(ApplyNewGoods applyNewGoods) {
		return applyManage.saveApplyNewGoods(applyNewGoods);
	}

	/**
	 * 查看自己的新增物资申请
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	public List<ApplyNewGoods> queryOurselfApplyNewGoods(ApplyNewGoods applyNewGoods, int pc, int ps, int user_id, String apply_state,Filters filters) {
		return applyManage.queryOurselfApplyNewGoods(applyNewGoods, pc, ps, user_id, apply_state,filters);
	}

	/**
	 * 查看自己的新增物资申请总数
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	public List<Integer> queryCount(ApplyNewGoods applyNewGoods, int user_id, String apply_state,Filters filters) {
		return applyManage.queryCount(applyNewGoods, user_id, apply_state,filters);
	}

	/**
	 * 删除我的新增物资申请
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	public List<String> deleteApplyNewGoods(ApplyNewGoods applyNewGoods) {
		return applyManage.deleteApplyNewGoods(applyNewGoods);
	}

	/**
	 * 采购人员查询所有申请采购的数据
	 * 
	 * @param purchaseManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<PurchaseManageFormBean> queryPurchaseManageFormBean(PurchaseManageFormBean purchaseManageFormBean, int pc, int ps, String apply_type,Filters filters) {
		return purchaseManage.queryPurchaseManageFormBean(purchaseManageFormBean, pc, ps, apply_type,filters);
	}

	/**
	 * 采购人员查询所有申请采购的信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @return
	 */
	public List<Integer> queryCount(PurchaseManageFormBean purchaseManageFormBean, String apply_type,Filters filters) {
		return purchaseManage.queryCount(purchaseManageFormBean, apply_type,filters);
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
	public List<PurchaseManageFormBean> queryPurchaseManageFormBean(PurchaseManageFormBean purchaseManageFormBean, int pc, int ps, String apply_type,
			int apply_id,Filters filters) {
		return purchaseManage.queryPurchaseManageFormBean(purchaseManageFormBean, pc, ps, apply_type, apply_id,filters);
	}

	/**
	 * 查询申请单详细物资信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @param apply_type
	 * @param apply_id
	 * @return
	 */
	public List<Integer> queryCount(PurchaseManageFormBean purchaseManageFormBean, String apply_type, int apply_id,Filters filters) {
		return purchaseManage.queryCount(purchaseManageFormBean, apply_type, apply_id,filters);
	}

	/**
	 * 查询单据凭证信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @param apply_type
	 * @param apply_id
	 * @return
	 */
	public List<Integer> queryCount(VoucherFormBean voucherFormBean, int apply_id,Filters filters) {
		return applyManage.queryCount(voucherFormBean, apply_id,filters);
	}

	/**
	 * 查询单据凭证
	 * 
	 * @param type
	 *            凭证类型
	 * @return
	 */
	@Override
	public List<VoucherFormBean> queryVoucherFormBean(VoucherFormBean voucherFormBean, int pc, int ps, int type,Filters filters) {
		return applyManage.getVoucherFormBean(voucherFormBean, pc, ps, type, filters);
	}

	/**
	 * 通过扫描二维码查找物资详细信息
	 * @param sets
	 * @return
	 */
	public List<CheckInputFormBean> queryCheckInputFormBean(HashSet<String> sets) {
		return checkInputStoreManage.queryCheckInputFormBean(sets);
	}

	@Override
	public List<StoreApplyDFormBean> queryYanShouGoods(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps,Filters filters) {
		// TODO Auto-generated method stub
		return applyManage.queryYanShouGoods(storeApplyDFormBean, pc, ps,filters);
	}

	@Override
	public List<Integer> queryYanShouGoodsCount(
			StoreApplyDFormBean storeApplyDFormBean,Filters filters) {
		// TODO Auto-generated method stub
		return applyManage.queryYanShouGoodsCount(storeApplyDFormBean,filters);
	}

	@Override
	public List<String> yanshouRuKu(String[] id,int user_id) {
		// TODO Auto-generated method stub
		return applyManage.yanshouRuKu(id,user_id);
	}

	@Override
	public List<StoreApplyFormBean> queryCaiGouApply(
			StoreApplyFormBean storeApplyFormBean, int pc, int ps,
			String queryRang,Filters filters) {
		// TODO Auto-generated method stub
		return purchaseManage.queryCaiGouApply(storeApplyFormBean, pc, ps, queryRang, filters);
	}

	@Override
	public List<Integer> queryCaiGouApplyCount(
			StoreApplyFormBean storeApplyFormBean, String queryRang,Filters filters) {
		// TODO Auto-generated method stub
		return purchaseManage.queryCaiGouApplyCount(storeApplyFormBean, queryRang,filters);
	}

	@Override
	public List<StoreApplyDFormBean> queryDetailApply(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps,
			int apply_id,Filters filters) {
		// TODO Auto-generated method stub
		return purchaseManage.queryDetailApply(storeApplyDFormBean, pc, ps, apply_id, filters);
	}

	@Override
	public List<Integer> queryDetailApplyCount(
			StoreApplyDFormBean storeApplyDFormBean, int  apply_id,Filters filters) {
		// TODO Auto-generated method stub
		return purchaseManage.queryDetailApplyCount(storeApplyDFormBean, apply_id,filters);
	}
	
	/**
	 * 采购人员审核采购申请单 check 1 代表 通过，0代表不通过
	 * 
	 * @param apply_ids
	 * @param check
	 * @return
	 */
	public List<String> checkCaiGouApply(String apply_ids[], int check,int user_id) {
		return purchaseManage.checkCaiGouApply(apply_ids, check,user_id);
	}
	
	//显示单据
	public List<VoucherDetailFormBean> queryDetailVoucher(VoucherDetailFormBean voucherDetailFormBean,int voucher_id) {
		return purchaseManage.queryDetailVoucher(voucherDetailFormBean,voucher_id);
	}
}