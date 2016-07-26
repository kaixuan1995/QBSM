<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>BlueDot物资管理系统</title>

		<meta name="keywords" content="BlueDot物资管理系统" />
		<meta name="description" content="BlueDot物资管理系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp" %>

	</head>

	<body>
	
		<%@include file="/assets/utiljsp/jsppart/navjsp.jsp"%>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed');}catch(e){}
					</script>

					
					<ul class="nav nav-list">
						<li class="active">
							<a href="<%=basePath%>SystemManageAction/admin">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li>
							<a href="<%=basePath%>assets/ADMIN/yonghuxinxiguanli.jsp#" class="dropdown-toggle">
								<i class="icon-barcode"></i>
								<span class="menu-text"> 用户信息管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
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
							<a href="<%=basePath%>assets/ADMIN/wuziliebiao.jsp#" class="dropdown-toggle">
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

					</ul>
					<!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
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
							<li>
								<i class="icon-home home-icon"></i>
								<a href="<%=basePath%>SystemManageAction/admin">首页</a>
							</li>

							
							<li class="active">欢迎</li>
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
								<a href="<%=basePath%>SystemManageAction/admin">首页</a>
								<small>
									<i class="icon-double-angle-right"></i>
									欢迎
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

	
		
		<%@include file="/assets/utiljsp/jsinclude/ojs.jsp" %>
		
	

	

		
	</body>
</html>
