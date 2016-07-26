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
		
		<title>评价详情</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />
		<!-- ace styles -->

		<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />


		<style>
			.spinner-preview {
				width: 100px;
				height: 100px;
				text-align: center;
				margin-top: 60px;
			}
			
			.dropdown-preview {
				margin: 0 5px;
				display: inline-block;
			}
			
			.dropdown-preview > .dropdown-menu {
				display: block;
				position: static;
				margin-bottom: 5px;
			}
		</style>

		<!-- ace settings handler -->

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
		<div class="page-content">
			<div class="page-header">
				<h1>
					供应商评价详情
					<small>
						<i class="icon-double-angle-right"></i>
						江西南昌XXX公司
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div class="col-sm-12">
						<h2 class="header smaller lighter green">江西南昌XXX公司</h2>

						<div class="row">

							<div class="col-xs-8">
								<h4>地址：江西省南昌市XXXX</h4>
								<h4>电话：124242353</h4>
								<h4>负责人：李XX</h4>
								<h3 style="color: red;">差评订单编号：</h3>
								<hr />
								<div id="list" style="font-size: 17px; line-height: 2;margin-left: 50px;">
									<ol>
										<li>订单编号：<a href="more.html">d00001</a>&nbsp;时间：2015-1-1 12:12:12</li>
										<li>订单编号：<a href="more.html">d00002</a>&nbsp;时间：2015-1-1 12:12:12</li>
										
										<li>订单编号：<a href="more.html">d00003</a>&nbsp;时间：2015-1-1 12:12:12</li>
										
										<li>订单编号：<a href="more.html">d00004</a>&nbsp;时间：2015-1-1 12:12:12</li>
										
										<li>订单编号：<a href="more.html">d00005</a>&nbsp;时间：2015-1-1 12:12:12</li>
									
									</ol>
								</div>
								
								<!--<a id="bootbox-options">d00001</a>-->
								<!---->
							</div>
								<!-- /span -->

								<div class="col-xs-4 ">
									<h3 style="color: green;">好评率：</h3>
									
									<div class="easy-pie-chart percentage" style="margin-top: 10px;" data-percent="90" data-color="#87B87F">
										<span class="percent">9/10</span>
									</div>
								</div>
								<!-- /span -->
							</div>
							<!-- /row-fluid -->
						</div>
						<!-- /span -->
					</div>
					<!-- /row -->
			</div>
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>

			<!-- /.main-container -->

			<!-- basic scripts -->

			<!--[if !IE]> -->

			<script type="text/javascript">
				window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
			</script>

			<!-- <![endif]-->

			<script type="text/javascript">
				if ("ontouchend" in document) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
			</script>
			<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
			<script src="<%=basePath%>assets/js/typeahead-bs2.min.js"></script>

			<script src="<%=basePath%>assets/js/jquery.easy-pie-chart.min.js"></script>

			<!-- ace scripts -->

			<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
			<script src="<%=basePath%>assets/js/ace.min.js"></script>

			<!-- inline scripts related to this page -->

			<script type="text/javascript">
				jQuery(function($) {
				
					
					var oldie = /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase());
					$('.easy-pie-chart.percentage').each(function() {
						$(this).easyPieChart({
							barColor: $(this).data('color'),
							trackColor: '#EEEEEE',
							scaleColor: false,
							lineCap: 'butt',
							lineWidth: 8,
							animate: oldie ? false : 1000,
							size: 75
						}).css('color', $(this).data('color'));
					});
				});
			</script>
			
	</body>

</html>
