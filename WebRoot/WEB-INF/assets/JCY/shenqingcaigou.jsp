<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<title>申请采购——BlueDot物资管理系统</title>

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
						<li class="active">
							<a href="<%=basePath%>StoreManageAction/jianceyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>
						<li>
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
								
								<li>
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
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class=" icon-book "></i>
								<span class="menu-text"> 采购申请</span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="open">
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
								<a href="#">采购申请</a>
							</li>
							<li class="active">申请采购</li>
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
								采购申请
								<small>
									<i class="icon-double-angle-right"></i>
									申请采购
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>
								
								<div class="modal-footer">
									<button class="btn btn-sm" data-dismiss="modal">
										<i class="icon-remove"></i> 取消
									</button>

									<button class="btn btn-sm btn-primary"  id="addwuzhi">
										<i class="icon-ok"></i> 添加到已选物资信息列表
									</button>
								</div>
								<table id="grid-tableo"></table>
								<div id="grid-pagero"></div>
								<div class="space-4"></div>
									<div class="row">
							<div class="col-xs-12">
								<div class="space-4"></div>
									<div class="modal-footer">
												<input type="checkbox" class="ace col-xs-9 col-sm-3"
												 name="apply_urgent" id="apply_urgent"/>
												<span class="lbl"> 加急</span>
										<button class="btn btn-sm btn-primary" id="yes">
											<i class="icon-ok"></i> 提交
										</button>
									</div>
								</div>
							</div>
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
		$("#addwuzhi").click(function(){
		        var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
		        if(""==idss){
					alert("请选择要提交的数据");
					return false;
				}
				$.ajax({
					 type:"GET",
		             url: "<%=basePath%>"+"StoreManageAction/addshenqingcangkuwuzidiaodu",
		             data: {"idss":idss.toString(),"time":new Date()},
		             success: function(data){
			             	alert(data);
							jQuery(grid_selectoro).trigger('reloadGrid');
		           		 }
		         });      
			});
			$("#yes").click(function(){
				var idss = jQuery(grid_selectoro).jqGrid('getGridParam', 'selarrrow');
				var apply_urgent = $("#apply_urgent").is(':checked');
				if(""==idss){
					alert("请选择要提交的数据");
					return false;
				}
				$.ajax({
					 type:"GET",
		             url: "<%=basePath%>"+"StoreManageAction/tongguoshenqingcaigou",
		             data: {"idss":idss.toString(),"apply_urgent":apply_urgent,"time":new Date()},
		             success: function(data){
			             	alert(data);
							jQuery(grid_selectoro).trigger('reloadGrid');
		           		 }
		         }); 
			});
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"PurchaseManageAction/getshenqingcaigou";
			var tablename = "物资列表";
			var pageediturl ="<%=basePath%>"+"PurchaseManageAction/saveshenqingcaigou";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['物资编号', '物资名称','单位','规格','物资类别'];//,'物资品牌','货号','供应商','有效期','最低库存','最高库存'
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
						editable: true,
						editoptions: {
							size: "50",
							maxlength: "50"
						}
					}
					,{
						name: 'goods_unit',
						index: 'goods_unit',
						width: 50,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 50,
						editable: true,
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
					}
					
					];
			
					
 			//主表
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";
		
			var pageurlo ="<%=basePath%>"+"StoreManageAction/getshenqingcangkuwuzidiaodu2";
			var tablenameo = "已选物资列表";
			var pageediturlo ="<%=basePath%>"+"StoreManageAction/saveshenqingcangkuwuzidiaodu";
			//navButtons
			var editobtn=true;
			var addobtn=false;
			var delobtn=true;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['shopping_id','物资编号', '物资名称','单位','规格','物资类别','采购数量'];//,'物资品牌','货号','供应商','有效期','最低库存','最高库存'
			var pagecolmodelo =  [
					{
						name: 'shopping_id',
						index: 'shopping_id',
						key: true,
						width: 100,
						hidden:true,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},
					{
						name: 'goods_id_fk',
						index: 'goods_id_fk',
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
						width: 50,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 50,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'type_name',
						index: 'type_name',
						width: 80,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},
					{
						name: 'apply_goods_count',
						index: 'apply_goods_count',						
						width: 80,
						editable: true,
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