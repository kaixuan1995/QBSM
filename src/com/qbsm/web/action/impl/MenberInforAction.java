package com.qbsm.web.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qbsm.bean.Message;
import com.qbsm.bean.Office;
import com.qbsm.bean.Role;
import com.qbsm.bean.Type;
import com.qbsm.bean.User;
import com.qbsm.service.ServiceFactory;
import com.qbsm.service.util.MessageUtil;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.action.SQLUtil.ServletUtils;
import com.qbsm.web.formbean.IndividualPerformFormBean;
import com.qbsm.web.formbean.MessageFormBean;
import com.qbsm.web.formbean.UserFormBean;
/**
 * 2016.3.23
 * @author xiaoyiming
 * 人员信息管理
 */

@Controller
@RequestMapping("MenberInforAction")
public class MenberInforAction {
	
	private Logger log = LoggerFactory.getLogger(MenberInforAction.class);
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
//		return "login";
		log.debug("index.jsp跳转");
		User poUser = (User) request.getSession().getAttribute("userSession");
		if(poUser==null){//没有登录
			log.debug("没有登录--跳转");
			return "login";
		}
		int role = poUser.getRole_id_fk();
		String view = "";
//		role = 3;
		log.info("role = {}",role);
		//根据角色返回到对应的界面
		view = getView(role, view);
		return view;
	}
