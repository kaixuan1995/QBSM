<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>系统维护——BlueDot物资管理系统</title>

<meta name="keywords" content="BlueDot物资管理系统" />
<meta name="description" content="BlueDot物资管理系统" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp"%>

</head>

<body>

	<%@include file="/assets/utiljsp/jsppart/navjsp.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span> </a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>


				<ul class="nav nav-list">
					<li class=""><a href="<%=basePath%>SystemManageAction/admin">
							<i class="icon-home"></i> <span class="menu-text"> 首页 </span> </a></li>

					<li><a href="<%=basePath%>assets/ADMIN/yonghuxinxiguanli.jsp"
						class="dropdown-toggle"> <i class="icon-barcode"></i> <span
							class="menu-text"> 用户信息管理</span> <b class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li><a
								href="<%=basePath%>SystemManageAction/yonghuxinxiliebiao"> <i
									class="icon-double-angle-right"></i> 用户信息列表 </a></li>
							<li><a
								href="<%=basePath%>SystemManageAction/shengchenggongyingshangzhanghao">
									<i class="icon-double-angle-right"></i> 生成供应商账号 </a></li>


						</ul></li>


					<li><a href="<%=basePath%>assets/ADMIN/wuziliebiao.jsp"
						class="dropdown-toggle"> <i class="icon-briefcase"></i> <span
							class="menu-text"> 物资管理 </span> <b class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li><a href="<%=basePath%>SystemManageAction/wuziliebiao">
									<i class="icon-double-angle-right"></i> 物资列表 </a></li>
							<li><a href="<%=basePath%>SystemManageAction/wuzileibie">
									<i class="icon-double-angle-right"></i> 物资类别 </a></li>
							<li><a href="<%=basePath%>SystemManageAction/cangkuguanli">
									<i class="icon-double-angle-right"></i> 仓库管理 </a></li>
							<li><a href="<%=basePath%>SystemManageAction/keshiguanli">
									<i class="icon-double-angle-right"></i> 科室管理 </a></li>
							<li><a
								href="<%=basePath%>SystemManageAction/wuzidanweiguanli"> <i
									class="icon-double-angle-right"></i> 物资单位管理 </a></li>

						</ul></li>

					<li class=""><a
						href="<%=basePath%>SystemManageAction/gerenjixiaoguanli"> <i
							class=" icon-bar-chart"></i> <span class="menu-text">
								个人绩效管理 </span> </a></li>
					<li class="active"><a
						href="<%=basePath%>SystemManageAction/xitongweihu"> <i
							class="icon-dashboard"></i> <span class="menu-text"> 系统维护
						</span> </a></li>



				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a
							href="<%=basePath%>assets/ADMIN/cangkuguanliyuan.jsp">首页</a></li>
						<li class="active">系统维护</li>
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search" action="#" method="get">
							<a href="<%=basePath%>assets/chaxun.jsp"
								style="margin-right: 5px;">高级查询</a> <span class="input-icon">

								<input type="text" placeholder="Search ..."
								class="nav-search-input" id="nav-search-input"
								autocomplete="off" /> <i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							首页 <small> <i class="icon-double-angle-right"></i> 系统维护 </small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="space-4"></div>
							<table id="grid-table"></table>

							<div id="grid-pager"></div>
							<div class="space-4"></div>
							<div class="modal-footer">
							<!--
								<button class="btn btn-sm btn-primary" id="backup"
									data-dismiss="modal">
									<i class="icon-ok"></i>备份数据库
								</button>
								<button class="btn btn-sm btn-primary">
									<i class="icon-facebook"></i> 恢复数据库
									
								</button>
							  -->
							  <center>
							  <div>
								<a href="<%=basePath%>SystemManageAction/dbBackup"><h3>备份数据库</h3></a>
							  </div>
							  <div>
							  	<form action="<%=basePath%>SystemManageAction/dbRecover" enctype="multipart/form-data">
							  	<div><input type="file" name="url" value="选择要恢复的sql文件"/></div>
							  	<div><input type="submit"  value="确认恢复"/></div>
								</form>
							  </div>
							</center>

								
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->


		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i> </a>
	</div>
	<!-- /.main-container -->


	<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp"%>

	<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
	<script type="text/javascript">
		
				$("#backup").click(function() {
					alert("你好");
					var url=<%=basePath%>+"SystemManageAction/dbBackup";
					$.post(url,null,
					function(data){
						alert("备份成功");
					});
				});
		
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"#";
			var tablename = "单位列表";
			var pageediturl ="<%=basePath%>"+"#";
			//navButtons
			var editbtn=true;
			var addbtn=false;
			var delbtn=true;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['备份编号','备份名称', '备份时间'];
			var pagecolmodel =  [
					{
						name: 'bf_id',
						index: 'unit_id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'bf_name',
						index: 'bf_name',
						
						width: 100,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'bf_date',
						index: 'bf_date',
						width: 150,
					
						editable: false,
						sorttype:"date",
						unformat: pickDate
					}];
					
			
					
		</script>
	<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>




</body>
</html>
