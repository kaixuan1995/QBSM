package com.qbsm.web.action.SQLUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServletUtils {
	
	//初始化排序filter
	public static Filters beforeSetTable(HttpServletRequest request) {
		String _search = request.getParameter("_search");
		String search = request.getParameter("search");
		String sidx = request.getParameter("sidx");//根据谁来排序
		String sord = request.getParameter("sord");//asc  desc
		Filters filters = new Filters();
		filters.setSidx(sidx);
		filters.setSord(sord);
		if("true".equals(_search)||"true".equals(search)) {
			//接下来解析参数 
			//&filters={"groupOp":"AND",
			//"rules":[{"field":"userName","op":"eq","data":"xiao10",
			//{"field":"sex","op":"eq","data":"男"}]}
			List<Rules> rulesList = new ArrayList<Rules>();
			String requestFilters = request.getParameter("filters");
			JSONObject obj = JSONObject.fromObject(requestFilters);
			filters.setGroupOp(obj.getString("groupOp"));
			JSONArray arr = obj.getJSONArray("rules");
			for(int i = 0;i<arr.size();i++){
				//拿到rules里面的第i个对象
				JSONObject arrobj = arr.getJSONObject(i);
				Rules rules = new Rules();
				rules.setField(arrobj.getString("field"));
				rules.setOp(arrobj.getString("op"));
				try {
					rules.setData(new String(arrobj.getString("data").getBytes("iso-8859-1"),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return filters;
				}
				rulesList.add(rules);
			}
			filters.setRules(rulesList);
		}
		return filters;
	}
	
}
