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
		<link href=" <%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href=" <%=basePath%>assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href=" <%=basePath%>assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="<%=basePath%>assets/css/daterangepicker.css" />
		<script src=" <%=basePath%>assets/js/ace-extra.min.js"></script>


	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed');
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
					ace.settings.check('main-container', 'fixed');
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
							<div class="space-24"></div>
							<center>
								<h2 class="lighter blue">高级查询</h2>
								<div class="space-24"></div>
								<div class="col-xs-12"></div>
							</center>
							<div class="col-xs-2"></div>

							<form class="form-search" action="#" method="get">

								<div class="col-xs-8">

									<div class="space-24"></div>
									<div class="input-group">
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-type" name="selece-type">
												
											
											</select>
											</span>
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-message" name="selece-message">
												
												
									
											</select>
											</span>
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="selece-role" name="selece-role">
																						
											</select>
											
											
											</span>
										<span class="input-group-btn width-auto">	
											<div>
												<input class="form-control form-control " type="text" name="date-range-picker" id="id-date-range-picker-1" placeholder="选择日期"/>
											</div>
											
												
											</span>
										<input type="text" class="form-control search-query" placeholder="请输入关键字" />
										<span class="input-group-btn">
											<button type="button" class="btn btn-purple btn-sm">
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

			</div>	<!-- PAGE CONTENT ENDS -->

			</div>
			<!-- /.page-content -->

			
		<!-- basic scripts -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src=' <%=basePath%>assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
		</script>

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src=" <%=basePath%>assets/js/bootstrap.min.js"></script>
		<script src=" <%=basePath%>assets/js/typeahead-bs2.min.js"></script>

		<script src=" <%=basePath%>assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src=" <%=basePath%>assets/js/chosen.jquery.min.js"></script>
		<script src=" <%=basePath%>assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/moment.min.js"></script>
		<script src=" <%=basePath%>assets/js/date-time/daterangepicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/bootstrap-colorpicker.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.knob.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.autosize.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src=" <%=basePath%>assets/js/jquery.maskedinput.min.js"></script>
		<script src=" <%=basePath%>assets/js/bootstrap-tag.min.js"></script>

		<!-- ace scripts -->

		<script src=" <%=basePath%>assets/js/ace-elements.min.js"></script>
		<script src=" <%=basePath%>assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
		$(function(){
        $("#main").each(function(){
        	
            var url = " json/area.json";
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
            //选择改变类别
            otype.change(function(){
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
				$('#id-disable-check').on('click', function() {
					var inp = $('#form-input-readonly').get(0);
					if(inp.hasAttribute('disabled')) {
						inp.setAttribute('readonly' , 'true');
						inp.removeAttribute('disabled');
						inp.value="This text field is readonly!";
					}
					else {
						inp.setAttribute('disabled' , 'disabled');
						inp.removeAttribute('readonly');
						inp.value="This text field is disabled!";
					}
				});
			
			
				$(".chosen-select").chosen(); 
				$('#chosen-multiple-style').on('click', function(e){
					var target = $(e.target).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			
			
				$('[data-rel=tooltip]').tooltip({container:'body'});
				$('[data-rel=popover]').popover({container:'body'});
				
				$('textarea[class*=autosize]').autosize({append: "\n"});
				$('textarea.limited').inputlimiter({
					remText: '%n character%s remaining...',
					limitText: 'max allowed : %n.'
				});
			
				$.mask.definitions['~']='[+-]';
				$('.input-mask-date').mask('99/99/9999');
				$('.input-mask-phone').mask('(999) 999-9999');
				$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
				$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			
			
			
				$( "#input-size-slider" ).css('width','200px').slider({
					value:1,
					range: "min",
					min: 1,
					max: 8,
					step: 1,
					slide: function( event, ui ) {
						var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
						var val = parseInt(ui.value);
						$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
					}
				});
			
				$( "#input-span-slider" ).slider({
					value:1,
					range: "min",
					min: 1,
					max: 12,
					step: 1,
					slide: function( event, ui ) {
						var val = parseInt(ui.value);
						$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
					}
				});
				
				
				$( "#slider-range" ).css('height','200px').slider({
					orientation: "vertical",
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1]+"";
			
						if(! ui.handle.firstChild ) {
							$(ui.handle).append("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('a').on('blur', function(){
					$(this.firstChild).hide();
				});
				
				$( "#slider-range-max" ).slider({
					range: "max",
					min: 1,
					max: 10,
					value: 2
				});
				
				$( "#eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( $( this ).text(), 10 );
					$( this ).empty().slider({
						value: value,
						range: "min",
						animate: true
						
					});
				});
			
				
				$('#id-input-file-1 , #id-input-file-2').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
				
				$('#id-input-file-3').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'icon-cloud-upload',
					droppable:true,
					thumbnail:'small'//large | fit
					//,icon_remove:null//set null, to hide remove/reset button
					/**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
					/**,before_remove : function() {
						return true;
					}*/
					,
					preview_error : function(filename, error_code) {
						//name of the file that failed
						//error_code values
						//1 = 'FILE_LOAD_FAILED',
						//2 = 'IMAGE_LOAD_FAILED',
						//3 = 'THUMBNAIL_FAILED'
						//alert(error_code);
					}
			
				}).on('change', function(){
					//console.log($(this).data('ace_input_files'));
					//console.log($(this).data('ace_input_method'));
				});
				
			
				//dynamically change allowed formats by changing before_change callback function
				$('#id-file-format').removeAttr('checked').on('change', function() {
					var before_change
					var btn_choose
					var no_icon
					if(this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "icon-picture";
						before_change = function(files, dropped) {
							var allowed_files = [];
							for(var i = 0 ; i < files.length; i++) {
								var file = files[i];
								if(typeof file === "string") {
									//IE8 and browsers that don't support File Object
									if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
								}
								else {
									var type = $.trim(file.type);
									if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
											|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
										) continue;//not an image so don't keep this file
								}
								
								allowed_files.push(file);
							}
							if(allowed_files.length == 0) return false;
			
							return allowed_files;
						}
					}
					else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "icon-cloud-upload";
						before_change = function(files, dropped) {
							return files;
						}
					}
					var file_input = $('#id-input-file-3');
					file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
					file_input.ace_file_input('reset_input');
				});
			
			
			
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.on('change', function(){
					//alert(this.value)
				});
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'icon-caret-up', icon_down:'icon-caret-down'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
			
			
				
				$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				$('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
				
				$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				$('#colorpicker1').colorpicker();
				$('#simple-colorpicker-1').ace_colorpicker();
			
				
				$(".knob").knob();
				
				
				//we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
				var tag_input = $('#form-field-tags');
				if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) ) 
				{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
					  }
					);
				}
				else {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//$('#form-field-tags').autosize({append: "\n"});
				}
				
				
				
			
				/////////
				$('#modal-form input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'icon-cloud-upload',
					droppable:true,
					thumbnail:'large'
				})
				
				//chosen plugin inside a modal will have a zero width because the select element is originally hidden
				//and its width cannot be determined.
				//so we set the width after modal is show
				$('#modal-form').on('shown.bs.modal', function () {
					$(this).find('.chosen-container').each(function(){
						$(this).find('a:first-child').css('width' , '210px');
						$(this).find('.chosen-drop').css('width' , '210px');
						$(this).find('.chosen-search input').css('width' , '200px');
					});
				})
				/**
				//or you can activate the chosen plugin after modal is shown
				//this way select element becomes visible with dimensions and chosen works as expected
				$('#modal-form').on('shown', function () {
					$(this).find('.modal-chosen').chosen();
				})
				*/
			
			});
		</script>
	</body>

</html>
