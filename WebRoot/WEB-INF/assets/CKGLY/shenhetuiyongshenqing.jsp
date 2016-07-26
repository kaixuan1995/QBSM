<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>申请退用——BlueDot物资管理系统</title>

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
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					
					<ul class="nav nav-list">
						<li >
							<a href="<%=basePath%>StoreManageAction/cangkuguanliyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li>
							<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao" class="dropdown-toggle">
								<i class="icon-barcode"></i>
								<span class="menu-text"> 物资信息管理</span>

								<b class="<%=basePath%>StoreManageAction/arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao">
										<i class="icon-double-angle-right"></i> 物资信息列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhexinxishenqing">
										<i class="icon-double-angle-right"></i> 审核信息申请
									</a>
								</li>
							
								
							</ul>
						</li>
						<li class="">
							<a href="<%=basePath%>StoreManageAction/gongyingshangxinxiliebiao">
								<i class="icon-globe"></i>
								<span class="menu-text"> 供应商信息列表 </span>
							</a>
						</li>
						
						
						<li>
							<a href="<%=basePath%>StoreManageAction/wuziliebiao" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 物资管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/wuziliebiao">
										<i class="icon-double-angle-right"></i> 物资列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/rukuguanli">
										<i class="icon-double-angle-right"></i> 入库管理
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/chukuguanli">
										<i class="icon-double-angle-right"></i> 出库管理
									</a>
								</li>
					
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingtuihuo">
										<i class="icon-double-angle-right"></i> 申请退货
									</a>
								</li>
								<%-- <li>
									<a href="<%=basePath%>StoreManageAction/tuihuowodeshenqing">
										<i class="icon-double-angle-right"></i> 我的申请
									</a>
								</li> --%>
							</ul>
						</li>
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-book"></i>
								<span class="menu-text"> 申请管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="active">
									<a href="<%=basePath%>StoreManageAction/shenhetuiyongshenqing">
										<i class="icon-double-angle-right"></i> 审核退用申请
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhecaigoushenqing">
										<i class="icon-double-angle-right"></i> 审核采购申请
									</a>
								</li>

								<li class="">
									<a href="<%=basePath%>StoreManageAction/suoyoushenqing">
										<i class="icon-double-angle-right"></i> 所有申请
									</a>
								</li>

								

							</ul>
						</li>
					<li class="">
							<a href="#" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 仓库调度管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingcangkuwuzidiaodu">
										<i class="icon-double-angle-right"></i> 申请仓库物资调度
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhecangkuwuzidiaodu">
										<i class="icon-double-angle-right"></i> 审核仓库物资调度
									</a>
								</li>

								<li class="">
									<a href="<%=basePath%>StoreManageAction/diaoduwode">
										<i class="icon-double-angle-right"></i> 我的仓库物资调度
									</a>
								</li>

								

							</ul>
						</li>
						<li class="">
							<a href="<%=basePath%>StoreManageAction/danjuchuli">
								<i class="icon-list"></i>
								<span class="menu-text"> 单据处理 </span>
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
								<a href="<%=basePath%>StoreManageAction/cangkuguanliyuan">首页</a>
							</li>

							<li>
								<a href="#">申请管理</a>
							</li>
							<li class="active">审核退用申请</li>
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
								申请管理
								<small>
									<i class="icon-double-angle-right"></i>
									审核退用申请
								</small>
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div id="">
									<table id="grid-table"></table>

									<div id="grid-pager"></div>
								</div>
								<div class="modal-footer">
										<button class="btn btn-sm" id="no">
											<i class="icon-ok"></i> 不通过
										</button>

										<button class="btn btn-sm btn-primary" id="yes">
											<i class="icon-ok"></i> 通过
										</button>
									</div>
								<div class="space-4"></div>
								<div id="">
									<table id="grid-tableo"></table>

									<div id="grid-pagero"></div>
								</div>

								<div class="space-4"></div>

								<script type="text/javascript">
									var $path_base = "/"; //this will be used in gritter alerts containing images
								</script>

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
	</div>
		
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
	

	
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script type="text/javascript">
		
				$("#no").click(function aaa(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				$.ajax({
					 type:"GET",
		             url: "<%=basePath%>"+"StoreManageAction/getshenhexinxishenqing2",
		             data: {"idss":idss.toString(),"q":"no","time":new Date()},
		             success: function(data){
			             	alert(data);
					jQuery(grid_selector).trigger('reloadGrid');
		           		 }
		         });
			});
			$("#yes").click(function(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				$.ajax({
					 type: "GET",
		             url: "<%=basePath%>"+"StoreManageAction/getshenhexinxishenqing2",
		             data: {"idss":idss.toString(),"q":"yes", "time":new Date()},
		             success: function(data){
			             	alert(data);
					jQuery(grid_selector).trigger('reloadGrid');
		           		 }
		         });
			});
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"StoreManageAction/getshenhetuiyongshenqing";
			var tablename = "申请列表";
			var pageediturl ="<%=basePath%>"+"StoreManageAction/saveshenhetuiyongshenqing";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['申请单号','申请人','申请时间','退用理由','是否加急'];
			var pagecolmodel =  [
					
					{
						name: 'apply_id',
						index: 'apply_id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'user_lianxiren',
						index: 'user_lianxiren',
						width: 70,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},
					{
						name: 'apply_time',
						index: 'apply_time',
						width: 100,
						editable: false,
						sorttype:"date",
						unformat: pickDate
					},
					{
						name: 'apply_remark',
						index: 'apply_remark',
						width: 100,
						editable: false,
						sorttype:"date",
						unformat: pickDate
					},
					{
						name: 'apply_urgent',
						index: 'apply_urgent',
						width: 70,
						editable: true,
						edittype: "select",
					} 
					];
					
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";		
			var pageurlo ="<%=basePath%>"+"StoreManageAction/getrukuguanli2";
			var tablenameo = "详细列表";
			var pageediturlo ="<%=basePath%>"+"StoreManageAction/saverukuguanli2";
			//navButtons
			var editobtn=false;
			var addobtn=false;
			var delobtn=false;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['物资编号', '物资名称','单位','规格','物资类别','物资品牌','货号','供应商','有效期','申请数量'];//,'最低库存','最高库存'
			var pagecolmodelo =  [
				
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
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'type_name',//类别名称
						index: 'type_name',
						width: 80,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'goods_brand',
						index: 'goods_brand',
						width: 50,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'goods_cas',
						index: 'goods_cas',
						width: 80,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'user_lianxiren',//供应商
						index: 'user_lianxiren',
						width: 80,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'barcode_validity',
						index: 'barcode_validity',
						width: 100,
						editable: false,
						sorttype:"date",
						unformat: pickDate
					},
					{
						name: 'apply_goods_count',
						index: 'apply_goods_count',						
						width: 80,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "5"
						}
					}
					];
			
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridTwoJS.js"></script>
</body>
</html>
