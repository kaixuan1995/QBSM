<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
	
		<title>单据处理————BlueDot物资管理系统</title>

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
							<a href="<%=basePath%>StoreManageAction/cangkuguanliyuan">
								<i class="icon-home"></i>
								<span class="menu-text"> 首页 </span>
							</a>
						</li>

						<li>
							<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao" class="dropdown-toggle">
								<i class="icon-barcode"></i>
								<span class="menu-text"> 物资信息管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/wuzixinxiliebiao">
										<i class="icon-double-angle-right"></i> 物资信息列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhexinxishenqing">
										<i class="icon-double-angle-right"></i> 审核信息申请
									</a>
								</li>
							
								
							</ul>
						</li>
						<li class="">
							<a href="<%=basePath%>StoreManageAction/gongyingshangxinxiliebiao">
								<i class="icon-globe"></i>
								<span class="menu-text"> 供应商信息列表 </span>
							</a>
						</li>
						
						
						<li>
							<a href="<%=basePath%>StoreManageAction/wuziliebiao" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 物资管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/wuziliebiao">
										<i class="icon-double-angle-right"></i> 物资列表
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/rukuguanli">
										<i class="icon-double-angle-right"></i> 入库管理
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/chukuguanli">
										<i class="icon-double-angle-right"></i> 出库管理
									</a>
								</li>
								
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingtuihuo">
										<i class="icon-double-angle-right"></i> 申请退货
									</a>
								</li>
								<%-- <li>
									<a href="<%=basePath%>StoreManageAction/tuihuowodeshenqing">
										<i class="icon-double-angle-right"></i> 我的申请
									</a>
								</li> --%>
							</ul>
						</li>
						<li class="">
							<a href="#" class="dropdown-toggle">
								<i class="icon-book"></i>
								<span class="menu-text"> 申请管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhetuiyongshenqing">
										<i class="icon-double-angle-right"></i> 审核退用申请
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhecaigoushenqing">
										<i class="icon-double-angle-right"></i> 审核采购申请
									</a>
								</li>

								<li class="">
									<a href="<%=basePath%>StoreManageAction/suoyoushenqing">
										<i class="icon-double-angle-right"></i> 所有申请
									</a>
								</li>

							</ul>
						</li>
						<li class="">
							<a href="#" class="dropdown-toggle">
								<i class="icon-briefcase"></i>
								<span class="menu-text"> 仓库调度管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="<%=basePath%>StoreManageAction/shenqingcangkuwuzidiaodu">
										<i class="icon-double-angle-right"></i> 申请仓库物资调度
									</a>
								</li>
								<li>
									<a href="<%=basePath%>StoreManageAction/shenhecangkuwuzidiaodu">
										<i class="icon-double-angle-right"></i> 审核仓库物资调度
									</a>
								</li>

								<li class="">
									<a href="<%=basePath%>StoreManageAction/diaoduwode">
										<i class="icon-double-angle-right"></i> 我的仓库物资调度
									</a>
								</li>

								

							</ul>
						</li>
						<li class="active">
							<a href="<%=basePath%>StoreManageAction/danjuchuli">
								<i class="icon-list"></i>
								<span class="menu-text"> 单据处理 </span>
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
								<a href="#">首页</a>
							</li>
							<li class="active">单据处理</li>
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
								单据处理
							</h1>
						</div>
						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="space-24"></div>
									<div class="input-group">
										<span class="input-group-btn ">
											<select class="form-control width-auto " id="select">
												<option value="3">物资入库单</option>
												<option value="4">物资出库单</option>	
												<option value="7">仓库调用凭证单</option>														
											</select>
										</span>
									</div>
									<div class="space-6"></div>
							
								
						
								<div id="">
										<table id="grid-table"></table>

										<div id="grid-pager"></div>
								</div>
							<div style="text-align: right; margin-top: 20px;">
										<button class="btn btn-sm btn-primary" id="xianshi">
											<i class=" icon-ok" ></i> 1.显示详细单据
										</button>
										<a href="<%=basePath%>PurchaseManageAction/getdaochu">
										<button class="btn btn-sm btn-primary">
											<i class=" icon-ok" ></i> 2.导出
										</button></a>
										<!-- <button class="btn btn-sm btn-primary" onclick="javascript:printit('printdiv')" id="dayin">
											<i class="icon-ok"></i> 3.打印
										</button> -->
									</div>
							
								<div id="printdiv" style="height: 900px; margin-left: 50px" >
									
										<div class=WordSection1 style='layout-grid:15.6pt'>

			<div align=center>

				<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td width=1125 colspan=2 style='width:675.0pt;padding:0cm 0cm 0cm 0cm'>
							<p class=MsoNormal align=center style='text-align:center'><span style='font-size:13.5pt;font-family:黑体;color:#333333'>江西检验检疫局综合技术中心</span></p>
						</td>
					</tr>
					<tr>
						<td width=1125 colspan=2 style='width:675.0pt;padding:0cm 0cm 0cm 0cm'>
							<p class=MsoNormal align=center style='text-align:center'><span style='font-size:13.5pt;font-family:黑体;color:#333333'>标准物质和易耗品请购单</span></p>
						</td>
					</tr>
					<tr>
						<td width=557 style='width:334.1pt;padding:0cm 0cm 0cm 0cm'>
							<p class=MsoNormal>
								<span style='font-size:10.0pt;font-family:宋体;color:#333333'>编号：</span>
								<span lang=EN-US style='font-size:10.0pt;color:#333333' id="voucher_id" name="voucher_id"></span>
							</p>
						</td>
						<td width=568 style='width:340.9pt;padding:0cm 0cm 0cm 0cm'>
							<p class=MsoNormal align=right style='text-align:right;word-break:break-all'><span lang=EN-US style='font-size:10.0pt;color:#333333'>&nbsp;&nbsp; </span><span style='font-size:10.0pt;font-family:宋体;color:#333333'>共</span><span lang=EN-US style='font-size:10.0pt;color:#333333'>_1_</span><span style='font-size:10.0pt;font-family:宋体;color:#333333'>页</span>
								<span
									style='font-size:10.0pt;color:#333333'> </span><span style='font-size:10.0pt;
  font-family:宋体;color:#333333'>第</span><span lang=EN-US style='font-size:10.0pt;
  color:#333333'>___1___</span><span style='font-size:10.0pt;font-family:宋体;
  color:#333333'>页</span></p>
						</td>
					</tr>
				</table>

			</div>

			<p class=MsoNormal><span lang=EN-US style='font-size:10.0pt;display:none'>&nbsp;</span></p>

			<div align=center>

				<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 style='border:outset black 1.0pt'>
					<tr style='height:83.95pt' id="tr_first">
						<td width=168 colspan=3 style='width:100.8pt;border:inset black 1.0pt; background:whitesmoke;padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>品</span>
							<span style='font-size:10.0pt;color:#333333'> </span>
							<span style='font-size:10.0pt;  font-family:宋体;color:#333333'>名</span></p>
						</td>
						<td width=165 style='width:99.25pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>规</span>
							<span style='font-size:10.0pt;color:#333333'> </span>
							<span style='font-size:10.0pt; font-family:宋体;color:#333333'>格</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'><br>  (</span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>型号、包装、重量</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'>)</span></p>
						</td>
						<td width=156 style='width:93.35pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>质量保证要求</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'><br>  (</span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>品牌、生产商、</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'>CAS</span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>号、使用仪器型号</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'>)</span></p>
						</td>
						<td width=128 style='width:76.75pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>售后服务要求</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'><br>  (</span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>运输要求、结算方式、跟踪服务、送货上门</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'>)</span></p>
						</td>
						<td width=71 style='width:42.5pt;border:inset black 1.0pt;background:whitesmoke;  padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>有效期限</span></p>
						</td>
						<td width=61 style='width:36.85pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>单</span>
							<span style='font-size:10.0pt;color:#333333'> </span>
							<span style='font-size:10.0pt; font-family:宋体;color:#333333'>位</span></p>
						</td>
						<td width=71 style='width:42.7pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>申</span>
							<span style='font-size:10.0pt;color:#333333'> </span>
							<span style='font-size:10.0pt; font-family:宋体;color:#333333'>购</span>
							<span style='font-size:10.0pt; color:#333333'> </span>
							<span style='font-size:10.0pt;font-family:宋体; color:#333333'>数</span></p>
						</td>
						<td width=71 style='width:42.7pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>库存数</span></p>
						</td>
						<td width=194 style='width:116.5pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>采购原因</span>
							<span lang=EN-US style='font-size:10.0pt;color:#333333'>/</span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>用途</span></p>
						</td>
						<td width=106 style='width:63.75pt;border:inset black 1.0pt;background:whitesmoke; padding:0cm 0cm 0cm 0cm;height:83.95pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>备</span>
							<span style='font-size:10.0pt;color:#333333'> </span>
							<span style='font-size:10.0pt;font-family:宋体;color:#333333'>注</span></p>
						</td>
					</tr>
					
					<tr style='height:37.35pt'>
						<td width=489 colspan=5 style='width:293.4pt;border:inset black 1.0pt;background:whitesmoke;padding:0cm 0cm 0cm 0cm;height:37.35pt'>
							<p class=MsoNormal style='word-break:break-all'>
								<strong> <span style='font-size:10.0pt;font-family:宋体;color:#333333'>请批准采购以上物资。</span> </strong>
								<span lang=EN-US style='font-size:10.0pt;color:#333333'><br> <br> </span>
								<strong><span style='font-size:10.0pt;font-family:宋体;color:#333333'>申请人：</span> </strong>
								<span style='font-size:10.0pt;color:#333333' id='user_lianxiren'> <span lang=EN-US>&nbsp;&nbsp;</span> </span>
								<strong><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </strong>
								<strong><span style='font-size:10.0pt;font-family:宋体;color:#333333'>时间:</span> </strong>
								<strong><span lang=EN-US style='font-size:10.0pt;color:#333333' id='voucher_createtime'></span> </strong>
								
							</p></td>
						<td width=128 style='width:76.75pt;border:inset black 1.0pt;background:whitesmoke;  padding:0cm 0cm 0cm 0cm;height:37.35pt'>
							<p class=MsoNormal align=center  style='text-align:center;word-break:break-all'>
								<strong><span style='font-size:10.0pt;font-family:宋体;color:#333333'>申请部门意见</span>
								</strong><span style='font-size:10.0pt;color:#333333'> </span>
							</p></td>
						<td width=575 colspan=6 style='width:345.0pt;border:inset black 1.0pt;background:whitesmoke;padding:0cm 0cm 0cm 0cm;height:37.35pt'>
							<p class=MsoNormal style='word-break:break-all'>
								<span lang=EN-US style='font-size:10.0pt;color:#333333'><br>
									<br> </span><strong><span
									style='font-size:10.0pt;font-family:宋体;color:#333333'>实验室技术负责人</span>
								</strong><strong><span lang=EN-US
									style='font-size:10.0pt;color:#333333'>/</span>
								</strong><strong><span
									style='font-size:10.0pt;font-family:宋体;color:#333333'>质量负责人</span>
								</strong><strong><span lang=EN-US
									style='font-size:10.0pt;color:#333333'>:</span>
								</strong><span lang=EN-US style='font-size:10.0pt;color:#333333'>
								</span><strong><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</strong><strong><span lang=EN-US
									style='font-size:10.0pt;color:#333333'>____</span>
								</strong><strong><span
									style='font-size:10.0pt;font-family:宋体;color:#333333'>年</span>
								</strong><strong><span lang=EN-US
									style='font-size:10.0pt;color:#333333'>___</span>
								</strong><strong><span
									style='font-size:10.0pt;font-family:宋体;color:#333333'>月</span>
								</strong><strong><span lang=EN-US
									style='font-size:10.0pt;color:#333333'>___</span>
								</strong><strong><span
									style='font-size:10.0pt;font-family:宋体;color:#333333'>日</span>
								</strong><span style='font-size:10.0pt;color:#333333'> </span>
							</p></td>
					</tr>
					<tr style='height:11.85pt'>
						<td width=168 colspan=3 style='width:100.8pt;border:inset black 1.0pt;
  padding:0cm 0cm 0cm 0cm;height:11.85pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'><strong><span
  style='font-size:10.0pt;font-family:宋体;color:#333333'>综合部审核意见</span></strong></p>
						</td>
						<td width=1024 colspan=9 style='width:614.35pt;border:inset black 1.0pt;
  padding:0cm 0cm 0cm 0cm;height:11.85pt'>
							<p class=MsoNormal style='word-break:break-all'><span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p>
						</td>
					</tr>
					<tr style='height:11.3pt'>
						<td width=168 colspan=3 style='width:100.8pt;border:inset black 1.0pt;
  padding:0cm 0cm 0cm 0cm;height:11.3pt'>
							<p class=MsoNormal align=center style='text-align:center;word-break:break-all'><strong><span
  style='font-size:10.0pt;font-family:宋体;color:#333333'>分管主任审核意见</span></strong></p>
						</td>
						<td width=1024 colspan=9 style='width:614.35pt;border:inset black 1.0pt;
  padding:0cm 0cm 0cm 0cm;height:11.3pt'>
							<p class=MsoNormal style='word-break:break-all'><span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p>
						</td>
					</tr>
					<tr>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=1>
							<p class='MsoNormal'>&nbsp;</td>
						<td width=1084 colspan=10 style='width:650.6pt;border:none;padding:0cm 0cm 0cm 0cm'>
							<p class=MsoNormal align=center style='text-align:center'><span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p>
						</td>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=106>
							<p class='MsoNormal'>&nbsp;</td>
					</tr>
					<tr style='height:15.0pt'>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=1>
							<p class='MsoNormal'>&nbsp;</td>
						<td width=63 style='width:37.5pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span style='font-size:10.0pt;
  font-family:宋体;color:#333333'>备注：</span></p>
						</td>
						<td width=1022 colspan=9 style='width:613.1pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span style='font-size:10.0pt;
  font-family:宋体;color:#333333'>品名按规定要求填写，如采购标准物质需填写中英文名称，必要时注册</span><span lang=EN-US style='font-size:10.0pt;color:#333333'>CAS</span><span style='font-size:10.0pt;font-family:宋体;color:#333333'>号；</span></p>
						</td>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=106>
							<p class='MsoNormal'>&nbsp;</td>
					</tr>
					<tr style='height:15.0pt'>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=1>
							<p class='MsoNormal'>&nbsp;</td>
						<td width=63 style='width:37.5pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p>
						</td>
						<td width=1022 colspan=9 style='width:613.1pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span style='font-size:10.0pt;
  font-family:宋体;color:#333333'>质量保证要求处应填写是否为标准物质，产品生产厂家，或注册与某型号仪器配套使用等；</span></p>
						</td>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=106>
							<p class='MsoNormal'>&nbsp;</td>
					</tr>
					<tr style='height:15.0pt'>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=1>
							<p class='MsoNormal'>&nbsp;</td>
						<td width=63 style='width:37.5pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p>
						</td>
						<td width=1022 colspan=9 style='width:613.1pt;border:none;padding:0cm 0cm 0cm 0cm;
  height:15.0pt'>
							<p class=MsoNormal style='word-break:break-all'><span style='font-size:10.0pt;
  font-family:宋体;color:#333333'>采购原因处按实际情况填写为：零库存需采购、已有库存但不足使用一周、科研制标用或备货等。</span></p>
						</td>
						<td style='border:none;padding:0cm 0cm 0cm 0cm' width=106>
							<p class='MsoNormal'>&nbsp;</td>
					</tr>
					<tr height=0>
						<td width=1 style='border:none'></td>
						<td width=58 style='border:none'></td>
						<td width=73 style='border:none'></td>
						<td width=125 style='border:none'></td>
						<td width=116 style='border:none'></td>
						<td width=97 style='border:none'></td>
						<td width=54 style='border:none'></td>
						<td width=47 style='border:none'></td>
						<td width=54 style='border:none'></td>
						<td width=54 style='border:none'></td>
						<td width=142 style='border:none'></td>
						<td width=77 style='border:none'></td>
					</tr>
				</table>

			</div>

			<p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>

		</div>
								</div>		
								
																	
								<div class="space-4"></div>

									
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

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
			
		<%@include file="/assets/utiljsp/jsinclude/jqgridjs.jsp" %>
		
		<script src="<%=basePath%>assets/js/jqGrid/jqGridStyleJS.js"></script>

		<script type="text/javascript">
			var selectsid = "#msg";
			$("#select").change(
			function getData(){
				var type = $("#select").val();
		    	jQuery(grid_selector).jqGrid('setGridParam', {
		                  url : "<%=basePath%>"+
			                  "PurchaseManageAction/getdanjuchuli"+"?q=2&type=" + type,
			                  //_search=false&nd=1460383241110&rows=10&page=1&sidx=user_id&sord=asc
			                  page : 1
			                }).trigger('reloadGrid');
			});
			$("#daochu").click(function(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				if(""==idss){
					alert("请选择要提交的数据编号");
					return false;
				}
				$.ajax({
					 type: "GET",
		             url: "<%=basePath%>"+"PurchaseManageAction/getdaochu",
		             data: {"idss":idss.toString(), "time":new Date()},
		             success: function(data){
		            
		             }
		         });
			});
			$("#dayin").click(function(obj){
				    var newWindow=window.open("打印窗口","_blank");
			   		var docStr = obj.innerHTML;
			        newWindow.document.write(docStr);
			    	newWindow.document.close();
			    	newWindow.print();
			    	newWindow.close();
			});
			var datalength = 0;
			$("#xianshi").click(function(){
				var idss = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
				if(""==idss){
					alert("请选择要提交的数据编号");
					return false;
				}
				/* alert(idss.length());
				if(idss.length>1){
					alert("只能选择单条数据");
					return false;
				} */
				$.ajax({
					 type: "GET",
		             url: "<%=basePath%>"+"PurchaseManageAction/getxianshi",
		             data: {"idss":idss.toString(), "time":new Date()},
		             dataType:'json',
		             success: function(data){
			             if(data==null){
			            	for(var i=0;i<datalength;i++){
		            	   		$("#datas"+i).remove();
		           		 	}
			            	datalength = 0;
			             }else{
			             	for(var i=0;i<datalength;i++){
		            	   		$("#datas"+i).remove();
		           		 	}
			             	datalength = data.length;
			             	for(var i=0;i<data.length;i++){
			             	 $("#tr_first").after(
				             	 "<tr style='height:22.5pt' id = 'datas"+i+"'>"+
					             	 "<td width=168 colspan=3 style='width:100.8pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span style='font-size:10.0pt;font-family:宋体;color:#333333' id='goods_name"+i+"'></span></p> "+
									"</td> "+
									"<td width=165 style='width:99.25pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm; height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span style='font-size:10.0pt; font-family:宋体;color:#333333' id='goods_standard"+i+"'> </span> "+
										"</p> "+
									"</td> "+
									"<td width=156 style='width:93.35pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm; height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>CAS</span> "+
										"<span style='font-size:10.0pt;font-family:宋体;color:#333333'>： "+
										"<span lang=EN-US id='goods_cas"+i+"'></span></span> "+
										"</p> "+
									"</td> "+
									"<td width=128 style='width:76.75pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'>&nbsp;</span></p> "+
									"</td> "+
									"<td width=71 style='width:42.5pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal align=center style='text-align:center;word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333'id='barcode_validity"+i+"'></span></p> "+
									"</td> "+
									"<td width=61 style='width:36.85pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm; height:22.5pt'> "+
										"<p class=MsoNormal align=center style='text-align:center;word-break:break-all'> "+
										"<span style='font-size:10.0pt;font-family:宋体;color:#333333' id='goods_unit"+i+"'></span></p> "+
									"</td> "+
									"<td width=71 style='width:42.7pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal align=center style='text-align:center;word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333' id='voucher_goods_count"+i+"'></span></p> "+
									"</td> "+
									"<td width=71 style='width:42.7pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal align=center style='text-align:center;word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333' id='goods_number"+i+"'></span></p> "+
									"</td> "+
									"<td width=194 style='width:116.5pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span style='font-size:10.0pt;font-family:宋体;color:#333333'></span></p> "+
									"</td> "+
									"<td width=106 style='width:63.75pt;border:inset black 1.0pt;padding:0cm 0cm 0cm 0cm;height:22.5pt'> "+
										"<p class=MsoNormal style='word-break:break-all'> "+
										"<span lang=EN-US style='font-size:10.0pt;font-family:宋体;color:#333333' id='voucher_remark"+i+"'>&nbsp;</span></p> "+
									"</td>	 "+
				             	 "</tr>"); 
				             	 $("#voucher_id").html(idss); 
				             	 $("#goods_name"+i).html(data[i].goods_name); 
				             	 $("#goods_standard"+i).html(data[i].goods_standard);
				             	 $("#goods_cas"+i).html(data[i].goods_cas); 
				             	 $("#barcode_validity"+i).html(data[i].barcode_validity); 
				             	 $("#goods_unit"+i).html(data[i].goods_unit); 
				             	 $("#voucher_goods_count"+i).html(data[i].voucher_goods_count); 
				             	 $("#goods_number"+i).html(data[i].goods_number); 
				             	 $("#voucher_remark"+i).html(data[i].voucher_remark); 
				             	 $("#user_lianxiren").html(data[i].user_lianxiren); 
				             	 $("#voucher_createtime"+i).html(data[i].voucher_createtime); 
			             		}
		             	 }
		           	 }
		         });
			});
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"PurchaseManageAction/getdanjuchuli?type=3";
			var tablename = "单据列表";
			var pageediturl ="<%=basePath%>"+"StoreManageAction/savedanjuchuli";
			//navButtons
			var editbtn=false;
			var addbtn=false;
			var delbtn=false;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['单据编号', '经手人','涉及仓库','供应商','生成时间'];//
			var pagecolmodel =  [
				
					{
						name: 'voucher_id',
						index: 'voucher_id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'user_name',//经手人名
						index: 'user_name',
						width: 70,
						editable: true,
						editoptions: {
							size: "50",
							maxlength: "50"
						}
					}
					,{
						name: 'storehouse_name',//仓库名
						index: 'storehouse_name',
						width: 100,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "20"
						}
					},{
						name: 'user_lianxiren',//供应商名
						index: 'user_lianxiren',
						width: 50,
						editable: true,
						editoptions: {
							size: "10",
							maxlength: "10"
						}
					
					},				
					{
						name: 'voucher_createtime',
						index: 'voucher_createtime',
						width: 100,
						editable: false,
						sorttype:"date",
						unformat: pickDate
					}
					];
					
			
					
		</script>
		<script src="<%=basePath%>assets/js/jqGrid/jqGridOneJS.js"></script>	
</body>
</html>
