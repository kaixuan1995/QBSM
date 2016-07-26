package com.qbsm.service.dateAnalysisService;

import java.util.List;

import com.qbsm.web.formbean.ApplyGoodsInfo;
import com.qbsm.web.formbean.StoreAnalysisFormBean;
import com.qbsm.web.formbean.StoreWaringFormBean;
import com.qbsm.web.formbean.SupplierAnalysisFromBean;
import com.qbsm.web.formbean.UseGoodsInfo;

public class DateAnalysisServiceImpl implements DateAnalysisService {
	
	private MySelfAnalysis mySelfAnalysis = new MySelfAnalysis();
	private OfficeAnalysis officeAnalysis = new OfficeAnalysis();
	private PurchaseAnalysis purchaseAnalysis = new PurchaseAnalysis();
	private StoreAnalysis storeAnalysis = new StoreAnalysis();
	private SupplierAnalysis supplierAnalysis = new SupplierAnalysis();
	private SelectList selectList = new SelectList();
	private StoreWaring storeWaring = new StoreWaring();
	
	@Override
	public List<String> querySelectList() {
		return selectList.querySelectList();
	}
	
	/**
	 * 查询出某段时间内某人对某物资（小柱）的请购数量
	 * @param user_name
	 * @param goods_id_fk
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<String> queryMySelfApplyBuyByGoods_id(int user_id,int goods_id_fk,String beform_Date,String after_Date) {
		return mySelfAnalysis.queryMySelfApplyBuyByGoods_id(user_id, goods_id_fk, beform_Date, after_Date);
	}
	
	/**
	 * 查询出某段时间内某个人(人员角色诠释检测员)对于某物资(小柱)的领用数量
	 * @param user_name
	 * @param goods_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<String> queryMyselfApplyUseBygo(int user_id,int goods_id,String beform_Date,String after_Date){
		return mySelfAnalysis.queryMyselfApplyUseBygo(user_id, goods_id, beform_Date, after_Date);
	}
	
	/**
	 * 查询出某段时间内某人请购的物资信息
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<ApplyGoodsInfo> queryApplyGoodsInfo(int user_id, String beform_Date,String after_Date,int pc,int ps){
		return mySelfAnalysis.queryApplyGoodsInfo(user_id, beform_Date, after_Date,pc,ps);
	}
		
	/**
	 * 查询出某段时间内某人领用的物资信息
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<ApplyGoodsInfo> queryApplyGoodsInfo(String beform_Date, String after_Date,int user_id,int pc,int ps){
		return mySelfAnalysis.queryApplyGoodsInfo(beform_Date, after_Date,user_id,pc,ps);
	}
	
	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资(小柱)的请购数量
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<String> queryOfficeApplyBuy(int goods_id,String before_Date,String after_Date,int office_id ) {
		return officeAnalysis.queryOfficeApplyBuy(goods_id, before_Date, after_Date, office_id);
	}
	
	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资的领用数量
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<String> queryOfficeApplyUse(int goods_id,String before_Date,String after_Date,int office_id ) {
		return officeAnalysis.queryOfficeApplyUse(goods_id, before_Date, after_Date, office_id);
	}
	
	/**
	 * 查询某段时间某个科室的请购物资信息
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<UseGoodsInfo> queryUseGoodsInfo(String before_Date,String after_Date, int office_id,int pc,int ps){
		return officeAnalysis.queryUseGoodsInfo(before_Date, after_Date, office_id,pc,ps);
	}
	
	/**
	 * 查询某段时间某个科室的领用物资信息
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<UseGoodsInfo> queryUseGoodsInfo(int office_id,String before_Date,String after_Date,int pc,int ps){
		return officeAnalysis.queryUseGoodsInfo(office_id,before_Date, after_Date,pc,ps);
	}
	
	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别
	 * 物资的信息及其数量
	 * @param user_id		//用户id
	 * @param goods_type_id		//物资列别id
	 * @param start_Date		//开始时间
	 * @param end_Date			//结束时间
	 * @return
	 */
	public List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int user_id,int goods_type_id,String start_Date,String end_Date,int pc,int ps) {
		return purchaseAnalysis.queryStoreAnalysisFormBean(user_id,goods_type_id,start_Date,end_Date,pc,ps);
	}
	
	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量
	 * @param storehouse_id		仓库id
	 * @param user_id			用户id
	 * @param voucher_type		凭证类别
	 * @param start_Date		开始时间
	 * @param end_Date			结束时间
	 * @return
	 */
	public List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int storehouse_id,int user_id,String voucher_type,String start_Date,String end_Date,int pc,int ps) {
		return storeAnalysis.queryStoreAnalysisFormBean(storehouse_id,user_id,voucher_type,start_Date,end_Date,pc,ps);
	}
	
	/**
	 * 查询某个供应商的异常供应记录
	 * @param user_id		//用户id
	 * @param start_Date	//开始时间
	 * @param end_Date		//结束时间
	 * @return
	 */
	public List<SupplierAnalysisFromBean> querySupplierAnalysisFromBean(int user_id,String start_Date,String end_Date,int pc,int ps) {
		return supplierAnalysis.querySupplierAnalysisFromBean(user_id, start_Date, end_Date,pc,ps);
	}

	/**
	 * 有效期预警
	 */
	@Override
	public List<StoreWaringFormBean> queryStoreWaringFormBean(String waringDate, int pc,int ps) {
		return storeWaring.queryStoreWaringFormBean(waringDate, pc, ps);
	}

	/**
	 * 查询出某段时间内某人请购的物资信息（查询出来的结果的数量）
	 */
	@Override
	public List<Integer> queryCount(int user_id, String beform_Date,
			String after_Date,int i) {
		return mySelfAnalysis.queryCount(user_id, beform_Date, after_Date);
	}

	/**
	 * 查询出某段时间内某人(人员角色诠释检测员)领用物资信息（查询出来的结果的数量）
	 */
	@Override
	public List<Integer> queryCount(String beform_Date, String after_Date,
			int user_id,int i) {
		return mySelfAnalysis.queryCount(beform_Date, after_Date, user_id);
	}

	/**
	 * 查询某段时间某个科室的请购物资信息(所查询出结果的数量)
	 */
	@Override
	public List<Integer> queryCount(String before_Date, int office_id,
			String after_Date) {
		return officeAnalysis.queryCount(before_Date, office_id, after_Date);
	}

	/**
	 * 查询某段时间某个科室的领用物资信息(所查询出的结果的数量)
	 */
	@Override
	public List<Integer> queryCount(int office_id,
			String before_Date, String after_Date) {
		return officeAnalysis.queryCount(office_id, before_Date, after_Date);
	}

	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别物资的信息及其数量(所查询出的结果的数量)
	 */
	@Override
	public List<Integer> queryCount(int user_id, int goods_type_id,
			String start_Date, String end_Date) {
		return purchaseAnalysis.queryCount(user_id, goods_type_id, start_Date, end_Date);
	}

	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量（所查询出的结果的数量）
	 */
	@Override
	public List<Integer> queryCount(int storehouse_id, int user_id,
			String voucher_type, String start_Date, String end_Date) {
		return storeAnalysis.queryCount(storehouse_id, user_id, voucher_type, start_Date, end_Date);
	}

	/**
	 * 查询出仓库中在某日期前两个月内过期的物资信息(所查询出的结果的数量)
	 */
	@Override
	public List<Integer> queryCount(String waringDate) {
		return storeWaring.queryCount(waringDate);
	}

	/**
	 * 查询某个供应商的异常供应记录（所查询出的结果的数量）
	 */
	@Override
	public List<Integer> queryCount(String start_Date,
			String end_Date, int user_id) {
		return supplierAnalysis.queryCount(user_id, start_Date, end_Date);
	}


	
	/**
	 * StoreWaringFormBean   有效期预警
	 * SupplierAnalysisFromBean  查询某个供应商的异常供应记录
	 * StoreAnalysisFormBean    查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量
	 * UseGoodsInfo   查询某段时间某个科室的请购物资信息
	 * ApplyGoodsInfo   查询出某段时间内某人请购的物资信息
	 */
}
