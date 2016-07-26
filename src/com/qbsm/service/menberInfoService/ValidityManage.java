package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Alarm;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;


@SuppressWarnings("all")
public class ValidityManage {
	
	private List list = null;
	private GladiolusDao dao = new GladiolusDaoImpl();
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	
	/**
	 * 设置有效期预警
	 * @return
	 */
	protected List<String> updateValidity(Alarm alarm){
		list = new ArrayList<String>(1);
		if(dao.update(alarm)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
}
