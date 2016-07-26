package com.qbsm.service.menberInfoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qbsm.bean.Office;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;

@SuppressWarnings("all")
public class OfficeManage {
	
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	private final String SYSTEM_ERROR = MessageEnum.E11111+"";

	/**
	 * 增加科室信息
	 * @param office
	 * @return
	 */
	protected List<String> saveOffice(Office office){
		list = new ArrayList<String>(1);
		office.setOffice_createtime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(dao.save(office)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	
	/**
	 * 删除科室信息
	 * @param officeId
	 * @return
	 */
	protected List<String> deleteOffice(Office office){
		list = new ArrayList<String>(1);
		office.setOffice_isdel(1);
		if(dao.update(office)){
			list.add(SUCCESS);
		}else{
			list.add(DELETE_ERROR);
		}
		return list;
		
	}
	
	/**
	 * 修改科室信息
	 * @param office
	 * @return
	 */
	protected List<String> updateOffice(Office office){
		list = new ArrayList<String>(1);
		if(dao.update(office)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
	/**
	 * 分页查询所有科室信息
	 * @return
	 */
	protected List<Office> queryOffice(Office office,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		if(office.getStorehouse_id_fk()==null){
			sql.append("select * from t_office where office_isdel = 0 ");
		}else {
			sql.append("select * from t_office where office_isdel = 0 " +
					"and storehouse_id_fk = "+office.getStorehouse_id_fk());
		}
		return dao.queryForList(filters.toLimitSql(sql,pc,ps), Office.class);
	}
	
	/**
	 * 通过office信息查找office
	 * @param office
	 * @return
	 */
	protected List<Office> queryOffice(Office office) {
		office.setOffice_isdel(0);
		return dao.queryForListByPo(office);
	}
	
	
	protected List<Integer> queryCount(Office office,Filters filters){
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(*) from t_office where office_isdel= 0 " +
				filters.toAndSql(),null));
		return list;
	}
	
}
