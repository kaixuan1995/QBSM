<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">

	<head>

		<title>消息列表</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
				
		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />

		<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />


		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css" />

		<script src="<%=basePath%>assets/js/ace-extra.min.js"></script>

	</head>

	<body>
	<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="<%=basePath%>MenberInforAction/index" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							BlueDot 物资管理系统
						</small>
					</a>
					<!-- /.brand -->
				</div>
				<!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">${MessageCount}</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-envelope-alt"></i> ${MessageCount}条消息
								</li>
								
								<li>
									<a href="<%=basePath%>MenberInforAction/lookMyMessage">
										查看所有消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="<%=basePath%>assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${sessionScope.userSession.user_name}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="<%=basePath%>MenberInforAction/shezhi">
										<i class="icon-cog"></i> 设置
									</a>
								</li>

								<li>
									<a href="<%=basePath%>MenberInforAction/gerenziliao">
										<i class="icon-user"></i> 个人资料
									</a>
								</li>
								<li>
									<a href="<%=basePath%>MenberInforAction/xiugaimima">
										<i class="icon-key"></i> 修改密码
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a href="<%=basePath%>MenberInforAction/exit">
										<i class="icon-off"></i> 退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
					<!-- /.ace-nav -->
				</div>
				<!-- /.navbar-header -->
			</div>
			<!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {}
			</script>

			<div class="main-container-inner">

				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">

						<div id="main">
							<div class="space-24"></div>
							
							<table id="grid-table"></table>

							<div id="grid-pager"></div>


						</div>
						

					</div>
				</div>

		
			</div>
			<!-- /.page-content -->

			
		</div>


		<!-- basic scripts -->
			<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
		</script>

		<!-- <![endif]-->


		<script type="text/javascript">
			if ("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
		</script>
		<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>


		<!-- page specific plugin scripts -->

		<script src="<%=basePath%>assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="<%=basePath%>assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="<%=basePath%>assets/js/jqGrid/i18n/grid.locale-en.js"></script>


		<!-- ace scripts -->

		<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
		<script src="<%=basePath%>assets/js/ace.min.js"></script>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script type="text/javascript">
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"MenberInforAction/getMyMessage";
			var tablename = "单位列表";
			var pageediturl ="<%=basePath%>"+"#";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['消息编号','申请单号', '消息内容','时间'];
			var pagecolmodel =  [
				
					{
						name: 'message_id',
						index: 'message_id',
						key: true,
						width: 50,
						editable: false,
						hidden:true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'apply_id_fk',
						index: 'apply_id_fk',
						width: 100,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "30"
						}
					}, {
						name: 'message_content',
						index: 'message_content',
						width: 100,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "30"
						},
					},
					{
						name: 'message_time',
						index: 'message_time',
						width: 100,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "30"
						}
					}
					];
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	
		
	</body>

</html>
