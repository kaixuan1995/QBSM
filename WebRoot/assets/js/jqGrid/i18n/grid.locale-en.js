;(function($){
/**
 * jqGrid English Translation
 * Tony Tomov tony@trirand.com
 * http://trirand.com/blog/ 
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "当前 {0} - {1} 共 {2}",
		emptyrecords: "未找到记录",
		loadtext: "正在加载...",
		pgtext : "第 {0} 页  共 {1} 页"
	},
	search : {
		caption: "检索...",
		Find: "检索",
		Reset: "重置",
		odata: [{ oper:'eq', text:'相等'},{ oper:'ne', text:'不相等'},{ oper:'lt', text:'小于'},{ oper:'le', text:'小于等于'},{ oper:'gt', text:'大于'},{ oper:'ge', text:'大于等于'},{ oper:'bw', text:'在...之间'},{ oper:'bn', text:'不在...之间'},{ oper:'in', text:'在'},{ oper:'ni', text:'不在'},{ oper:'ew', text:'以...结束'},{ oper:'en', text:'不以...结束'},{ oper:'cn', text:'包含'},{ oper:'nc', text:'不包含'}],
		groupOps: [	{ op: "AND", text: "所有" },	{ op: "OR",  text: "任意" }	]
	},
	edit : {
		addCaption: "添加",
		editCaption: "修改",
		bSubmit: "提交",
		bCancel: "取消",
		bClose: "关闭",
		saveData: "信息已经被修改! 保存修改?",
		bYes : "是",
		bNo : "否",
		bExit : "取消",
		msg: {
			required:"字段是必填的",
			number:"请输入有效数字",
			minValue:"值必须大于或等于",
			maxValue:"值必须小于或等于",
			email: "不是有效的电子邮件",
			integer: "请输入有效的整数值",
			date: "请输入有效日期值",
			url: "是不是一个有效的网址。前缀的要求（'http：/ /”或“https：/ /”）",
			nodefined : "未定义！",
			novalue : "返回值是必需的！",
			customarray : "自定义函数应该返回数组！",
			customfcheck : "自定义函数应该是在自定义检查的情况下出现的！"
			
		}
	},
	view : {
		caption: "查看记录",
		bClose: "关闭"
	},
	del : {
		caption: "删除",
		msg: "删除选定的记录（s）？",
		bSubmit: "删除",
		bCancel: "取消"
	},
	nav : {
		edittext: "",
		edittitle: "编辑选定行",
		addtext:"",
		addtitle: "添加新的行",
		deltext: "",
		deltitle: "删除选定行",
		searchtext: "",
		searchtitle: "查找记录",
		refreshtext: "",
		refreshtitle: "重载网格",
		alertcap: "警告",
		alerttext: "请选择行",
		viewtext: "",
		viewtitle: "查看选定行"
	},
	col : {
		caption: "选择列",
		bSubmit: "确定",
		bCancel: "取消"
	},
	errors : {
		errcap : "错误",
		nourl : "没有网址被设置",
		norecords: "没有过程被记录",
		model : "列名的个数不等于模型的个数"
	},
	formatter : {
		integer : {thousandsSeparator: ",", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"日", "一", "二", "三", "四", "五", "六",
				"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
			],
			monthNames: [
				"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月",
				"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
			],
			AmPm : ["am","pm","AM","PM"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';},
			srcformat: 'Y-m-d',
			newformat: 'n/j/Y',
			parseRe : /[Tt\\\/:_;.,\t\s-]/,
			masks : {
				// see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
				// and see http://docs.jquery.com/UI/Datepicker/formatDate
				// and https://github.com/jquery/globalize#dates for alternative formats used frequently
				// one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
				// information about date, time, numbers and currency formats used in different countries
				// one should just convert the information in PHP format
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				// short date:
				//    n - Numeric representation of a month, without leading zeros
				//    j - Day of the month without leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				// example: 3/1/2012 which means 1 March 2012
				ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
				// long date:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
				// long date with long time:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
				// month day:
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
				// short time (without seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
				// long time (with seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				// month with year
				//    Y - A full numeric representation of a year, 4 digits
				//    F - A full textual representation of a month
				YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);