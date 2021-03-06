<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">

	<head>

		<title>个人资料</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />

		<script src="<%=basePath%>assets/js/ace-extra.min.js"></script>

	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="<%=basePath%>MenberInforAction/index" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							BlueDot 物资管理系统
						</small>
					</a>
					<!-- /.brand -->
				</div>
				<!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">${MessageCount}</span>
							</a>

							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-envelope-alt"></i> ${MessageCount}条消息
								</li>
								
								
								<li>
									<a href="<%=basePath%>MenberInforAction/lookMyMessage">
										查看所有消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="<%=basePath%>assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${sessionScope.userSession.user_name}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="<%=basePath%>MenberInforAction/shezhi">
										<i class="icon-cog"></i> 设置
									</a>
								</li>

								<li>
									<a href="<%=basePath%>MenberInforAction/gerenziliao">
										<i class="icon-user"></i> 个人资料
									</a>
								</li>
								<li>
									<a href="<%=basePath%>MenberInforAction/xiugaimima">
										<i class="icon-key"></i> 修改密码
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="<%=basePath%>MenberInforAction/exit">
										<i class="icon-off"></i> 退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
					<!-- /.ace-nav -->
				</div>
				<!-- /.navbar-header -->
			</div>
			<!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {}
			</script>

			<div class="main-container-inner">

				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">

						<div class="alert alert-block alert-danger">
							<button type="button" class="close" data-dismiss="alert">
								<i class="icon-remove"></i>
							</button>

						</div>
						<div id="main">
							<div class="space-24"></div>
							
								<h2 class="lighter blue">
									个人资料&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red" size="3">${requestScope.msg }</font>
								</h2>
								<hr></hr>
								<div class="space-24"></div>
<form action="<%=basePath%>MenberInforAction/updateGerenziliao"
	 method="post" class="form-horizontal" role="form">
	 <input type="hidden" name="user_id" value="${requestScope.userRequest.user_id }">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-1" > 联系人：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-1"  placeholder="联系人"
			 name="user_lianxiren" value="${requestScope.userRequest.user_lianxiren }" class="col-xs-10 col-sm-5" />
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field--3">所属科室：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-3" name="office_id_fk"
			 value="${requestScope.userRequest.office_id_fk }"  placeholder="所属科室" class="col-xs-10 col-sm-5" disabled="true"/>
			<span class="help-inline col-xs-12 col-sm-7">
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">手机号：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_phone"
			 value="${requestScope.userRequest.user_phone }" placeholder="手机号" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">邮箱：</label>

		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_email"
			 value="${requestScope.userRequest.user_email }" placeholder="邮箱" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">地址：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_address"
			 value="${requestScope.userRequest.user_address }" placeholder="地址" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="form-field-2">传真：</label>
		<div class="col-sm-9">
			<input type="text" id="form-field-2" name="user_fax"
			 value="${requestScope.userRequest.user_fax }" placeholder="传真" class="col-xs-10 col-sm-5" />
			<span class="help-inline col-xs-12 col-sm-7">
			</span>
		</div>
	</div>
	<div class="space-4"></div>
	
<div class="space-4"></div>
	<div class="space-4"></div>
	<div class="modal-footer">
	 	<button class="btn btn-sm" data-dismiss="modal" type="reset">
			<i class="icon-remove"></i> 取消
		</button>
		<button class="btn btn-sm btn-primary" type="submit">
			<i class="icon-ok"></i> 保存
		</button>
	</div>
</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- basic scripts -->
			<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
	</script>

	<script type="text/javascript">
		if ("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
	</script>
	<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>

	<!-- ace scripts -->
	<script src="<%=basePath%>assets/js/typeahead-bs2.min.js"></script>
	<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
	<script src="<%=basePath%>assets/js/ace.min.js"></script>
		
	</body>

</html>
