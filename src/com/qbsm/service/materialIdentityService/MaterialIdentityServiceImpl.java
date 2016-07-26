package com.qbsm.service.materialIdentityService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Barcode;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.storeManageService.GoodsManageFormBean_Manage;
import com.qbsm.web.formbean.GoodsManageFormBean;


public class MaterialIdentityServiceImpl implements MaterialIdentityService {
	
	private GoodsManageFormBean_Manage gmfm = new GoodsManageFormBean_Manage();
	private GladiolusDao dao = new GladiolusDaoImpl();
	
	@Override
	public List<String> createCode(int goodsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> changeCode(String codeId) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * 获取被购买的且没有生成条形码的物资
	 */
	public List<GoodsManageFormBean> queryBuyBoods(GoodsManageFormBean goodsManageFormBean,int user_id,int pc,int ps) {
		return dao.queryForList("select goods_id, goods_name,goods_brand ,goods_unit,goods_standard " +
				" from t_goods g where g.goods_id in" +
				" (select goods_id_fk from t_voucher_goods vg, t_voucher v " +
				" where v.voucher_brokerage = ? and v.voucher_id = vg.voucher_id_fk and barcode_id_fk is null)" +
				" limit ? , ?", new Object[]{user_id,(pc-1)*ps,ps}, GoodsManageFormBean.class);
	}

	//查询数量
	public List<Integer> queryCount(GoodsManageFormBean g,int user_id) {
		List<Integer> list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from " +
				" t_goods g where g.goods_id in " +
				" (select goods_id_fk from t_voucher_goods vg, t_voucher v where" +
				" v.user_id_fk = ? and v.voucher_id = vg.voucher_id_fk and barcode_id_fk is null)",new Object[]{user_id}));
		return list;
	}
	
	//添加条形码信息
	public void addBarcodeInfo(Barcode barcode) {
		dao.save(barcode);
	}
	
	//更改t_voucher_goods中的barcode_id_fk
	public void editvoucher(int barcode_id, int goods_id) {
		dao.update("update t_voucher_goods set barcode_id_fk = ? where goods_id_fk = ?", new Object[]{barcode_id, goods_id});
	}
	
	//查找最近的条形码
	public List<Barcode> findBar(Barcode barcode, int user_id) {
		
		List<Barcode> list =  dao.queryForList("select * from t_barcode where user_id_fk = ? and barcode_id = (SELECT MAX(barcode_id) FROM t_barcode)", 
				new Object[]{user_id}, Barcode.class);
		for(Barcode b : list) {
			System.out.println(b.getBarcode_price()+"wsgfwsfwhsxgjwgjw");
			System.out.println(b.getBarcode_validity());
		}
		return list;	
	}
		
	//最近条形码数量
	public List<Integer> queryBarCount(Barcode barcode) {
		List<Integer> list = new ArrayList<Integer>(1);
		list.add(1);
		return list;
	}
}
