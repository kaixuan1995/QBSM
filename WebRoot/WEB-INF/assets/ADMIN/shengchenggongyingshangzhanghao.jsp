<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

	<head>
	
		<title>生成供应商账号 —— BlueDot 物资管理系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<%@include file="/assets/utiljsp/csslink/ocsslink.jsp" %>
		<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />

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

					<ul class="nav nav-list">
						<li class="">
							<a href="<%=basePath%>SystemManageAction/admin">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li class="active open">
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
								<li class="open">
									<a href="<%=basePath%>SystemManageAction/shengchenggongyingshangzhanghao">
										<i class="icon-double-angle-right"></i> 生成供应商账号
									</a>
								</li>
							
								
							</ul>
						</li>
					
						
						<li>
							<a href="<%=basePath%>assets/ADMIN/wuziliebiao.jsp" class="dropdown-toggle">
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
								<a href="<%=basePath%>SystemManageAction/admin">首页</a>
							</li>

							<li>
								<a href="#">信息申请</a>
							</li>
							<li class="active">申请添加供应商信息</li>
						</ul>
						<!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
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
								信息申请
								<small>
									<i class="icon-double-angle-right"></i>
									申请添加供应商信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">${requestScope.msg }</font>
								</small>
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

<form action="<%=basePath%>MenberInforAction/savegongyinshang"  method="post" class="form-horizontal" role="form">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 供应商名称：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-1" name="user_name" 
				value="${requestScope.user.user_name }"
			placeholder="供应商名称" class="col-xs-10 col-sm-5" />
		</div>
	</div>

	<div class="space-4"></div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">联系人：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_lianxiren"
				value="${requestScope.user.user_lianxiren }"
			placeholder="联系人" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
				 
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">手机号：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2"  name="user_phone"
				value="${requestScope.user.user_phone }"
				 placeholder="手机号" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
				 
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">传真：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_fax" 
				value="${requestScope.user.user_fax }"
			placeholder="传真" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
				 
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">地址：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_address"
				value="${requestScope.user.user_address }"
				 placeholder="地址" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
				 
			</span>
		</div>
	</div>
	
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">电子邮箱：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_email"
				value="${requestScope.user.user_email }"
				 placeholder="电子邮箱" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
				 
			</span>
		</div>
	</div>
	
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-4">供应类别：</label>

		<div class="col-sm-9" style="hight:50px;"><!-- multiple (是否添加多个)-->
			<select multiple name="type_name" class=" width-40 chosen-select" 
			 data-placeholder="选择供应产品类别">
			 <c:forEach items="${requestScope.tpyeList}" var="type">
				<option value="${type.type_id }">${type.type_name }</option>
			</c:forEach>
			</select>
			
		</div>
	</div>
	<div class="space-4"></div>
	

	<div class="space-4"></div>

	<div class="modal-footer">
		<button class="btn btn-sm" data-dismiss="modal" type="reset">
			<i class="icon-remove"></i> 取消
		</button>

		<button class="btn btn-sm btn-primary" type="submit">
			<i class="icon-ok"></i> 生成
		</button>
		
		
	</div>
	
</form>
							<!-- 	<button class="btn btn-sm btn-primary" id = "bootbox-confirm" >
											<i class="icon-ok"></i> 显示
								</button> -->
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
		<%@include file="/assets/utiljsp/jsinclude/chosenselectjs.jsp" %>
		<script type="text/javascript">
				var bootbox_regular = "#bootbox-regular";
				
				var bootbox_confirm = "#bootbox-confirm";
				var confirm_message = "<p>账号：#</p><p>密码：#</p><p>账号密码已发送至该E-Mail中，请注意查收</p>";
				var bootbox_options = "#bootbox-options";
		</script>
		<%@include file="/assets/utiljsp/jsinclude/bootboxjs.jsp" %>
		
	</body>

</html>
