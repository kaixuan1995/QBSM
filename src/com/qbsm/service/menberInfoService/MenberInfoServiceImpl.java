package com.qbsm.service.menberInfoService;

import java.util.List;

import com.qbsm.bean.Alarm;
import com.qbsm.bean.Office;
import com.qbsm.bean.Role_Privilege;
import com.qbsm.bean.User;
import com.qbsm.bean.Wordbook;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.IndividualPerformFormBean;
import com.qbsm.web.formbean.MessageFormBean;
import com.qbsm.web.formbean.UserFormBean;

public class MenberInfoServiceImpl implements MenberInfoService{
	
	private Role_privilegeManage role_privilegeManage = new Role_privilegeManage();
	private Goods_TypeManage goods_typeManage = new Goods_TypeManage();
	private OfficeManage officeManage = new OfficeManage();
	private ValidityManage validityManage = new ValidityManage();
	private User_Information user_Information = new User_Information();
	private NewMessageInfo newMessageInfo = new NewMessageInfo();
	private MenberKPI menberKPI = new MenberKPI();
	
	/**
	 * 登入功能
	 */
	public List<User> login(User user) {
		return user_Information.login(user);
	}
	
	/**
	 * 删除用户信息功能
	 */
	public List<String> deleteUser(User user) {
		return user_Information.deletUser(user);
	}

	/**
	 * 更新用户信息功能
	 */
	public List<String> updateUser(User user) {
		return user_Information.updateUser(user);
	}
	
	
	/**
	 * 添加用户信息
	 */
	public List<String> saveUser(User user) {
		return user_Information.saveUser(user);
	}
	
	
	/**
	 * 通过条件查询分页查询用户信息
	 */
	public List<UserFormBean> queryUser(UserFormBean userFormBean,int pc,int ps,Filters filters) {
		return user_Information.queryUser(userFormBean,pc, ps,filters);
	}
	
	public List<UserFormBean> queryUser(User user) {
		return user_Information.queryUser(user);
	}
	/**
	 * 增加物资类别信息
	 * @param wordBook
	 * @return
	 */
	public List<String> saveWordbook(Wordbook wordBook){
		return goods_typeManage.saveWordbook(wordBook);
	}
	
	/**
	 * 删除物资类别信息
	 * @param wordBook
	 * @return
	 */
	public List<String> deleteWordbook(Wordbook wordBook){
		return goods_typeManage.deleteWordbook(wordBook);
		
	}
	
	/**
	 * 查询所有的物资类别信息
	 * @return
	 */
	public List<Wordbook> queryWordbook(Wordbook wordbook){
		return goods_typeManage.queryWordbook(wordbook);
	}
	
	/**
	 * 通过分页查询物资类别
	 */
	public List<Wordbook> queryWordbook(Wordbook wordbook, int pc, int ps,Filters filters) {
		return goods_typeManage.queryWordbook(wordbook,pc,ps,filters);
	}
	
	/**
	 * 更新物资类别信息
	 */
	public List<String> updateWordbook(Wordbook wordbook) {
		return goods_typeManage.updateWordbook(wordbook);
	}
	
	
	/**
	 * 查找物资类别信息总数
	 */
	public List<Integer> queryCount(Wordbook wordbook,Filters filters) {
		return goods_typeManage.queryCount(wordbook,filters);
	}
	
	
	
	
	/**
	 * 删除角色权限
	 * 
	 * @param role_Privilege
	 * @return
	 */
	public List<String> deletePrivilege(Role_Privilege role_Privilege){
		return role_privilegeManage.deletePrivilege(role_Privilege);
	}

	/**
	 * 添加角色权限
	 * 
	 * @param role_Privilege
	 * @return
	 */
	public List<String> savePrivilege(Role_Privilege role_Privilege){
		return role_privilegeManage.savePrivilege(role_Privilege);
		
	}

