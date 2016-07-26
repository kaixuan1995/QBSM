<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8" />
		<title>物资列表——BlueDot物资管理系统</title>

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
					ace.settings.check('main-container', 'fixed')
				} catch (e) {}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try {
							ace.settings.check('sidebar', 'fixed')
						} catch (e) {}
					</script>

					
					<ul class="nav nav-list">
						<li class="">
							<a href="<%=basePath%>SystemManageAction/admin">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li>
							<a href="<%=basePath%>assets/ADMIN/yonghuxinxiguanli.jsp" class="dropdown-toggle">
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
					
						
						<li class="active open">
							<a href="<%=basePath%>assets/ADMIN/wuziliebiao.jsp" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 物资管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="open">
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
							ace.settings.check('sidebar', 'collapsed')
						} catch (e) {}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try {
								ace.settings.check('breadcrumbs', 'fixed')
							} catch (e) {}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">物资管理</a>
							</li>
							<li class="active">物资列表</li>
						</ul>
						<!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search" action="#" method="get">
								<a href="<%=basePath%>assets/chaxun.jsp" style="margin-right: 5px;">高级查询</a>
								<span class="input-icon">
									
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div>
						<!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								物资管理
								<small>
									<i class="icon-double-angle-right"></i>
									物资列表
								</small>
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

							</div>
						</div>
					</div>
				</div>

		</div>
			<!-- /.main-container-inner -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script type="text/javascript">
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"StoreManageAction/getwuziliebiao";
			var tablename = "物资列表";
			var pageediturl ="<%=basePath%>"+"StoreManageAction/savewuziliebiao";
			//navButtons
			var editbtn=true;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['物资编号', '物资名称','物资品牌','单位','规格','物资类别','货号','供应商联系人','联系电话','有效期','现有库存','最低库存','最高库存'];//
			var pagecolmodel =  [
				
					{
						name: 'goods_id',
						index: 'goods_id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'goods_name',
						index: 'goods_name',
						width: 150,
						editable: false,
						editoptions: {
							size: "50",
							maxlength: "50"
						}
					}
					,{
						name: 'goods_brand',
						index: 'goods_brand',
						width: 150,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "20"
						}
					},{
						name: 'goods_unit',
						index: 'goods_unit',
						
						width: 50,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 150,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "20"
						}
					},{
						name: 'type_name',
						index: 'type_name',
						key: true,
						width: 80,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},				
					{
						name: 'goods_cas',
						index: 'goods_cas',
						width: 50,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'user_lianxiren',//供应商
						index: 'user_lianxiren',
						
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'user_phone',//供应商
						index: 'user_phone',
						
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},				
					{
						name: 'barcode_validity',
						index: 'barcode_validity',
						width: 100,
						editable: false,
					//	sorttype:"date",
						unformat: pickDate
					},{
						name: 'goods_number',//现有库存
						index: 'goods_number',						
						width: 80,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "5"
						}
					},
					{
						name: 'goods_min',
						index: 'goods_min',
						width: 80,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "5"
						}
					},{
						name: 'goods_max',
						index: 'goods_max',						
						width: 80,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "5"
						}
					}
					];
					
			
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	

	</body>

</html>