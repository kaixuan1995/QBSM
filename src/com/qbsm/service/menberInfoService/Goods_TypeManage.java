package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Wordbook;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.service.annotation.MessageEnum;
import com.qbsm.web.action.SQLUtil.Filters;
@SuppressWarnings("all")
public class Goods_TypeManage {
	
	
	private GladiolusDao dao = new GladiolusDaoImpl();
	private List list = null;
	private final String DELETE_ERROR = MessageEnum.E30004+"";
	private final String UPDATE_ERROR = MessageEnum.E30003+"";
	private final String SAVE_ERROR =   MessageEnum.E30002+"";
	private final String SUCCESS = MessageEnum.S00000+"";
	private final String SYSTEM_ERROR = MessageEnum.E11111+"";
	
	/**
	 * 增加物资类别信息
	 * @param wordBook
	 * @return
	 */
	protected List<String> saveWordbook(Wordbook wordBook){ 
		list = new ArrayList<String>(1);
		if(dao.save(wordBook)){
			list.add(SUCCESS);
		}else{
			list.add(SAVE_ERROR);
		}
		return list;
	}
	
	/**
	 * 删除物资类别信息
	 * @param wordBook
	 * @return
	 */
	protected List<String> deleteWordbook(Wordbook wordBook){
		list = new ArrayList<String>(1);
		if(dao.delete(wordBook)){
			list.add(DELETE_ERROR);
		}
		return list;
	}
	
	/**
	 * 通过分页查询所有的物资类别信息
	 * @return
	 */
	protected List<Wordbook> queryWordbook(Wordbook wordbook,int pc,int ps,Filters filters){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_wordbook ");
		return dao.queryForList(filters.toWhereLimitSql(sql, pc, ps), Wordbook.class);
	}
	
	
	/**
	 * 查询所有的物资类别信息
	 * @return
	 */
	protected List<Wordbook> queryWordbook(Wordbook wordbook){
		return dao.queryForListByPo(wordbook);
	}
	
	
	/**
	 * 查询物资类别总数
	 * @param wordbook
	 * @return
	 */
	protected List<Integer> queryCount(Wordbook wordbook,Filters filters){
		list = new ArrayList<Integer>(1);
		list.add(dao.getDataCount("select count(wordbook_id) from t_wordbook " +
				filters.toWhereSql(), null));
		return list;
	}

	
	/**
	 * 更新物资类别信息
	 * @param wordbook
	 * @return
	 */
	public List<String> updateWordbook(Wordbook wordbook) {
		list = new ArrayList<String>(1);
		if(dao.update(wordbook)){
			list.add(SUCCESS);
		}else{
			list.add(UPDATE_ERROR);
		}
		return list;
	}
	
}
