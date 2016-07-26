package com.qbsm.web.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qbsm.bean.Apply;
import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Goods;
import com.qbsm.bean.GoodsType;
import com.qbsm.bean.Place;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.Storehouse;
import com.qbsm.bean.User;
import com.qbsm.service.ServiceFactory;
import com.qbsm.service.util.MessageUtil;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.action.SQLUtil.ServletUtils;
import com.qbsm.web.formbean.AdminGoodsInforFormBean;
import com.qbsm.web.formbean.ApplyPictureFormBean;
import com.qbsm.web.formbean.GoodsManageFormBean;
import com.qbsm.web.formbean.LookInStoreApplyFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;

/**
 * 2016.3.24
 * 
 * @author xiaoyiming 仓库管理
 */
@Controller
@RequestMapping("StoreManageAction")
public class StoreManageAction {

	private Logger log = LoggerFactory.getLogger(StoreManageAction.class);

	// ///////////////////////////////////////////////////////////////////////
	// 检测员
	// 检测员首页
	@RequestMapping("/jianceyuan")
	public String jianceyuan() {
		log.info("JCY/jianceyuan.jsp  检测员首页  界面跳转");
		return "JCY/jianceyuan";
	}

	// 申请添加物资信息
	@RequestMapping("/shenqingtianjiawuzixinxi")
	public String shenqingtianjiawuzixinxi(HttpServletRequest request) {
		log.info("JCY/shenqingtianjiawuzixinxi.jsp  申请添加物资信息  界面跳转");
		List<?> lists = invokeService("StoreManageAction.queryGoodsType",new GoodsType());
		request.setAttribute("goodsTypeList", lists);
		return "JCY/shenqingtianjiawuzixinxi";
	}
	// 物资信息管理 申请添加物资信息
	@RequestMapping(value = "/saveshenqingtianjiawuzixinxi", method = RequestMethod.POST)
	public String saveshenqingtianjiawuzixinxi(ApplyNewGoods applyNewGoods,
			HttpServletRequest request) {
		log.info("CGY/shenqingtianjiawuzixinxi.jsp  申请添加物资信息  界面跳转");
		User user = (User) request.getSession().getAttribute("userSession");
		if(user!=null){
			applyNewGoods.setUser_id_fk(user.getUser_id());
			applyNewGoods.setUser_lianxiren(user.getUser_lianxiren());
		}
		log.info("物资属性:\napplyNewGoods = {}", applyNewGoods);
		List<?> lists = invokeService("PurchaseManageAction.saveApplyNewGoods", applyNewGoods);
		request.setAttribute("msg", MessageUtil.getMessage((String) lists.get(0)));
		request.setAttribute("applyNewGoods", applyNewGoods);
		List<?> ll = invokeService("StoreManageAction.queryGoodsType",new GoodsType());
		request.setAttribute("goodsTypeList", ll);
		return "JCY/shenqingtianjiawuzixinxi";
	}

	// 我的申请
	@RequestMapping("/xinxiwodeshenqing")
	public String xinxiwodeshenqing() {
		log.info("JCY/xinxiwodeshenqing.jsp  我的申请  界面跳转");
		return "JCY/xinxiwodeshenqing";
	}

	// 申请领用
	@RequestMapping("/shenqinglingyong")
	public String shenqinglingyong(HttpServletRequest request) {
		List<?> lists = invokeService("SystemManageAction.queryStorehouse",new Storehouse());
		request.setAttribute("storehouseList", lists);
		log.info("JCY/shenqinglingyong.jsp  申请领用  界面跳转");
		return "JCY/shenqinglingyong";
	}

	// 申请退用
	@RequestMapping("/shenqingtuiyong")
	public String shenqingtuiyong() {
		log.info("JCY/shenqingtuiyong.jsp  申请退用  界面跳转");
		return "JCY/shenqingtuiyong";
	}

	// 我的申请
	@RequestMapping("/wuziwodeshenqing")
	public String wuziwodeshenqing() {
		log.info("JCY/wuziwodeshenqing.jsp  我的申请  界面跳转");
		return "JCY/wuziwodeshenqing";
	}

