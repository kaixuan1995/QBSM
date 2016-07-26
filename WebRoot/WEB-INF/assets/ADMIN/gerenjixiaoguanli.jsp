<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		
		<title>个人绩效——BlueDot物资管理系统</title>

		<meta name="keywords" content="BlueDot物资管理系统" />
		<meta name="description" content="BlueDot物资管理系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp" %>
		<%@include file="/assets/utiljsp/csslink/dccsslink.jsp" %> 
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

					
					<!-- #sidebar-shortcuts -->

					
					<!-- #sidebar-shortcuts -->
					<ul class="nav nav-list">
						<li class="">
							<a href="<%=basePath%>SystemManageAction/admin">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li>
							<a href="<%=basePath%>SystemManageAction/yonghuxinxiguanli" class="dropdown-toggle">
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
							<a href="<%=basePath%>SystemManageAction/wuziliebiao" class="dropdown-toggle">
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
						
						<li class="active">
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
								<a href="<%=basePath%>SystemManageAction/admin">首页</a>
							</li>
							<li class="active">个人绩效</li>
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
								个人绩效
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">

							<div class="widget-box">
								<div class="widget-header">
									<h4>上传标准文件设置误差范围</h4>

									<span class="widget-toolbar">
										<a href="#" data-action="collapse">
											<i class="icon-chevron-up"></i>
										</a>
										<!--
										<a href="#" data-action="close">
											<i class="icon-remove"></i>
										</a>
										-->
										
									</span>
								</div>

								<div class="widget-body">
									<div class="widget-main">
									
										<div>
									
											<input type="text" class="input-mini" id="spinner3" /><span style="font-size: 35px;" >%</span>
										</div>
										<span class="input-group-btn width-auto">												
											<input class="form-control form-control " type="text"
												value="01/01/2016 - 12/31/2022" name="date-range-picker" id="id-date-range-picker-1" placeholder="选择日期"/>	
										</span>
										
										<span class="input-group-btn">
											<button type="button" id="chaxun" class="btn btn-purple btn-sm">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										</span>			
										<div class="space-4"></div>
										<input multiple="" type="file" id="id-input-file-3" />
										
									</div>
								</div>
							</div>
							<div>
								<table id="grid-table"></table>

								<div id="grid-pager"></div>
								<div class="space-4"></div>
								<table id="grid-tableo"></table>

								<div id="grid-pagero"></div>
								<div class="space-4"></div>
							</div>
								
								
								<div class="modal-footer">
										<button class="btn btn-sm btn-primary" data-dismiss="modal">
											<i class="icon-list"></i> 显示未合格人员列表
										</button>

										<button class="btn btn-sm btn-primary">
											<i class="icon-ok"></i> 导出文件
										</button>
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

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<!-- /.main-container -->
		
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		<script src="<%=basePath%>assets/js/fuelux/fuelux.spinner.min.js"></script>
		<%@include file="/assets/utiljsp/jsinclude/dcjs.jsp" %>

		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script type="text/javascript">
			$('#spinner3').ace_spinner({value:0,min:0,max:100,step:5, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
			$("#chaxun").click(function aaa(){
				alert("该按钮事件暂未开放");
				return false;
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				var data2 = $("#id-date-range-picker-1").val();
				var percent = $("#spinner3").val();
				if(idss==null||data2==null){
					alert("请选择要提交的数据(选中员工编号和前后日期)");
					return false;
				}
				$.ajax({
					 type:"GET",
		             url: "<%=basePath%>"+"MenberInforAction/#",
		             data: {"time":new Date()},
		             success: function(data){
		            
		           		 }
		         });
			});
			var data2 = $("#id-date-range-picker-1").val();
			var percent = $("#spinner3").val();
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"MenberInforAction/getgerenjixiaoguanli";
			var tablename = "人员列表";
			var pageediturl ="<%=basePath%>"+"MenberInforAction/savegerenjixiaoguanli";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['员工工号', '员工姓名','所属科室','评定结果'];
			var pagecolmodel =  [
					{
						name: 'user_id',
						index: 'user_id',
						key: true,
						width: 100,
						editable: false,
					}, {
						name: 'user_lianxiren',
						index: 'user_lianxiren',
						width: 200,
					},{
						name: 'office_name',//科室姓名
						index: 'office_name',
						
						width: 70
					},{
						name: 'performance_result',
						index: 'performance_result',
						width: 200,
					} 
					];
					
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";		
			var pageurlo ="<%=basePath%>"+"MenberInforAction/getgerenjixiaoguanli2";
			var tablenameo = "详细列表";
			var pageediturlo ="<%=basePath%>"+"MenberInforAction/getgerenjixiaoguanli2";
			//navButtons
			var editobtn=false;
			var addobtn=false;
			var delobtn=false;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['物资编号', '物资名称','申请数量','领用数量','实际生产','是否符合'];//,
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
						width: 100,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},{
						name: 'apply_goods_count',
						index: 'apply_goods_count',
						width: 60,
						sorttype: "int",
						editable: true
					}, {
						name: 'voucher_goods_count',
						index: 'voucher_goods_count',
						width: 60,
						sorttype: "double",
						editable: true
					}, {
						name: 'infactOutput',
						index: 'infactOutput',
						width: 60,
						sorttype: "double",
						editable: true
					}, 
					{
						name: 'isRight',
						index: 'isRight',
						width: 80,
						editable: true,
						edittype: "select",
						editoptions: {
							value: "0:Y;1:N"
						}
					} 
					//, 
					//{
					//	name: 'storehouse_id',
					//	index: 'storehouse_id',
						
					//	width: 100,
					//	editable: false,
					//	editoptions: {
					//		size: "20",
					//		maxlength: "30"
					//	}
					//}
					];
			
		
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/gerenjixiaoguanlijqGridTwoJS.js"></script>

		<script type="text/javascript">
			var id_input_file = '#id-input-file-3';

		</script>
		<script src="<%=basePath%>assets/js/utiljs/file-input.js" ></script>
		
	</body>

</html>