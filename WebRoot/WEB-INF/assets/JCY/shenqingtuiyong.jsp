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
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-globe"></i>
								<span class="menu-text"> 物资信息管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingtianjiawuzixinxi">
										<i class="icon-double-angle-right"></i> 申请添加物资信息
									</a>
								</li>

								<li>
									<a href="<%=basePath%>StoreManageAction/xinxiwodeshenqing">
										<i class="icon-double-angle-right"></i> 我的申请
									</a>
								</li>

							</ul>
						</li>

						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class=" icon-briefcase "></i>
								<span class="menu-text"> 物资申请</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="">
									<a href="<%=basePath%>StoreManageAction/shenqinglingyong">
										<i class="icon-double-angle-right"></i> 申请领用
									</a>
								</li>
								<li class="open">
									<a href="<%=basePath%>StoreManageAction/shenqingtuiyong">
										<i class="icon-double-angle-right"></i> 申请退用
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/wuziwodeshenqing">
										<i class="icon-double-angle-right"></i> 我的申请
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
										<i class="icon-double-angle-right"></i> 申请采购
									</a>
								</li>

								<li>
									<a href="<%=basePath%>PurchaseManageAction/caigouwodeshenqing">
										<i class="icon-double-angle-right"></i> 我的申请
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

					</ul>
					<!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try {
							ace.settings.check('sidebar', 'collapsed');
						} catch (e) {}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try {
								ace.settings.check('breadcrumbs', 'fixed');
							} catch (e) {}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">物资申请</a>
							</li>
							<li class="active">申请退还</li>
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
								物资申请
								<small>
									<i class="icon-double-angle-right"></i>
									申请退用
								</small>
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<table id="grid-table"></table>

								<div id="grid-pager"></div>
								<div class="space-4"></div>
								<div class="modal-footer">
									<button class="btn btn-sm" data-dismiss="modal">
										<i class="icon-remove"></i> 取消
									</button>

									<button class="btn btn-sm btn-primary"  id="addwuzhi">
										<i class="icon-ok"></i> 添加到已选物资列表
									</button>
								</div>
								<table id="grid-tableo"></table>
								<div id="grid-pagero"></div>
								<div class="space-4"></div>
								<div id="">
									<div id="">
										<input type="text" value="" name="note" placeholder="请填写理由" id="note"/>
									</div>

									<div class="space-4"></div>
									<input multiple="" type="file" id="id-input-file-3" name="file"/>
<!-- 									<label>
										<input type="checkbox" name="file-format" id="id-file-format" class="ace" />
										<span class="lbl"> 只上传图片</span>
									</label>
 -->									<div class="modal-footer">

										<button class="btn btn-sm" data-dismiss="modal">
											<i class="icon-remove"></i> 取消
										</button>

										<button class="btn btn-sm btn-primary"  id="yes">
											<i class="icon-ok"></i> 提交
										</button>
									</div>
								</div>
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
			<!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<!-- /.main-container -->

		<!-- basic scripts -->

		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
		$("#addwuzhi").click(function(){
		        var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
		        if(idss==null){
					alert("请选择物资在提交");
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
				var note = $("#note").val();
				if(idss==null){
					alert("请选择物资在提交");
					return false;
				}
				var uploadUrl = encodeURI(encodeURI("<%=basePath%>" + 
					"StoreManageAction/tongguoshenqingtuiyong?note="+note+"&idss="+idss));
				$.ajaxFileUpload({
					 url: uploadUrl,
					 type: 'POST',
					 secureuri:false, 
					 fileElementId:'id-input-file-3',
					 dataType: 'text',
		             success: function(data , status){
			             	alert(data);
							jQuery(grid_selectoro).trigger('reloadGrid');
		           		 },
		           	 error: function (data, status, e){
                        alert(e);
                    }
		         }); 
			});
			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"StoreManageAction/getshenqingtuiyong";
			var tablename = "已领用的物资列表";
			var pageediturl ="<%=basePath%>"+"StoreManageAction/saveshenqingtuiyong";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['物资编号', '物资名称','物资品牌','单位','规格','物资类别','货号','联系人','有效期','领用数量'];//,'最低库存','最高库存'
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
						name: 'goods_cas',
						index: 'goods_cas',
						width: 50,
						editable: true,
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
					},				
					{
						name: 'barcode_validity',
						index: 'barcode_validity',
						width: 100,
					},{
						name: 'apply_goods_count',//领用数量
						index: 'apply_goods_count',						
						width: 80,
					}
					];
					
 			//主表
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";
		
			var pageurlo ="<%=basePath%>"+"StoreManageAction/getshenqingcangkuwuzidiaodu2";
			var tablenameo =  "已选物资列表";
			var pageediturlo ="<%=basePath%>"+"StoreManageAction/saveshenqingcangkuwuzidiaodu";
			//navButtons
			var editobtn=true;
			var addobtn=false;
			var delobtn=true;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['shopping_id','物资编号', '物资名称','单位','规格','物资类别','退用数量'];//,'物资品牌','货号','供应商','有效期','最低库存','最高库存'
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
		<script type="text/javascript">
			var id_input_file = '#id-input-file-3';

		</script>
		<script src="<%=basePath%>assets/js/utiljs/file-input.js" ></script>
	</body>

</html>