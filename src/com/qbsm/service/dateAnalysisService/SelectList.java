package com.qbsm.service.dateAnalysisService;

import java.util.ArrayList;
import java.util.List;

import com.qbsm.bean.Office;
import com.qbsm.bean.Storehouse;
import com.qbsm.bean.User;
import com.qbsm.dao.GladiolusDao;
import com.qbsm.dao.core.GladiolusDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SelectList {
	private GladiolusDao dao = new GladiolusDaoImpl();
	
	protected List<String> querySelectList() {
		List<String> list = new ArrayList<String>();
		//检测人员部分
		JSONObject jianceJsonObject = new JSONObject();
		jianceJsonObject.accumulate("t", "检测人员");
		
		JSONArray jianceJsonArray = new JSONArray();
		User user = new User();
		user.setUser_isdel(0);
		user.setRole_id_fk(2);//检测人员
		List<User> users = new ArrayList<User>();
		users =  dao.queryForListByPo(user);
		if (users!=null) {
			for (User user2 : users) {
				JSONObject userJsonObject = new JSONObject();
				userJsonObject.accumulate("m",user2.getUser_id()+" # "+ user2.getUser_name());
				JSONArray gerenr = new JSONArray();
				JSONObject qinggouJsonObject = new JSONObject();
				qinggouJsonObject.accumulate("r", "请购");
				
				JSONObject lingyongJsonObject = new JSONObject();
				lingyongJsonObject.accumulate("r", "领用");
				gerenr.add(qinggouJsonObject);
				gerenr.add(lingyongJsonObject);
				userJsonObject.accumulate("d", gerenr);
				jianceJsonArray.add(userJsonObject);
			}
		}
		
		jianceJsonObject.accumulate("c", jianceJsonArray);
		
		
		//采购人员部分
		JSONObject gerenJsonObject = new JSONObject();
		gerenJsonObject.accumulate("t", "采购人员");
		
		JSONArray gerenJsonArray = new JSONArray();
		User user1 = new User();
		user1.setUser_isdel(0);
		user1.setRole_id_fk(1);
		List<User> users1 = new ArrayList<User>();
		users1 =  dao.queryForListByPo(user1);
		if (users1!=null) {
			for (User user2 : users1) {
				JSONObject userJsonObject = new JSONObject();
				userJsonObject.accumulate("m",user2.getUser_id()+" # "+ user2.getUser_name());
				JSONArray gerenr = new JSONArray();
				JSONObject qinggouwuzileibieJsonObject = new JSONObject();
				qinggouwuzileibieJsonObject.accumulate("r", "采购物资类别");
				gerenr.add(qinggouwuzileibieJsonObject);
				userJsonObject.accumulate("d", gerenr);
				gerenJsonArray.add(userJsonObject);
			}
		}
		
		gerenJsonObject.accumulate("c", gerenJsonArray);
		
		
		
		//科室部分
		JSONArray keshiJsonArray = new JSONArray();
		JSONObject keshiJsonObject = new JSONObject();
		keshiJsonObject.accumulate("t", "科室");
		Office office = new Office();
		office.setOffice_isdel(0);
		List<Office> offices = new ArrayList<Office>();
		offices = dao.queryForListByPo(office);
		if (offices!=null) {
			for (Office office2 : offices) {
				JSONObject offoceJsonObject = new JSONObject();
				offoceJsonObject.accumulate("m", office2.getOffice_id()+" # "+office2.getOffice_name());
				JSONArray gongyingks = new JSONArray();
				JSONObject qinggouJsonObject = new JSONObject();
				qinggouJsonObject.accumulate("r", "请购");
				JSONObject lingyongJsonObject = new JSONObject();
				lingyongJsonObject.accumulate("r", "领用");
				gongyingks.add(qinggouJsonObject);
				gongyingks.add(lingyongJsonObject);
				offoceJsonObject.accumulate("d", gongyingks);
				keshiJsonArray.add(offoceJsonObject);
			}
		}
		
		keshiJsonObject.accumulate("c", keshiJsonArray);
		
		//仓库部分
		JSONArray cangkuJsonArray = new JSONArray();
		JSONObject cangkuJsonObject = new JSONObject();
		cangkuJsonObject.accumulate("t", "仓库");
		Storehouse storehouse = new Storehouse();
		storehouse.setStorehouse_isdel(0);
		List<Storehouse> storehouses = new ArrayList<Storehouse>();
		storehouses = dao.queryForListByPo(storehouse);
		if (storehouses!=null) {
			for (Storehouse storehouse2 : storehouses) {
				JSONObject storehouseJsonObject = new JSONObject();
				storehouseJsonObject.accumulate("m", storehouse2.getStorehouse_id()+" # "+storehouse2.getStorehouse_name());
				JSONArray storehouseJsonArray = new JSONArray();
				JSONObject chucangJsonObject = new JSONObject();
				chucangJsonObject.accumulate("r", "出仓");
				JSONObject rucangJsonObject = new JSONObject();
				rucangJsonObject.accumulate("r", "入仓");
				storehouseJsonArray.add(chucangJsonObject);
				storehouseJsonArray.add(rucangJsonObject);
				storehouseJsonObject.accumulate("d", storehouseJsonArray);
				cangkuJsonArray.add(storehouseJsonObject);
				
			}
		}
		
		cangkuJsonObject.accumulate("c", cangkuJsonArray);
		
		
		
		//供应商部分
		JSONArray gongyingshangJsonArray = new JSONArray();
		JSONObject gongyingshangJsonObject = new JSONObject();
		gongyingshangJsonObject.accumulate("t", "供应商");
		
		user1.setRole_id_fk(4);//供应商
		List<User> users11 = new ArrayList<User>();
		users11 =  dao.queryForListByPo(user1);
		if (users11!=null) {
			for (User user2 : users11) {
				JSONObject user1JsonObject = new JSONObject();
				user1JsonObject.accumulate("m", user2.getUser_id()+" # "+ user2.getUser_name());
				JSONArray gongyingr = new JSONArray();
				JSONObject gonghuoyichang = new JSONObject();
				gonghuoyichang.accumulate("r", "供货异常");

				gongyingr.add(gonghuoyichang);
				user1JsonObject.accumulate("d", gongyingr);
				gongyingshangJsonArray.add(user1JsonObject);
			}
		}
		
		
		gongyingshangJsonObject.accumulate("c", gongyingshangJsonArray);
		
		
		JSONArray selectList = new JSONArray();
		selectList.add(jianceJsonObject);
		selectList.add(gerenJsonObject);
		selectList.add(keshiJsonObject);
		selectList.add(cangkuJsonObject);
		selectList.add(gongyingshangJsonObject);
		
		//System.out.println("SELECT LIST:"+selectList.toString());
		list.add(selectList.toString());
		return  list;
	}
}
