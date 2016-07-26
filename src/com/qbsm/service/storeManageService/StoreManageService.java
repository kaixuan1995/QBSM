package com.qbsm.service.storeManageService;

import java.util.HashSet;
import java.util.List;

import com.qbsm.bean.Apply;
import com.qbsm.bean.ApplyNewGoods;
import com.qbsm.bean.Goods;
import com.qbsm.bean.GoodsType;
import com.qbsm.bean.Office;
import com.qbsm.bean.Place;
import com.qbsm.bean.Shopping;
import com.qbsm.bean.Type;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.AdminGoodsInforFormBean;
import com.qbsm.web.formbean.GoodsManageFormBean;
import com.qbsm.web.formbean.LookInStoreApplyFormBean;
import com.qbsm.web.formbean.StoreApplyDFormBean;
import com.qbsm.web.formbean.StoreApplyFormBean;

public interface StoreManageService {

	/**
	 * 查看特定的申请单
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @param user_id
	 * @return
	 */
	public List<StoreApplyDFormBean> queryStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean,
			int pc, int ps, int apply_type,Filters filters);
	
	/**
	 * 查看特定的申请单据数量
	 * @param storeApplyDFormBean
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean,int apply_type,Filters filters);
	
	/**
	 * 分页查询物资信息
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean,int pc,int ps,Filters filters);
	
	
	/**
	 * 通过条件查询总数，条件封装到GoodsManageFormBean对象中，没有条件可以传递一个空实现
	 * @param goodsManageFormBean
	 * @return
	 */
	public List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,Filters filters);
	
