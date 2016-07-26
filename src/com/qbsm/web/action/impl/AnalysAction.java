package com.qbsm.web.action.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbsm.service.ServiceFactory;
/**
 * 
 * @author xeonmic
 * 数据分析管理
 */
@Controller
@RequestMapping("AnalysAction")
public class AnalysAction {
	private Logger log = LoggerFactory.getLogger(AnalysAction.class);
	//个人绩效
	@RequestMapping("/getgerenjixiao")
	public void getgerenjixiao(Object object, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		setTable(object, request, response);
	}
	//获取联动列表
	@RequestMapping("/getselectlist")
	public void getselectlist(
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<?> selectList= invokeService("AnalysAction.querySelectList");
		if (selectList!=null &&!selectList.isEmpty()) {
			response.getWriter().print(selectList.get(0));
		}else {
			System.out.println("错误！");
		}
		//response.getWriter().print(new DateAnalysisServiceImpl().querySelectList().get(0));
	}
	//高级查询
		@RequestMapping("/search")
		public void search(
				HttpServletRequest request, HttpServletResponse response) throws IOException{
			String typeString = request.getParameter("type");
			String messageString = request.getParameter("message");
			String roleString = request.getParameter("role");
			String dataString = request.getParameter("date");
			String valueString = request.getParameter("value");
			String rowString = request.getParameter("rows");
			String pageString = request.getParameter("page");
			typeString = new String(typeString.getBytes("iso-8859-1"),"utf-8");
			messageString = new String(messageString.getBytes("iso-8859-1"),"utf-8");
			roleString = new String(roleString.getBytes("iso-8859-1"),"utf-8");
			valueString = new String(valueString.getBytes("iso-8859-1"),"utf-8");
			List<?> count = null;
			int size = 0;
			System.out.println("typeString="+typeString);
			System.out.println("messageString="+messageString);
			System.out.println("roleString="+roleString);
			System.out.println("dataString="+dataString);
			System.out.println("valueString="+valueString);
			if (dataString!=null&&!"".equals(dataString)) {
				System.out.println(dataString);
				String before_Date = dataString.substring(0, dataString.indexOf("-")-1);
				String[] strings = before_Date.split("/");
				if (strings.length == 3) {
					before_Date = strings[2]+"-" +strings[0]+"-"+strings[1];
				}else {
					System.out.println("日期错误");
				}
				String after_Date =  dataString.substring(dataString.indexOf("-")+2,dataString.length());
				strings = after_Date.split("/");
				if (strings.length == 3) {
					after_Date = strings[2]+"-" +strings[0]+"-"+strings[1];
				}else {
					System.out.println("日期错误");
				}
				//String keysString=request.getParameter("key");
				System.out.println("before_Date ： "+before_Date);
				System.out.println("after_Date ： "+after_Date);
				List<?> list = null;
				if (typeString.equals("检测人员")) {
					String user_idString = messageString.substring(0, messageString.indexOf("#")-1);
					System.out.println("user_idString: "+user_idString);
					if (roleString.equals("请购")) {
						
						list = invokeService("AnalysAction.queryApplyGoodsInfo",Integer.valueOf(user_idString),before_Date,after_Date,Integer.valueOf(pageString),Integer.valueOf(rowString));
						count = invokeService("AnalysAction.queryCount",Integer.valueOf(user_idString),before_Date,after_Date,1);
					}else if(roleString.equals("领用")){
						list = invokeService("AnalysAction.queryApplyGoodsInfo",before_Date,after_Date,Integer.valueOf(user_idString),Integer.valueOf(pageString),Integer.valueOf(rowString));
						count = invokeService("AnalysAction.queryCount",before_Date,after_Date,Integer.valueOf(user_idString),2);
					}
					
				}
				else if (typeString.equals("采购人员")) {
					String user_idString = messageString.substring(0, messageString.indexOf("#")-1);
					if(roleString.equals("采购物资类别")){
						list = invokeService("AnalysAction.queryStoreAnalysisFormBean",Integer.valueOf(user_idString),Integer.valueOf(valueString),before_Date,after_Date,Integer.valueOf(pageString),Integer.valueOf(rowString));
						count = invokeService("AnalysAction.queryCount",Integer.valueOf(user_idString),Integer.valueOf(valueString),before_Date,after_Date);
					}
					
				}else if(typeString.equals("科室")){
					String offic_idString = messageString.substring(0, messageString.indexOf("#")-1);
					if (roleString.equals("请购")) {
						list = invokeService("AnalysAction.queryUseGoodsInfo",before_Date,after_Date,Integer.valueOf(offic_idString),Integer.valueOf(pageString),Integer.valueOf(rowString));
						count = invokeService("AnalysAction.queryCount",before_Date,Integer.valueOf(offic_idString),after_Date);
					}else if(roleString.equals("领用")){
						list = invokeService("AnalysAction.queryUseGoodsInfo",Integer.valueOf(offic_idString),before_Date,after_Date,Integer.valueOf(pageString),Integer.valueOf(rowString));
						count = list = invokeService("AnalysAction.queryCount",Integer.valueOf(offic_idString),before_Date,after_Date);
					}
				}else if(typeString.equals("仓库")){
					String storehouse_id = messageString.substring(0, messageString.indexOf("#")-1);
					if (roleString.equals("出仓")) {
						roleString="出库凭证单";
					}else if (roleString.equals("入仓")) {
						roleString="入库凭证单";
					}
					System.out.println("storehouse_id"+storehouse_id);
					list = invokeService("AnalysAction.queryStoreAnalysisFormBean",Integer.valueOf(storehouse_id),Integer.valueOf(valueString),roleString,before_Date,after_Date,Integer.valueOf(pageString),Integer.valueOf(rowString));
					count = invokeService("AnalysAction.queryCount",Integer.valueOf(storehouse_id),Integer.valueOf(valueString),roleString,before_Date,after_Date);
				}else if(typeString.equals("供应商")){
					String user_idString = messageString.substring(0, messageString.indexOf("#")-1);
					list = invokeService("AnalysAction.querySupplierAnalysisFromBean",Integer.valueOf(user_idString),before_Date,after_Date,Integer.valueOf(pageString),Integer.valueOf(rowString));
					count = invokeService("AnalysAction.queryCount",before_Date,after_Date,Integer.valueOf(user_idString));
				}else {
					System.out.println("错误！");
				}
				//if (list!=null) {
					
					if (count!=null) {
						log.info("数据总数:"+count.get(0));
						size = (Integer) count.get(0);
						System.out.println("size"+size);
					}
					if (list!=null) {
						System.out.println(list.toString());
						
					}
					setTable(list,size, request, response);
				//}else {
					//System.out.println("无结果");
				//}
				
				
				
			}
//			
			
			
		}
	
		// 获得表格数据
		private void setTable(List<?> lists,int records, HttpServletRequest request,
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
		
			int total =0;
			if(lists!=null){
				for (int i = 0; i < lists.size(); i++) {
					array.add(JSONObject.fromObject((Object) lists.get(i)));
				}
				//数据库 总数
				
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
		if ("getwuziliebiao".equals(url)) {
			return invokeService("StoreManageAction.queryGoodsFormBeanCount", object);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryCount", object);
		} else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.queryCount", object);
		}else {
			return invokeService("AnalysAction.queryCount", object);
		}
	}

	// 获得分页数据
	private List<?> getPage(Object object, int page, int rows, String url) {
		if ("getwuziliebiao".equals(url)) {
			return invokeService("StoreManageAction.queryGoodsFormBean", object, page, rows);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryStoreApplyDFormBean", object, page, rows);
		} else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.queryGoodsManageFormBean", object, page, rows);
		}else {
			return invokeService("AnalysAction.queryCount", object);
		}
	}
	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}

}
