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
		<title>高级查询——BlueDot物资管理系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<%@include file="/assets/utiljsp/csslink/jqcsslink.jsp" %>
		<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/daterangepicker.css" />


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

							<i class="icon-check red"></i> 未连接后台！，无查询结果。

						</div>
						<div id="main">
							<div id="hid">
								<div class="space-24"></div>
							<center>
								<h2 class="lighter blue">高级查询</h2>
								<div class="space-24"></div>
								<div class="col-xs-12"></div>
								<div class="space-24"></div>
							</center>
							<div class="col-xs-2"></div>
							
							</div>
							

							<form class="form-search" action="#" method="get">

								<div class="col-xs-8">

									
									<div class="input-group">
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-type" name="selece-type" value="">
												
											
											</select>
											</span>
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-message" name="selece-message" value="">
												
												
									
											</select>
											</span>
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-role" name="selece-role" value="">
																						
											</select>
											
											
											</span>
										<span class="input-group-btn width-auto">	
											<div>
												<input class="form-control form-control "  value="" type="text" name="date-range-picker" id="id-date-range-picker-1" placeholder="选择日期"/>
											</div>
											
												
											</span>
										<input type="text"id="key" class="form-control search-query" placeholder="请输入关键字" value="" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-purple btn-sm" id="sub">
												查询
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										</span>
									</div>
								</div>
							</form>
							<div class="col-xs-2"></div>
						</div>
					</div>
				</div>
				<div id="grid" style="margin-top:50px;">
					<table id="grid-table"></table>

					<div id="grid-pager"></div>
				</div>
				
			</div>

			</div>
			<!-- /.page-content -->

			
		<!-- basic scripts -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src=' <%=basePath%>assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
		</script>

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		<script src=" <%=basePath%>assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src=" <%=basePath%>assets/js/chosen.jquery.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/moment.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/daterangepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.autosize.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.maskedinput.min.js"></script>

	
		<!-- inline scripts related to this page -->
	
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>
		<script type="text/javascript">
		
		var typev ="";
		var messagev ="";
		var rolev ="";
		var date ="";
		var key ="";
		//某检测人员/科室 请购/领用 
		//var n1=['物资名称','物资品牌','物资单位','物资规格','物资货号'];
		//某采购人员/仓库 出仓、入仓采购 某物资类别
		//var n2=['物资类别','物资名称','物资品牌','物资单位','物资规格','物资货号','数量','时间'];
		//供应商异常记录
		//var n3=['异常单号','物资名称','物资品牌','物资单位','物资规格','物资货号','数量','退货原因','凭证'];
		var n3=['异常单号','物资类别','物资名称','物资品牌','物资单位','物资规格','物资货号','数量','时间','退货原因','凭证'];
		
		
		var m3=[
				{name: 'voucher_id',index: 'voucher_id',width: 50,editable: false}, 
				{name: 'goods_type_id',index: 'goods_type_id',width: 50,editable: false},
				{name: 'goods_brand',index: 'goods_brand',width: 50,editable: false},
				{name: 'goods_name',index: 'goods_name',width: 50,editable: false},
				{name: 'goods_unit',index: 'goods_unit',width: 30,editable: false},
				{name: 'goods_standard',index: 'goods_standard',width: 30,editable: false},
				{name: 'goods_cas',index: 'goods_cas',width: 30,editable: false},
				{name: 'voucher_goods_count',index: 'voucher_goods_count',width: 40,editable: false},
				{name: 'voucher_createtime',index: 'voucher_createtime',width: 40,editable: false},
				{name: 'voucher_remark',index: 'voucher_remark',width: 50,editable: false},
				{name: 'apply_picture_url',index: 'apply_picture_url',width: 50,editable: false},
		
			];
		
		var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"AnalysAction/search";
			var tablename = "查询结果";
			var pageediturl ="#";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=false;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = n3;
			var pagecolmodel =  m3;
			
			
			
			
			
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>
		
		<script type="text/javascript">
		function inits(){
			jQuery(grid_selector).setGridParam().hideCol("voucher_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("goods_type_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_goods_count").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_remark").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("apply_picture_url").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_createtime").trigger("reloadGrid");
		}
		
		$(function(){
		inits();
        $("#main").each(function(){
        	
            var url = "<%=basePath%>AnalysAction/getselectlist";
            var areaJson;
           
            var temp_html;
            var otype = $(this).find("#selece-type");
            var omessage = $(this).find("#selece-message");
            var orole = $(this).find("#selece-role");
            //初始化种类
            var type = function(){
                $.each(areaJson,function(i,type){
                    temp_html+="<option value='"+type.t+"'>"+type.t+"</option>";
                });
                otype.html(temp_html);
                message();
            };
            //赋值类别
            var message = function(){
                temp_html = ""; 
                var n = otype.get(0).selectedIndex;
                $.each(areaJson[n].c,function(i,message){
                    temp_html+="<option value='"+message.m+"'>"+message.m+"</option>";
                });
                omessage.html(temp_html);
                role();
            };
            //赋值角色
            var role = function(){
                temp_html = ""; 
                var m = otype.get(0).selectedIndex;
                var n = omessage.get(0).selectedIndex;
                if(typeof(areaJson[m].c[n].d) == ""){
                    orole.css("display","none");
                }else{
                    orole.css("display","inline");
                    $.each(areaJson[m].c[n].d,function(i,role){
                        temp_html+="<option value='"+role.r+"'>"+role.r+"</option>";
                    });
                    orole.html(temp_html);
                };
            };
            function showall(){
            	jQuery(grid_selector).setGridParam().showCol("voucher_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_type_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("voucher_goods_count").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("voucher_remark").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("apply_picture_url").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("voucher_createtime").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_brand").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_name").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_unit").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_cas").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().showCol("goods_standard").trigger("reloadGrid");
            }
            
            
            //选择改变类别
            otype.change(function(){
            var typ = document.getElementById('selece-type');
			typev =typ.options[typ.selectedIndex].value;
            if(typev == "检测人员"){
            	showall();
            	jQuery(grid_selector).setGridParam().hideCol("voucher_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("goods_type_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_goods_count").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_remark").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("apply_picture_url").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_createtime").trigger("reloadGrid");
            }else if(typev == "供应商"){
            	showall();
            	jQuery(grid_selector).setGridParam().hideCol("goods_type_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_createtime").trigger("reloadGrid");
            }else{
            	showall();
            	jQuery(grid_selector).setGridParam().hideCol("voucher_id").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("voucher_remark").trigger("reloadGrid");
            	jQuery(grid_selector).setGridParam().hideCol("apply_picture_url").trigger("reloadGrid");
            }

                message();
            });
            //选择类别改变角色
            omessage.change(function(){
                role();
            });
            //获取json数据
            $.getJSON(url,function(data){
                areaJson = data;
                type();
            });
        });
    });
    
    jQuery(function($) {
				
				$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				$('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
				function ref(){
				
						
				}
				$('#sub').click(function(){
				
				
				
			   //页面上的button按钮的click事件，重新获取参数后发送参数，然后重新加载数据。
              var  keyword = "selece-type="+typev+"&selece-message="+messagev+"&selece-role="+rolev+"&date-range-picker="+date+"&value="+key;       //获取输入框内容
               var typ = document.getElementById('selece-type');
				 	typev =typ.options[typ.selectedIndex].value;
					var messa = document.getElementById('selece-message');
					 messagev = messa.options[messa.selectedIndex].value;
					var ro = document.getElementById('selece-role');
					 rolev = ro.options[ro.selectedIndex].value;
					 date = document.getElementById('id-date-range-picker-1').value;
					 key = document.getElementById('key').value;
					 
                $(grid_selector).jqGrid('setGridParam',{
                    datatype:'json',
                    postData:{'type':typev,'message':messagev,'role':rolev,'date':date,'value':key}, //发送数据
                    page:1
                }).trigger("reloadGrid"); //重新载入
       
				
				
				
					
					
			
					 $('#hid').hide();
						
		               // alert(typev+","+messagev+","+rolev+","+date+","+key+";");
		              
					
					 
				});
			
			
			});
		</script>
	</body>

</html>
