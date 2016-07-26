/**
 * var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var pageurl ="<%=basePath%>"+"#";
			var tablename = "仓库列表";
			var pageediturl ="<%=basePath%>"+"#";
			//navButtons
			var editbtn=true;
			var addbtn=true;
			var delbtn=true;
			var searchbtn=true;
			var refreshbtn=true;
			var viewbtn=true;
		
			var pagecolnames = ['仓库id', '仓库名', '添加时间'];
			var pagecolmodel = [
									{
										name: 'id',
										index: 'id',
										key: true,
										width: 60,
										editable: false,
										editoptions: {
											size: "20",
											maxlength: "30"
										}
									},
									{
										name: 'name',
										index: 'name',
										width: 100,
										editable: true,
										editoptions: {
											size: "20",
											maxlength: "30"
										}
									},
						 			{
										name: 'time',
										index: 'time',
										width: 70,
										editable: true,
										sorttype: "date",
										
									}
								
					
								];
 */

jQuery(function($) {
	
	jQuery(grid_selector).jqGrid({
		//direction: "rtl",
		//data: grid_data,
		url: pageurl,
		mtype: "get",
		datatype: "json",
		height: 400,
		colNames: pagecolnames,
		colModel: pagecolmodel,
	//	sortname:'user_id',
		viewrecords: true,
		rowNum: 10,
		rowList: [10, 20, 30],
		pager: pager_selector,
		altRows: true,
		editurl: pageediturl,
		caption: tablename,
		autowidth: true,
//		prmNames: {
//			search: "search"
//		}, //(1)
		//toppager: true,
		multiselect: true,
//		//multikey: "ctrlKey",
		multiboxonly: true,
 	// subGrid : true, 
		jsonReader: {
			root: "rows", // json中代表实际模型数据的入口
			page: "page", // json中代表当前页码的数据
			total: "total", // json中代表页码总数的数据
			records: "records", // json中代表数据行总数的数据
			repeatitems: false, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
//			cell: "cell",
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
	//navButtons
	jQuery(grid_selector).jqGrid('navGrid', pager_selector, { //navbar options
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
			alert(" 操作结果:[ "+ data.responseText + " ]");
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
			alert(" 操作结果:[ "+ data.responseText + " ]");
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
			alert(" 操作结果:[ "+ data.responseText + " ]");
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
		multipleSearch: true,
		afterShowSearch: function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-title').
				wrap('<div class="widget-header" />');
			style_search_form(form);
		},
		afterRedraw: function() {
			style_search_filters($(this));
		},
		
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
	});

	
	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
});