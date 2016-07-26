<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
		<title>条形码生成——BlueDot物资管理系统</title>

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
							<a href="<%=basePath%>MaterialIdentityAction/gongyingshang">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>
						<li>
						
						<li class="">
							<a href="<%=basePath%>MaterialIdentityAction/tiaoxingmazhuanhuan">
								<i class="icon-list"></i>
								<span class="menu-text">条形码转换</span>
							</a>
						</li>
						
						
						<li class="active">
							<a href="<%=basePath%>MaterialIdentityAction/tiaoxingmashengcheng">
								<i class=" icon-bar-chart"></i>
								<span class="menu-text"> 条形码生成 </span>
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
								<a href="<%=basePath%>MaterialIdentityAction/gongyingshang">首页</a>
							</li>
							<li class="active"> 条形码生成 </li>
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
								首页
								<small>
									<i class="icon-double-angle-right"></i>
									  条形码生成 
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>
								<div class="space-4"></div>
								<div class="modal-footer">
									

									
								</div>
								<div class="space-4"></div>
								<table id="grid-tableo"></table>

								<div id="grid-pagero"></div>
								
								<div class="space-4"></div>
								<div class="modal-footer">
									
									<form action="${pageContext.request.contextPath }/MaterialIdentityAction/download">
										<input type="hidden" name="barcode_id" value="">
											<button class="btn btn-sm btn-primary" id="ms12">
												<i class="icon-ok"></i> 下载条形码
											</button>
									</form>
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
			var pageurl ="<%=basePath%>"+"MaterialIdentityAction/gettiaoxingmashengcheng";
			var tablename = "物资列表";
			var pageediturl ="<%=basePath%>"+"MaterialIdentityAction/savetiaoxingmashengcheng";
			
			//navButtons
			var editbtn=true;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['物资编号', '物资名称','物资品牌','单位','规格','单价','有效期'];//
			var pagecolmodel =  [
				
					{
						name: 'goods_id',
						index: 'goods_id',
						key: true,
						width: 70,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'goods_name',
						index: 'goods_name',
						width: 70,
						editable: false,
						editoptions: {
							size: "50",
							maxlength: "50"
						}
					}
					,{
						name: 'goods_brand',
						index: 'goods_brand',
						width: 70,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "20"
						}
					},{
						name: 'goods_unit',
						index: 'goods_unit',
						width: 70,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'goods_standard',
						index: 'goods_standard',
						width: 70,
						editable: false,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					},{
						name: 'barcode_price',
						index: 'barcode_price',						
						width: 80,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					},				
					{
						name: 'barcode_validity',
						index: 'barcode_validity',
						width: 150,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					}
					];
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";		
			var pageurlo ="<%=basePath%>"+"MaterialIdentityAction/findBar";
			var tablenameo = "条形码列表";
			var pageediturlo ="<%=basePath%>"+"#";
			//navButtons
			var editobtn=false;
			var addobtn=false;
			var delobtn=false;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['条形码编号', '对应物资Id','有效期','单价'];//
			var pagecolmodelo =  [
				
					{
						name: 'barcode_id',
						index: 'barcode_id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'goods_id_fk',
						index: 'goods_id_fk',
						width: 150,
						editable: true,
						editoptions: {
							size: "50",
							maxlength: "50"
						}
					}
					,{
						name: 'barcode_validity',
						index: 'barcode_validity',
						width: 150,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "20"
						}
					},{
						name: 'barcode_price',
						index: 'barcode_price',
						width: 50,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					}
					];
			
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridTwoJS.js"></script>	
		
		<script type="text/javascript" >
		jQuery('#ms12').click(function() {
		    var id;
		    id = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
		    document.getElementById('barcode_id').value=id;
		   alert(id);
		   // var url = ajaxurl;
		   // var args={"id":id,"time":new Date()};
		    //$.post(url,args,function(data){
			//	if(data){
			//		alert("操作成功");
			//	}else{
			//		alert("操作失败");
			//	}
			//});
		  });
		</script>
	</body>

</html>