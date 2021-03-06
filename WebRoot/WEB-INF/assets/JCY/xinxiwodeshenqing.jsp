<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<title>我的申请——BlueDot物资管理系统</title>

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
							<a href="<%=basePath%>StoreManageAction/jianceyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-globe"></i>
								<span class="menu-text"> 物资信息管理 </span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingtianjiawuzixinxi">
										<i class="icon-double-angle-right"></i>
										申请添加物资信息
									</a>
								</li>
								
								<li class="open">
									<a href="<%=basePath%>StoreManageAction/xinxiwodeshenqing">
										<i class="icon-double-angle-right"></i>
										我的申请
									</a>
								</li>
								
							</ul>
						</li>

						<li>
							<a href="#" class="dropdown-toggle">
								<i class=" icon-briefcase "></i>
								<span class="menu-text"> 物资申请</span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqinglingyong">
										<i class="icon-double-angle-right"></i>
										申请领用
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingtuiyong">
										<i class="icon-double-angle-right"></i>
										申请退用
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/wuziwodeshenqing">
										<i class="icon-double-angle-right"></i>
										我的申请
									</a>
								</li>
								
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class=" icon-book "></i>
								<span class="menu-text"> 采购申请</span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>PurchaseManageAction/shenqingcaigou">
										<i class="icon-double-angle-right"></i>
										申请采购
									</a>
								</li>

								<li>
									<a href="<%=basePath%>PurchaseManageAction/caigouwodeshenqing">
										<i class="icon-double-angle-right"></i>
										我的申请
									</a>
								</li>
								
							</ul>
						</li>
						
						<li class="">
							<a href="<%=basePath%>MenberInforAction/danjuchuli">
								<i class=" icon-list"></i>
								<span class="menu-text"> 单据处理 </span>
							</a>
						</li>
						<li class="">
							<a href="<%=basePath%>MenberInforAction/gerenjixiao">
								<i class=" icon-bar-chart"></i>
								<span class="menu-text"> 个人绩效 </span>
							</a>
						</li>

						
					</ul><!-- /.nav-list -->


					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed');}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed');}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">物资信息管理 </a>
							</li>
							<li class="active">我的申请</li>
						</ul><!-- .breadcrumb -->

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
								物资信息管理 
								<small>
									<i class="icon-double-angle-right"></i>
									我的申请                                                                                                                                                                                                             
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<table id="grid-table"></table>

								<div id="grid-pager"></div>
								
								<table id="grid-tableo"></table>

								<div id="grid-pagero"></div>
								
								<script type="text/javascript">
									var $path_base = "/";//this will be used in gritter alerts containing images
								</script>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

		</div>
			<!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<!-- /.main-container -->

		<!-- basic scripts -->

		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>

		<script type="text/javascript">
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"PurchaseManageAction/getxinxiwodeshenqing";
			var tablename = "物资列表,审核通过";
			var pageediturl ="<%=basePath%>"+"PurchaseManageAction/savexinxiwodeshenqing";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['申请编号', '物资名称','物资品牌','单位','规格','物资类别','货号','申请时间','状态'];//
			var pagecolmodel =  [
				
					{
						name: 'applygoods_id',
						index: 'applygoods_id',
						key: true,
						width: 100,
						editable: false,
						hidden:true,
					}, {
						name: 'goods_name',
						index: 'goods_name',
						width: 150,
						editable: true,
					}
					,{
						name: 'goods_brand',
						index: 'goods_brand',
						width: 150,
						editable: true,
					},{
						name: 'goods_unit',
						index: 'goods_unit',
						width: 50,
						editable: true,
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 100,
						editable: true,
					},{
						name: 'type_name',
						index: 'type_name',
						width: 80,
						editable: false,
					},				
					{
						name: 'goods_cas',
						index: 'goods_cas',
						width: 50,
						editable: true,
					},{
						name: 'apply_time',
						index: 'apply_time',
						width: 100,
						editable: false,
					},
				
				{
					name : 'apply_state',
					index : 'apply_state',
					width : 70,
					editable : true,
				},
				 ];
				 
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";		
			var pageurlo ="<%=basePath%>"+"PurchaseManageAction/getxinxiwodeshenqing2";
			var tablenameo = "物资列表(未审核和审核失败)";
			var pageediturlo ="<%=basePath%>"+"PurchaseManageAction/savexinxiwodeshenqing2";
			//navButtons
			var editobtn=false;
			var addobtn=false;
			var delobtn=true;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['申请编号', '物资名称','物资品牌','单位','规格','物资类别','货号','申请时间','状态'];//
			var pagecolmodelo =  [
				
					{
						name: 'applygoods_id',
						index: 'applygoods_id',
						key: true,
						width: 100,
						editable: false,
						hidden:true,
					}, {
						name: 'goods_name',
						index: 'goods_name',
						width: 150,
						editable: true,
					}
					,{
						name: 'goods_brand',
						index: 'goods_brand',
						width: 150,
						editable: true,
					},{
						name: 'goods_unit',
						index: 'goods_unit',
						width: 50,
						editable: true,
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 100,
						editable: true,
					},{
						name: 'type_name',
						index: 'type_name',
						width: 80,
						editable: false,
					},				
					{
						name: 'goods_cas',
						index: 'goods_cas',
						width: 50,
						editable: true,
					},{
						name: 'apply_time',
						index: 'apply_time',
						width: 100,
						editable: false,
					},
				
				{
					name : 'apply_state',
					index : 'apply_state',
					width : 70,
					editable : true,
				},
				 ];
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridTwoJS.js"></script>	
	</body>
</html>