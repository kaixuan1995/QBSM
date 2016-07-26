package com.qbsm.web.action.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import sun.misc.BASE64Encoder;

import com.qbsm.bean.Barcode;
import com.qbsm.bean.User;
import com.qbsm.service.ServiceFactory;
import com.qbsm.service.util.BarcodeUtil;
import com.qbsm.web.formbean.BarcodeFormBean;
import com.qbsm.web.formbean.GoodsManageFormBean;

/**
 * 
 * @author xiaoyiming
 * 物资识别码管理(供应商)
 */
@Controller
@RequestMapping("MaterialIdentityAction")
public class MaterialIdentityAction {
	
	private Logger log = LoggerFactory.getLogger(MaterialIdentityAction.class);
	private int user_id =  0;
	
	//条形码转换 
	@RequestMapping("/gettiaoxingmazhuanhuan")
	public void gettiaoxingmazhuanhuan(BarcodeFormBean barcodeFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		setTable(barcodeFormBean, request, response);
	}
		
	//条形码生成 
	@RequestMapping("/gettiaoxingmashengcheng")
	public void gettiaoxingmashengcheng(GoodsManageFormBean goodsManageFormBean,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		} 
		setTable(goodsManageFormBean, request, response);
	}
	
	// 获得表格数据
	private void setTable(Object object, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		log.info("setTable获得表格数据   \n请求参数为:{}",request.getRequestURI()+"?"+request.getQueryString());
		String  url = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		log.info("url = {}",url);
		//第几页
		int page = Integer.valueOf(request.getParameter("page"));
		//每页显示的数量
		int rows = Integer.valueOf(request.getParameter("rows"));
		// 定义返回的数据类型：json，使用了json-lib
		JSONObject jsonObj = new JSONObject();
		// 定义rows，存放数据
		JSONArray array = new JSONArray();
		//拿数据
		List<?> lists = getPage(object, page, rows,url);
		int total =0;int records=0;
		if(lists!=null){
			for (int i = 0; i < lists.size(); i++) {
				array.add(JSONObject.fromObject((Object) lists.get(i)));
			}
			//数据库 总数
			List<?> count = getCount(object,url);
			log.info("数据总数:"+count.get(0));
			records = (Integer) count.get(0);
			//计算总页数
			total = records % rows == 0 ? records / rows : records / rows + 1;
		}
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", page); // 当前页
		jsonObj.put("total", total); // 总页数
		jsonObj.put("records", records); // 总记录数
		// 将rows放入json对象中
		jsonObj.put("rows", array);
		// 自控制台打印输出，以检验json对象生成是否正确
		log.info("要返回的json对象：\n Object = {}",jsonObj.toString());
		// 返回json对象（通过PrintWriter输出）
		response.getWriter().print(jsonObj);
	}

	// 获得 数量
	private List<?> getCount(Object object, String url) {
		if ("gettiaoxingmashengcheng".equals(url)) {
			return invokeService("MaterialIdentityAction.queryCount", object, user_id);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryCount", object);
		} else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.queryCount", object);
		} else if("findBar".equals(url)) {
			return invokeService("Material.queryBarCount", object);
		}
		return null;
	}

	// 获得分页数据
	private List<?> getPage(Object object, int page, int rows, String url) {
		if ("gettiaoxingmashengcheng".equals(url)) {
			return invokeService("MaterialIdentityAction.queryBuyBoods", object, user_id, page, rows);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryStoreApplyDFormBean", object, page, rows);
		} else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.queryGoodsManageFormBean", object, page, rows);
		} else if("findBar".equals(url)) {
			return invokeService("Material.findBar", object, user_id);
		}
		return null;
	}
	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}

	//供应商首页跳转
	@RequestMapping("/gongyingshang")
	public String gongyingshang(){
		log.info("GYS/gongyingshang.jsp 供应商首页跳转  界面跳转");
		return "GYS/gongyingshang";
	}
	//条形码转换
	@RequestMapping("/tiaoxingmazhuanhuan")
	public String tiaoxingmazhuanhuan(){
		log.info("GYS/tiaoxingmazhuanhuan.jsp 条形码转换  界面跳转");
		return "GYS/tiaoxingmazhuanhuan";
	}
	//条形码生成
	@RequestMapping("/tiaoxingmashengcheng")
	public String tiaoxingmashengcheng(){
		log.info("GYS/tiaoxingmashengcheng.jsp 条形码生成  界面跳转");
		return "GYS/tiaoxingmashengcheng";
	}
	
	//生辰条形码
	@RequestMapping("/savetiaoxingmashengcheng")
	public void savetiaoxingmashengcheng(Barcode barcode, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("物资单位管理(增删改查)={}", oper);
		if("edit".equals(oper)) {			
			//封装barcode
			int goods_id = Integer.valueOf(request.getParameter("goods_id"));
			barcode.setGoods_id_fk(goods_id);
			User user = (User)request.getSession().getAttribute("userSession");
			barcode.setUser_id_fk(user.getUser_id());
			//保存信息
			invokeService("Material.addbarcode", barcode);
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmm");//设置日期格式
			Integer barcode_id = new Integer(df.format(new Date()));
			invokeService("Material.editvoucher", barcode_id, goods_id);								
		}
		response.getWriter().print("条形码生成成功");
	}
	
	//用来对下载的文件名称进行编码的！
	private String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); //获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		} else if(agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
	
	@RequestMapping("/download")  
	public void downLoadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String barcode_id = request.getParameter("barcode_id");
		String rootpath = request.getSession().getServletContext().getRealPath("");		
		String filepath = rootpath + "\\barcode\\" + barcode_id;
		BarcodeUtil.createBarcode(filepath, barcode_id);
		BufferedInputStream bis = null;    
		BufferedOutputStream bos = null;    		 
		long fileLength=new File(filepath + ".png").length();      
		response.setContentType("application/octet-stream");  
		response.setHeader("Content-disposition", "attachment; filename="    
				+ new String((barcode_id+".png").getBytes("utf-8"), "ISO8859-1"));    
		response.setHeader("Content-Length", String.valueOf(fileLength));  
		bis = new BufferedInputStream(new FileInputStream(filepath + ".png"));    
		bos = new BufferedOutputStream(response.getOutputStream());    
		byte[] buff = new byte[2048];    
		int bytesRead;    
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {    
			bos.write(buff, 0, bytesRead);    
		}    
		bis.close();    
		bos.close();    
	}  
	
	@RequestMapping("/findBar")
	public void findBar(Barcode barcode, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		setTable(barcode, request, response);
	}

}
