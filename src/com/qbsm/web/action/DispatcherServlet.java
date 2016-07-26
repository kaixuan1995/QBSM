package com.qbsm.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbsm.web.action.help.ActionMapping;
import com.qbsm.web.action.help.ActionXmlUtil;
import com.qbsm.web.action.help.XmlActionMappings;

/**
 * 解析action.xml进行用户请求转发
 * 
 * @author xieguoping
 * 
 */
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {

	// kaiser-action.xml配置文件路径
	private final String xmlpath = this.getClass().getClassLoader()
			.getResource("/").getPath()
			+ "action.xml";

	private static XmlActionMappings xmlActionMappings = null;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到表单action的值
		String action = request.getServletPath().replaceAll("/", "");

		// 获得action对应的映射对象
		ActionMapping actionMapping = xmlActionMappings
				.getActionMapping(action);

		int index = action.lastIndexOf(".");// 获取操作类型的位置

		if (index == -1) {
			throw new RuntimeException(
					" 该action访问路径未定义操作类型; servlet定义格式为:" +
					" ‘action名’+‘.’+‘操作方法名’ 例如：userAction.login ");
		}

		// 得到视图层传入的操作类型（login、query、delete……）
		String operate = action.substring(index + 1, action.length());

		// 将操作类型设置到action映射对象中
		actionMapping.setOperate(operate);
		// 得到映射对象中映射的action的完全类路径
		String actionClass = actionMapping.getActionClass();
		try {
			ActionSupport ac = (ActionSupport) Class.forName(actionClass)
					.newInstance();
			ac.setActionMapping(actionMapping);
			ac.service(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		// dispatcherServlet初始化时解析action配置文件，获得所有的action配置映射集合
		xmlActionMappings = ActionXmlUtil.parsexml(xmlpath);
	}

}
