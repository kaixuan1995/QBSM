package com.qbsm.service.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.qbsm.service.purchaseManageService.PurchaseManage;
import com.qbsm.web.formbean.VoucherDetailFormBean;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @Desc：word操作工具类
 */
public class WordUtil {

	/**
	 * @Desc：生成word文件
	 * @param dataMap
	 *            word中需要展示的动态数据，用map集合来保存
	 * @param templateName
	 *            word模板名称，例如：test.ftl
	 * @param filePath
	 *            文件生成的目标路径，例如：D:/wordFile/
	 * @param fileName
	 *            生成的文件名称，例如：test.doc
	 */
	@SuppressWarnings("unchecked")
	public static void createWord(Map dataMap, String templateName,
			String filePath) {
		try {
			// 创建配置实例
			Configuration configuration = new Configuration();
			// 设置编码
			configuration.setDefaultEncoding("UTF-8");
			// ftl模板文件统一放至 com.lun.template 包下面
			configuration.setClassForTemplateLoading(WordUtil.class, "/");
			// 获取模板
			Template template = configuration.getTemplate(templateName);
			// 输出文件
			File outFile = new File(filePath);
			// 如果输出目标文件夹不存在，则创建
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			// 生成文件
			template.process(dataMap, out);
			// 关闭流
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static String setDownLoadPath(List<VoucherDetailFormBean>VoucherDetailFormBeans,String path) {
		/** 用于组装word页面需要的数据 */
		Map<String, Object> dataMap = new HashMap<String, Object>();
		/** 组装数据 */
		dataMap.put("voucher_id", VoucherDetailFormBeans.get(0).getVoucher_id()==null?"":VoucherDetailFormBeans.get(0).getVoucher_id());
		dataMap.put("user_lianxiren", VoucherDetailFormBeans.get(0).getUser_lianxiren()==null?"": VoucherDetailFormBeans.get(0).getUser_lianxiren());
		List<Map<String, Object>> voucherList = new ArrayList<Map<String, Object>>();
		for (VoucherDetailFormBean voucherDetailFormBean : VoucherDetailFormBeans) {
			System.out.println(voucherDetailFormBean);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goods_name", voucherDetailFormBean.getGoods_name()==null?"":voucherDetailFormBean.getGoods_name());
			map.put("goods_standard", voucherDetailFormBean.getGoods_standard()==null?"":voucherDetailFormBean.getGoods_standard());
			map.put("goods_cas", voucherDetailFormBean.getGoods_cas()==null?"":voucherDetailFormBean.getGoods_cas());
			map.put("barcode_validity",voucherDetailFormBean.getBarcode_validity()==null?"":voucherDetailFormBean.getBarcode_validity());
			map.put("goods_brand", voucherDetailFormBean.getGoods_brand()==null?"":voucherDetailFormBean.getGoods_brand());
			map.put("goods_number", voucherDetailFormBean.getGoods_number()==null?"":voucherDetailFormBean.getGoods_number());
			map.put("user_lianxiren", voucherDetailFormBean.getUser_lianxiren()==null?"":voucherDetailFormBean.getUser_lianxiren());
			map.put("goods_unit", voucherDetailFormBean.getGoods_unit()==null?"":voucherDetailFormBean.getGoods_unit());
			map.put("voucher_goods_count", voucherDetailFormBean.getVoucher_goods_count()==null?"": voucherDetailFormBean.getVoucher_goods_count());
			map.put("voucher_remark", voucherDetailFormBean.getVoucher_remark()==null?"":voucherDetailFormBean.getVoucher_remark());
			map.put("voucher_createtime", voucherDetailFormBean.getVoucher_createtime()==null?"": voucherDetailFormBean.getVoucher_createtime());
			map.put("voucher_type", voucherDetailFormBean.getVoucher_type()==null?"":voucherDetailFormBean.getVoucher_type());
			voucherList.add(map);
		}
		dataMap.put("voucherList", voucherList);//加入凭证的数据
		String fileName = "qbsm_voucher_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+new Random().nextInt(100000)+".doc";
	//	fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".doc";
		WordUtil.createWord(dataMap, "voucher.ftl", path+fileName);
		return fileName;
	}
	
	public static void main(String[] args) {
		PurchaseManage purchaseManage = new PurchaseManage();
		List<VoucherDetailFormBean>VoucherDetailFormBeans = purchaseManage.queryDetailVoucher(new VoucherDetailFormBean(), 1);
		setDownLoadPath(VoucherDetailFormBeans,"g:\\");
	}

}