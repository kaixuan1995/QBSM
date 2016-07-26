package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;
import com.qbsm.web.action.SQLUtil.Filters;
import com.qbsm.web.formbean.MessageFormBean;

public class NewMessageInfo {
	private static GladiolusDao dao = new GladiolusDaoImpl();

	/**
	 * 更新用户申请单的状态
	 * @param user_id_fk
	 * @param message_content
	 */
	public static void updateMyMessage(int user_id_fk,String message_content){
		dao.save("INSERT into t_message(user_id_fk,message_content,message_time,apply_id_fk) values (?,?,NOW(),1)", new Object[]{user_id_fk,message_content});
	}
	
	protected List<MessageFormBean> queryMessageFormBean(MessageFormBean messageFormBean,int pc,int ps,int user_id,Filters filters) {
		StringBuffer sql = new StringBuffer();
		sql.append("select mess.apply_id_fk as apply_id_fk," +
				"mess.message_content as message_content," +
				"mess.message_time as message_time " +
				"from t_message mess " +
				"where mess.user_id_fk=? and message_state=0");
		List<MessageFormBean> mess = dao.queryForList(filters.toLimitSql(sql, pc, ps),
				new Object[]{user_id},MessageFormBean.class);
//		dao.update("update t_message set message_state=1 where  t_message.user_id_fk=?",
//				new Object[] { user_id });
		return mess;
	}

	/*public static void main(String[] args) {
		NewMessageInfo n = new NewMessageInfo();
		n.lookMyMessage(151);
	}*/
	
	/**
	 * 查询某个人的消息数量
	 * @param user_id_fk
	 * @return
	 */
	protected List<Integer> queryCount(MessageFormBean messageFormBean,Filters filters){
		List<Integer> list = new ArrayList<Integer>(1);
		String sql = "select count(*) from t_message mess " +
				"where mess.user_id_fk=? and mess.message_state=0 "+filters.toAndSql();
		Object unReadCount = dao.queryForObject(sql, new Object[]{messageFormBean.getUser_id()});
	    list.add(Integer.parseInt(String.valueOf(unReadCount)));
		return list;
	}
	
	protected List<Integer> queryCount(int user_id){
		List<Integer> list = new ArrayList<Integer>(1);
		String sql = "select count(*) from t_message mess where mess.user_id_fk=? and mess.message_state=0";
		Object unReadCount = dao.queryForObject(sql, new Object[]{user_id});
	    list.add(Integer.parseInt(String.valueOf(unReadCount)));
		return list;
	}
}
