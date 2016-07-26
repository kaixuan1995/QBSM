package com.qbsm.service.menberInfoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.service.purchaseManageService.PurchaseManage;
import com.qbsm.service.util.EmailUtil;
import com.qbsm.service.util.ValidateUtil;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.PurchaseManageFormBean;
import com.qbsm.web.formbean.UserFormBean;


@SuppressWarnings("all")
public class User_Information {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	private final String SUCCESS_EMAIL = MessageEnum.S00001+"";
	private final String SUCCESS_ERROREMAIL = MessageEnum.S00002+"";
	
	
	/**
	 * 登入功能
	 * @param user
	 * @return
	 */
	protected List<User> login(User user){
		//保留用户的ip地址
		String user_loginip = user.getUser_lastloginip();
		user.setUser_lastloginip(null);
		list = new ArrayList<User>(1);
		list.add(dao.queryForObject(user));
		User user1 = (User) list.get(0);
		if(user1 != null) {
			user1.setUser_lastloginip(user_loginip);
			user1.setUser_lastlogintime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			dao.update(user1);
		}
		return list;
	}
	
	
	
	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	protected List<String> deletUser(User user){
		user.setUser_isdel(1);
		list = new ArrayList<String>(1);
		if(dao.update(user)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	protected List<String> updateUser(User user) {
		list = new ArrayList<String>(1);
		if(dao.update(user)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	protected List<String> saveUser(User user){
		list = new ArrayList<String>(1);
		boolean boo = true;
		user.setUser_createtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		user.setUser_password(user.getUser_email());
		boo = dao.save(user);
		if(boo) {
			list.add(SUCCESS);
			boo = EmailUtil.send_email(user.getUser_name(), user.getUser_email());
			if(boo) {
				list.add(0, SUCCESS_EMAIL);
			}else{
				list.add(0,SUCCESS_ERROREMAIL);
			}
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	protected List<UserFormBean> queryUser(User user) {
		list = new ArrayList<User>(1);
		list.add(dao.queryForObject(user));
		return list;
	}
	protected List<UserFormBean> queryUser(UserFormBean userFormBean,int pc, int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				"t_user left outer join t_office " +
				"on t_user.office_id_fk = t_office.office_id " +
				"left outer join t_role " +
				"on t_user.role_id_fk = t_role.role_id " +
				"where t_user.user_isdel = 0 ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), UserFormBean.class);
	}
	
	protected List<Integer> queryCount(UserFormBean userFormBean,Filters filters) {
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_user where user_isdel = 0"+filters.toAndSql(), null));
		return list;
	}
	
	//查询user不包括供应商
	protected List<UserFormBean> queryUserNotGys(UserFormBean userFormBean,int pc, int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_user,t_role, t_office " +
				" where t_user.role_id_fk = t_role.role_id" +
				" and t_user.office_id_fk = t_office.office_id " +
				" and t_role.role_name != '供应商'"+
				" and t_user.user_isdel = 0 "
				);
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), UserFormBean.class);
	}
	//查询user不包括供应商数量
	protected List<Integer> queryUserNotGysCount(UserFormBean userFormBean,Filters filters) {
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_user,t_role where t_user.role_id_fk = t_role.role_id and t_role.role_name!='供应商' and user_isdel = 0"+filters.toAndSql(), null));
		return list;
	}
	public static void main(String[] args) {
		User_Information user_Information = new User_Information();
		printList(user_Information.queryUserNotGys(new UserFormBean(), 1, 5, new Filters()));
		printList(user_Information.queryUserNotGysCount(new UserFormBean(), new Filters()));
	}
	// 打印list集合测试
		public static void printList(List list) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
			}
		}



		
	
}
