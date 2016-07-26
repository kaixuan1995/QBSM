<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<title>审核物资信息申请——BlueDot物资管理系统</title>

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
						<li >
							<a href="<%=basePath%>StoreManageAction/cangkuguanliyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li class="active open">
							<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao" class="dropdown-toggle">
								<i class="icon-barcode"></i>
								<span class="menu-text"> 物资信息管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao">
										<i class="icon-double-angle-right"></i> 物资信息列表
									</a>
								</li>
								<li class="active">
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
						<li class="">
							<a href="#" class="dropdown-toggle">
								<i class="icon-book"></i>
								<span class="menu-text"> 申请管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
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
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">物资信息管理</a>
							</li>
							<li class="active">审核信息申请</li>
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
								物资信息管理
								<small>
									<i class="icon-double-angle-right" ></i>
									审核信息申请 &nbsp; &nbsp; &nbsp;
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="space-4"></div>

									<div class="modal-footer">
										<button class="btn btn-sm" id="no">
											<i class="icon-ok"></i> 不通过
										</button>

										<button class="btn btn-sm btn-primary" id="yes">
											<i class="icon-ok"></i> 通过
										</button>
									</div>
									<table id="grid-table"></table>

								<div id="grid-pager"></div>
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

			
		</div>
		<!-- /.main-container -->

		<!-- basic scripts -->

		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>

		<script type="text/javascript">
			var selectsid = "#msg";
			$("#no").click(function aaa(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				$.ajax({
					 type:"GET",
		             url: "<%=basePath%>"+"StoreManageAction/saveshenhexinxishenqing",
		             data: {"idss":idss.toString(),"q":"no","time":new Date()},
		             success: function(data){
							jQuery(grid_selector).trigger('reloadGrid');
		           		 }
		         });
			});
			$("#yes").click(function(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				$.ajax({
					 type: "GET",
		             url: "<%=basePath%>"+"StoreManageAction/saveshenhexinxishenqing",
		             data: {"idss":idss.toString(),"q":"yes", "time":new Date()},
		             success: function(data){
			             	alert(data);
							jQuery(grid_selector).trigger('reloadGrid');
		           		 }
		         });
			});
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"StoreManageAction/getshenhexinxishenqing";
			var tablename = "物资列表";
			var pageediturl ="<%=basePath%>"+"StoreManageAction/saveshenhexinxishenqing";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['申请编号', '物资名称','物资品牌','单位','规格','物资类别','货号','申请人','申请时间','状态'];
			var pagecolmodel =  [
				
					{
						name: 'applygoods_id',
						index: 'applygoods_id',
						key: true,
						width: 100,
						editable: false,
					//	hidden:true,
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
						width: 50,
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
						width: 100,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
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
						name: 'user_lianxiren',//申请人
						index: 'user_lianxiren',
						
						width: 80,
						editable: false,
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
					},{
						name: 'apply_state',
						index: 'apply_state',
						width: 100,
						editable: false,
					}
					];
					
			
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	

	</body>

</html>