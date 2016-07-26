package com.qbsm.service.purchaseManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Barcode;
import com.qbsm.bean.Supplier_Goodstype;
import com.qbsm.bean.User;
import com.qbsm.bean.Voucher;
import com.qbsm.bean.Voucher_Goods;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.CheckInputFormBean;
import com.qbsm.web.formbean.PurchaseManageFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;
import com.qbsm.web.formbean.UserFormBean;
import com.qbsm.web.formbean.VoucherDetailFormBean;
import com.qbsm.web.formbean.VoucherFormBean;

public interface PurchaseManageService {

	/**
	 * 分页查询供应商的信息
	 * 
	 * @param userForBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<UserFormBean> queryApplyer(UserFormBean userForBean, int pc, int ps,Filters filters);

	/**
	 * 查询用户信息总数
	 * 
	 * @param userForBean
	 * @return
	 */
	public List<Integer> queryCount(UserFormBean userForBean,Filters filters);

	/**
	 * 添加供应商信息
	 * 
	 * @param user
	 * @param type_ids
	 * @return
	 */
	public List<String> saveApplyer(User user, String[] type_ids);

	/**
	 * 删除供应商信息
	 * 
	 * @param user
	 * @return
	 */
	public List<String> deleteApplyer(User user);

	/**
	 * 更新供应商供应类别信息
	 * 
	 * @param aupplier_Goodstypes
	 * @return
	 */
	public List<String> updateSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes);

	/**
	 * 删除供应商供应类别信息
	 * 
	 * @param aupplier_Goodstypes
	 * @return
	 */
	public List<String> deleteSupplier_Goodstype(Supplier_Goodstype aupplier_Goodstypes);

	/**
	 * 分页查询采购人员申请信息
	 */
	public List<StoreApplyDFormBean> queryApply(StoreApplyDFormBean storeApplyDFormBean, int pc, int ps, int user_id,Filters filters);

	/**
	 * 查询申请列表总数
	 * 
	 * @param userForBean
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean, int user_id,Filters filters);

	/**
	 * 通过用户id查找生成的采购计划
	 * 
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	List<PurchaseManageFormBean> queryPurchasePlan(PurchaseManageFormBean purchaseManageFormBean, int pc, int ps,Filters filters);

	/**
	 * 查询采购计划总数）
	 * 
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	List<Integer> queryPurchasePlanCount(
			PurchaseManageFormBean purchaseManageFormBean,
			Filters filters);


	/***
	 * 新增物资信息功能
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	public List<String> saveApplyNewGoods(ApplyNewGoods applyNewGoods);

	/**
	 * 查看自己的新增物资申请
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	public List<ApplyNewGoods> queryOurselfApplyNewGoods(ApplyNewGoods applyNewGoods, int pc, int ps, int user_id, String apply_state,Filters filters);

	/**
	 * 查看自己的新增物资申请总数
	 * 
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	public List<Integer> queryCount(ApplyNewGoods applyNewGoods, int user_id, String apply_state,Filters filters);

	/**
	 * 删除我的新增物资申请
	 * 
	 * @param applyNewGoods
	 * @return
	 */
	public List<String> deleteApplyNewGoods(ApplyNewGoods applyNewGoods);

	/**
	 * 采购人员查询所有申请采购的数据
	 * 
	 * @param purchaseManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<PurchaseManageFormBean> queryPurchaseManageFormBean(PurchaseManageFormBean purchaseManageFormBean, int pc, int ps, String apply_type,Filters filters);

	/**
	 * 采购人员查询所有申请采购的信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @return
	 */
	public List<Integer> queryCount(PurchaseManageFormBean purchaseManageFormBean, String apply_type,Filters filters);

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
			int apply_id,Filters filters);

	/**
	 * 查询申请单详细物资信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @param apply_type
	 * @param apply_id
	 * @return
	 */
	public List<Integer> queryCount(PurchaseManageFormBean purchaseManageFormBean, String apply_type, int apply_id,Filters filters);

	/**
	 * 查询单据凭证
	 * 
	 * @param type
	 *            凭证类型
	 * @return
	 */
	public List<VoucherFormBean> queryVoucherFormBean(VoucherFormBean voucherFormBean, int pc, int ps, int type,Filters filters);

	/**
	 * 查询单据凭证信息数量
	 * 
	 * @param purchaseManageFormBean
	 * @param apply_type
	 * @param apply_id
	 * @return
	 */
	public List<Integer> queryCount(VoucherFormBean voucherFormBean, int apply_id,Filters filters);
	
	/**
	 * 通过扫描二维码查找物资详细信息
	 * @param sets
	 * @return
	 */
	public List<CheckInputFormBean> queryCheckInputFormBean(HashSet<String> sets);
	
	/**
	 * 查询所有的要入库的入库申请物资详细（分页）
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyDFormBean> queryYanShouGoods(StoreApplyDFormBean storeApplyDFormBean,int pc,int ps,Filters filters);
	/**
	 * 查询所有的要入库的入库申请物资详细数量（分页）
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<Integer> queryYanShouGoodsCount(StoreApplyDFormBean storeApplyDFormBean,Filters filters);
	
	
	/**
	 * 验收入库
	 * 步骤：更改t_apply中的apply_type为1申请入库单的apply_state改为已到货
	 * @param shopping_ids
	 * @return
	 */
	public List<String> yanshouRuKu(String[] id,int user_id);
	/**
	 * 采购人员查询 采购申请总表（分页） 根据标记queryRang 值为 all---->查询所有的未审批和审批的采购申请
	 * notCheck----->未审批的采购申请 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 6仓库之间的调度
	 * 
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	List<StoreApplyFormBean> queryCaiGouApply(
			StoreApplyFormBean storeApplyFormBean, int pc, int ps,
			String queryRang,Filters filters) ;

	/**
	 * 采购人员查询 采购申请总表（分页）总数 根据标记queryRang 值为 all---->查询所有的未审批和审批的采购申请
	 * @param storeApplyFormBean
	 * @param queryRang
	 * @return
	 */
	List<Integer> queryCaiGouApplyCount(
			StoreApplyFormBean storeApplyFormBean,String queryRang,Filters filters);
	
	/**
	 * 采购人员查询 采购申请详细表（分页)
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	List<StoreApplyDFormBean> queryDetailApply(
			StoreApplyDFormBean storeApplyDFormBean, int pc, int ps,int apply_id,Filters filters);
	
	
	/**
	 * 采购人员查询 采购申请详细表 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	List<Integer> queryDetailApplyCount(
			StoreApplyDFormBean storeApplyDFormBean,int apply_id,Filters filters);
	
	/**
	 * 采购人员审核采购申请单 check 1 代表 通过，0代表不通过
	 * 
	 * @param apply_ids
	 * @param check 
	 * @param user_id 
	 * @return
	 */
	List<String> checkCaiGouApply(String apply_ids[], int check,int user_id);
	
	//显示单据
	public List<VoucherDetailFormBean> queryDetailVoucher(VoucherDetailFormBean voucherDetailFormBean, 
				int voucher_id);

	
	
}
