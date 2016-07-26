package com.qbsm.service.storeManageService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.Apply_Goods;
import com.qbsm.bean.Barcode;
import com.qbsm.bean.Goods;
import com.qbsm.bean.Goods_number;
import com.qbsm.bean.Inventory;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.StoreApplyFormBean;


@SuppressWarnings("all")
public class StoreApplyFormBean_Manage {

	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	private final String SYSTEM_ERROR = MessageEnum.E11111+"";
	
	
	/**
	 * 查找审核退用单据、审核采购申请单总数
	 * 查询单据类型   :1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryCheckCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters) {
		list = new ArrayList<Integer>(1);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from t_apply where apply_type = ? and apply_state = ? ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",  new Object[]{apply_type,"待审核"}));
//		String apply_state = "待审核";
//		Object[] obj = new Object[]{apply_type,apply_state};
//		list.add(dao.getDataCount("select count(*) from t_apply where apply_type = ? and apply_state = ?", obj));
		return list;
	}
	
	/**
	 * 查找仓库之间调度的申请
	 * apply_type:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryOurselfStoreApply(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply as ta left outer join t_storehouse as tsh" +
				" on ta.storehouse_id_fk = tsh.storehouse_id" +
				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
				" where tu.role_id_fk = 3 AND ta.apply_type = ?" +
				" AND tu.user_id = ? ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{apply_type,user_id}, StoreApplyFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_apply as ta left outer join t_storehouse as tsh" +
//				" on ta.storehouse_id_fk = tsh.storehouse_id" +
//				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//				" where tu.role_id_fk = 3 AND ta.apply_type = "+apply_type+" " +
//				" AND tu.user_id ="+user_id +
//				" limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
	}
	
	/**
	 * 查询仓库管理员提交的仓库转让申请
	 * apply_type：1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryOurselfStoreApplyCount(StoreApplyFormBean storeApplyFormBean,int apply_type,int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from " +
				" t_apply,t_user " +
				" where t_user.user_id = t_apply.user_id_fk " +
				" AND t_user.role_id_fk = ?" +
				" AND t_apply.apply_type = ?" +
				" AND t_user.user_id = ?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{3,apply_type,user_id}));
//		list.add(dao.getDataCount("select count(*) from " +
//				" t_apply,t_user " +
//				" where t_user.user_id = t_apply.user_id_fk " +
//				" AND t_user.role_id_fk = ?" +
//				" AND t_apply.apply_type = ?" +
//				" AND t_user.user_id = ?", new Object[]{3,apply_type,user_id}));
		return list;
	}
	
	
	/**
	 * 查找所有申请信息
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply as ta left outer join t_storehouse as tsh" +
				" on ta.storehouse_id_fk = tsh.storehouse_id" +
				" left outer join t_user as tu on ta.user_id_fk = tu.user_id");
		return dao.queryForList(filters.toWhereLimitSql(sql, pc, ps), StoreApplyFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_apply as ta left outer join t_storehouse as tsh" +
//				" on ta.storehouse_id_fk = tsh.storehouse_id" +
//				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//				" limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
	}
	
	
	/**
	 * 通过状态或者单据类型查找单据信息
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,String apply_state,Filters filters) {
		StringBuffer sql = new StringBuffer();
		//通过状态查询单据类型
		if("".equals(apply_type) && !"".equals(apply_state)) {
			sql.append("select * from " +
					" t_apply as ta left outer join t_storehouse as tsh" +
					" on ta.storehouse_id_fk = tsh.storehouse_id" +
					" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
					" where ta.apply_state = ? ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), StoreApplyFormBean.class);
//			return dao.queryForList("select * from " +
//					" t_apply as ta left outer join t_storehouse as tsh" +
//					" on ta.storehouse_id_fk = tsh.storehouse_id" +
//					" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//					" where ta.apply_state = +'"+apply_state+ "' limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
		}else if("".equals(apply_state) && !"".equals(apply_type)){
			//通过单据类型查找单据信息
			return new StoreApplyFormBean_Manage().queryCheckStoreApplyFormBean(storeApplyFormBean, pc, ps, apply_type,filters);
		}else if(!"".equals(apply_state) && !"".equals(apply_type)){
			//有单据状态、有单据类别查找单据信息
			sql.append("select * from " +
					" t_apply as ta left outer join t_storehouse as tsh" +
					" on ta.storehouse_id_fk = tsh.storehouse_id" +
					" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
					" where ta.apply_type = ? "+
					" AND ta.apply_state = ? ");
			return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{apply_type,apply_state},StoreApplyFormBean.class);
//			return dao.queryForList("select * from " +
//					" t_apply as ta left outer join t_storehouse as tsh" +
//					" on ta.storehouse_id_fk = tsh.storehouse_id" +
//					" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//					" where ta.apply_type ="+apply_type +
//					" AND ta.apply_state = '"+apply_state +"' limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
		}else {
			return new StoreApplyFormBean_Manage().queryStoreApplyFormBean(storeApplyFormBean, pc, ps,filters);
		}
	}
	
	/**
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货    信息
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters) {
		System.out.println("-----------------------------------------------");
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply as ta left outer join t_storehouse as tsh" +
				" on ta.storehouse_id_fk = tsh.storehouse_id" +
				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
				" where ta.apply_type = ? ");
		list = dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{apply_type}, StoreApplyFormBean.class);
		System.out.println(list.size()+"----------"+list.get(0));
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{apply_type}, StoreApplyFormBean.class);
	}
	
	
	/**
	 * 查询仓库管理员提交的仓库转让申请
	 * apply_type：1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryCheckStoreCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from " +
				" t_apply,t_user " +
				" where t_user.user_id = t_apply.user_id_fk " +
				" AND t_user.role_id_fk = ?" +
				" AND t_apply.apply_type = ? and t_apply.apply_state = ? ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", new Object[]{3,apply_type,"待审核"}));
//		list.add(dao.getDataCount("select count(*) from " +
//				" t_apply,t_user " +
//				" where t_user.user_id = t_apply.user_id_fk " +
//				" AND t_user.role_id_fk = ?" +
//				" AND t_apply.apply_type = ? and t_apply.apply_state = ?", new Object[]{3,apply_type,"待审核"}));
		return list;
	}
	
	/**
	 * 查找仓库之间调度的申请
	 * apply_type:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryCheckStoreApply(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply as ta left outer join t_storehouse as tsh" +
				" on ta.storehouse_id_fk = tsh.storehouse_id" +
				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
				" where tu.role_id_fk = ? AND ta.apply_type = ? AND ta.apply_state = ? ");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps),new Object[]{3,apply_type,"待审核"}, StoreApplyFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_apply as ta left outer join t_storehouse as tsh" +
//				" on ta.storehouse_id_fk = tsh.storehouse_id" +
//				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//				" where tu.role_id_fk = 3 AND ta.apply_type = "+apply_type+" AND ta.apply_state = '待审核' limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
	}
	
	
	
	/**
	 * 查找所有申请数量
	 * 查询单据类型   :1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_apply where 1 = 1 ");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
//		list.add(dao.getDataCount("select count(*) from t_apply", null));
		return list;
	}
	
	
	/**
	 * 审核仓库物资申请:出库与入库
	 * @param apply_id
	 * @param apply_state
	 * @return
	 */
	protected List<String> checkStoreApplyFormBean(String[] apply_ids,String apply_type,String apply_state,int user_id,String place_id){
		list = new ArrayList<String>(1);
		boolean boo = false;
		if("审核通过".equals(apply_state)) {
			if("1".equals(apply_type)) {
				//完成仓库管理员同意申请入库功能
				for(String apply_id : apply_ids) {
					boo = new StoreApplyFormBean_Manage().checkInputStoreApply(apply_id, place_id, user_id);
					if(!boo) {
						list.add(SYSTEM_ERROR);
						return list;
					}
				}
			}
			if("3".equals(apply_type)){
				//完成仓库管理员同意申请出库功能
				for(String apply_id : apply_ids) {
					if(!new StoreApplyFormBean_Manage().checkOutPutStoreApply(apply_id, user_id)) {
						list.add(SYSTEM_ERROR);
						return list;
					}
				}
			}
		}else if("审核失败".equals(apply_state)) {
			Apply apply = new Apply();
			//完成仓库管理员同意申请入库功能
			dao.beginTransaction();
			for(String apply_id : apply_ids) {
				apply.setApply_id(Integer.valueOf(apply_id));
				apply.setApply_type(apply_type);
				apply.setApply_state("审核失败");
				boo = dao.update(apply);
				if(!boo){
					dao.rollbackTransaction();
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
			if(boo) {
				dao.commitTransaction();
				list.add(boo);
				return list;
			}
		}
		list.add(SUCCESS);
		return list;
	}
	
	
	/**
	 * 仓库管理员通过入库申请
	 * 步骤：
	 * 	1：通过申请id得到申请单详细表
	 *  2：通过申请单详细表得到物资id
	 *  3：通过物资id得到物资条形码
	 * @param apply_id
	 * @param place_id
	 * @return
	 */
	private boolean checkInputStoreApply(String apply_id,String place_id,int user_id) {
		List listApply_Goods = new ArrayList<Apply_Goods>();
		Inventory inventory = new Inventory();
		boolean boo = false; 
		
		//开启事务
		dao.beginTransaction();
		
		//补全申请单信息
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply = dao.queryForObject(apply);
		
		//得到仓库id
		int storehouse_id = apply.getStorehouse_id_fk();
		
		//更新申请表信息
		apply.setApply_state("审核通过");
		boo = dao.update(apply);
		//根据id得到apply的信息
		
		
		//通过apply_id得到申请单详细信息
		Apply_Goods apply_Goods = new Apply_Goods();
		apply_Goods.setApply_id_fk(Integer.valueOf(apply_id));
		listApply_Goods = dao.queryForListByPo(apply_Goods);
		
		//通过申请apply_goods_id得到goods_id
		for(int i = 0;listApply_Goods != null && i<listApply_Goods.size() && boo;i++) {
			apply_Goods = (Apply_Goods) listApply_Goods.get(i);
			//得到物资id
			int goods_id = apply_Goods.getGoods_id_fk();
			int apply_goods_count = apply_Goods.getApply_goods_count();
			
			//通过goods_id得到条形码id
			Barcode barcode = new Barcode();
			barcode.setGoods_id_fk(goods_id);
			barcode = dao.queryForObject(barcode);
			//得到条形码id
			int barcode_id = barcode.getBarcode_id();
			
			inventory.setBarcode_id_fk(barcode_id);
			inventory.setGoods_id_fk(goods_id);
			inventory.setInventory_count(apply_goods_count);
			inventory.setInventory_isdel(0);
			inventory.setPlace_id_fk(Integer.valueOf(place_id));
			inventory.setStorehouse_id_fk(storehouse_id);
			inventory.setUser_id_fk(Integer.valueOf(user_id));
			inventory.setInventory_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			boo = dao.save(inventory);

			Goods_number goods_number = new Goods_number();
			goods_number.setGoods_id_fk(goods_id);
			goods_number.setStorehouse_id_fk(storehouse_id);
			goods_number = dao.queryForObject(goods_number);
			//判断数据库里有没有这条信息
			if(goods_number != null && boo) {
				goods_number.setGoods_number(apply_goods_count);
				boo = dao.save(goods_number);
			}else if(boo) {
				dao.update("update t_goods_number " +
						" set goods_number = goods_number + ?" +
						" where goods_id_fk = ? " +
						" AND storehouse_id_fk = ?", new Object[]{apply_goods_count,goods_id,storehouse_id});
			}
		}
		if(boo) {
			dao.commitTransaction();
		}else {
			dao.rollbackTransaction();
		}
		return boo;
	}
	
	
	
	
	
	/**
	 * 审核退货功能
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	protected List<String> checkReturnSales(String[] apply_ids,String apply_state) {
		list = new ArrayList<String>(1);
		boolean boo = false;
		if("审核失败".equals(apply_state)) {
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().rejectReturnSales(apply_id);
				if(!boo) {
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
		}else {
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().passReturnSales(apply_id);
				if(!boo) {
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
		}
		list.add(SUCCESS);
		return list;
	}
	
	
	/**
	 * 审核退货失败的功能实现
	 * @param apply_id
	 * @return
	 */
	private boolean rejectReturnSales(String apply_id) {
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply.setApply_state("审核失败");
		return dao.update(apply);
	}
	
	/**
	 * 审核退货成功的实现
	 * @param apply_id
	 * @return
	 */
	private boolean passReturnSales(String apply_id) {
		List listApply_Goods = new ArrayList<Apply_Goods>();
		Inventory inventory = new Inventory();
		boolean boo = false;
		Goods_number goods_number = null;
		
		dao.beginTransaction();
		//补全申请单信息
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply = dao.queryForObject(apply);
		
		//得到仓库id
		int storehouse_id = apply.getStorehouse_id_fk();
		//更新申请表信息
		apply.setApply_state("审核通过");
		boo = dao.update(apply);
		
		//通过apply_id得到申请单详细信息
		Apply_Goods apply_Goods = new Apply_Goods();
		apply_Goods.setApply_id_fk(Integer.valueOf(apply_id));
		listApply_Goods = dao.queryForListByPo(apply_Goods);
		//通过申请apply_goods_id得到goods_id
		for(int i = 0;listApply_Goods != null && i<listApply_Goods.size() && boo ;i++) {
			apply_Goods = (Apply_Goods) listApply_Goods.get(i);
			//得到物资id
			int goods_id = apply_Goods.getGoods_id_fk();
			int apply_goods_count = apply_Goods.getApply_goods_count();
			
			//通过仓库id与物资id更改数量，防止重复领用
			goods_number = new Goods_number();
			goods_number.setGoods_id_fk(goods_id);
			goods_number.setStorehouse_id_fk(storehouse_id);
			
			//先查询goods_number是否满足申请
			goods_number = dao.queryForObject(goods_number);
			if(goods_number != null && goods_number.getGoods_number()>apply_goods_count){
				goods_number.setGoods_number(goods_number.getGoods_number()-apply_goods_count);
				boo = dao.update(goods_number);
			}else {
				boo = false;
			}
		}
		if(boo) {
			dao.commitTransaction();
			
		}else{
			dao.rollbackTransaction();
		}
		return boo;
	}
	
	
	private boolean checkOutPutStoreApply(String apply_id,int user_id) {
		List listApply_Goods = new ArrayList<Apply_Goods>();
		Inventory inventory = new Inventory();
		boolean boo = true;
		
		dao.beginTransaction();
		//补全申请单信息
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply = dao.queryForObject(apply);
		
		//得到仓库id
		int storehouse_id = apply.getStorehouse_id_fk();
		//更新申请表信息
		apply.setApply_state("审核通过");
		boo = dao.update(apply);
		
		//通过apply_id得到申请单详细信息
		Apply_Goods apply_Goods = new Apply_Goods();
		apply_Goods.setApply_id_fk(Integer.valueOf(apply_id));
		listApply_Goods = dao.queryForListByPo(apply_Goods);
		//通过申请apply_goods_id得到goods_id
		for(int i = 0;listApply_Goods != null && i<listApply_Goods.size() && boo ;i++) {
			apply_Goods = (Apply_Goods) listApply_Goods.get(i);
			//得到物资id
			int goods_id = apply_Goods.getGoods_id_fk();
			int apply_goods_count = apply_Goods.getApply_goods_count();
			
			//通过仓库id与物资id更改数量，防止重复领用
			Goods_number goods_number = new Goods_number();
			goods_number.setGoods_id_fk(goods_id);
			goods_number.setStorehouse_id_fk(storehouse_id);
			
			//先查询goods_number是否满足申请
			goods_number = dao.queryForObject(goods_number);
			if(goods_number != null && goods_number.getGoods_number()>apply_goods_count){
				goods_number.setGoods_number(goods_number.getGoods_number()-apply_goods_count);
				boo = dao.update(goods_number);
			}else {
				boo = false;
			}
			
			//判断数量是否超过最低库存，通过goods_id查询物资表，得到最低库存
			Goods goods = new Goods();
			goods.setGoods_id(goods_id);
			goods = dao.queryForObject(goods);
			//小于最低库存，生成采购申请单
			if(boo && goods != null &&  goods_number != null && goods.getGoods_min()>goods_number.getGoods_number()-apply_goods_count) {
				Apply newApply = new Apply();
				newApply.setApply_isdel(0);
				newApply.setApply_remark("超过最低该物资的最低库存，自动申请采购申请");
				newApply.setApply_state("待审核");
				newApply.setApply_time(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				newApply.setApply_type("采购申请");
				newApply.setApply_urgent("是");
				newApply.setStorehouse_id_fk(storehouse_id);
				newApply.setUser_id_fk(Integer.valueOf(user_id));
				
				//保存自动生成的采购申请信息
				int newApply_id = -1;
				newApply_id = dao.getGeneratedKeyByInsert(newApply);
				if(newApply_id != -1){
					boo = true;
				}else {
					boo = false;
				}
				if(boo) {
					//在申请采购申请单的详细清单
					Apply_Goods newapply_goods = new Apply_Goods();
					newapply_goods.setApply_goods_count(goods.getGoods_min()-(goods_number.getGoods_number()-apply_goods_count));
					newapply_goods.setApply_goods_isdel(0);
					newapply_goods.setApply_id_fk(newApply_id);
					newapply_goods.setGoods_id_fk(goods_id);
					boo = dao.save(newapply_goods);
				}
			}
		}
		if(boo) {
			dao.commitTransaction();
		}else{
			dao.rollbackTransaction();
		}
		return boo;
	}
	
	
	/**
	 * 通过单据类型与状态查找总数
	 * @param storeApplyFormBean
	 * @param apply_type
	 * @param apply_state
	 * @return
	 */
	protected List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,int apply_type,String apply_state,Filters filters){
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		//通过状态查询单据类型
		if("".equals(apply_type) && !"".equals(apply_state)) {
			sql.append("select count(t_apply.apply_id) from t_apply where apply_state = ? ");
			list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",new Object[]{apply_state}));
		}else if("".equals(apply_state) && !"".equals(apply_type)){
			sql.append("select count(t_apply.apply_id) from t_apply where apply_type = ? ");
			list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",new Object[]{apply_type}));
		}else if(!"".equals(apply_state) && !"".equals(apply_type)){
			sql.append("select count(t_apply.apply_id) from t_apply where apply_type = ? AND apply_state = ? ");
			list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",new Object[]{apply_type,apply_state}));
		}else {
			sql.append("select count(t_apply.apply_id) from t_apply where 1 = 1 ");
			list.add(dao.getDataCount(sql.append(filters.toAndSql())+"", null));
		}
		return list;
	}
	
	

	/**
	 * 查询单据类型   :1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	protected List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		list = new ArrayList<Integer>(1);
		sql.append("select count(*) from t_apply where apply_type = ?");
		list.add(dao.getDataCount(sql.append(filters.toAndSql())+"",  new Object[]{apply_type}));
//		Object[] obj = new Object[]{apply_type};
//		list.add(dao.getDataCount("select count(*) from t_apply where apply_type = ?", obj));
		return list;
	}
	
	
	/**
	 * 查找审核退用单据、审核采购申请单信息
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	protected List<StoreApplyFormBean> queryCheckStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " +
				" t_apply as ta left outer join t_storehouse as tsh" +
				" on ta.storehouse_id_fk = tsh.storehouse_id" +
				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
				" where ta.apply_type = ? AND ta.apply_state = ?");
		return dao.queryForList(filters.toLimitSql(sql, pc, ps), new Object[]{apply_type,"待审核"},StoreApplyFormBean.class);
//		return dao.queryForList("select * from " +
//				" t_apply as ta left outer join t_storehouse as tsh" +
//				" on ta.storehouse_id_fk = tsh.storehouse_id" +
//				" left outer join t_user as tu on ta.user_id_fk = tu.user_id" +
//				" where ta.apply_type = "+apply_type+" AND ta.apply_state = '待审核' limit "+(pc-1)*ps+" , "+ps, StoreApplyFormBean.class);
	}
	
	
	/**
	 * 审核退用功能实现
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	protected List<String> checkReturnUse(String[] apply_ids,String apply_state) {
		list = new ArrayList<String>(1);
		boolean boo = false;
		if("审核通过".equals(apply_state)) {
			dao.beginTransaction();
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().passReturnUse(apply_id);
				if(!boo) {
					dao.rollbackTransaction();
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
			if(boo) {
				dao.commitTransaction();
			}
		}else {
			dao.beginTransaction();
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().rejectReturnUse(apply_id);
				if(!boo) {
					list.add(SYSTEM_ERROR);
					dao.rollbackTransaction();
					return list;
				}
			}
			if(boo) {
				dao.commitTransaction();
			}
		}
		list.add(SUCCESS);
		return list;
	}
	
	
	private boolean rejectReturnUse(String apply_id) {
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply.setApply_state("审核失败");
		return dao.update(apply);
	}
	
	private boolean passReturnUse(String apply_id) {
		List listApply_Goods = new ArrayList<Apply_Goods>();
		Inventory inventory = new Inventory();
		Goods_number goods_number = null;
		boolean boo = false;
		
		//补全申请单信息
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply = dao.queryForObject(apply);
		
		//得到仓库id
		int storehouse_id = apply.getStorehouse_id_fk();
		//更新申请表信息
		apply.setApply_state("审核通过");
		boo = dao.update(apply);
		
		//通过apply_id得到申请单详细信息
		Apply_Goods apply_Goods = new Apply_Goods();
		apply_Goods.setApply_id_fk(Integer.valueOf(apply_id));
		listApply_Goods = dao.queryForListByPo(apply_Goods);
		//通过申请apply_goods_id得到goods_id
		for(int i = 0;listApply_Goods != null && i<listApply_Goods.size() && boo ;i++) {
			apply_Goods = (Apply_Goods) listApply_Goods.get(i);
			//得到物资id
			int goods_id = apply_Goods.getGoods_id_fk();
			int apply_goods_count = apply_Goods.getApply_goods_count();
			
			//通过仓库id与物资id更改数量，防止重复领用
			goods_number = new Goods_number();
			goods_number.setGoods_id_fk(goods_id);
			goods_number.setStorehouse_id_fk(storehouse_id);
			
			//先查询goods_number是否满足申请
			goods_number = dao.queryForObject(goods_number);
			if(goods_number != null){
				goods_number.setGoods_number(goods_number.getGoods_number()+apply_goods_count);
				boo = dao.update(goods_number);
			}else {
				boo = false;
			}
			if(!boo) {
				return boo;
			}
		}
		if(boo) {
			return boo;
		}
		return boo;
	}
	
	/**
	 * 审核采购申请
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	protected List<String> checkApply(String[] apply_ids,String apply_state) {
		list = new ArrayList<Boolean>(1);
		boolean boo = false;
		if("审核通过".equals(apply_state)) {
			dao.beginTransaction();
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().passApply(apply_id);
				if(!boo) {
					dao.rollbackTransaction();
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
			if(boo) {
				dao.commitTransaction();
			}
		}else {
			dao.beginTransaction();
			for(String apply_id : apply_ids) {
				boo = new StoreApplyFormBean_Manage().rejectApply(apply_id);
				if(!boo) {
					dao.rollbackTransaction();
					list.add(SYSTEM_ERROR);
					return list;
				}
			}
			if(boo) {
				dao.commitTransaction();
			}
		}
		list.add(SUCCESS);
		return list;
	}
	
	private boolean passApply(String apply_id) {
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply.setApply_state("审核通过");
		return dao.update(apply);
	}
	
	private boolean rejectApply(String apply_id) {
		Apply apply = new Apply();
		apply.setApply_id(Integer.valueOf(apply_id));
		apply.setApply_state("审核失败");
		return dao.update(apply);
	}
	
	
}
