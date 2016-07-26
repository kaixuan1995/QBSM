package com.qbsm.web.action.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.GoodsType;
import com.qbsm.bean.User;
import com.qbsm.service.ServiceFactory;
import com.qbsm.service.util.MessageUtil;
import com.qbsm.service.util.WordUtil;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.action.SQLUtil.ServletUtils;
import com.qbsm.web.formbean.GoodsManageFormBean;
import com.qbsm.web.formbean.PurchaseManageFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;
import com.qbsm.web.formbean.UserFormBean;
import com.qbsm.web.formbean.VoucherDetailFormBean;
import com.qbsm.web.formbean.VoucherFormBean;

/**
 * 2016.3.24
 * 
 * @author xiaoyiming 采购管理
 */
@Controller
@RequestMapping("PurchaseManageAction")
public class PurchaseManageAction {
	Logger log = LoggerFactory.getLogger(PurchaseManageAction.class);

	// ///////////////////////////////////////////////////////////////////////
	// 检测员
	// 申请采购
	@RequestMapping("/shenqingcaigou")
	public String shenqingcaigou() {
		log.info("JCY/shenqingcaigou.jsp  申请采购  界面跳转");
		return "JCY/shenqingcaigou";
	}

	// 我的申请
	@RequestMapping("/caigouwodeshenqing")
	public String caigouwodeshenqing() {
		log.info("JCY/caigouwodeshenqing.jsp  我的申请  界面跳转");
		return "JCY/caigouwodeshenqing";
	}

	// 采购申请 申请采购
	@RequestMapping("/getshenqingcaigou")
	public void getshenqingcaigou(GoodsManageFormBean goodsManageFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(goodsManageFormBean, request, response, filters);
	}

