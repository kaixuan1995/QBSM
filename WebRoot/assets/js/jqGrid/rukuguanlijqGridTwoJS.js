/**
 * 
 * 			var selectsid = "#msg";
 			//主表
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
		
			var pageurl ="<%=basePath%>"+"demoServlet";
			var tablename = "DEMO";
			var pageediturl ="<%=basePath%>"+"demochangeServlet";
			//navButtons
			var editbtn=true;
			var addbtn=true;
			var delbtn=true;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
			var pagecolnames = ['出库单号', '出库类型', '总金额', '申请人（单位）', '备注'];
			var pagecolmodel =  [
					//{name:'',index:'', width:80, fixed:true, sortable:false, resize:false,search:false,
					//	formatter:'actions', 
					//	formatoptions:{ 
							
							
					//		delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
							//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
					//	}
					//},
					{
						name: 'id',
						index: 'id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'type',
						index: 'type',
						width: 200,
						editable: true,
						edittype: "select",
						editoptions: {
							value: "1:1;2:2;3:3"
						}
					}, {
						name: 'pay',
						index: 'pay',
						width: 60,
						sorttype: "double",
						editable: true
					}, {
						name: 'name',
						index: 'name',
						width: 150,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'text',
						index: 'text',
						width: 250,
						sortable: false,
						editable: true,
						edittype: "textarea",
						editoptions: {
							rows: "2",
							cols: "10"
						}
					} ];
					
			var grid_selectoro = "#grid-tableo";
			var pager_selectoro = "#grid-pagero";		
			var pageurlo ="<%=basePath%>"+"demooServlet";
			var tablenameo = "详细列表";
			var pageediturlo ="<%=basePath%>"+"demoochangeServlet";
			//navButtons
			var editobtn=true;
			var addobtn=true;
			var delobtn=true;
			var searchobtn=true;
			var refreshobtn=true;
			var viewobtn=true;
			var pagecolnameso = ['出库单号', '出库类型', '总金额', '申请人（单位）', '备注'];
			var pagecolmodelo =  [
					//{name:'',index:'', width:80, fixed:true, sortable:false, resize:false,search:false,
					//	formatter:'actions', 
					//	formatoptions:{ 
							
							
					//		delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
							//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
					//	}
					//},
					{
						name: 'id',
						index: 'id',
						key: true,
						width: 100,
						editable: false,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'type',
						index: 'type',
						width: 200,
						editable: true,
						edittype: "select",
						editoptions: {
							value: "1:1;2:2;3:3"
						}
					}, {
						name: 'pay',
						index: 'pay',
						width: 60,
						sorttype: "double",
						editable: true
					}, {
						name: 'name',
						index: 'name',
						width: 150,
						editable: true,
						editoptions: {
							size: "20",
							maxlength: "30"
						}
					}, {
						name: 'text',
						index: 'text',
						width: 250,
						sortable: false,
						editable: true,
						edittype: "textarea",
						editoptions: {
							rows: "2",
							cols: "10"
						}
					} ];	
 */




	$(function(){
		  pageInit();
		});
		function pageInit(){
		  jQuery(grid_selector).jqGrid(
		          {
		            url : pageurl,
		            datatype : "json",
		            mtype:"GET",
		            colNames: pagecolnames,
		    		colModel: pagecolmodel,
		    		viewrecords: true,
		    		rowNum: 10,
		    		rowList: [10, 20, 30],
		    		pager: pager_selector,
		    		altRows: true,
		    		prmNames: {
		    			search: "search"
		    		}, //(1)
		    		//toppager: true,
		    		multiselect: true,
		    		//multikey: "ctrlKey",
		    		multiboxonly: true,
		    		// subGrid : true, 
		    	//	sortname : 'id',
		    		jsonReader: {
		    			root: "rows", // json中代表实际模型数据的入口
		    			page: "page", // json中代表当前页码的数据
		    			total: "total", // json中代表页码总数的数据
		    			records: "records", // json中代表数据行总数的数据
		    			repeatitems: false, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
		    			cell: "cell",
		    			id: "id",
		    			userdata: "userdata",
		    			subgrid: {
		    				root: "rows",
		    				repeatitems: true,
		    				cell: "cell"
		    			}
		    		},

		    		loadComplete: function() {
		    			var table = this;
		    			setTimeout(function() {
		    				styleCheckbox(table);
		    				updateActionIcons(table);
		    				updatePagerIcons(table);
		    				enableTooltips(table);
		    			}, 0);
		    		},
		    		editurl: pageediturl,
		    		caption: tablename,
		    		autowidth: true,
		    		
		            onSelectRow : function(ids) {
//			       		 $.ajax({
//							 type:"GET",
//							 url : getPlaceUrl,
//				             data: {"idss":ids.toString(),"time":new Date()},
//				             success: function(data){
//				            	 alert(data);
//				            	 for(var i =0;i<data.length;i++){
//				       				 var place_id = data[i].place_id;
//				       				 var place_name = data[i].place_name;
//				       				 $("#place").append("<option value='"+ place_id +"'>"+ place_name +"</option>");
//				       			 }
//				             }
//				        });
		            	$("#place option:not(:first)").remove();
			       		var url= getPlaceUrl;
			       		var args = {"idss":ids.toString(),"time":new Date()};
			       		$.getJSON(url,args,function(data){
			       			for(var i =0;i<data.length;i++){
			       				 var place_id = data[i].place_id;
			       				 var place_name = data[i].place_name;
			       				 $("#place").append("<option value='"+ place_id +"'>"+ place_name +"</option>");
			       			 }
			   		 });
		                jQuery(grid_selectoro).jqGrid('setGridParam', {
		                  url : pageurlo+"?q=1&id=" + ids,
		                  page : 1
		                });
		                jQuery(grid_selectoro).jqGrid('setCaption',
		                    tablenameo +" : " + ids).trigger(
		                    'reloadGrid');
		              }
		          });
		//enable search/filter toolbar
			//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
			//switch element when editing inline
			function aceSwitch(cellvalue, options, cell) {
				setTimeout(function() {
					$(cell).find('input[type=checkbox]')
						.wrap('<label class="inline" />')
						.addClass('ace ace-switch ace-switch-5')
						.after('<span class="lbl"></span>');
				}, 0);
			}
			//enable datepicker
			function pickDate(cellvalue, options, cell) {
				setTimeout(function() {
					$(cell).find('input[type=text]')
						.datepicker({
							format: 'yyyy-mm-dd',
							autoclose: true
						});
				}, 0);
			}
		  jQuery(grid_selector).jqGrid('navGrid', pager_selector, {
			  edit: editbtn,
				editicon: 'icon-pencil blue',
				add: addbtn,
				addicon: 'icon-plus-sign purple',
				del: delbtn,
				delicon: 'icon-trash red',
				search: searchbtn,
				searchicon: 'icon-search orange',
				refresh: refreshbtn,
				refreshicon: 'icon-refresh green',
				view: viewbtn,
				viewicon: 'icon-zoom-in grey',
		  }, {
				//edit record form
				closeAfterEdit : true,
			 	recreateForm: true,
				closeOnEscape : true,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
					$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_edit_form(form);
				}
			}, {
				//new record form
				closeAfterAdd: true,
				recreateForm: true,
				viewPagerButtons: false,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
				//	$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_edit_form(form);
				}
			}, {
				//delete record form
				recreateForm: true,
				multipleSearch : true,
				closeOnEscape : true,
				closeAfterSearch : true,
				multipleGroup : true,
				showQuery : true,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
				//	$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					if (form.data('styled')) return false;
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_delete_form(form);
					form.data('styled', true);
				},
			}, {
				//search form
				recreateForm: true,
				closeOnEscape : true,
				afterShowSearch: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').
						wrap('<div class="widget-header" />');
					style_search_form(form);
				},
				afterRedraw: function() {
					style_search_filters($(this));
				},
				multipleSearch: true,
				/**
				multipleGroup:true,
				showQuery: true
				*/
			}, {
				//view record form
				recreateForm: true,
				closeOnEscape : true,
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').
						wrap('<div class="widget-header" />');
				}
			}
		  );
		  
		  
		  jQuery(grid_selectoro).jqGrid({
		    height : 300,
		    url : pageurlo,
		    datatype : "json",
		    colNames: pagecolnameso,
    		colModel: pagecolmodelo,
    		viewrecords: true,
    		rowNum: 10,
    		rowList: [10, 20, 30],
    		pager: pager_selectoro,
    		altRows: true,
    		prmNames: {
    			search: "search"
    		}, //(1)
    		//toppager: true,
    		multiselect: true,
    		//multikey: "ctrlKey",
    		multiboxonly: true,
    		// subGrid : true, 
    		caption : tablenameo,
    		loadComplete: function() {
		    			var table = this;
		    			setTimeout(function() {
		    				styleCheckbox(table);
		    				updateActionIcons(table);
		    				updatePagerIcons(table);
		    				enableTooltips(table);
		    			}, 0);
		    		},
		    		editurl: pageediturlo,
		    		//caption: tablenameo,
		    		autowidth: true,
		  }).navGrid(pager_selectoro, {
			  	edit: editobtn,
				editicon: 'icon-pencil blue',
				add: addobtn,
				addicon: 'icon-plus-sign purple',
				del: delobtn,
				delicon: 'icon-trash red',
				search: searchobtn,
				searchicon: 'icon-search orange',
				refresh: refreshobtn,
				refreshicon: 'icon-refresh green',
				view: viewobtn,
				viewicon: 'icon-zoom-in grey',
		  }, {
				//edit record form
				closeAfterEdit : true,
			 	recreateForm: true,
				closeOnEscape : true,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
					$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_edit_form(form);
				}
			}, {
				//new record form
				closeAfterAdd: true,
				recreateForm: true,
				viewPagerButtons: false,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
				//	$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_edit_form(form);
				}
			}, {
				//delete record form
				recreateForm: true,
				multipleSearch : true,
				closeOnEscape : true,
				closeAfterSearch : true,
				multipleGroup : true,
				showQuery : true,
				afterComplete :function(data) {
					alert(" 操作结果:["+ data.responseText + "]");
				//	$(grid_selector).trigger("reloadGrid");
				},
				beforeShowForm: function(e) {
					var form = $(e[0]);
					if (form.data('styled')) return false;
					form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').
						wrapInner('<div class="widget-header" />');
					style_delete_form(form);
					form.data('styled', true);
				},
			}, {
				//search form
				recreateForm: true,
				afterShowSearch: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').
						wrap('<div class="widget-header" />');
					style_search_form(form);
				},
				afterRedraw: function() {
					style_search_filters($(this));
				},
				multipleSearch: true,
				/**
				multipleGroup:true,
				showQuery: true
				*/
			}, {
				//view record form
				recreateForm: true,
				beforeShowForm: function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').
						wrap('<div class="widget-header" />');
				}
			});

			function style_edit_form(form) {
				//enable datepicker on "sdate" field and switches for "stock" field
				form.find('input[name=sdate]').datepicker({
						format: 'yyyy-mm-dd',
						autoclose: true
					})
					.end().find('input[name=stock]')
					.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove(); //ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>');
				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').remove();
				buttons.eq(0).append('<i class="icon-chevron-left"></i>');
				buttons.eq(1).append('<i class="icon-chevron-right"></i>');
			}

			function style_delete_form(form) {
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove(); //ui-icon, s-icon
				buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>')
			}

			function style_search_filters(form) {
				form.find('.delete-rule').val('X');
				form.find('.add-rule').addClass('btn btn-xs btn-primary');
				form.find('.add-group').addClass('btn btn-xs btn-success');
				form.find('.delete-group').addClass('btn btn-xs btn-danger');
			}

			function style_search_form(form) {
				var dialog = form.closest('.ui-jqdialog');
				var buttons = dialog.find('.EditTable');
				buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
				buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
				buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
			}

			function beforeDeleteCallback(e) {
				var form = $(e[0]);
				if (form.data('styled')) return false;
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				form.data('styled', true);
			}

			function beforeEditCallback(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
			}
			//it causes some flicker when reloading or navigating grid
			//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
			//or go back to default browser checkbox styles for the grid
			function styleCheckbox(table) {

				$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />');


				$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');

			}
			//unlike navButtons icons, action icons in rows seem to be hard-coded
			//you can change them like this in here if you want
			function updateActionIcons(table) {

				var replacement = {
					'ui-icon-pencil': 'icon-pencil blue',
					'ui-icon-trash': 'icon-trash red',
					'ui-icon-disk': 'icon-ok green',
					'ui-icon-cancel': 'icon-remove red'
				};
				$(table).find('.ui-pg-div span.ui-icon').each(function() {
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
				});

			}
			//replace icons with FontAwesome icons like above
			function updatePagerIcons(table) {
				var replacement = {
					'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev': 'icon-angle-left bigger-140',
					'ui-icon-seek-next': 'icon-angle-right bigger-140',
					'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
				};
				$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
				});
			}

			function enableTooltips(table) {
				$('.navtable .ui-pg-button').tooltip({
					container: 'body'
				});
				$(table).find('.ui-pg-div').tooltip({
					container: 'body'
				});
			}
		  
		  jQuery(selectsid).click(function() {
		    var id;
		    id = jQuery(grid_selector).jqGrid('getGridParam', 'selarrrow');
		    alert(id);
		    var url = ajaxurl;
		    var args={"id":id,"time":new Date()};
		    $.post(url,args,function(data){
				if(data){
					alert("操作成功");
				}else{
					alert("操作失败");
				}
			});
		  });
		  jQuery(selectsido).click(function() {
			    var id;
			    id = jQuery(grid_selectoro).jqGrid('getGridParam', 'selarrrow');
			    alert(id);
			    var url = ajaxurlo;
			    var args={"id":id,"time":new Date()};
			    $.post(url,args,function(data){
					if(data){
						alert("操作成功");
					}else{
						alert("操作失败");
					}
				});
		  	});
		}