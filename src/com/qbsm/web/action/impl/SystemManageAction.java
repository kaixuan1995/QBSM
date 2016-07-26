package com.qbsm.web.action.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qbsm.bean.Office;
import com.qbsm.bean.Place;
import com.qbsm.bean.Storehouse;
import com.qbsm.bean.Type;
import com.qbsm.bean.User;
import com.qbsm.bean.Wordbook;
import com.qbsm.service.ServiceFactory;
import com.qbsm.service.systemManageService.BackupAndRecoverManage;
import com.qbsm.service.util.MessageUtil;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.action.SQLUtil.ServletUtils;

/**
 * 2016.2.23
 * 
 * @author xiaoyiming 系统管理
 */
@Controller
@RequestMapping("SystemManageAction")
public class SystemManageAction {
	private Logger log = LoggerFactory.getLogger(SystemManageAction.class);

	// 首页
	@RequestMapping("/admin")
	public String admin() {
		log.info("/ADMIN/admin.jsp 首页界面跳转");
		return "ADMIN/admin";
	}

	// 用户信息列表
	@RequestMapping("/yonghuxinxiliebiao")
	public String yonghuxinxiliebiao() {
		log.info("/ADMIN/yonghuxinxiliebiao.jsp 用户信息列表界面跳转");
		return "ADMIN/yonghuxinxiliebiao";
	}

	// 生成供应商账号
	@RequestMapping("/shengchenggongyingshangzhanghao")
	public String shengchenggongyingshangzhanghao(HttpServletRequest request) {
		log.info("/ADMIN/shengchenggongyingshangzhanghao.jsp 生成供应商账号界面跳转");
		List<?> lists = invokeService("SystemManageAction.queryType",
				new Type());
		request.setAttribute("tpyeList", lists);
		return "ADMIN/shengchenggongyingshangzhanghao";
	}

	// 物资列表
	@RequestMapping("/wuziliebiao")
	public String wuziliebiao() {
		log.info("/ADMIN/wuziliebiao.jsp  物资列表 界面跳转");
		return "ADMIN/wuziliebiao";
	}

	// 物资类别列表
	@RequestMapping("/wuzileibie")
	public String wuzileibie() {
		log.info("ADMIN/wuzileibie.jsp  物资类别 界面跳转");
		return "ADMIN/wuzileibie";
	}

	// 获得物资列表
	@ResponseBody
	@RequestMapping("/getwuziliebiao")
	public void getwuziliebiao(User user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	}

	// 编辑物资列表(有效期预警设置)
	@ResponseBody
	@RequestMapping("/savewuziliebiao")
	public void savewuziliebiao(User user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	}

	// 仓库管理
	@RequestMapping("/cangkuguanli")
	public String cangkuguanli() {
		log.info("/ADMIN/cangkuguanli.jsp  仓库管理 界面跳转");
		return "ADMIN/cangkuguanli";
	}

	// 科室管理
	@RequestMapping("/keshiguanli")
	public String keshiguanli() {
		log.info("/ADMIN/keshiguanli.jsp  仓科室管理 界面跳转");
		return "ADMIN/keshiguanli";
	}

	// 物资单位管理
	@RequestMapping("/wuzidanweiguanli")
	public String wuzidanweiguanli() {
		log.info("/ADMIN/wuzidanweiguanli.jsp  物资单位管理 界面跳转");
		return "ADMIN/wuzidanweiguanli";
	}

	// 获得物资单位管理列表
	@RequestMapping("/getwuzidanweiguanli")
	public void getwuzidanweiguanli(Wordbook wordbook,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(wordbook, request, response, filters);
	}