	/**
	 * 修改角色权限信息
	 * 
	 * @param role_Privilege
	 * @param newPrivilegeId
	 *            ：新的权限id
	 * @return
	 */
	public List<String> updatePrivilege(Role_Privilege role_Privilege){
		return role_privilegeManage.updatePrivilege(role_Privilege);
	}

	/**
	 * 查找角色权限表
	 * 
	 * @param role_id
	 *            ：角色id
	 * @return
	 */
	public List<Role_Privilege> queryPrivilege(Role_Privilege role_Privilege){
		return role_privilegeManage.queryPrivilege(role_Privilege);
	}
	
	/**
	 * 设置有效期预警
	 * @return
	 */
	public List<String> updateValidity(Alarm alarm){
		return validityManage.updateValidity(alarm);
	}



	/**
	 * 添加科室信息
	 */
	public List<String> saveOffice(Office office) {
		return officeManage.saveOffice(office);
	}

	/**
	 * 删除科室信息
	 */
	public List<String> deleteOffice(Office office) {
		return officeManage.deleteOffice(office);
	}

	/**
	 * 更新科室信息
	 */
	public List<String> updateOffice(Office office) {
		return officeManage.updateOffice(office);
	}

	/**
	 * 查找科室信息
	 */
	public List<Office> queryOffice(Office office, int pc, int ps,Filters filters) {
		return officeManage.queryOffice(office, pc, ps,filters);
	}

	/**
	 * 通过科室信息查找科室信息
	 */
	public List<Office> queryOffice(Office office) {
		return officeManage.queryOffice(office);
	}

	/**
	 * 查找科室信息总数
	 */
	public List<Integer> queryCount(Office office,Filters filters) {
		return officeManage.queryCount(office,filters);
	}
	
	public List<Integer> queryCount(UserFormBean userFormBean,Filters filters){
		return user_Information.queryCount(userFormBean, filters);
	}

	/**
	 * 查看我的新消息
	 */
	@Override
	public List<MessageFormBean> queryMessageFormBean(MessageFormBean messageFormBean,int pc,int ps,int user_id,Filters filters){
		// TODO Auto-generated method stub
		return newMessageInfo.queryMessageFormBean(messageFormBean,pc,ps,user_id,filters);
	}

	/**
	 * 查询某人的未读消息数量
	 */
	@Override
	public List<Integer> queryCount(MessageFormBean messageFormBean,Filters filters) {
		return newMessageInfo.queryCount(messageFormBean,filters);
	}
	public List<Integer> queryCount(int user_id) {
		return newMessageInfo.queryCount(user_id);
	}
	/**
	 * 个人绩效
	 */
	@Override
	public List<IndividualPerformFormBean> queryIndividualPerformFormBean(
			IndividualPerformFormBean individualPerformFormBean,
			String date,int pc, int ps, Filters filters) {
		List<String> datelist = MenberKPI.conv(date);
		return menberKPI.queryIndividualPerformFormBean(individualPerformFormBean, datelist.get(1), datelist.get(0), pc, ps, filters);
	}
	
	/**
	 * 个人绩效数量
	 * @param individualPerformFormBean
	 * @param beforeDate
	 * @param afterDate
	 * @param pc
	 * @param ps
	 * @param filters
	 * @return
	 */
	 public List<Integer> queryCount(IndividualPerformFormBean individualPerformFormBean,String date,Filters filters){
		 List<String> datelist = MenberKPI.conv(date);
		 return menberKPI.queryCount(individualPerformFormBean, datelist.get(1), datelist.get(0), filters);
	 }

	//查询user不包括供应商
		public List<UserFormBean> queryUserNotGys(UserFormBean userFormBean,int pc, int ps,Filters filters){
			return user_Information.queryUserNotGys(userFormBean, pc, ps, filters);
		}
    //查询user不包括供应商数量
		public List<Integer> queryUserNotGysCount(UserFormBean userFormBean,Filters filters) {
			return user_Information.queryUserNotGysCount(userFormBean, filters);
		}
	
}
