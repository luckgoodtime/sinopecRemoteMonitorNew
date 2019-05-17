/*
jquery 取得select选中值和文本
$("#seachprov option:selected").val()
$("#seachprov option:selected").text()
单选值：$("input[name='addrId']:checked").val(); 
单选是否选中：$("input[name='addrId']").is(':checked')
 */
$(function() {
	
	//左侧菜单--导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	//左侧菜单--
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
	//******************************************************************//
	// 全选或全不选
	$("#selectAllCheck").click(function() {
		if (this.checked) {
			$("input[name='ids']:checkbox").each(function() {
				$(this).attr("checked", true);
			});
		} else { // 反之 取消全选
			$("input[name='ids']:checkbox").each(function() {
				$(this).attr("checked", false);
			});
		}
	});
	// 列表隔行加不同背景
	$('.tablelist tbody tr:odd').addClass('odd');

	$(".del").click(function() {
		del();
	});

});

function del() {
	if (confirm("确认要删除所选的吗")) {
		$("#myForm").submit();
	}
}

function getNewSubmitForm() {
	var submitForm = document.createElement("FORM");
	document.body.appendChild(submitForm);
	submitForm.method = "POST";
	return submitForm;
}

function createNewFormElement(inputForm, elementName, elementValue) {
	var newElement = document.createElement("input");
	newElement.setAttribute("name", elementName);
	newElement.setAttribute("type", "hidden");
	newElement.setAttribute("value", elementValue);
	inputForm.appendChild(newElement);
	return newElement;
}

/**
 * 
 * 格式化日期时间
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	if (isNaN(this.getMonth())) {
		return '';
	}
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	var o = {
		/* month */
		"M+" : this.getMonth() + 1,
		/* day */
		"d+" : this.getDate(),
		/* hour */
		"h+" : this.getHours(),
		/* minute */
		"m+" : this.getMinutes(),
		/* second */
		"s+" : this.getSeconds(),
		/* quarter */
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		/* millisecond */
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * input 文本框必须输入金额格式,保留小数点两位
 * @param inputId 文本框id
 */
function inputMoneyFormat() {

	for(var i=0;i<arguments.length;i++){
        $("#"+arguments[i]).keyup(function () {
        	
        	var v = $(this).val();
    	    //var reg = v.match(/\d+\.?\d{0,2}/);
        	var reg = v.match(/^(-?\d+)(\.\d{0,2})?$/);
    	    var txt = '';
    	    if (reg != null) {
    	        txt = reg[0];
    	    } else {
    	    	if(v.length == 1)
    	    		txt = v;
    	    }
    	    $(this).val(txt);
    	}).change(function () {
    	    $(this).keypress();
    	    var v = $(this).val();
    	    if (/\.$/.test(v))
    	    {
    	        $(this).val(v.substr(0, v.length - 1));
    	    }
    	});
    }
}

// 加法函数，用来得到精确的加法结果
// 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
// 调用：accAdd(arg1,arg2)
// 返回值：arg1加上arg2的精确结果
function accAdd(arg1, arg2) {
	var r1, r2,  m, maxlen, result;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	console.log("length:" + r1 + "_" + r2);
	maxlen = Math.max(r1, r2);
	m = Math.pow(10, maxlen);
	console.log("Math.pow:" + m);
	result = (arg1 * m + arg2 * m) / m;
	console.log("result:" + result);
	return result.toFixed(maxlen);
}


//将浮点数四舍五入，取小数点后2位     
function toDecimal(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return;
	}
	f = Math.round(x * 100) / 100;
	return f;
}

//输入正负数，保证指定位数小数
function checkNum(obj,pointCount) {
	//var reg = /^[+-]?\d*\.?\d{0,4}$/;
	if(!pointCount) pointCount = 2;
	var reg = new RegExp("^[+-]?\\d*\\.?\\d{0," + pointCount  + "}$"); 

	if(!reg.test(obj.value)) {
		alert("请输入" + pointCount +"位小数");
		obj.value = "";
		return false;
	}
	return true;
}



//检查ids是否有勾选
function checkIds() {
	var flag = false;
	$("input[name='ids']:checkbox").each(function() {
		if($(this).prop("checked")) {
			flag = true;
			return false;
		}
	});
	return flag;
}