	// 采购申请 我的申请 
	@RequestMapping("/getcaigouwodeshenqing")
	public void getcaigouwodeshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		} 
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
  

	// ///////////////////////////////////////////////////////////////////////
	// 采购员
	// 采购管理首页
	@RequestMapping("/caigouyuan")
	public String caigouyuan() {
		log.info("CGY/caigouyuan.jsp 采购管理首页   界面跳转");
		return "CGY/caigouyuan";
	}

	// 物流信息列表
	@RequestMapping("/wuzixinxiliebiao")
	public String wuzixinxiliebiao() {
		log.info("CGY/wuzixinxiliebiao.jsp 物流信息列表    界面跳转");
		return "CGY/wuzixinxiliebiao";
	}

	// 申请添加物资信息
	@RequestMapping("/shenqingtianjiawuzixinxi")
	public String shenqingtianjiawuzixinxi(HttpServletRequest request) {
		log.info("CGY/shenqingtianjiawuzixinxi.jsp 申请添加物资信息   界面跳转");
		List<?> lists = invokeService("StoreManageAction.queryGoodsType",new GoodsType());
		request.setAttribute("goodsTypeList", lists);
		return "CGY/shenqingtianjiawuzixinxi";
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
		return "CGY/shenqingtianjiawuzixinxi";
	}

	// 我的申请
	@RequestMapping("/xinxiwodeshenqing")
	public String xinxiwodeshenqing() {
		log.info("CGY/xinxiwodeshenqing.jsp 我的申请  界面跳转");
		return "CGY/xinxiwodeshenqing";
	}

	// 供应商信息列表
	@RequestMapping("/gongyingshangxinxiliebiao")
	public String gongyingshangxinxiliebiao() {
		log.info("CGY/gongyingshangxinxiliebiao.jsp 供应商信息列表  界面跳转");
		return "CGY/gongyingshangxinxiliebiao";
	}

	// 获得物资信息管理 物资信息列表 (查)
	@RequestMapping("/getwuzixinxiliebiao")
	public void getwuzixinxiliebiao(GoodsManageFormBean goodsManageFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(goodsManageFormBean, request, response, filters);
	}

	// 物资信息管理 我的申请 (查)
	//前提是要登录
	@RequestMapping("/getxinxiwodeshenqing")
	public void getxinxiwodeshenqing(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(applyNewGoods, request, response, filters);
	}
	private int user_id = 0;
	// 物资信息管理 我的申请 (查)
	//前提是要登录
	@RequestMapping("/getxinxiwodeshenqing2")
	public void getxinxiwodeshenqing2(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(applyNewGoods, request, response, filters);
	}
	// 获得供应商信息列表
	@RequestMapping("/getgongyingshangxinxiliebiao")
	public void getgongyingshangxinxiliebiao(UserFormBean userFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(userFormBean, request, response, filters);
	}

	// 获得采购管理 审核申请 列表(查)
	@RequestMapping("/getshenheshenqing")
	public void getshenheshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	//采购管理 审核申请
	@RequestMapping("/getshenheshenqing2")
	public void getshenheshenqing2(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		log.info("查询与上表述相关数据getshenheshenqing2 id = {}",id);
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		//如果id有值,就说明是操作下面那张table,所以根据id来进行查询
		if(id!=null){
			storeApplyDFormBean.setApply_id(Integer.valueOf(id));
			
		}else {
			storeApplyDFormBean.setApply_id(-1);
		}
		log.info("storeApplyDFormBean = {}",storeApplyDFormBean);
		setTable(storeApplyDFormBean, request, response, filters);
	}
	//采购管理 审核申请  判断是否通过
	@RequestMapping("/tongguoshenheshenqing")
	public void tongguoshenheshenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String q = request.getParameter("q");
		String ids = request.getParameter("idss");
		log.info("采购管理 审核申请  判断是否通过 q={},ids={}",q,ids.split(","));
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		List<?> lists = null;
		if("no".equals(q)){
			lists = invokeService("PurchaseManageAction.checkCaiGouApply",ids.split(","),0,user_id);
		}else if("yes".equals(q)){
			lists = invokeService("PurchaseManageAction.checkCaiGouApply",ids.split(","),1,user_id);
		}
		operResponse(response, lists);
	}
	// 获得采购管理 所有申请列表(查)
	@RequestMapping("/getsuoyoushenqing")
	public void getsuoyoushenqing(StoreApplyFormBean storeApplyFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyFormBean, request, response, filters);
	}
	// 获得采购管理 所有申请列表(查)
	@RequestMapping("/getsuoyoushenqing2")
	public void getsuoyoushenqing2(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		log.info("查询与上表述相关数据getshenheshenqing2 id = {}",id);
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		//如果id有值,就说明是操作下面那张table,所以根据id来进行查询
		if(id!=null){
			storeApplyDFormBean.setApply_id(Integer.valueOf(id));
			
		}else {
			storeApplyDFormBean.setApply_id(-1);
		}
		log.info("storeApplyDFormBean = {}",storeApplyDFormBean);
		setTable(storeApplyDFormBean, request, response, filters);
	}
	// 获得采购管理 采购计划(曾删改查)
	@RequestMapping("/getcaigoujihua")
	public void getcaigoujihua(PurchaseManageFormBean purchaseManageFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(purchaseManageFormBean, request, response, filters);
	}

	// 获得 到货管理 验收入库 列表
	@RequestMapping("/getyanshouruku")
	public void getyanshouruku(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(storeApplyDFormBean, request, response, filters);
	}
	// 获得 到货管理 验收入库 列表 一键生成入库单
	@RequestMapping("/yanshouRuKu")
	public void yanshouRuKu(StoreApplyDFormBean storeApplyDFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("一键生成入库单 ");
		String ids = request.getParameter("idss");
		log.info("yanshouRuKuids={}",ids.split(","));
		User user = (User)request.getSession().getAttribute("userSession");
		if(user!=null){
			user_id = user.getUser_id();
		}
		List<?> lists = invokeService("PurchaseManageAction.yanshouRuKu",ids.split(","),user_id);
		operResponse(response, lists);
	}
	// 获得 采购人员 单据处理
	@RequestMapping("/getdanjuchuli")
	public void getdanjuchuli(VoucherFormBean voucherFormBean, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String t = request.getParameter("type");
		log.info("采购人员 单据处理 getdanjuchuli type={}",type);
		if(t!=null){
			User user = (User)request.getSession().getAttribute("userSession");
			if(user!=null){
				user_id = user.getUser_id();
			}
			voucherFormBean.setUser_id_fk(user_id);
			type = Integer.valueOf(t);
			voucherFormBean.setVoucher_type(type);
			Filters filters = ServletUtils.beforeSetTable(request);
			log.info("filters={}",filters);
			setTable(voucherFormBean, request, response, filters);
		}
	}
	private int type = 1;
	//单据处理,显示详细单据
	@SuppressWarnings("unchecked")
	@RequestMapping("/getxianshi")
	public void getxianshi(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String idss = request.getParameter("idss");
		if(idss==null){
			response.getWriter().print("请选择要显示的单据编号");
			return ;
		}
		List<?> lists = invokeService("PurchaseManageAction.queryDetailVoucher",
				new VoucherDetailFormBean(),Integer.valueOf(idss));
		danjuList = (List<VoucherDetailFormBean>) lists;
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(lists);
		log.info("拿到的数据:lists={}",lists);
		response.setContentType("text/javascript");
		response.getWriter().print(result);
	}
	List<VoucherDetailFormBean> danjuList = null;
	//单据处理,导出
	@ResponseBody
	@RequestMapping("/getdaochu")
	public ResponseEntity<byte []> getdaochu(HttpServletRequest request, 
			HttpServletResponse response,HttpSession session) throws IOException{
		log.info("单据处理,导出");//
		String path = request.getSession().getServletContext().getRealPath("") +"\\WEB-INF\\voucher\\";
		if(danjuList==null){
			response.getWriter().println("错误!!!请先选中数据后点击详细单据!");
			return null;
		}
		String fileName = WordUtil.setDownLoadPath(danjuList, path);
		byte[] body = null;
		ServletContext context = session.getServletContext();
		InputStream is = context.getResourceAsStream("WEB-INF\\voucher\\"+fileName);
		body = new byte[is.available()];
		is.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=" +fileName);
		
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte []> responseEntity = 
				new ResponseEntity<byte []>(body, headers, statusCode);
		return responseEntity;
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
		if ("getwuzixinxiliebiao".equals(url)) {
			return invokeService("StoreManageAction.queryCount.queryAllGoodsCount", object,filters);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("PurchaseManageAction.queryCount", object,user_id,"审核通过",filters);
		} else if ("getxinxiwodeshenqing2".equals(url)) {
			return invokeService("PurchaseManageAction.queryCount", object,user_id,"",filters);
		} else if ("getgongyingshangxinxiliebiao".equals(url)) {
			return invokeService("PurchaseManageAction.queryCount", object,filters);
		} else if ("getshenheshenqing".equals(url)) {//采购管理 审核申请 
			return invokeService("PurchaseManageAction.queryCaiGouApplyCount", object,"notCheck",filters);
		} else if ("getshenheshenqing2".equals(url)) {//采购管理 审核申请 2
			return invokeService("PurchaseManageAction.queryDetailApplyCount", object,((StoreApplyDFormBean)object).getApply_id(),filters);
		}else if ("getsuoyoushenqing".equals(url)) {//采购管理 所有申请 
			return invokeService("PurchaseManageAction.queryCaiGouApplyCount", object,"all",filters);
		}else if ("getsuoyoushenqing2".equals(url)) {//采购管理 所有申请 2
			return invokeService("PurchaseManageAction.queryDetailApplyCount", object,((StoreApplyDFormBean)object).getApply_id(),filters);
		} else if ("getcaigoujihua".equals(url)) {
			return invokeService("PurchaseManageAction.queryPurchasePlanCount", object,filters);
		} else if ("getyanshouruku".equals(url)) {
			return invokeService("PurchaseManageAction.queryYanShouGoodsCount", object,filters);
		}else if ("getdanjuchuli".equals(url)) {
			return invokeService("PurchaseManageAction.queryCount", object,type,filters);
		}else if("getshenqingcaigou".equals(url)){
			return invokeService("StoreManageAction.queryCount", object,filters);
		}else if("getcaigouwodeshenqing".equals(url)){
			return invokeService("StoreManageAction.queryMyApplyCount", object,user_id,"2",filters);
		}
		return invokeService("PurchaseManageAction.queryCount", object,filters);
	}

	// 获得分页数据
	private List<?> getPage(Object object, int page, int rows, String url, Filters filters) {
		if ("getwuzixinxiliebiao".equals(url)) {
			return invokeService("StoreManageAction.queryGoodsFormBean.queryAllGoods", object, page, rows,filters);
		} else if ("getxinxiwodeshenqing".equals(url)) {
			return invokeService("PurchaseManageAction.queryOurselfApplyNewGoods", object, page, rows,user_id,"审核通过",filters);
		} else if ("getxinxiwodeshenqing2".equals(url)) {
			return invokeService("PurchaseManageAction.queryOurselfApplyNewGoods", object, page, rows,user_id,"",filters);
		} else if ("getgongyingshangxinxiliebiao".equals(url)) {
			return invokeService("PurchaseManageAction.queryApplyer", object, page, rows,filters);
		} else if ("getshenheshenqing".equals(url)) {//采购管理 审核申请 
			return invokeService("PurchaseManageAction.queryCaiGouApply", object, page, rows,"notCheck",filters);
		} else if ("getshenheshenqing2".equals(url)) {//采购管理 审核申请2 
			return invokeService("PurchaseManageAction.queryDetailApply", object, page, rows,((StoreApplyDFormBean)object).getApply_id(),filters);
		} else if ("getsuoyoushenqing".equals(url)) {//采购管理 所有申请 
			return invokeService("PurchaseManageAction.queryCaiGouApply", object, page, rows,"all",filters);
		} else if ("getsuoyoushenqing2".equals(url)) {//采购管理 所有申请 2
			return invokeService("PurchaseManageAction.queryDetailApply", object, page, rows,((StoreApplyDFormBean)object).getApply_id(),filters);
		} else if ("getcaigoujihua".equals(url)) {
			return invokeService("PurchaseManageAction.queryPurchasePlan", object, page, rows,filters);
		} else if ("getyanshouruku".equals(url)) {
			return invokeService("PurchaseManageAction.queryYanShouGoods", object, page, rows,filters);
		}else if ("getdanjuchuli".equals(url)) {//单据处理 
			return invokeService("PurchaseManageAction.queryVoucherFormBean", object,page, rows,type,filters);
		}else if("getshenqingcaigou".equals(url)){
			return invokeService("StoreManageAction.queryGoodsManageFormBean", object, page, rows,filters);
		}else if("getcaigouwodeshenqing".equals(url)){
			return invokeService("StoreManageAction.queryMyApply", object, page, rows,user_id,"2",filters);
		}
		return null;
	}
	// 物资列表(未审核和审核失败)
	@RequestMapping("/savexinxiwodeshenqing2")
	public void savexinxiwodeshenqing2(ApplyNewGoods applyNewGoods, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String oper = (String) request.getParameter("oper");
		log.info("用户信息列表界面的保存用户功能 操作方式={}", oper);
		List<?> lists = null;
		if ("add".equals(oper)) {
		} else if ("edit".equals(oper)) {// 支持一个编辑功能
		} else if ("del".equals(oper)) {// 支持批量删除
			Object[] ids = (Object[]) (String.valueOf(request.getParameter("id"))).split(",");
			for(int i=0;i<ids.length;i++){
				log.info("物资类别(增删改查)ids={}",ids[i]);
				applyNewGoods.setApplygoods_id(Integer.valueOf((String) ids[i]));
				lists = invokeService("PurchaseManageAction.savexinxiwodeshenqing2del", applyNewGoods);
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
	// 审核申请
	@RequestMapping("/shenheshenqing")
	public String shenheshenqing() {
		log.info("CGY/shenheshenqing.jsp 审核申请  界面跳转");
		return "CGY/shenheshenqing";
	}

	// 所有申请
	@RequestMapping("/suoyoushenqing")
	public String suoyoushenqing() {
		log.info("CGY/suoyoushenqing.jsp 所有申请  界面跳转");
		return "CGY/suoyoushenqing";
	}

	// 采购计划
	@RequestMapping("/caigoujihua")
	public String caigoujihua() {
		log.info("CGY/caigoujihua.jsp 采购计划  界面跳转");
		return "CGY/caigoujihua";
	}

	// 验收入库
	@RequestMapping("/yanshouruku")
	public String yanshouruku() {
		log.info("CGY/yanshouruku.jsp 验收入库  界面跳转");
		return "CGY/yanshouruku";
	}

	// 单据处理
	@RequestMapping("/danjuchuli")
	public String danjuchuli() {
		log.info("CGY/danjuchuli.jsp  单据处理  界面跳转");
		return "CGY/danjuchuli";
	}

	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}
}
