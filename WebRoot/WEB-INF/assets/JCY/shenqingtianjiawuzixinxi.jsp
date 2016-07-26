<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>申请添加物资信息—— BlueDot 物资管理系统</title>

		<meta name="keywords" content="BlueDot物资管理系统" />
		<meta name="description" content="BlueDot物资管理系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
		<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp" %>

		<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />
	</head>

	<body>
	
		<%@include file="/assets/utiljsp/jsppart/navjsp.jsp"%>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
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
								<li class="open"> 
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
									<a href="<%=basePath%>StoreManageAction/shenqingtuihuan">
										<i class="icon-double-angle-right"></i>
										申请退还
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
								<a href="<%=basePath%>StoreManageAction/jianceyuan">首页</a>
							</li>

							<li>
								<a href="<%=basePath%>StoreManageAction/wuziliebiao">物资信息管理 </a>
							</li>
							<li class="active">申请添加物资信息</li>
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
								物资信息管理 
								<small>
									<i class="icon-double-angle-right"></i>
									申请添加物资信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">${requestScope.msg }</font>
								</small>
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

	<form action="<%=basePath%>StoreManageAction/saveshenqingtianjiawuzixinxi" method="post"
		class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-1" > 物资名称：</label>

			<div class="col-sm-9">
				<input type="text" id="form-field-1" name="goods_name"
				 placeholder="物资名称" class="col-xs-10 col-sm-5" 
				 value="${requestScope.applyNewGoods.goods_name }"/>
			</div>
		</div>
		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"   for="form-field-select-3">物资类别：</label>

			<div class="col-sm-9">
				<select class="chosen-select col-xs-9 col-sm-3"
				 	id="form-field-select-4" data-placeholder="类别" name = "type_id_fk">
				 	<c:forEach items="${requestScope.goodsTypeList}" var="goodsType">
						<option value="${goodsType.type_id }">${goodsType.type_name }</option>
					</c:forEach>
				</select>
				<span class="help-inline .col-xs-10 col-sm-0">
					 
				</span>
			</div>
		</div>
		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field--3">规格：</label>

			<div class="col-sm-9">
				<input type="text" id="form-field-3" name="goods_standard"
				 placeholder="规格" class="col-xs-10 col-sm-5" 
				 value="${requestScope.applyNewGoods.goods_standard }"/>
				<span class="help-inline col-xs-12 col-sm-7">
					 
				</span>
			</div>
		</div>
		<div class="space-4"></div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-2">单位：</label>

			<div class="col-sm-9">
				<input type="text" id="form-field-2" name="goods_unit" 
				placeholder="单位" class="col-xs-10 col-sm-5" 
				value="${requestScope.applyNewGoods.goods_unit }"/>
				<span class="help-inline col-xs-12 col-sm-7">
					 
				</span>
			</div>
		</div>
		<div class="space-4"></div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-2">货号/CAS：</label>

			<div class="col-sm-9">
				<input type="text" id="form-field-2" name="goods_cas"
				 placeholder="货号/CAS" class="col-xs-10 col-sm-5" 
				 value="${requestScope.applyNewGoods.goods_cas }"/>
				<span class="help-inline col-xs-12 col-sm-7">
					 
				</span>
			</div>
		</div>
		<div class="space-4"></div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right" for="form-field-2">品牌：</label>

			<div class="col-sm-9">
				<input type="text" id="form-field-2" name="goods_brand"
				 placeholder="品牌" class="col-xs-10 col-sm-5" 
				 value="${requestScope.applyNewGoods.goods_brand }"/>
				<span class="help-inline col-xs-12 col-sm-7">
					 
				</span>
			</div>
		</div>
	<div class="space-4"></div>
	

		<div class="space-4"></div>

		<div class="modal-footer">
			<button class="btn btn-sm" data-dismiss="modal" type="reset">
				<i class="icon-remove"></i> 取消
			</button>

			<button class="btn btn-sm btn-primary" type="submit">
				<i class="icon-ok"></i> 提交
			</button>
		</div>
	</form>
</div>
						
						</div>
						
					</div>
					
					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content -->
		
			

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
	

	
		
		<%@include file="/assets/utiljsp/jsinclude/ojs.jsp" %>
		<script src="<%=basePath%>assets/js/chosen.jquery.min.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				$(".chosen-select").chosen();
				//$(".chzn-select").chosen({no_results_text: "没有匹配结果"});
			});
		</script>
</body>
</html>