	// 获得仓库管理列表
	@RequestMapping("/getcangkuguanli")
	public void getcangkuguanli(Storehouse storehouse,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storehouse, request, response, filters);
	}

	// 获得仓库管理列表
	@RequestMapping("/getcangkuguanliPlace")
	public void getcangkuguanliPlace(Place place, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		log.info("查询与上表述相关数据getcangkuguanliPlace  id = {}", id);
		// 如果id有值,就说明是操作下面那张table,所以根据id来进行查询
		if (id != null) {
			place.setStorehouse_id_fk(Integer.valueOf(id));
		}else {
			place.setStorehouse_id_fk(-1);
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(place, request, response, filters);
	}

	// 获得科室管理列表
	@RequestMapping("/getkeshiguanli")
	public void getkeshiguanli(Office office, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		log.info("id = {}", id);
		// 如果id有值,就说明是操作下面那张table,所以根据id来进行查询
		if (id != null) {
			office.setStorehouse_id_fk(Integer.valueOf(id));
			log.info("查询与上表述相关数据getcangkuguanliPlace  id ={}", id);
		}else {
			office.setStorehouse_id_fk(-1);
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(office, request, response, filters);
	}
	// 获得表格数据
	private void setTable(Object object,HttpServletRequest request,
			HttpServletResponse response,Filters filters) throws IOException {
		log.info("setTable获得表格数据   \n请求参数为:{}",request.getRequestURI()+"?"+request.getQueryString());
		String url = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
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
		List<?> lists = getPage(object, page, rows,url,filters);
		int total =0;int records=0;
		if(lists!=null){
			for (int i = 0; i < lists.size(); i++) {
				array.add(JSONObject.fromObject((Object) lists.get(i)));
			}
			//数据库 总数
			List<?> count = getCount(object,url,filters);
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
	private List<?> getCount(Object object, String url, Filters filters) {
		if ("getwuzidanweiguanli".equals(url)) {
			return invokeService("MenberInforAction.queryCount", object,filters);
		} else if ("getcangkuguanli".equals(url)) {
			return invokeService("SystemManageAction.queryCount", object,filters);
		} else if ("getcangkuguanliPlace".equals(url)) {
			return invokeService("SystemManageAction.queryCount", object,filters);
		} else if ("getkeshiguanli".equals(url)) {
			return invokeService("MenberInforAction.queryCount", object,filters);
		}
		return null;
	}

	// 获得分页数据
	private List<?> getPage(Object object, int page, int rows, String url, Filters filters) {
		if ("getwuzidanweiguanli".equals(url)) {
			log.info("getPage");
			return invokeService("MenberInforAction.queryWordbook", object,page, rows,filters);
		} else if ("getcangkuguanli".equals(url)) {
			return invokeService("SystemManageAction.queryStorehouse", object,page, rows,filters);
		} else if ("getcangkuguanliPlace".equals(url)) {
			return invokeService("SystemManageAction.queryPlace", object, page,rows,filters);
		} else if ("getkeshiguanli".equals(url)) {
			return invokeService("MenberInforAction.queryOffice", object, page,rows,filters);
		}
		return null;
	}

	// 物资单位管理(增删改查)
	@RequestMapping("/savewuzidanweiguanli")
	public void savewuzidanweiguanli(Wordbook wordbook,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("物资单位管理(增删改查)={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {
			log.info("物资单位管理(增删改查)user={}", wordbook);
			lists = invokeService("MenberInforAction.savewuzidanweiguanliadd",
					wordbook);
		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			wordbook.setWordbook_id(Integer.valueOf((String) request
					.getParameter("id")));
			log.info("物资单位管理(增删改查)user={}", wordbook);
			lists = invokeService("MenberInforAction.savewuzidanweiguanliedit",
					wordbook);
		} else if ("del".equals(oper)) {// 支持批量删除//1,2,3
			Object[] ids = (Object[]) (String.valueOf(request
					.getParameter("id"))).split(",");
			for (int i = 0; i < ids.length; i++) {
				log.info("物资单位管理(增删改查) ids={}", ids[i]);
				wordbook.setWordbook_id(Integer.valueOf((String) ids[i]));
				lists = invokeService(
						"MenberInforAction.savewuzidanweiguanlidel", wordbook);
			}
		}
		operResponse(response, lists);
	}

	// 仓库管理(增删改)
	@RequestMapping("/savecangkuguanli")
	public void savecangkuguanli(Storehouse storehouse,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("仓库管理(增删改查)={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {
			log.info("仓库管理(增删改查) user={}", storehouse);
			lists = invokeService("SystemManageAction.savecangkuguanliadd",
					storehouse);
		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			storehouse.setStorehouse_id(Integer.valueOf((String) request
					.getParameter("id")));
			log.info("仓库管理(增删改查)user={}", storehouse);
			lists = invokeService("SystemManageAction.savecangkuguanliedit",
					storehouse);
		} else if ("del".equals(oper)) {// 支持批量删除//1,2,3
			Object[] ids = (Object[]) (String.valueOf(request
					.getParameter("id"))).split(",");
			for (int i = 0; i < ids.length; i++) {
				log.info("仓库管理(增删改查)ids={}", ids[i]);
				storehouse.setStorehouse_id(Integer.valueOf((String) ids[i]));
				lists = invokeService("SystemManageAction.savecangkuguanlidel",
						storehouse);
			}
		}
		operResponse(response, lists);
	}

	// 仓库分区管理(增删改)
	@RequestMapping("/savecangkuguanliPlace")
	public void savecangkuguanliPlace(Place place, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("仓库分区管理(增删改查)={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {
			log.info("仓库分区管理(增删改查) user={}", place);
			lists = invokeService(
					"SystemManageAction.savecangkuguanliPlaceadd", place);
		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			place.setPlace_id(Integer.valueOf((String) request
					.getParameter("id")));
			log.info("仓库分区管理(增删改查)user={}", place);
			lists = invokeService(
					"SystemManageAction.savecangkuguanliPlaceedit", place);
		} else if ("del".equals(oper)) {// 支持批量删除//1,2,3
			Object[] ids = (Object[]) (String.valueOf(request
					.getParameter("id"))).split(",");
			for (int i = 0; i < ids.length; i++) {
				log.info("仓库分区管理(增删改查)ids={}", ids[i]);
				place.setPlace_id(Integer.valueOf((String) ids[i]));
				lists = invokeService(
						"SystemManageAction.savecangkuguanliPlacedel", place);
			}
		}
		operResponse(response, lists);
	}

	// 科室列表管理(增删改)
	@RequestMapping("/savekeshiguanli")
	public void savekeshiguanli(Office office, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("科室列表管理(增删改查)={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {
			log.info("科室列表管理(增删改查) user={}", office);
			lists = invokeService("MenberInforAction.savekeshiguanliadd",
					office);
		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			office.setOffice_id(Integer.valueOf((String) request
					.getParameter("id")));
			log.info("科室列表管理(增删改查)user={}", office);
			lists = invokeService("MenberInforAction.savekeshiguanliedit",
					office);
		} else if ("del".equals(oper)) {// 支持批量删除//1,2,3
			Object[] ids = (Object[]) (String.valueOf(request
					.getParameter("id"))).split(",");
			for (int i = 0; i < ids.length; i++) {
				log.info("科室列表管理(增删改查)ids={}", ids[i]);
				office.setOffice_id(Integer.valueOf((String) ids[i]));
				lists = invokeService("MenberInforAction.savekeshiguanliedel",
						office);
			}
		}
		operResponse(response, lists);
	}

	private void operResponse(HttpServletResponse response, List<?> lists)
			throws IOException {
		String message = MessageUtil.getMessage((String) lists.get(0));
		log.info("返回值 Message={}",message);
		response.getWriter().print(message);
	}

	// 个人绩效管理
	@RequestMapping("/gerenjixiaoguanli")
	public String gerenjixiaoguanli() {
		log.info("/ADMIN/gerenjixiaoguanli.jsp  个人绩效管理  界面跳转");
		return "ADMIN/gerenjixiaoguanli";
	}

	// 系统维护
	@RequestMapping("/xitongweihu")
	public String xitongweihu() {
		log.info("/ADMIN/xitongweihu.jsp  系统维护  界面跳转");
		return "ADMIN/xitongweihu";
	}

	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}

	// 数据库备份
	@RequestMapping("/dbBackup")
	public String dbBackup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("/ADMIN/xitongweihu.jsp  系统数据库备份");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String downLoadPath = BackupAndRecoverManage.backup();
		downLoadPath = new String(downLoadPath.getBytes("ISO8859-1"), "utf-8");
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			System.out.println("fileLength---------" + fileLength);
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ downLoadPath);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
				System.out.println("下载中....");
			}
			bos.flush();
		} catch (Exception e) {
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		//
		return null;
	}

	// 数据库上传恢复
	@RequestMapping("/uploadSqlFile")
	public String dbRecover(
			@RequestParam(value = "url", required = true) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/sql");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileSql = "/"+path+fileName;
		System.out.println(fileSql);
//		BackupAndRecoverManage.recover(fileSql);
		return null;
	}
	
	// 数据库上传恢复
		@RequestMapping("/dbBackUp")
		public String dbBackUp(
				@RequestParam(value = "url", required = true) MultipartFile file,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/sql");
			String fileName = file.getOriginalFilename();
			System.out.println(path);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String fileSql = "/"+path+fileName;
			System.out.println(fileSql);
//			BackupAndRecoverManage.recover(fileSql);
			return null;
		}

}
