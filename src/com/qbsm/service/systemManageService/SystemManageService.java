package com.qbsm.service.systemManageService;

import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.bean.Role;
import com.qbsm.bean.Storehouse;
import com.qbsm.web.action.SQLUtil.Filters;

public interface SystemManageService {

	/**
	 * 数据库备份
	 */
	public void backUp();
	
	/**
	 * 数据库恢复
	 * @param filePath：数据库的地址
	 */
	public void recover(String filePath);
	
	/**
	 * 删除仓库信息
	 * @param storehouse
	 * @return
	 */
	public List<String> deleteStorehouse(Storehouse storehouse);
	
	/**
	 * 更新仓库信息
	 * @param storehouse
	 * @return
	 */
	public List<String> updateStorehouse(Storehouse storehouse);
	
	/**
	 * 添加仓库信息
	 * @param storehouse
	 * @return
	 */
	public List<String> saveStorehouse(Storehouse storehouse);
	
	/**
	 * 查找仓库总数
	 * @param storehouse
	 * @return
	 */
	public List<Integer> queryCount(Storehouse storehouse,Filters filters);
	
	/**
	 * 通过分页查找仓库信息
	 * @param storehouse
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Storehouse> queryStorehouse(Storehouse storehouse,int pc,int ps,Filters filters);
	
	
	/**
	 * 通过仓库信息查找信息
	 * @param storehouse
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Storehouse> queryStorehouse(Storehouse storehouse);
	
	/**
	 * 查找所有仓库信息
	 * @return
	 */
	public List<Storehouse> queryStorehouse();
	/**
	 * 查询分区总数
	 * @param place
	 * @return
	 */
	public List<Integer> queryCount(Place place,Filters filters);
	
	/**
	 * 通过分页查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Place> queryPlace(Place place,int pc,int ps,Filters filters);
	
	/**
	 * 查询分区信息
	 * @param place
	 * @return
	 */
	public List<Place> queryPlcae(Place place);
	
	
	/**
	 * 更新分区信息
	 * @param place
	 * @return
	 */
	public List<String> updatePlace(Place place);
	
	/**
	 * 删除分区信息
	 * @param place
	 * @return
	 */
	public List<String> deletePlace(Place place);
	
	
	
	/**
	 * 查询角色总数
	 * @param place
	 * @return
	 */
	public List<Integer> queryCount(Role role);
	
	/**
	 * 通过角色查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Place> queryRole(Role role,int pc,int ps);
	/**
	 * 查询角色信息
	 * @param place
	 * @return
	 */
	public List<Place> queryRole(Role role);
	
	/**
	 * 更新角色信息
	 * @param place
	 * @return
	 */
	public List<String> updateRole(Role role);
	
	
	/**
	 * 删除角色信息
	 * @param place
	 * @return
	 */
	public List<String> deleteRole(Role role);
	
	/**
	 * 新增角色信息
	 * @param place
	 * @return
	 */
	public List<String> saveRole(Role role);
	
}
