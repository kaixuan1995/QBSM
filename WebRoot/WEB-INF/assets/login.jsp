<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
	<head>
		
		<title>登录 —— BlueDot 物资管理系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />

		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />

	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
<div class="center">
	<h1>
		<i class="icon-leaf green"></i>
		<span class="red">BlueDot</span>
		<span class="white">物资管理系统</span>
	</h1>
	<h4 class="blue">&copy; BlueDot 2013</h4>
</div>

<div class="space-6"></div>

<div class="position-relative">
	<div id="login-box" class="login-box visible widget-box no-border">
		<div class="widget-body">
			<div class="widget-main">
				<h4 class="header blue lighter bigger">
					<i class="icon-coffee green"></i>
					欢迎登录 
				</h4>
				<div class="space-6"></div>

				<form action="<%=basePath%>MenberInforAction/login" method="post">
					<fieldset>
						<label class="block clearfix">
							<span class="block input-icon input-icon-right">
								<input type="text" name="user_name" 
									class="form-control" placeholder="用户名,邮箱,手机号码" 
									value="${requestScope.voUser.user_name }"/>
								<i class="icon-user"></i>
							</span>
						</label>

						<label class="block clearfix">
							<span class="block input-icon input-icon-right">
								<input type="password" name="user_password" 
									class="form-control" placeholder="密码" />
								<i class="icon-lock"></i>
							</span>
						</label>

						<div class="space"></div>
						<font color="red">${requestScope.errors }</font>
						<div class="clearfix">
							<label class="inline">
								<input type="checkbox" class="ace" />
								<span class="lbl"> 记住密码</span>
							</label>
							<input type="submit"
							
								class="width-35 pull-right btn btn-sm btn-primary" value="登录"/>
					<!-- 		<button type="button" class="width-35 pull-right btn btn-sm btn-primary">
								<i class="icon-key"></i>
								登录
							</button>
							 -->
						</div>

						<div class="space-4"></div>
					</fieldset>
				</form>

				
			</div><!-- /widget-main -->

	<div class="toolbar clearfix">
		<div>
			<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
				<i class="icon-arrow-left"></i>
				忘记密码？
			</a>
		</div>

		<div>
			<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
				注册
				<i class="icon-arrow-right"></i>
			</a>
		</div>
	</div>
</div><!-- /widget-body -->
</div><!-- /login-box -->

<div id="forgot-box" class="forgot-box widget-box no-border">
	<div class="widget-body">
		<div class="widget-main">
			<h4 class="header red lighter bigger">
				<i class="icon-key"></i>
				找回密码
			</h4>

			<div class="space-6"></div>
			<p>
				请输入注册邮箱
			</p>

			<form action="#" method="post">
				<fieldset>
					<label class="block clearfix">
						<span class="block input-icon input-icon-right">
							<input type="email" name="email" class="form-control" placeholder="Email" />
							<i class="icon-envelope"></i>
						</span>
					</label>

					<div class="clearfix">
						<button type="submit" class="width-35 pull-right btn btn-sm btn-danger">
							<i class="icon-lightbulb"></i>
							确定
						</button>
					</div>
				</fieldset>
			</form>
		</div><!-- /widget-main -->

	<div class="toolbar center">
		<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
			返回
			<i class="icon-arrow-right"></i>
		</a>
	</div>
</div><!-- /widget-body -->
</div><!-- /forgot-box -->

<div id="signup-box" class="signup-box widget-box no-border">
	<div class="widget-body">
		<div class="widget-main">
			<h4 class="header green lighter bigger">
				<i class="icon-group blue"></i>
				注册
			</h4>

			<div class="space-6"></div>
			<p> 请联系管理员</p>
			<p> email:xxx@xxx.com</p>
		</div>

		<div class="toolbar center">
			<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
			<i class="icon-arrow-left"></i>
			返回
		</a>
	</div>
</div><!-- /widget-body -->
</div><!-- /signup-box -->
</div><!-- /position-relative -->
</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		
		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='../assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->


		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
		</script>
</body>
</html>

