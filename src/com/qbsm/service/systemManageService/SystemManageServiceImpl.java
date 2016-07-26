package com.qbsm.service.systemManageService;

import java.util.List;

import com.qbsm.bean.Place;
import com.qbsm.bean.Role;
import com.qbsm.bean.Storehouse;
import com.qbsm.web.action.SQLUtil.Filters;


public class SystemManageServiceImpl implements SystemManageService {

	private StorehouseManage storehouseManage = new StorehouseManage();
	private PlaceManage placeManage = new PlaceManage();
	private RoleManage roleManage = new RoleManage();
	
	
	//备份数据库
	public void backUp() {
		
	}

	//还原数据库
	public void recover(String filePath) {
		
	}

	/**
	 * 删除仓库信息
	 */
	public List<String> deleteStorehouse(Storehouse storehouse) {
		return storehouseManage.deleteStorehouse(storehouse);
	}

	/**
	 * 更新仓库信息
	 */
	public List<String> updateStorehouse(Storehouse storehouse) {
		return storehouseManage.updateStorehouse(storehouse);
	}

	/**
	 * 添加仓库信息
	 */
	public List<String> saveStorehouse(Storehouse storehouse) {
		return storehouseManage.saveStorehouse(storehouse);
	}

	/**
	 * 查找仓库信息总数
	 */
	public List<Integer> queryCount(Storehouse storehouse,Filters filters) {
		return storehouseManage.queryCount(storehouse, filters);
	}

	/**
	 * 通过分页查找仓库信息
	 */
	public List<Storehouse> queryStorehouse(Storehouse storehouse, int pc,
			int ps,Filters filters) {
		return storehouseManage.queryStorehouse(storehouse, pc, ps, filters);
	} 

	/**
	 * 查找仓库信息
	 */
	public List<Storehouse> queryStorehouse(Storehouse storehouse) {
		return storehouseManage.queryStorehouse(storehouse);
	}
	/**
	 * 查找所有仓库
	 */
	public List<Storehouse> queryStorehouse() {
		return storehouseManage.queryStorehouse();
	}

	/**
	 * 查询分区总数
	 */
	public List<Integer> queryCount(Place place,Filters filters) {
		return placeManage.queryCount(place, filters);
	}

	/**
	 * 通过分页查询分区信息
	 */
	public List<Place> queryPlace(Place place, int pc, int ps,Filters filters) {
		return placeManage.queryPlace(place, pc, ps, filters);
	}

	/**
	 * 查询分区信息
	 */
	public List<Place> queryPlcae(Place place) {
		return placeManage.queryPlcae(place);
	}
	/**
	 * 新增分区信息
	 */
	public List<String> savePlace(Place place) {
		return placeManage.savePlace(place);
	}

	/**
	 * 更新分区信息
	 */
	public List<String> updatePlace(Place place) {
		return placeManage.updatePlace(place);
	}

	/**
	 * 删除分区信息
	 */
	public List<String> deletePlace(Place place) {
		return placeManage.deletePlace(place);
	}

	
	/**
	 * 查找角色总数
	 */
	public List<Integer> queryCount(Role role) {
		return roleManage.queryCount(role);
	}

	/**
	 * 通过分页查询角色信息
	 */
	public List<Place> queryRole(Role role, int pc, int ps) {
		return roleManage.queryRole(role, pc, ps);
	}

	/**
	 * 查询角色信息
	 */
	public List<Place> queryRole(Role role) {
		return roleManage.queryRole(role);
	}

	/**
	 * 更新角色信息
	 */
	public List<String> updateRole(Role role) {
		return roleManage.updateRole(role);
	}

	/**
	 * 逻辑删除角色信息
	 */
	public List<String> deleteRole(Role role) {
		return roleManage.deleteRole(role);
	}

	/**
	 * 添加角色信息
	 */
	public List<String> saveRole(Role role) {
		return roleManage.saveRole(role);
	}

}
