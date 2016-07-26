package com.qbsm.service.storeManageService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Goods;
import com.qbsm.bean.Goods_number;
import com.qbsm.bean.Inventory;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.Storehouse;
import com.qbsm.bean.Type;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.dao.util.ResetBeanUtils;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;

@SuppressWarnings("all")
public class Shopping_Manage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String ApplyNumber_ERROR = MessageEnum.E40001+"";
	private final String SYSTEM_ERROR = MessageEnum.E11111+"";
	private final String NotHaveGoods = MessageEnum.E40003+"";
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 通过物资信息及用户id查找购物车信息
	 * @param shopping
	 * @param goods_ids
	 * @param user_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<Shopping> queryShopping(Shopping shopping,int user_id,int pc,int ps,Filters filters) {
		
		Shopping_Manage shopping_Manage = new Shopping_Manage();
		list = shopping_Manage.queryShoppingByUser_id(shopping, user_id, pc, ps,filters);
		return list;
	}
	
	/**
	 * 通过user_id查询购物车信息
	 * @param shopping
	 * @param user_id
	 * @return
	 */
	protected List<Integer> queryCount(Shopping shopping,int user_id,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_shopping" +
				" where user_id_fk = ?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",new Object[]{user_id}));
//		list.add(dao.getDataCount("select * from t_shopping" +
//				" where user_id_fk = ?", new Object[]{user_id}));
		return list;
	}
	
	/**
	 * 通过user_id查找购物车信息
	 * @param shopping
	 * @param user_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	private List<Shopping> queryShoppingByUser_id(Shopping shopping,int user_id,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_shopping" +
				" where user_id_fk = ? ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{user_id},  Shopping.class);
//		return dao.queryForList("select * from t_shopping" +
//				" where user_id_fk = ? limit ? , ?", new Object[]{user_id,(pc-1)*ps,ps}, Shopping.class);
	}
	
	
	/**
	 * 添加信息到购物车中
	 * @param goods_ids
	 * @param user_id
	 * @return
	 */
	protected List<String> saveShopping(String[] inventory_ids,int user_id,String storehouse_id){
		list = new ArrayList<String>(1);
		Shopping shopping = null;
		User user = null;
		Goods goods = null;
		Storehouse storehouse = null;
		Inventory inventory = null;
		Type type = null;
		boolean boo = true;
		dao.beginTransaction();
		for(int i = 0;inventory_ids != null && i<inventory_ids.length && boo;i++) {
			
			//通过库存id得到goods_id
			inventory = new Inventory();
			inventory.setInventory_id(Integer.valueOf(inventory_ids[i]));
			inventory = dao.queryForObject(inventory);
			String goods_ids = inventory.getGoods_id_fk()+"";
			
			/**
			 * new出一个购物车
			 */
			shopping = new Shopping();
			shopping.setGoods_id_fk(Integer.valueOf(goods_ids));
			shopping.setUser_id_fk(Integer.valueOf(user_id));
			shopping.setStorehouse_id_fk(Integer.valueOf(storehouse_id));
			shopping.setInventory_id_fk(Integer.valueOf(inventory_ids[i]));
			Shopping shopping1 = dao.queryForObject(shopping);
			//如果购物车里面没有这条信息，则说明重新添加
			if(shopping1 == null) {
				//通过goods_id查询goods
				goods = new Goods();
				goods.setGoods_id(Integer.valueOf(goods_ids));
				
				//如果物资部存在则不添加，返回该物资部存在
				goods = dao.queryForObject(goods);
				if(goods == null) {
					list.add(NotHaveGoods);
					System.out.println("---------------");
					return list;
				}else{
					ResetBeanUtils.copy(goods, shopping);
					//通过goods里面的type_id查询类别信息
					type = new Type();
					type.setType_id(goods.getType_id_fk());
					type = dao.queryForObject(type);
					
					//将类别信息添加到购物车
					shopping.setType_name(type.getType_name());
					
					//通过user_Id查询用户信息
					user = new User();
					user.setUser_id(Integer.valueOf(user_id));
					user = dao.queryForObject(user);
					
					//将用户信息保存到shopping表
					ResetBeanUtils.copy(user, shopping);
					
					
					//通过storehouse_id得到仓库信息
					storehouse = new Storehouse();
					storehouse.setStorehouse_id(Integer.valueOf(storehouse_id));
					storehouse = dao.queryForObject(storehouse);
					shopping.setStorehouse_name(storehouse.getStorehouse_name());
					shopping.setStorehouse_id_fk(Integer.valueOf(storehouse_id));
					boo = dao.save(shopping);
				}
			}
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else{
			dao.rollbackTransaction();
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	/**
	 * 删除购物车信息
	 * @param shopping_ids
	 * @return
	 */
	protected List<String> deleteShopping(String [] shopping_ids) {
		list = new ArrayList<String>(1);
		boolean boo = true;
		Shopping shopping = null;
		dao.beginTransaction();
		for(int i = 0;i<shopping_ids.length && boo;i++) {
			shopping = new Shopping();
			shopping.setShopping_id(Integer.valueOf(shopping_ids[i]));
			boo = dao.delete(shopping);
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else {
			dao.rollbackTransaction();
			list.add(DELETE_ERROR);
		}
		list.add(boo);
		return list;
	}
	
	/**
	 * 更新购物车信息
	 * @param shopping
	 * @return
	 */
	protected List<String> updateShopping(Shopping saveShopping) {
		list = new ArrayList<String>(1);
		Goods_number goods_number = new Goods_number();
		boolean boo = true;
		
		dao.beginTransaction();
		
		//保留需要保存的购物车id
		int shopping_id = saveShopping.getShopping_id();
		Shopping shopping = new Shopping();
		shopping.setShopping_id(shopping_id);
		//通过购物车id查找购物车信息
		shopping = dao.queryForObject(shopping);
		shopping.setApply_goods_count(saveShopping.getApply_goods_count());
		
		if(shopping == null) {
			list.add(NotHaveGoods);
		}else {
			goods_number.setGoods_id_fk(shopping.getGoods_id_fk());
			goods_number.setStorehouse_id_fk(shopping.getStorehouse_id_fk());
			goods_number = dao.queryForObject(goods_number);
			if(goods_number == null) {
				list.add(NotHaveGoods);
			}else {
				if(goods_number.getGoods_number() < shopping.getApply_goods_count()) {
					list.add(ApplyNumber_ERROR);
				}else {
					if(dao.update(shopping)){
						list.add(SUCCESS);
					}else {
						list.add(UPDATE_ERROR);
					}
				}
			}
		}
		if(list.get(0).equals(SUCCESS)) {
			dao.commitTransaction();
		}else{
			dao.rollbackTransaction();
		}
		return list;
	}
	
	
	/**
	 * 填写仓库之间调度功能实现
	 * 步骤
	 * 		1、添加一个apply申请
	 * 		2、添加apply_goods详细信息
	 * 		3、得到所有shopping里面的数量
	 * 		3、通过仓库id与物资id更改goods_number表中的信息
	 * @param shopping_ids
	 * @param apply_urgent
	 * @param storehouse_id
	 * @return
	 */
	protected List<String> passShopping(String[] shopping_ids,String apply_urgent,String storehouse_id) {
		list = new ArrayList<String>(1);
		int user_id = -1;
		int apply_id = -1;
		Apply_Goods apply_goods = null;
		boolean boo = true;
		Goods_number goods_number = null;
		dao.beginTransaction();
		
		//添加申请总表信息
		Apply apply = new Apply();
		apply.setApply_isdel(0);
		apply.setApply_state("待审核");
		apply.setApply_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		apply.setApply_type("6");
		apply.setApply_urgent(apply_urgent);
		apply.setStorehouse_id_fk(Integer.valueOf(storehouse_id));
		
		//通过shopping_ids得到user_id
		Shopping shopping = new Shopping();
		if( shopping_ids != null &&shopping_ids[0]!=null) {
			shopping.setShopping_id(Integer.valueOf(shopping_ids[0]));
		}
		shopping = dao.queryForObject(shopping);
		if(shopping != null) {
			user_id = shopping.getUser_id_fk();
		}
		
		//添加user_id到
		apply.setUser_id_fk(user_id);
		//添加一个
		apply_id = dao.getGeneratedKeyByInsert(apply);
		if(apply_id != -1) {
			//添加申请单详细信息,遍历shopping_ids得到详细信息
			for(int i = 0;i<shopping_ids.length && boo;i++){
				shopping = new Shopping();
				shopping.setShopping_id(Integer.valueOf(shopping_ids[i]));
				shopping= dao.queryForObject(shopping);
				
				//添加申请单详细信息
				apply_goods = new Apply_Goods();
				apply_goods.setApply_goods_count(shopping.getApply_goods_count());
				apply_goods.setApply_goods_isdel(0);
				apply_goods.setApply_id_fk(apply_id);
				apply_goods.setGoods_id_fk(shopping.getGoods_id_fk());
				boo = dao.save(apply_goods);
				
				//更改库存表
				if(boo) {
					goods_number = new Goods_number();
					goods_number.setGoods_id_fk(apply_goods.getGoods_id_fk());
					goods_number.setStorehouse_id_fk(Integer.valueOf(storehouse_id));
					goods_number = dao.queryForObject(goods_number);
					
					//判断库存数量是否大于申请数量
					if(goods_number != null && goods_number.getGoods_number() > shopping.getApply_goods_count()) {
						int number = goods_number.getGoods_number();
						goods_number.setGoods_number(number-shopping.getApply_goods_count());
						boo = dao.update(goods_number);
						//删除购物车信息
						if(boo) {
							boo = dao.delete(shopping);
						}
					}else {
						list.add(ApplyNumber_ERROR);
						boo = false;
					}
					
				}
				
			}
			
		}
		if(boo) {
			dao.commitTransaction();
			list.add(SUCCESS);
		}else {
			if(list.size() < 1) {
				list.add(SYSTEM_ERROR);
			}
		}
		System.out.println(list);
		return list;
	}
}
