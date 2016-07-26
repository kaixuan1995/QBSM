<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		
		<title>用户信息列表——BlueDot物资管理系统</title>

		<meta name="keywords" content="BlueDot物资管理系统" />
		<meta name="description" content="BlueDot物资管理系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp" %>
	</head>

	<body>
		<%@include file="/assets/utiljsp/jsppart/navjsp.jsp"%>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed');
				} catch (e) {}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try {
							ace.settings.check('sidebar', 'fixed');
						} catch (e) {}
					</script>

					
					<ul class="nav nav-list">
						<li class="">
							<a href="<%=basePath%>SystemManageAction/admin">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li class="active open">
							<a href="<%=basePath%>assets/ADMIN/yonghuxinxiguanli.jsp" class="dropdown-toggle">
								<i class="icon-barcode"></i>
								<span class="menu-text"> 用户信息管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="open">
									<a href="<%=basePath%>SystemManageAction/yonghuxinxiliebiao">
										<i class="icon-double-angle-right"></i> 用户信息列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>SystemManageAction/shengchenggongyingshangzhanghao">
										<i class="icon-double-angle-right"></i> 生成供应商账号
									</a>
								</li>
							
								
							</ul>
						</li>
					
						
						<li>
							<a href="<%=basePath%>assets/ADMIN/wuziliebiao.jsp" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 物资管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>SystemManageAction/wuziliebiao">
										<i class="icon-double-angle-right"></i> 物资列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>SystemManageAction/wuzileibie">
										<i class="icon-double-angle-right"></i> 物资类别
									</a>
								</li>
								<li>
									<a href="<%=basePath%>SystemManageAction/cangkuguanli">
										<i class="icon-double-angle-right"></i> 仓库管理
									</a>
								</li>
								<li>
									<a href="<%=basePath%>SystemManageAction/keshiguanli">
										<i class="icon-double-angle-right"></i> 科室管理
									</a>
								</li>
								<li>
									<a href="<%=basePath%>SystemManageAction/wuzidanweiguanli">
										<i class="icon-double-angle-right"></i> 物资单位管理
									</a>
								</li>
								
							</ul>
						</li>
						
						<li class="">
							<a href="<%=basePath%>SystemManageAction/gerenjixiaoguanli">
								<i class=" icon-bar-chart"></i>
								<span class="menu-text"> 个人绩效管理 </span>
							</a>
						</li>
						<li class="">
							<a href="<%=basePath%>SystemManageAction/xitongweihu">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 系统维护 </span>
							</a>
						</li>

						
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try {
							ace.settings.check('sidebar', 'collapsed');
						} catch (e) {}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try {
								ace.settings.check('breadcrumbs', 'fixed');
							} catch (e) {}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">用户信息管理</a>
							</li>
							<li class="active">用户信息列表</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="#" method="get">
								<a href="<%=basePath%>assets/chaxun.jsp" style="margin-right: 5px;">高级查询</a>
								<span class="input-icon">
									
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								用户信息管理
								<small>
									<i class="icon-double-angle-right"></i>
									用户信息列表
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<table id="grid-table"></table>
								<div id="grid-pager"></div>
							</div>
						</div>
					</div>
				</div>
		</div>
		</div>
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>

		<script type="text/javascript">
		
			function getoffice_name(){   
				var data = null;
	            $.ajax({url:"<%=basePath%>"+"MenberInforAction/getoffice_name",
	            async:false,success:function(e){if (e != null) { data = e;} }}); 
	  			return data; 
			} 
			function getrole_name(){   
				var data = null;
	            $.ajax({url:"<%=basePath%>"+"MenberInforAction/getrole_name",
	            async:false,success:function(e){if (e != null) { data = e;} }}); 
	  			return data; 
			}
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"MenberInforAction/getyonghuxinxiliebiao";
			var tablename = "用户信息列表";
			var pageediturl ="<%=basePath%>"+"MenberInforAction/saveyonghuxinxiliebiao";
			//navButtons
			var editbtn=true;
			var addbtn=true;
			var delbtn=true;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['用户id', '用户名','邮箱','地址','联系人','手机号码',
					'传真','角色','所属科室','用户创建时间','最后登录时间','最后登录IP'];
			var pagecolmodel =  [
					{
						name: 'user_id',
						index: 'user_id',
						align:'center',
						maxlength:10,
						key: true,
						width: 80,
						editable: false,
						hidden : true ,
						
					}, {
						name: 'user_name',
						index: 'user_name',
						align:'center',
						search:true,
						width: 80,
						editable: true
					}
					,{
						name: 'user_email',
						index: 'user_email',
						align:'center',
						width: 150,
						editable: true,
						editrules:{email:true}
					},{
						name: 'user_address',
						index: 'user_address',
						align:'center',
						editable: true,
						width: 250,
					},{
						name: 'user_lianxiren',
						index: 'user_lianxiren',
						align:'center',
						width: 80,
						editable: true
					},{
						name: 'user_phone',
						index: 'user_phone',
						align:'center',
						width:120,
						editable: true
					},				
					{
						name: 'user_fax',
						index: 'user_fax',
						align:'center',
						width: 80,
						editable: true
					},{
						name: 'role_name',//角色名
						index: 'role_name',
						align:'center',
						width: 70,
						editable: true,
						edittype: "select",
						editoptions: {
							value: getrole_name()
						}
					},{
						name: 'office_name',//科室姓名
						index: 'office_name',
						align:'center',
						width: 70,
						editable: true,
						edittype: "select",
						editoptions: {
							value: getoffice_name()
						}
					},			
					{
						name: 'user_createtime',
						index: 'user_createtime',
						align:'center',
						width: 80,
						editable: false,
					},{
						name: 'user_lastlogintime',
						index: 'user_lastlogintime',
						align:'center',
						width: 80,
						editable: false,
					},{
						name: 'user_lastloginip',
						index: 'user_lastloginip',
						align:'center',
						width: 100,
						editable: false,
					},
					];
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	
	</body>

</html>