//	/**
//	 * 通过vo对象查询GoodsFormBean信息
//	 * @param goodsManageFormBean
//	 * @return
//	 */
//	public List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean);
	
	
	/**
	 * 查询物资申请详细信息总数
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyDFormBean storeApplyDFormBean,Filters filters);

	
	/**
	 * 分页查询物资申请详细信息
	 * @param storeApplyDFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyDFormBean> queryStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean,int pc,int ps,Filters filters);
		
	
//	/**
//	 * 查询物资申请详细信息
//	 * @param storeApplyDFormBean
//	 * @return
//	 */
//	public List<StoreApplyDFormBean> queryStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean);
	
	
	/**
	 * 更新物资申请详细信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<String> updateStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean);
	
	
	/**
	 * 查询分页的物资信息
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean,int pc,int ps,HashSet<String> goods_ids,Filters filters);
	
	
	/**
	 * 查询分页的物资信息总数
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,HashSet<String> goods_ids);
	/**
	 * 保存物资申请详细信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<String> saveStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean);
	
	
	/**
	 * 删除物资申请详细信息
	 * @param storeApplyDFormBean
	 * @return
	 */
	public List<String> deleteStoreApplyDFormBean(StoreApplyDFormBean storeApplyDFormBean);
	

	/**
	 * 查询角色总数
	 * @param place
	 * @return
	 */
	public List<Integer> queryCount(Type type,Filters filters);
	
	/**
	 * 通过角色查询分区信息
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Place> queryType(Type type,int pc,int ps,Filters filters);
	
	/**
	 * 查询角色信息
	 * @param place
	 * @return
	 */
	public List<Place> queryType(Type type);
	
	
	/**
	 * 更新角色信息
	 * @param place
	 * @return
	 */
	public List<String> updateType(Type type);
	
	
	/**
	 * 删除角色信息
	 * @param place
	 * @return
	 */
	public List<String> deletePlace(Type type);
	
	
	/**
	 * 新增角色信息
	 * @param place
	 * @return
	 */
	public List<String> saveType(Type type);
	
	
	/**
	 * 通过分页查找物资信息
	 * @param goods
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Goods> queryGoods(Goods goods,int pc,int ps,Filters filters);

	/**
	 * 增加物资信息
	 * @param goods
	 * @return
	 */
	public List<String> saveGoods(Goods goods);
	
	
	/**
	 * 删除物资信息
	 * @param officeId
	 * @return
	 */
	public List<String> deleteGoods(Goods goods);
	
	/**
	 * 修改物资信息
	 * @param office
	 * @return
	 */
	public List<String> updateGoods(Goods goods);
	
	
	/**
	 * 通过goods信息查找goods
	 * @param office
	 * @return
	 */
	public List<Office> queryGoods(Goods goods,Filters filters);
	
	/**
	 * 查找物资总数
	 * @param goods
	 * @return
	 */
	public List<Integer> queryCount(Goods goods,Filters filters);
	
	
	
	
	/**
	 * 查找入库申请单信息
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters);
	
	
	
	/**
	 * 查询入库申请总数
	 * @param storeApplyFormBean
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters);
	
	/**
	 * 查询物资类别总数
	 * @param place
	 * @return
	 */
	List<Integer> queryCount(GoodsType type,Filters filters);
	
	/**
	 * 通过物资类别（分页）
	 * @param place
	 * @param pc
	 * @param ps
	 * @return
	 */
	List<GoodsType> queryGoodsType(GoodsType type,int pc,int ps,Filters filters) ;
	
	/**
	 * 查询物资类别
	 * @param place
	 * @return
	 */
	List<GoodsType> queryGoodsType(GoodsType type) ;
	
	
	/**
	 * 更新物资类别
	 * @param place
	 * @return
	 */
	List<String> updateGoodsType(GoodsType type) ;
	
	
	/**
	 * 删除物资类别
	 * @param place
	 * @return
	 */
	List<String> deleteGoodsType(GoodsType type) ;
	
	/**
	 * 新增物资类别
	 * @param place
	 * @return
	 */
	List<String> saveGoodsType(GoodsType type);
	
	/**
	 * 查找审核退用单据、审核采购申请单总数
	 * 查询单据类型   :1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	public List<Integer> queryCheckCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters);
	
	/**
	 * 查找审核退用单据、审核采购申请单信息
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryCheckStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters);
	
	
	/**
	 * 查找所有申请数量
	 * 查询单据类型   :1申请入库 2申请采购 3申请领用 4申请退用 5申请退货 总数
	 * @param storeApplyFormBean
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,Filters filters);
	
	
	
	/**
	 * 查找所有申请信息
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,Filters filters);
	
	
	/**
	 * 查找单据类型:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货    信息
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 * //通过状态查询单据类型
	 * //通过申请单据类型查找单据信息
	 * //通过单据类型和单据状态查找物资类型
	 */
	public List<StoreApplyFormBean> queryStoreApplyFormBean(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,String apply_state,Filters filters);
	
	/**
	 * 通过单据类型与状态查找总数
	 * @param storeApplyFormBean
	 * @param apply_type
	 * @param apply_state
	 * @return
	 */
	public List<Integer> queryCount(StoreApplyFormBean storeApplyFormBean,int apply_type,String apply_state,Filters filters);

	/**
	 * 查询仓库管理员提交的仓库转让申请
	 * apply_type：1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
	 * @param storeApplyFormBean
	 * @return
	 */
	public List<Integer> queryCheckStoreCount(StoreApplyFormBean storeApplyFormBean,int apply_type,Filters filters);
	
	
	
	/**
	 * 查找仓库之间调度的申请
	 * apply_type:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryCheckStoreApply(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,Filters filters);
	
	
	/**
	 * 查找仓库之间调度的申请
	 * apply_type:1申请入库 2申请采购 3申请领用 4申请退用 5申请退货  
	 * @param storeApplyFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyFormBean> queryOurselfStoreApply(StoreApplyFormBean storeApplyFormBean,int pc,int ps,int apply_type,int user_id,Filters filters);
	

	
	/**
	 * 查询仓库管理员提交的仓库转让申请
	 * apply_type：1申请入库 2申请采购 3申请领用 4申请退用 5申请退货
	 * @param storeApplyFormBean
	 * @return
	 */
	public List<Integer> queryOurselfStoreApplyCount(StoreApplyFormBean storeApplyFormBean,int apply_type,int user_id,Filters filters);

	
	/**
	 * 查询审核物资新增信息
	 * @param applyNewGoods
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<ApplyNewGoods> queryApplyNewGoods(ApplyNewGoods applyNewGoods,int pc,int ps,Filters filters);
	
	/**
	 * 查询审核物资新增信息
	 * @param applyNewGoods
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Integer> queryCount(ApplyNewGoods applyNewGoods,Filters filters);
	
	
	
	/**
	 * 审核新增物资申请信息
	 * @param applygoods_ids
	 * @param apply_state
	 * @return
	 */
	public List<String> updateApplyNewGoods(String[] applygoods_ids,String apply_state);
	
	
	/**
	 * 查询分页的检测人员已经领用的物资信息
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	List<StoreApplyDFormBean> queryLingYongGoods(StoreApplyDFormBean storeApplyDFormBean,
			int pc, int ps, int user_id,Filters filters);
	
	/**
	 * 查询检测人员已经领用的物资信息的总数
	 * @param goodsManageFormBean
	 * @return
	 */
	List<Integer> queryLingYongCount(StoreApplyDFormBean storeApplyDFormBean,int user_id,Filters filters) ;
	
	
	/**
	 * 通过申请单号查找仓库分区表
	 * @param place
	 * @param apply_id
	 * @return
	 */
	public List<Place> queryPlace(Place place,String apply_id);
	
	
	/**
	 * 审核仓库物资申请:出库与入库
	 * @param apply_id
	 * @param apply_state
	 * @return
	 */
	public List<String> checkStoreApplyFormBean(String[] apply_ids,String apply_type,String apply_state,int user_id,String place_id);
	
	
	/**
	 * 查询检测人员查看我的申请（总表）
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	List<StoreApplyFormBean> queryMyApply(
			StoreApplyFormBean storeApplyFormBean, int pc, int ps, int user_id,String apply_type,Filters filters);

	/**
	 * 查询检测人员我的申请信息的总数
	 * 
	 * @param goodsManageFormBean
	 * @return
	 */
	List<Integer> queryMyApplyCount(
			StoreApplyFormBean storeApplyFormBean, int user_id,String apply_type,Filters filters);

	/**
	 * 通过分页特定的申请单详细信息
	 */
	List<StoreApplyDFormBean> queryStoreApplyDFormBean(int apply_id,
			int pc, int ps,Filters filters);
	
	
	/**
	 * 审核退货功能
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	public List<String> checkReturnSales(String[] apply_ids,String apply_state);
	
	
	/**
	 * 审核退用功能实现
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	public List<String> checkReturnUse(String[] apply_ids,String apply_state);
	
	
	/**
	 * 审核采购申请
	 * @param apply_ids
	 * @param apply_state
	 * @return
	 */
	public List<String> checkApply(String[] apply_ids,String apply_state);
	
	
	/**
	 * 通过物资信息及用户id查找购物车信息
	 * @param shopping
	 * @param goods_ids
	 * @param user_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Shopping> queryShopping(Shopping shopping,int user_id,int pc,int ps,Filters filters);
	
	/**
	 * 通过user_id查询购物车信息
	 * @param shopping
	 * @param user_id
	 * @return
	 */
	public List<Integer> queryCount(Shopping shopping,int user_id,Filters filters);
	
	
	/**
	 * 添加购物车信息
	 * @param goods_ids
	 * @param user_id
	 * @return
	 */
	public List<String> saveShopping(String[] goods_ids,int user_id,String storehouse_id);
	
	
	/**
	 * 删除购物车信息
	 * @param shopping_ids
	 * @return
	 */
	public List<String> deleteShopping(String shopping_ids);
	
	/**
	 * 更新购物车信息
	 * @param shopping
	 * @return
	 */
	public List<String> updateShopping(Shopping shopping);
	
	
	
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
	public List<String> passShopping(String[] shopping_ids,String apply_urgent,String storehouse_id);
	
	
	/**
	 * 通过仓库查找物资信息
	 * @param goodsManageFormBean
	 * @param storehouse_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<GoodsManageFormBean> queryGoodsFormBean(GoodsManageFormBean goodsManageFormBean,String storehouse_id, int pc, int ps,Filters filters);
	
	
	/**
	 * 通过仓库id查找物资信息总数
	 * @param goodsManageFormBean
	 * @param storehouse_id
	 * @return
	 */
	public List<Integer> queryCount(GoodsManageFormBean goodsManageFormBean,String storehouse_id,Filters filters);
	
	/**
	 * 
	 * 添加申请单------》根据Apply 来决定是什么申请单 
	 * 
	 * @return
	 */

	List<String> saveApply(String[] shopping_ids,
			Apply apply) ;


	/* 检测人员提交退用申请单,主要是退用申请要上传文件 */

	List<String> saveTuiYongApply(String[] shopping_ids,
			Apply apply, String fileUrl);
	
	 
	
	/**
	 * 查询所有的物资信息：
	 * 		使用范围：仓库管理人员里面的查询物资信息列表
	 * @param goodsManageFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<GoodsManageFormBean> queryAllGoods(GoodsManageFormBean goodsManageFormBean,int pc,int ps,Filters filters);
	
	
	/**
	 * 查询申请单详细信息
	 * 		步骤：
	 *			1：通过apply_id查询t_apply_goods表得到goods_id
	 *			2：通过goods_id查询t_goods表得到详细信息
	 *			3：通过goods_id查询t_barcode得到供应商user_id
	 *			4：通过user_id得到供应商信息		
	 * @param storeApplyDFormBean
	 * @param apply_id
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<StoreApplyDFormBean> queryStoreApplyDFormBeanByApply_id(StoreApplyDFormBean storeApplyDFormBean,int apply_id,int pc,int ps,Filters filters);
	
	
	/**
	 * 查询所有物资数目
	 * @param goodsManageFormBean
	 * @return
	 */
	public List<Integer> queryAllGoodsCount(GoodsManageFormBean goodsManageFormBean,Filters filters);
	
	
	/**
	 * 用于显示管理员的信息列表
	 * @param adminGoodsInforFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<AdminGoodsInforFormBean> queryAll(AdminGoodsInforFormBean adminGoodsInforFormBean,int pc,int ps,Filters filters);
	
	/**
	 * 用于查询总共有多少条物资信息
	 * @param adminGoodsInforFormBean
	 * @return
	 */
	public List<Integer> queryCount(AdminGoodsInforFormBean adminGoodsInforFormBean,Filters filters);
	
	/**
	 * 通过用户所属的科室，查找该科室的库存物资
	 * @param adminGoodsInforFormBean
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<AdminGoodsInforFormBean> queryAllGoodsByStorehouse_id(AdminGoodsInforFormBean adminGoodsInforFormBean,int user_id,int pc,int ps,Filters filters);
	
	/**
	 * 通过科室id查找该科室总共有多少物资数目
	 * @param adminGoodsInforFormBean
	 * @return
	 */
	public List<Integer> queryCount(AdminGoodsInforFormBean adminGoodsInforFormBean,int user_id,Filters filters);
    
	/**
	 * 查看申请中的物资详细信息
	 * @param apply_id
	 * @return
	 */
	public List<LookInStoreApplyFormBean> queryLookInStoreApply(LookInStoreApplyFormBean lookInStoreApplyformBean,int pc,int ps,Filters filters);
    
	/**
	 * 统计申请查询出来的数量
	 */
	public List<Integer> queryCount(LookInStoreApplyFormBean lookInStoreApplyFormBean,Filters filters);
}
