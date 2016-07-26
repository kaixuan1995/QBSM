package com.qbsm.service.materialIdentityService;

import java.util.List;

import com.qbsm.bean.Barcode;
import com.qbsm.web.formbean.GoodsManageFormBean;

public interface MaterialIdentityService {

	/**
	 * 生成物资条形码
	 * @param goodsId：物资id号
	 * @return
	 */
	public List<String> createCode(int goodsId);
	
	/**
	 * 条形码或者二维码的转化
	 * @param codeId：原来有的条形码id和二维码id
	 * @return
	 */
	public List<String> changeCode(String codeId);
	
	/*
	 * 获取被购买的物资信息
	 */
	public List<GoodsManageFormBean> queryBuyBoods(GoodsManageFormBean goodsManageFormBean,int user_id,int pc,int ps);
	
	/*获取被购买物资信息数量
	 * 
	 */
	public List<Integer> queryCount(GoodsManageFormBean g,int user_id);
	
	/*
	 * 添加条形码信息
	 */
	public void addBarcodeInfo(Barcode barcode);
	
	/*
	 *更改t_voucher_goods中的barcode_id_fk
	 */
	public void editvoucher(int barcode_id, int goods_id);
	
	//最近一张条形码
	public List<Barcode> findBar(Barcode barcode, int user_id);
	//shuliang
	public List<Integer> queryBarCount(Barcode barcode);
}
