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

public interface MenberInfoService {
	
	
	/**
	 * 登入功能
	 * @param user
	 * @return
	 */
	public List<User> login(User user);
	
	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 */
	public List<String> deleteUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public List<String> updateUser(User user);
	
	/**
	 * 查看新消息
	 * @return
	 */
	public List<MessageFormBean> queryMessageFormBean(MessageFormBean messageFormBean,int pc,int ps,int user_id,Filters filters);
	
	/**
	 * 查询某人的未读消息数量
	 * @param user_id_fk
	 * @return
	 */
	public List<Integer> queryCount(MessageFormBean messageFormBean,Filters filters);
	public List<Integer> queryCount(int user_id) ;
	/**
	 * 增加物资类别信息
	 * @param wordBook
	 * @return
	 */
	public List<String> saveWordbook(Wordbook wordBook);
	
	/**
	 * 删除物资类别信息
	 * @param wordBook
	 * @return
	 */
	public List<String> deleteWordbook(Wordbook wordBook);
	
	/**
	 * 通过分页查询所有的物资类别信息
	 * @return
	 */
	public List<Wordbook> queryWordbook(Wordbook wordbook,int pc,int ps,Filters filters);
	
	/**
	 * 通过物资信息查询物资类别
	 * @return
	 */
	public List<Wordbook> queryWordbook(Wordbook wordbook);
	
	
	/**
	 * 更新物资类别信息
	 * @param wordbook
	 * @return
	 */
	public List<String> updateWordbook(Wordbook wordbook);
	
	
	/**
	 * 查询物资总数
	 * @param wordbook
	 * @return
	 */
	public List<Integer> queryCount(Wordbook wordbook,Filters filters);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<String> saveUser(User user);

	/**
	 * 删除角色权限
	 * 
	 * @param role_Privilege
	 * @return
	 */
	public List<String> deletePrivilege(Role_Privilege role_Privilege);

	/**
	 * 添加角色权限
	 * 
	 * @param role_Privilege
	 * @return
	 */
	public List<String> savePrivilege(Role_Privilege role_Privilege);

	/**
	 * 修改角色权限信息
	 * 
	 * @param role_Privilege
	 * @param newPrivilegeId
	 *            ：新的权限id
	 * @return
	 */
	public List<String> updatePrivilege(Role_Privilege role_Privilege);

	/**
	 * 查找角色权限表
	 * 
	 * @param role_id
	 *            ：角色id
	 * @return
	 */
	public List<Role_Privilege> queryPrivilege(Role_Privilege role_Privilege);
	
	
	/**
	 * 设置有效期预警
	 * @return
	 */
	public List<String> updateValidity(Alarm alarm);
	
	

	/**
	 * 增加科室信息
	 * @param office
	 * @return
	 */
	public List<String> saveOffice(Office office);
	
	
	/**
	 * 删除科室信息
	 * @param officeId
	 * @return
	 */
	public List<String> deleteOffice(Office office);	
	
	/**
	 * 修改科室信息
	 * @param office
	 * @return
	 */
	public List<String> updateOffice(Office office);
	
	/**
	 * 分页查询所有科室信息
	 * @return
	 */
	public List<Office> queryOffice(Office office,int pc,int ps,Filters filters);
	
	/**
	 * 通过office信息查找office
	 * @param office
	 * @return
	 */
	public List<Office> queryOffice(Office office);
	
	
	
	/**
	 * 查询所有科室的总数
	 * @param office
	 * @return
	 */
	public List<Integer> queryCount(Office office,Filters filters);
	
	
	
	/**
	 * 通过条件查询分页查询用户信息
	 */
	public List<UserFormBean> queryUser(UserFormBean userFormBean,int pc,int ps,Filters filters);

	
	public List<Integer> queryCount(UserFormBean userFormBean,Filters filters);
	
	/**
	 * 个人绩效
	 * @param individualPerformFormBean
	 * @param beforeDate
	 * @param afterDate
	 * @param pc
	 * @param ps
	 * @param filters
	 * @return
	 */
	public List<IndividualPerformFormBean> queryIndividualPerformFormBean(IndividualPerformFormBean individualPerformFormBean,String date,int pc, int ps, Filters filters);
	
	/**
	 * 个人绩效数量
	 * @param individualPerformFormBean
	 * @param date
	 * @param pc
	 * @param ps
	 * @param filters
	 * @return
	 */
	 public List<Integer> queryCount(IndividualPerformFormBean individualPerformFormBean,String date,Filters filters);
	
	 public List<UserFormBean> queryUser(User user);
	 
	//查询user不包括供应商
	List<UserFormBean> queryUserNotGys(UserFormBean userFormBean,int pc, int ps,Filters filters);
	//查询user不包括供应商数量
	List<Integer> queryUserNotGysCount(UserFormBean userFormBean,Filters filters);
}