//	@ModelAttribute
	public void Message(HttpServletRequest request){
	//	log.info("传递消息列表Message");
		List<Message> l = new ArrayList<Message>();
		for(int i=0;i<5;i++){
			Message messagei = new Message();
			messagei.setMessage_id(i);
			messagei.setMessage_time(i+"");
			messagei.setApply_id_fk(i);
			messagei.setMessage_state(i%2==0?"已读":"未读");
			l.add(messagei);
		}
		request.getSession().setAttribute("messageList",l);
	}

	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User voUser,HttpServletRequest request) throws IOException{
		log.info("login.jsp跳转");
//		voUser.setUser_name("111");
//		voUser.setUser_password("111");
		if(voUser.getUser_name().length()<3||voUser.getUser_password().length()<3){
			request.setAttribute("errors", "用户名或密码格式错误!");
			return "login";
		}
		voUser.setUser_lastloginip(getRemoteHost(request));
		log.info(">>>\nvoUser={}",voUser);
		List<?> lists = invokeService("MenberInfoServiceImpl.login", voUser);
		System.out.println(lists.size()+"qwqwqweqwe");
		int role = 0;
		String view = "";
		//如果登录成功
		if(lists.get(0)!=null){
			User poUser = (User) lists.get(0);
			System.out.println(poUser);
			request.getSession().setAttribute("userSession", poUser);
			List<?> MessageCount = invokeService("MenberInfoServiceImpl.queryCount",poUser.getUser_id());
			if(MessageCount.isEmpty()){
				request.getSession().setAttribute("MessageCount", MessageCount.get(0));
			}else{
				request.getSession().setAttribute("MessageCount", 0);
			}
		//	role = 3;
			role = poUser.getRole_id_fk();
			log.info("role = {}",role);
			view = getView(role, view);
			return view;
		}else{//否则失败
			request.setAttribute("errors", "用户名或密码错误!");
			request.setAttribute("voUser", voUser);
			return "login";
		}
	}
	//查询所有消息
	@RequestMapping("/lookMyMessage")
	public String lookMyMessage(HttpServletRequest request){
		log.info("查询所有消息");
		return "message";
	}
	@RequestMapping("/getMyMessage")
	public void getMyMessage(MessageFormBean messageFormBean,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		User user = (User) request.getSession().getAttribute("userSession");
		if(user!=null){
			messageFormBean.setUser_id(user_id);
			user_id = user.getUser_id();
		}
		setTable(messageFormBean, request, response,filters);
		request.getSession().removeAttribute("MessageCount");
		List<?> MessageCount = invokeService("MenberInfoServiceImpl.queryCount",user_id);
		if(MessageCount!=null){
			request.getSession().setAttribute("MessageCount", MessageCount.get(0));
		}
	}
	private int user_id = 0;
	//转发到个人资料界面
	@RequestMapping("/gerenziliao")
	public String gerenziliao(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("userSession");
		if(user==null){//没有登录
			return "login";
		}
		User u = new User();u.setUser_id(user.getUser_id());
		List<?> lists = invokeService("MenberInfoServiceImpl.queryUser",u);
		request.setAttribute("userRequest", (User)lists.get(0));
		if(isGerenziliao){
			if(isUpdateGerenziliao){
				request.setAttribute("msg", "修改成功!!!");
			}else{
				request.setAttribute("msg", "修改失败!!!");
			}
			isGerenziliao = false;
		}
		log.info("个人资料:gerenziliao.jsp跳转");
		return "gerenziliao";
	}
	private boolean isUpdateGerenziliao = false;
	private boolean isGerenziliao = false;
	
	
	//修改个人资料
	@RequestMapping(value="/updateGerenziliao",method=RequestMethod.POST)
	public String updateGerenziliao(User user,HttpServletRequest request){
		log.info("\n>>>修改个人资料  User ={}",user);
		List<?> lists = invokeService("MenberInforAction.saveyonghuxinxiliebiaoedit", user);
		isGerenziliao = true;
		if("S00000".equals((String) lists.get(0))){
			isUpdateGerenziliao = true;
		}else{
			isUpdateGerenziliao = false;
		}
		return "redirect:/MenberInforAction/gerenziliao";
	}
	
	
	//修改密码界面跳转
	@RequestMapping("/xiugaimima")
	public String xiugaimima(HttpServletRequest request){
		log.info("xiugaimima.jsp跳转 修改密码界面跳转");
		User user = (User) request.getSession().getAttribute("userSession");
		if(user==null){//没有登录
			return "login";
		}
		return "/xiugaimima";
	}
	//确认修改密码
	@RequestMapping(value="/surexiugaimima",method=RequestMethod.POST)
	public String surexiugaimima(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("userSession");
		if(user==null){//没有登录
			return "login";
		}
		if("".equals(request.getParameter("oldpassword").trim())||
				"".equals(request.getParameter("newpassword").trim())||
				"".equals(request.getParameter("surenewpassword").trim())){
			request.setAttribute("msg","请输入密码" );
			return "/xiugaimima";
		}
		User u = new User();
		u.setUser_id(user.getUser_id());
		u.setUser_password(request.getParameter("oldpassword"));
		log.info("确认修改密码   界面跳转 User ={}",u);
		List<?> lists = invokeService("MenberInfoServiceImpl.queryUser",u);
		//原密码正确
		if(lists.get(0)!=null){
			u = (User) lists.get(0);
			if(request.getParameter("newpassword").equals(request.getParameter("surenewpassword"))
					&&request.getParameter("newpassword").length()>=3){
				u.setUser_password(request.getParameter("newpassword"));
				List<?> ll = invokeService("MenberInforAction.saveyonghuxinxiliebiaoedit", u);
				if("S00000".equals((String) ll.get(0))){
					request.setAttribute("msg", "修改密码成功");
				}else{
					request.setAttribute("msg", "修改密码失败,请检查密码长度");
				}
			}else{
				request.setAttribute("msg", "两次密码输入不一致,或长度不符合要求");
			}
		}else{
			request.setAttribute("msg","原密码错误" );
		}
		return "/xiugaimima";
	}
	//生成供应商账号
	@RequestMapping(value="/savegongyinshang",method=RequestMethod.POST)
	public String savegongyinshang(User user,HttpServletRequest request){
		log.info("/ADMIN/savegongyinshang.jsp 生成供应商账号界面跳转");
		String[] types = request.getParameterValues("type_name");
		log.info(">>>\n供应商     type={}, user={}, ",types,user);
		List<?> lists = invokeService("MenberInforAction.savegongyinshang", user,types);
		List<?> ll = invokeService("SystemManageAction.queryType",new Type());
		request.setAttribute("tpyeList", ll);
		request.setAttribute("msg", MessageUtil.getMessage((String) lists.get(0))); 
		request.setAttribute("user",user );
		return "ADMIN/shengchenggongyingshangzhanghao";
	}
	
	
	//用户信息列表加载数据
	@RequestMapping("/getyonghuxinxiliebiao")
	public void getyonghuxinxiliebiao(UserFormBean user,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("filters={}",filters);
		setTable(user, request, response,filters);
	}
	
	//个人绩效
	@RequestMapping("/getgerenjixiaoguanli")
	public void getgerenjixiaoguanli(UserFormBean userFormBean,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		Filters filters = ServletUtils.beforeSetTable(request);
		log.info("getgerenjixiaoguanli___filters={}",filters);
		setTable(userFormBean, request, response,filters);
	}
	//个人绩效2
	String data = null;
	@RequestMapping("/getgerenjixiaoguanli2")
	public void getgerenjixiaoguanli2(IndividualPerformFormBean individualPerformFormBean,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		data = request.getParameter("data");@SuppressWarnings("unused")
		String percent = request.getParameter("percent");
		if(id!=null){
			individualPerformFormBean.setUser_id(Integer.valueOf(id));
			Filters filters = ServletUtils.beforeSetTable(request);
			log.info("getgerenjixiaoguanli2_____id={},filters={}",id,filters);
			log.info("getgerenjixiaoguanli2_____data={},individualPerformFormBean={}",data,individualPerformFormBean);
			setTable(individualPerformFormBean, request, response,filters);
		}
	}
			
	//加入查询条件的 setTable
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
	private List<?> getCount(Object object, String url,Filters filters) {
		if ("getyonghuxinxiliebiao".equals(url)) {
			return invokeService("MenberInforAction.queryCount",object,filters);
		}else if("getMyMessage".equals(url)){
			return invokeService("MenberInforAction.queryCount",object,filters);
		}else if("getgerenjixiaoguanli".equals(url)){
			return invokeService("MenberInfoServiceImpl.queryUserNotGysCount",object,filters);
		}else if("getgerenjixiaoguanli2".equals(url)){
			return invokeService("MenberInfoServiceImpl.queryCount",object,data,filters);
		}
		return null;
	}
	
	// 获得分页数据
	private List<?> getPage(Object object, int page, int rows, String url,Filters filters) {
		if ("getyonghuxinxiliebiao".equals(url)) {
			return invokeService("MenberInforAction.queryUser",object,page,rows,filters);
		}else if("getMyMessage".equals(url)){
			return invokeService("MenberInfoServiceImpl.queryMessageFormBean",object,page,rows,user_id,filters);
		}else if("getgerenjixiaoguanli".equals(url)){
			return invokeService("MenberInfoServiceImpl.queryUserNotGys",object,page,rows,filters);
		}else if("getgerenjixiaoguanli2".equals(url)){
			return invokeService("MenberInfoServiceImpl.queryIndividualPerformFormBean",
					object,data,page,rows,filters);
		}
		return null;
	}
	//用户信息列表界面的保存用户功能 
	@ResponseBody
	@RequestMapping("/saveyonghuxinxiliebiao")
	public void saveyonghuxinxiliebiao(User user,HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String oper = (String) request.getParameter("oper");
		if((String) request.getParameter("office_name")!=null){
			user.setOffice_id_fk(Integer.valueOf((String) request.getParameter("office_name")));
		}
		if((String) request.getParameter("role_name")!=null){
			user.setRole_id_fk(Integer.valueOf((String) request.getParameter("role_name")));
		}
		log.info("用户信息列表界面的保存用户功能 操作方式={}",oper);
		List<?> lists = null;
		if("add".equals(oper)){
			lists = invokeService("MenberInforAction.saveyonghuxinxiliebiaoadd", user);
		}else if("edit".equals(oper)){//支持一个编辑功能
			user.setUser_id(Integer.valueOf((String) request.getParameter("id")));
			lists = invokeService("MenberInforAction.saveyonghuxinxiliebiaoedit", user);
		}else if("del".equals(oper)){//支持批量删除
			Object[] ids = (Object[]) (String.valueOf(request.getParameter("id"))).split(",");
			for(int i=0;i<ids.length;i++){
				user.setUser_id(Integer.valueOf((String) ids[i]));
				lists = invokeService("MenberInforAction.saveyonghuxinxiliebiaodel", user);
			}
		}
		operResponse(response, lists);
	}
	private void operResponse(HttpServletResponse response, List<?> lists)
			throws IOException {
		String message = MessageUtil.getMessage((String) lists.get(0));
		response.getWriter().print(message);
	}
	//获得角色名
	@RequestMapping("/getrole_name")
	public void getrole_name(HttpServletResponse response) throws IOException{
		List<?> lists = invokeService("MenberInforAction.queryRole",new Role());
		if(!lists.isEmpty()){
			StringBuffer buffer = new StringBuffer();
			for(int i =0;i<lists.size();i++){
				buffer.append(((Role) lists.get(i)).getRole_id()).append(":")
				.append(((Role) lists.get(i)).getRole_name()).append(";"); 
			}
			buffer.replace(buffer.lastIndexOf(";"), buffer.lastIndexOf(";")+1, "");
			response.getWriter().print(buffer.toString());
		}
	}
	//获得 仓库名
	@RequestMapping("/getoffice_name")
	public void getoffice_name(HttpServletResponse response) throws IOException{
		List<?> lists = invokeService("MenberInforAction.queryOffice",new Office());
		if(!lists.isEmpty()){
			if(!lists.isEmpty()){
				StringBuffer buffer = new StringBuffer();
				for(int i =0;i<lists.size();i++){
					buffer.append(((Office) lists.get(i)).getOffice_id()).append(":")
					.append(((Office) lists.get(i)).getOffice_name()).append(";"); 
				}
				buffer.replace(buffer.lastIndexOf(";"), buffer.lastIndexOf(";")+1, "");
				response.getWriter().print(buffer.toString());
			}
		}
	}
	@RequestMapping("/shezhi")
	public String shezhi(){
		return "shezhi";
	}
	@RequestMapping("/ADMIN")
	public String ADMIN(){
		return "ADMIN/admin";
	}
	@RequestMapping("/CGY")
	public String CGY(){
		return "CGY/caigouyuan";
	}
	@RequestMapping("/CKGLY")
	public String CKGLY(){
		return "CKGLY/cangkuguanliyuan";
	}
	@RequestMapping("/GYS")
	public String GYS(){
		return "GYS/gongyingshang";
	}
	@RequestMapping("/JCY")
	public String JCY(){
		return "JCY/jianceyuan";
	}
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		
		return "login";
	}
/////////////////////////////////////////////////////////////////////////
	//检测员
	//单据处理 
	@RequestMapping("/danjuchuli")
	public String danjuchuli(){
	log.info("JCY/danjuchuli.jsp  单据处理   界面跳转");
	return "JCY/danjuchuli";
	}
	//个人绩效
	@RequestMapping("/gerenjixiao")
	public String gerenjixiao(){
	log.info("JCY/gerenjixiao.jsp  个人绩效  界面跳转");
	return "JCY/gerenjixiao";
	}
	// 调用service(这里要对应配置service.xml文件)
	protected List<?> invokeService(String serviceXMLName, Object... objects) {
		return new ServiceFactory().getService(serviceXMLName, objects);
	}
	//根据用户权限转发到对应的视图
	private String getView(int role, String view) {
		if(0==role){//0仓库管理员
			view = "/CKGLY/cangkuguanliyuan";
		}else if(1==role){//1采购人员
			view = "/CGY/caigouyuan";
		}else if(2==role){//2检测人员
			view = "/JCY/jianceyuan";
		}else if(3==role){//3系统管理员
			view = "/ADMIN/admin";
		}else if(4==role){//4供应商
			view = "/GYS/gongyingshang";
		}
		return view;
	}
	
	
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