	// 物资申请 我的申请
	@RequestMapping("/getwuziwodeshenqing")
	public void getwuziwodeshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}

	// 物资申请 申请退用
	@RequestMapping("/getshenqingtuiyong")
	public void getshenqingtuiyong(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		} 
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyDFormBean, request, response, filters);
	}
	// 获得物资申请 申请领用 
	@RequestMapping("/getshenqinglingyong")
	public void getshenqinglingyong(AdminGoodsInforFormBean adminGoodsInforFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(adminGoodsInforFormBean, request, response, filters);
	}

	// 获得信息申请 我的申请 
	@RequestMapping("/getxinxiwodeshenqing")
	public void getxinxiwodeshenqing(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	//	setTable(storeApplyDFormBean, request, response);
	}
	// ///////////////////////////////////////////////////////////////////////
	// 仓库管理员
	// 物资信息管理 物资信息列表
	@RequestMapping("/getwuzixinxiliebiao")
	public void getwuzixinxiliebiao(GoodsManageFormBean goodsManageFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(goodsManageFormBean, request, response, filters);
	}

	// 物资信息管理 审核信息申请
	@RequestMapping("/getshenhexinxishenqing")
	public void getshenhexinxishenqing(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(applyNewGoods, request, response, filters);
	}
	// 物资信息管理 审核信息申请  通过不通过
	@RequestMapping("/saveshenhexinxishenqing")
	public void saveshenhexinxishenqing(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("saveshenhexinxishenqing 物资信息管理 审核信息申请  通过不通过q={},ids={}",q,ids);
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.saveshenhexinxishenqing",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.saveshenhexinxishenqing",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	//申请管理 审核退用申请  通过不通过
	@RequestMapping("/getshenhexinxishenqing2")
	public void getshenhexinxishenqing2(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("getshenhexinxishenqing2 审核信息申请 q={},ids={}",q,ids);
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnUse",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnUse",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	// 物资管理 物资列表
	@RequestMapping("/getwuziliebiao")
	public void getwuziliebiao(AdminGoodsInforFormBean adminGoodsInforFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(adminGoodsInforFormBean, request, response, filters);
	}
	
	
	// 仓库管理员得到物资列表
	@RequestMapping("/storegetwuziliebiao")
	public void storegetwuziliebiao(AdminGoodsInforFormBean adminGoodsInforFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(adminGoodsInforFormBean, request, response, filters);
	}
	// 物资管理 物资类别列表
	@RequestMapping("/getwuzileibie")
	public void getwuzileibie(GoodsType goodsType, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(goodsType, request, response, filters);
	}
	@RequestMapping("/queryPlace")
	public void queryPlace(Place place, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("idss");
		log.info("queryPlace拿到的id ={}",id);
		List<?> lists = invokeService("StoreManageAction.queryPlace",place,id);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(lists);
		log.info("拿到的数据:lists={}",lists);
		response.setContentType("text/javascript");
		response.getWriter().print(result);
	}
	// 物资管理 入库管理
	@RequestMapping("/getrukuguanli")
	public void getrukuguanli(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	// 物资管理 入库管理 详细列表
	@RequestMapping("/getrukuguanli2")
	public void getrukuguanli2(LookInStoreApplyFormBean lookInStoreApply, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		log.info("查询与上表述相关数据getrukuguanli2 id = {}",id);
		//如果id有值,就说明是操作下面那张table,所以根据id来进行查询
		if(id!=null){
			lookInStoreApply.setApply_id_fk(Integer.valueOf(id));
			
			
		}else {
			lookInStoreApply.setApply_id_fk(-1);
		}
		log.info("goodsManageFormBean = {}",lookInStoreApply);
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(lookInStoreApply, request, response, filters);
	}
	//入库管理判断是否通过
	@RequestMapping("/getrukuguanli3")
	public void getrukuguanli3(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		String place_id = request.getParameter("place_id");
		log.info("getrukuguanli3 审核信息申请 q={},ids={}",q,ids.split(","));
		log.info("getrukuguanli3 审核信息申请 place_id={}",place_id);
		List<?> lists = null;
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkStoreApplyFormBean"
					,ids.split(","),"1","审核失败",user_id,place_id);
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkStoreApplyFormBean"
					,ids.split(","),"1","审核通过",user_id,place_id);
		}
		operResponse(response, lists);
	}
	// 物资管理 出库管理
	@RequestMapping("/getchukuguanli")
	public void getchukuguanli(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	//出库管理判断是否通过
	@RequestMapping("/getchukuguanli3")
	public void getchukuguanli3(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("getchukuguanli3 审核信息申请 q={},ids={}",q,ids.split(","));
		List<?> lists = null;
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkStoreApplyFormBean"
					,ids.split(","),"3","审核失败",user_id,"");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkStoreApplyFormBean"
					,ids.split(","),"3","审核通过",user_id,"");
		}
		operResponse(response, lists);
	}
	// 物资管理 申请退货
	@RequestMapping("/getshenqingtuihuo")
	public void getshenqingtuihuo(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	
	
	//物资管理 申请退货  通过不通过
	@RequestMapping("/getshenqingtuihuo2")
	public void getshenqingtuihuo2(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("物资管理 申请退货  通过不通过 审核信息申请 q={},ids={}",q,ids.split(","));
		List<?> lists = null;
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnSales",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnSales",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	// 物资管理 所有采购申请
	@RequestMapping("/gettuihuowodeshenqing")
	public void gettuihuowodeshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// setTable(goodsManageFormBean, request, response);
	}

	// 申请管理 审核退用申请
	@RequestMapping("/getshenhetuiyongshenqing")
	public void getshenhetuiyongshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	//申请管理 审核退用申请 判断是否通过
	@RequestMapping("/getshenhetuiyongshenqing3")
	public void getshenhetuiyongshenqing3(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("getshenhetuiyongshenqing3 审核信息申请 q={},ids={}",q,ids);
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.updateStoreApplyFormBean",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.updateStoreApplyFormBeans",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	// 申请管理 审核采购申请
	@RequestMapping("/getshenhecaigoushenqing")
	public void getshenhecaigoushenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	//申请管理 审核采购申请  判断是否通过
	@RequestMapping("/getshenhecaigoushenqing3")
	public void getshenhecaigoushenqing3(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("申请管理 审核采购申请  判断是否通过 q={},ids={}",q,ids);
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkApply",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkApply",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	// 申请管理 所有申请
	@RequestMapping("/getsuoyoushenqing")
	public void getsuoyoushenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	//检测人员 物资申请 申请领用  增加到购物车
	@RequestMapping("/addshenqinglingyong")
	public void addshenqinglingyong(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idss = request.getParameter("idss");
		log.info("检测人员 物资申请 申请领用  增加到购物车 已选物资列表(添加) idss = {}",idss);
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		List<?> lists = invokeService("StoreManageAction.saveShopping",idss.split(","),user_id,storehouse_id);
		operResponse(response, lists);
	}
	//检测人员 物资申请 申请领用  购物车  提交按钮
	@RequestMapping("/tongguoshenqinglingyong")
	public void tongguoshenqinglingyong(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String ids = request.getParameter("idss");
		String apply_urgent = request.getParameter("apply_urgent").equals("false")?"不加急":"加急";
		String office_id = request.getParameter("office_id");
		log.info("申请管理 审核采购申请  判断是否通过 ids={}",ids);
		log.info("申请管理 审核采购申请  判断是否通过 apply_urgent={},office_id={}",apply_urgent,office_id);
		Apply apply = new Apply();
		apply.setApply_urgent(apply_urgent);
		apply.setApply_type("3");
		apply.setStorehouse_id_fk(Integer.valueOf(office_id));
		List<?> lists = invokeService("StoreManageAction.saveApply",ids.split(","),apply);
		operResponse(response, lists);
	}
	//检测人员 物资申请 申请退用  提交按钮
	@RequestMapping(value="/tongguoshenqingtuiyong",method = RequestMethod.POST)
	public void tongguoshenqingtuiyong(@RequestParam MultipartFile[] file,Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<String> imgs = Arrays.asList("jpg","png","bmp","gif");
		String ids = URLDecoder.decode(request.getParameter("idss"), "UTF-8");
		String note = URLDecoder.decode(request.getParameter("note"), "UTF-8");
		Apply apply = new Apply();
		apply.setApply_type("4");
		apply.setApply_remark(note);
		log.info("ids={},note={}",ids,note);
		try{  
			for(int i=0;i<file.length;i++){
				if(file[i].getSize()>=10485760){//文件长度  
		        	response.getWriter().println("文件过大(10M)不能上传!");
					return;
		        } 
				String fileType = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".")+1,
						file[i].getOriginalFilename().length());
				if(!imgs.contains(fileType)){
					response.getWriter().println("文件格式不正确,只能是图片格式!");
					return;
				}
		        InputStream is = file[i].getInputStream();
		        byte[] b = new byte[(int)file[i].getSize()];  
		        int read = 0;  
		        int j = 0;  
		        while((read=is.read())!=-1){  
		            b[j] = (byte) read;  
		            j++;  
		        }  
		        is.close();  
		        String path = request.getSession().getServletContext().getRealPath("/")+"upload\\";
		        String fileName = System.currentTimeMillis()+ 
		        		new Random().nextInt(1000000)+file[i].getOriginalFilename();
		        log.info("fileName = {}",path+fileName);
		        OutputStream os = new FileOutputStream(new File(path+fileName));
		        os.write(b);  
		        os.flush();  
		        os.close(); 
		        List<?> lists = invokeService("StoreManageAction.saveTuiYongApply",
		        		ids.split(","),apply,fileName);
				operResponse(response, lists); 
			//	response.getWriter().println("上传成功!");
			}
	    }catch (Exception e) {  
	    	e.printStackTrace();
	    	response.getWriter().println("文件异常,上传失败!");
	    }  
	}
	//检测人员 采购申请 申请采购   购物车 提交按钮
	@RequestMapping("/tongguoshenqingcaigou")
	public void tongguoshenqingcaigou(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String ids = request.getParameter("idss");
		String apply_urgent = request.getParameter("apply_urgent").equals("false")?"不加急":"加急";
		log.info("申请管理 审核采购申请  判断是否通过 ids={}",ids);
		log.info("申请管理 审核采购申请  判断是否通过 apply_urgent={}",apply_urgent);
		Apply apply = new Apply();
		apply.setApply_urgent(apply_urgent);
		apply.setApply_type("2");
		List<?> lists = invokeService("StoreManageAction.saveApply",ids.split(","),apply);
		operResponse(response, lists);
	}
	
	// 仓库调度管理 申请仓库物资调度
	@RequestMapping("/getshenqingcangkuwuzidiaodu")
	public void getshenqingcangkuwuzidiaodu(GoodsManageFormBean goods, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		storehouse_id = request.getParameter("type");
		if(storehouse_id==null){
			List<?> lists = invokeService("SystemManageAction.queryStorehouse");
			if(lists!=null){
				storehouse_id = ((Storehouse) lists.get(0)).getStorehouse_id()+"";
			}
		}
		log.info("仓库id = {}",storehouse_id);
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(goods, request, response, filters);
	}
	private String storehouse_id = null;
	//仓库调度管理 申请仓库物资调度 增加到购物车
	@RequestMapping("/addshenqingcangkuwuzidiaodu")
	public void addshenqingcangkuwuzidiaodu(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idss = request.getParameter("idss");
		log.info("仓库调度管理 申请仓库物资调度 已选物资列表(添加) idss = {}",idss);
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		if(storehouse_id==null){
			List<?> lists = invokeService("SystemManageAction.queryStorehouse");
			if(lists!=null){
				storehouse_id = ((Storehouse) lists.get(0)).getStorehouse_id()+"";
			}
		}
		List<?> lists = invokeService("StoreManageAction.saveShopping",idss.split(","),user_id,storehouse_id);
		operResponse(response, lists);
	}
	// 仓库调度管理 申请仓库物资调度 已选物资列表 : 2
	@RequestMapping("/getshenqingcangkuwuzidiaodu2")
	public void getshenqingcangkuwuzidiaodu2(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info("仓库调度管理 申请仓库物资调度 已选物资列表(初始化)");
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(shopping, request, response, filters);
	}
	//仓库调度管理 申请仓库物资调度 已选物资列表  编辑 删除
	@RequestMapping("/saveshenqingcangkuwuzidiaodu")
	public void saveshenqingcangkuwuzidiaodu(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("用户信息列表界面的保存用户功能 操作方式={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {

		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			shopping.setShopping_id(Integer.valueOf((String) request.getParameter("id")));
			log.info("仓库调度管理 申请仓库物资调度 已选物资列表  编辑 删除shopping={}", shopping);
			lists = invokeService("StoreManageAction.updateShopping", shopping);
		} else if ("del".equals(oper)) {// 支持批量删除
			String ids = request.getParameter("id");
			log.info("仓库调度管理 申请仓库物资调度 已选物资列表  编辑 删除 ids={}", ids);
			lists = invokeService("StoreManageAction.deleteShopping",request.getParameter("id"));
		}
		operResponse(response, lists);
	}
	//仓库调度管理 申请仓库物资调度 已选物资列表  确定
	@RequestMapping("/tongguoshenqingcangkuwuzidiaodu")
	public void tongguoshenqingcangkuwuzidiaodu(Shopping shopping, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		String office_id = request.getParameter("office_id");
		String apply_urgent = request.getParameter("apply_urgent").equals("false")?"不加急":"加急";
		log.info("申请管理 审核采购申请  判断是否通过 q={},ids={}",q,ids);
		log.info("申请管理 审核采购申请  判断是否通过 office_id={},apply_urgent={}",office_id,apply_urgent);
		List<?> lists = invokeService("StoreManageAction.passShopping",ids.split(","),apply_urgent,office_id);
		
		operResponse(response, lists);
	}
	// 仓库调度管理 审核仓库物资调度
	@RequestMapping("/getshenhecangkuwuzidiaodu")
	public void getshenhecangkuwuzidiaodu(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	// 仓库调度管理 审核仓库物资调度  通过不通过
	@RequestMapping("/getshenhecangkuwuzidiaodu2")
	public void getshenhecangkuwuzidiaodu2(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("申请管理 审核采购申请  判断是否通过 q={},ids={}",q,ids);
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnSales",ids.split(","),"审核失败");
		}else if("yes".equals(q)){
			lists = invokeService("StoreManageAction.checkReturnSales",ids.split(","),"审核通过");
		}
		operResponse(response, lists);
	}
	private int user_id = 0;
	// 仓库调度管理 我的仓库物资调度
	@RequestMapping("/getdiaoduwode")
	public void getdiaoduwode(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		} 
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}

	// 单据处理
	@RequestMapping("/getdanjuchuli")
	public void getdanjuchuli(ApplyPictureFormBean pictureFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// setTable(goodsManageFormBean, request, response);
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
		if ("getwuziliebiao".equals(url)) {
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryCount", object,filters);
		}else if("storegetwuziliebiao".equals(url)) { 
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryCount", object,user_id,filters);
		}else if("getwuzileibie".equals(url)){
			return invokeService("StoreManageAction.queryCount", object,filters);
		}else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryCount", object,filters);
		}else if ("getshenhexinxishenqing".equals(url)) {//物资信息管理 审核信息申请 
			return invokeService("StoreManageAction.queryCount", object,filters); 
		}else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryCount", object,user_id,filters);
		}else if("getwuzixinxiliebiao".equals(url)){
			return invokeService("StoreManageAction.queryCount.queryAllGoodsCount", object,filters);
		}else if("getrukuguanli".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryCount", object,1,"待审核",filters);
		}else if("getrukuguanli2".equals(url)){
			return invokeService("StoreManageAction.queryCount", object,filters);
		}else if("getchukuguanli".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryCount", object,3,"待审核",filters);
		}else if("getshenqingtuihuo".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryCount", object,5,"待审核",filters);
		}else if("getshenhetuiyongshenqing".equals(url)){
			return invokeService("StoreManageAction.queryCheckCount", object,4,filters);
		}else if("getshenhecaigoushenqing".equals(url)){
			return invokeService("StoreManageAction.queryCheckCount", object,2,filters);
		}else if("getsuoyoushenqing".equals(url)){
			return invokeService("StoreManageAction.queryCount", object,filters);
		}else if("getshenqingcangkuwuzidiaodu".equals(url)){
			return invokeService("StoreManageAction.queryCount", object,storehouse_id,filters);
		}else if("getshenqingcangkuwuzidiaodu2".equals(url)){
			return invokeService("StoreManageAction.queryCount",object,user_id,filters);
		}else if("getshenhecangkuwuzidiaodu".equals(url)){
			return invokeService("StoreManageAction.queryCheckStoreCount", object,3,filters);
		}else if("getdiaoduwode".equals(url)){
			return invokeService("StoreManageAction.queryOurselfStoreApplyCount", object,3,user_id,filters);
		}else if("getshenqingtuiyong".equals(url)){
			return invokeService("StoreManageAction.queryLingYongCount", object,user_id,filters);
		}else if("getwuziwodeshenqing".equals(url)){
			return invokeService("StoreManageAction.queryMyApplyCount", object,user_id,"3",filters);
		}
		return null;
	}

	private List<?> getPage(Object object, int page, int rows, String url, Filters filters) {
		if ("getwuziliebiao".equals(url)) {
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryAll", object, page, rows,filters);
		}else if("storegetwuziliebiao".equals(url)) {
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryAllGoodsByStorehouse_id", object,user_id,page, rows,filters);
		}else if("getwuzileibie".equals(url)){
			return invokeService("StoreManageAction.queryGoodsType", object, page, rows,filters);
		}else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("StoreManageAction.queryStoreApplyDFormBean", object, page, rows,filters);
		}else if ("getshenhexinxishenqing".equals(url)) {//物资信息管理 审核信息申请 
			return invokeService("StoreManageAction.queryApplyNewGoods", object, page, rows,filters);
		} else if ("getshenqinglingyong".equals(url)) {
			return invokeService("StoreManageAction.AdminGoodsInforFormBean_Manage.queryAllGoodsByStorehouse_id", object,user_id,page, rows,filters);
		}else if("getwuzixinxiliebiao".equals(url)){
			return invokeService("StoreManageAction.queryGoodsFormBean.queryAllGoods", object, page, rows,filters);
		}else if("getrukuguanli".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryStoreApplyFormBean", object, page, rows,1,"待审核",filters);
		}else if("getrukuguanli2".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryLookInStoreApply", object, page, rows,filters);
		}else if("getchukuguanli".equals(url)){// 单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
			return invokeService("StoreManageAction.queryStoreApplyFormBean", object, page, rows,3,"待审核",filters);
		}else if("getshenqingtuihuo".equals(url)){//?
			return invokeService("StoreManageAction.queryStoreApplyFormBean", object, page, rows,5,"待审核",filters);
		}else if("getshenhetuiyongshenqing".equals(url)){// 状态:待审核，审核通过    审核失败，采购中   已到货   已入库    已领用
			return invokeService("StoreManageAction.queryCheckStoreApplyFormBean", object, page, rows,4,filters);
		}else if("getshenhecaigoushenqing".equals(url)){// 状态:待审核，审核通过    审核失败，采购中   已到货   已入库    已领用
			return invokeService("StoreManageAction.queryCheckStoreApplyFormBean", object, page, rows,2,filters);
		}else if("getsuoyoushenqing".equals(url)){// 状态:待审核，审核通过    审核失败，采购中   已到货   已入库    已领用
			return invokeService("StoreManageAction.queryStoreApplyFormBean", object, page, rows,filters);
		}else if("getshenqingcangkuwuzidiaodu".equals(url)){//申请仓库物资调度 
			return invokeService("StoreManageAction.queryGoodsFormBean", object,storehouse_id, page, rows,filters);
		}else if("getshenqingcangkuwuzidiaodu2".equals(url)){//申请仓库物资调度 2
			return invokeService("StoreManageAction.queryShopping",object,user_id,page,rows,filters);
		}else if("getshenhecangkuwuzidiaodu".equals(url)){
			return invokeService("StoreManageAction.queryCheckStoreApply", object, page, rows,3,filters);
		}else if("getdiaoduwode".equals(url)){
			return invokeService("StoreManageAction.queryOurselfStoreApply", object, page, rows,3,user_id,filters);
		}else if("getshenqingtuiyong".equals(url)){//物资申请 申请退用 
			return invokeService("StoreManageAction.queryLingYongGoods", object, page, rows,user_id,filters);
		}else if("getwuziwodeshenqing".equals(url)){//检测人员物资申请 我的申请 
			return invokeService("StoreManageAction.queryMyApply", object, page, rows,user_id,"3",filters);
		}
		return null;
	}

	// 编辑物资列表(有效期预警设置)
	@RequestMapping("/savewuziliebiao")
	public void savewuziliebiao(Goods goods, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("用户信息列表界面的保存用户功能 操作方式={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {

		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			goods.setGoods_id(Integer.valueOf((String) request.getParameter("id")));
			log.info("用户信息列表界面的--编辑用户功能 user={}", goods);
			lists = invokeService("StoreManageAction.savewuziliebiaoedit", goods);
		} else if ("del".equals(oper)) {// 支持批量删除
			
		}
		operResponse(response, lists);
	}

	// 物资类别(增删改)
	@RequestMapping("/savewuzileibie")
	public void savewuzileibie(GoodsType goodsType, HttpServletRequest request, 
			HttpServletResponse response)throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("物资类别(增删改查)={}",oper);
		List<?> lists = null;
		if("add".equals(oper)){
			log.info("物资类别(增删改查) goodsType={}",goodsType);
			lists = invokeService("StoreManageAction.savewuzileibieadd", goodsType);
		}else if("edit".equals(oper)){//支持一个编辑功能
			goodsType.setType_id(Integer.valueOf((String) request.getParameter("id")));
			log.info("物资类别(增删改查)goodsType={}",goodsType);
			lists = invokeService("StoreManageAction.savewuzileibieedit", goodsType);
		}else if("del".equals(oper)){//支持批量删除//1,2,3
			Object[] ids = (Object[]) (String.valueOf(request.getParameter("id"))).split(",");
			for(int i=0;i<ids.length;i++){
				log.info("物资类别(增删改查)ids={}",ids[i]);
				goodsType.setType_id(Integer.valueOf((String) ids[i]));
				lists = invokeService("StoreManageAction.savewuzileibiedel", goodsType);
			}
		}
		operResponse(response, lists);
	}
	// 编辑物资列表(有效期预警设置)
	@RequestMapping("/saveshenqingcangkuwuzidiaodu2")
	public void saveshenqingcangkuwuzidiaodu2(GoodsManageFormBean goods, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("用户信息列表界面的保存用户功能 操作方式={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {

		} else if ("edit".equals(oper)) {// 支持一个编辑功能
			goods.setGoods_id(Integer.valueOf((String) request.getParameter("id")));
			log.info("用户信息列表界面的--编辑用户功能 user={}", goods);
			lists = invokeService("StoreManageAction.savewuziliebiaoedit", goods);
		} else if ("del".equals(oper)) {// 支持批量删除
			@SuppressWarnings("unused")
			Object[] ids = (Object[]) (String.valueOf(request.getParameter("id"))).split(",");
			return;
		}
		operResponse(response, lists);
	}
	private void operResponse(HttpServletResponse response, List<?> lists)
			throws IOException {
		String message = MessageUtil.getMessage((String) lists.get(0));
		log.info("返回值 Message={},(String) lists.get(0)={}",message,(String) lists.get(0));
		response.getWriter().print(message);
	}
	// 仓库管理员首页
	@RequestMapping("/cangkuguanliyuan")
	public String cangkuguanliyuan() {
		log.info("CKGLY/cangkuguanliyuan.jsp  仓库管理员首页  界面跳转");
		return "CKGLY/cangkuguanliyuan";
	}

	// 物资信息列表
	@RequestMapping("/wuzixinxiliebiao")
	public String wuzixinxiliebiao() {
		log.info("CKGLY/wuzixinxiliebiao.jsp   物资信息列表  界面跳转");
		return "CKGLY/wuzixinxiliebiao";
	}

	// 审核信息申请
	@RequestMapping("/shenhexinxishenqing")
	public String shenhexinxishenqing() {
		log.info("CKGLY/shenhexinxishenqing.jsp  审核信息申请  界面跳转");
		return "CKGLY/shenhexinxishenqing";
	}

	// 供应商信息列表
	@RequestMapping("/gongyingshangxinxiliebiao")
	public String gongyingshangxinxiliebiao() {
		log.info("CKGLY/gongyingshangxinxiliebiao.jsp  供应商信息列表  界面跳转");
		return "CKGLY/gongyingshangxinxiliebiao";
	}

	// 物资管理物资列表
	@RequestMapping("/wuziliebiao")
	public String wuziliebiao() {
		log.info("CKGLY/wuziliebiao.jsp  物资管理 界面跳转");
		return "CKGLY/wuziliebiao";
	}
	// 入库管理
	@RequestMapping("/rukuguanli")
	public String rukuguanli() {
		log.info("CKGLY/rukuguanli.jsp  入库管理 界面跳转");
		return "CKGLY/rukuguanli";
	}

	// 出库管理
	@RequestMapping("/chukuguanli")
	public String chukuguanli() {
		log.info("CKGLY/chukuguanli.jsp  出库管理  界面跳转");
		return "CKGLY/chukuguanli";
	}

	// 申请退货
	@RequestMapping("/shenqingtuihuo")
	public String shenqingtuihuo() {
		log.info("CKGLY/shenqingtuihuo.jsp  申请退货  界面跳转");
		return "CKGLY/shenqingtuihuo";
	}

	// 我的申请
	@RequestMapping("/tuihuowodeshenqing")
	public String tuihuowodeshenqing() {
		log.info("CKGLY/tuihuowodeshenqing.jsp  我的申请  界面跳转");
		return "CKGLY/tuihuowodeshenqing";
	}

	// 审核退用申请
	@RequestMapping("/shenhetuiyongshenqing")
	public String shenhetuiyongshenqing() {
		log.info("CKGLY/shenhetuiyongshenqing.jsp  审核退用申请 界面跳转");
		return "CKGLY/shenhetuiyongshenqing";
	}

	// 审核采购申请
	@RequestMapping("/shenhecaigoushenqing")
	public String shenhecaigoushenqing() {
		log.info("CKGLY/shenhecaigoushenqing.jsp  审核采购申请  界面跳转");
		return "CKGLY/shenhecaigoushenqing";
	}

	// 所有申请
	@RequestMapping("/suoyoushenqing")
	public String suoyoushenqing() {
		log.info("CKGLY/suoyoushenqing.jsp  所有申请  界面跳转");
		return "CKGLY/suoyoushenqing";
	}

	// 申请仓库物资调度
	@RequestMapping("/shenqingcangkuwuzidiaodu")
	public String shenqingcangkuwuzidiaodu(HttpServletRequest request) {
		log.info("CKGLY/shenqingcangkuwuzidiaodu.jsp  申请仓库物资调度 界面跳转");
		List<?> lists = invokeService("SystemManageAction.queryStorehouse",new Storehouse());
		request.setAttribute("storehouseList", lists);
		return "CKGLY/shenqingcangkuwuzidiaodu";
	}

	// 审核仓库物资调度
	@RequestMapping("/shenhecangkuwuzidiaodu")
	public String shenhecangkuwuzidiaodu() {
		log.info("CKGLY/shenhecangkuwuzidiaodu.jsp  审核仓库物资调度  界面跳转");
		return "CKGLY/shenhecangkuwuzidiaodu";
	}

	// 我的仓库物资调度
	@RequestMapping("/diaoduwode")
	public String diaoduwode() {
		log.info("CKGLY/diaoduwode.jsp 我的仓库物资调度  界面跳转");
		return "CKGLY/diaoduwode";
	}

	// 单据处理
	@RequestMapping("/danjuchuli")
	public String danjuchuli() {
		log.info("CKGLY/danjuchuli.jsp 单据处理  界面跳转");
		return "CKGLY/danjuchuli";
	}

	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}
}
