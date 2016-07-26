<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>供应商信息列表——BlueDot物资管理系统</title>

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
							<a href="<%=basePath%>PurchaseManageAction/caigouyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>


						<li>
							<a href="#" class="dropdown-toggle">
								<i class=" icon-briefcase "></i>
								<span class="menu-text">物资信息管理</span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>PurchaseManageAction/wuzixinxiliebiao">
										<i class="icon-double-angle-right"></i>
										物资信息列表
									</a>
								</li>

								<li>
									<a href="<%=basePath%>PurchaseManageAction/shenqingtianjiawuzixinxi">
										<i class="icon-double-angle-right"></i>
										申请添加物资信息
									</a>
								</li>
								<li>
									<a href="<%=basePath%>PurchaseManageAction/xinxiwodeshenqing">
										<i class="icon-double-angle-right"></i>
										我的申请
									</a>
								</li>
							</ul>
						</li>
						<li class="">
							<a href="<%=basePath%>PurchaseManageAction/gongyingshangxinxiliebiao">
								<i class="icon-globe"></i>
								<span class="menu-text"> 供应商信息列表 </span>
							</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class=" icon-book"></i>
								<span class="menu-text">采购管理</span>
								
								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>PurchaseManageAction/shenheshenqing">
										<i class="icon-double-angle-right"></i>
										审核申请
									</a>
								</li>
								<li>
									<a href="<%=basePath%>PurchaseManageAction/suoyoushenqing">
										<i class="icon-double-angle-right"></i>
										所有申请
									</a>
								</li>
								<li>
									<a href="<%=basePath%>PurchaseManageAction/caigoujihua">
										<i class="icon-double-angle-right"></i>
										采购计划
									</a>
								</li>
								
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class=" icon-bar-chart"></i>
								<span class="menu-text"> 到货管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								
								<li>
									<a href="<%=basePath%>PurchaseManageAction/yanshouruku">
										<i class="icon-double-angle-right"></i>
										验收入库
									</a>
								</li>
							</ul>
						</li>
						<li class="">
							<a href="<%=basePath%>PurchaseManageAction/danjuchuli">
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

							
							<li class="active">供应商信息列表</li>
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
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								供应商信息列表
								
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>

								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div>
				

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
			
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>

		<script type="text/javascript">
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
														//做好了
			var pageurl ="<%=basePath%>"+"PurchaseManageAction/getgongyingshangxinxiliebiao";
			var tablename = "供应商信息列表";
			var pageediturl ="<%=basePath%>"+"#";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = [ '供应商编号','供应商名称','联系人', '手机号','传真','电子邮箱','供应类别','评价'];//
			var pagecolmodel =  [
		
				{name:'user_id',index:'user_id', key:true,width:70,editable: false,hidden:true,editoptions:{size:"20",maxlength:"30"}},
				{name:'user_name',index:'user_name', width:70,editable: false,editoptions:{size:"20",maxlength:"30"}},
				{name:'user_lianxiren',index:'user_lianxiren', width:70,editable: false,editoptions:{size:"20",maxlength:"30"}},
				{name:'user_phone',index:'user_phone', width:100,editable: false,editoptions:{size:"20",maxlength:"30"}},
				{name:'user_fax',index:'user_fax', width:100,editable: false,editoptions:{size:"20",maxlength:"30"}},
				{name:'user_email',index:'user_email', width:100,editable: false,editoptions:{size:"20",maxlength:"30"}},
				{name:'type_name',index:'yx', width:150,editable: false,editoptions:{size:"20",maxlength:"30"}},//类别
				{name:'user_evaluate',index:'user_evaluate', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}}//供应商评价 

					];
		
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	
		
	

	

		
</body>
</html>
