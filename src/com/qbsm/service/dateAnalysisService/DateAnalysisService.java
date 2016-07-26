package com.qbsm.service.dateAnalysisService;

import java.util.List;

import com.qbsm.web.formbean.ApplyGoodsInfo;
import com.qbsm.web.formbean.StoreAnalysisFormBean;
import com.qbsm.web.formbean.StoreWaringFormBean;
import com.qbsm.web.formbean.SupplierAnalysisFromBean;
import com.qbsm.web.formbean.UseGoodsInfo;



public interface DateAnalysisService {

	public List<String> querySelectList();
	/**
	 * 查询出某段时间内某人对某物资（小柱）的请购数量1
	 * @param user_name
	 * @param goods_id_fk
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<String> queryMySelfApplyBuyByGoods_id(int user_id,int goods_id_fk,String beform_Date,String after_Date);
	
	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资(小柱)的请购数量
	 * @param user_name
	 * @param goods_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<String> queryMyselfApplyUseBygo(int user_id,int goods_id,String beform_Date,String after_Date);
	
	/**
	 * 查询出某段时间内某人请购的物资信息
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	
	public List<ApplyGoodsInfo> queryApplyGoodsInfo(int user_id, String beform_Date,String after_Date,int pc,int ps);

	/**
	 * 查询出某段时间内某人领用的物资信息
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @return
	 */
	public List<ApplyGoodsInfo> queryApplyGoodsInfo(String beform_Date, String after_Date,int user_id,int pc,int ps);
	
	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资(小柱)的请购数量
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<String> queryOfficeApplyBuy(int goods_id,String before_Date,String after_Date,int office_id );
		
	
	/**
	 * 查询出某段时间内某个科室(人员角色诠释检测员)对于某物资的领用数量
	 * @param goods_id
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<String> queryOfficeApplyUse(int goods_id,String before_Date,String after_Date,int office_id );
	
	/**
	 * 查询某段时间某个科室的请购物资信息
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<UseGoodsInfo> queryUseGoodsInfo(String before_Date,String after_Date, int office_id,int pc,int ps);
	
	/**
	 * 查询某段时间某个科室的领用物资信息
	 * @param before_Date
	 * @param after_Date
	 * @param office_id
	 * @return
	 */
	public List<UseGoodsInfo> queryUseGoodsInfo(int office,String before_Date,String after_Date,int pc,int ps);
			
	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别物资的信息及其数量
	 * @param user_id		//用户id
	 * @param goods_type_id		//物资列别id
	 * @param start_Date		//开始时间
	 * @param end_Date			//结束时间
	 * @return
	 */
	public List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int user_id,int goods_type_id,String start_Date,String end_Date,int pc,int ps);
	
	
	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量
	 * @param storehouse_id		仓库id
	 * @param user_id			用户id
	 * @param voucher_type		凭证类别
	 * @param start_Date		开始时间
	 * @param end_Date			结束时间
	 * @return
	 */
	public List<StoreAnalysisFormBean> queryStoreAnalysisFormBean(int storehouse_id,int user_id,String voucher_type,String start_Date,String end_Date,int pc,int ps);

	
	/**
	 * 查询某个供应商的异常供应记录
	 * @param user_id		//用户id
	 * @param start_Date	//开始时间
	 * @param end_Date		//结束时间
	 * @return
	 */
	public List<SupplierAnalysisFromBean> querySupplierAnalysisFromBean(int user_id,String start_Date,String end_Date,int pc,int ps);

	/**
	 * 有效期预警
	 * @param storeWaringFormBean
	 * @param waringDate
	 * @param pc
	 * @param ps
	 * @param filters
	 * @return
	 */
	public List<StoreWaringFormBean> queryStoreWaringFormBean(String waringDate, int pc,int ps);


	/**
	 * 
	 * @param user_id
	 * @param beform_Date
	 * @param after_Date
	 * @param i
	 * @return
	 */
	public List<Integer> queryCount(int user_id, String beform_Date,String after_Date,int i);

	/**
	 * 查询出某段时间内某人(人员角色诠释检测员)领用物资信息（查询出来的结果的数量）
	 * @param beform_Date
	 * @param after_Date
	 * @param user_id
	 * @param filters
	 * @return
	 */
	public List<Integer> queryCount(String beform_Date, String after_Date,int user_id,int i);

	/**
	 * 查询某段时间某个科室的请购物资信息(所查询出结果的数量)
	 * @param before_Date
	 * @param office_id
	 * @param after_Date
	 * @param filters
	 * @return
	 */
	public List<Integer> queryCount(String before_Date, int office_id,String after_Date);

	/**
	 * 查询某段时间某个科室的领用物资信息(所查询出的结果的数量)
	 * @param filters
	 * @param office_id
	 * @param before_Date
	 * @param after_Date
	 * @return
	 */
	public List<Integer> queryCount(int office_id,String before_Date,String after_Date);

	/**
	 * 查询某段时间内由某人经手的纳入采购计划单的某类别物资的信息及其数量(所查询出的结果的数量)
	 * @param user_id
	 * @param goods_type_id
	 * @param start_Date
	 * @param end_Date
	 * @param filters
	 * @return
	 */
	public List<Integer> queryCount(int user_id,int goods_type_id,String start_Date,String end_Date);

	/**
	 * 查询某仓库在某时间段内由某人经手所进出仓(库)的物资信息及其进出仓(库)数量（所查询出的结果的数量）
	 * @param storehouse_id
	 * @param user_id
	 * @param voucher_type
	 * @param start_Date
	 * @param end_Date
	 * @param filters
	 * @return
	 */
	public List<Integer> queryCount(int storehouse_id,int user_id,String voucher_type,String start_Date,String end_Date);

	/**
	 * 查询出仓库中在某日期前两个月内过期的物资信息(所查询出的结果的数量)
	 * @param waringDate
	 * @param filters
	 * @return
	 */
	public List<Integer> queryCount(String waringDate);

	/**
	 * 查询某个供应商的异常供应记录（所查询出的结果的数量）
	 * @param filters
	 * @param start_Date
	 * @param end_Date
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryCount(String start_Date,String end_Date,int user_id);

}
