$(function(){	
	
		//初始化送气地址，省市县级联
		PCASNew();
		//加载订单列表
		requestorderDataGrid();
		//今日订单已受理数量，待受理数量
		orderHandleCount();
});

//省市县先清空，再设置
function PCASNew(province,city,county) {
	
	$("#s_province").empty();
	$("#s_city").empty();
	$("#s_county").empty();
	
	if(!province) {
		province = "浙江省";
	}
	if(!city) {
		city = "台州市";
	}
	if(!county) {
		county = "临海市";
	}
	new PCAS("s_province","s_city","s_county",province,city,county);
}

//通话计时开始
var startTime;
var answerInterval;
var pad = function(num) {
	return (num < 10 ? '0' : '') + num;
};
var formatTime = function(time, settings) {
	var i, len, ms, output, prefix, separator, step, steps, value, _i, _len;
	settings = settings || {};
	steps = [3600000, 60000, 1000, 10];
	separator = ['', ':', ':', '.'];
	prefix = '';
	output = '';
	ms = settings.milliseconds;
	len = steps.length;
	value = 0;
	if (time < 0) {
	  time = Math.abs(time);
	  prefix = '-';
	}
	for (i = _i = 0, _len = steps.length; _i < _len; i = ++_i) {
	  step = steps[i];
	  value = 0;
	  if (time >= step) {
	    value = Math.floor(time / step);
	    time -= value * step;
	  }
	  if ((value || i > 1 || output) && (i !== len - 1 || ms)) {
	    output += (output ? separator[i] : '') + pad(value);
	  }
	}
	return prefix + output;
};
function anserTimerunner($obj, method) {
	if(method == 'start') {		
		startTime = new Date().getTime();		
		answerInterval = setInterval(function(){
			var _startTime = new Date().getTime();
			$obj.text((formatTime(_startTime - startTime)));
		},2000);
		
	} else if(method == 'stop') {
		clearInterval(answerInterval);
	}
}
//通话计时结束

var customerList;//临时保存查询出来的客户列表

//获取电话
function getPhone() {
	var phoneNo = $("#phoneNo").val();//呼入电话
	//浙江临海市电话前面带了9,外地号码带了90
	//如 913566863501--本地手机，9013671108090--外地手机，9057685686327--本地座机，外地座机9001067651238
	//其实只有是否是 901
	if(phoneNo.indexOf("901") == 0)
		phoneNo = phoneNo.slice(2);
	else if(phoneNo.indexOf("9") == 0)
		phoneNo = phoneNo.slice(1);
	
	if(phoneNo.indexOf("00") == 0)
		phoneNo = phoneNo.slice(1);
	
	return phoneNo;
}

//根据手机号查询用户信息，如果不存在，则根据用气证查询用户；可能查询到多个用户
function getCustomerList() {
	
	$("#tip").html("");
	
	var phoneNo = getPhone();

	$("#phoneNo").val(phoneNo);	
	
	var gasCardNo = $("#gasCardNo").val();//用气证号
	var cusName = $("#cusName").val();//客户姓名
	if(cusName)cusName = cusName.trim();

	sy.progress();//进度条

	$.ajax({
	      type : "post",
	      url : "getCustomerList.do",
	      data:{"phoneNo":phoneNo,"gasCardNo":gasCardNo,"cusName":cusName},
	      success : function(d){
	         console.log(d);
	         sy.progressClose();//关闭进度条
	         customerList = d;
	         
	         if(d != null && d.length >0) {
	        	 //第一条直接显示在页面，其他信息显示在隐藏页面
	        	 $("#customerForm").find("#id").val(d[0].id);
	        	 $("#requestOrderForm").find("#customerId").val(d[0].id);	        	 
	        	 
        		 $("#gasCardNo").val(d[0].gasCardNo);
	        	 $("#cusName").val(d[0].cusName);
	        	 $("#cusContactTel").val(d[0].cusContactTel);
	        	 $("#cusContactIdNo").val(d[0].cusContactIdNo);
	        	 
	        	 PCASNew(d[0].province,d[0].city,d[0].county);//送气地址
	        	 $("#receiptPlace").val(d[0].town+d[0].village);//送气地址:详细地址
	        	 
	        	 
	        	 var customerIdInput = "";
	        	 for(var i=1;i<d.length;i++){
	        		 
	        		 customerIdInput += '<p><input type="radio" name="customerIdItem"   value="'+d[i].id+'" />';
	        		 customerIdInput += '<span>'+d[0].province+d[i].city+d[i].county+d[i].town+d[i].village+'</span></p>';
		 		 }
	        	 $("#addressListDiv").html(customerIdInput);
	        	 
	        	//获取价格
	     		getPrice();
	        	 //用气历史
	        	 requestOrderHistory();
	         }else {
	        	 //$.messager.alert('提示','没有查询到信息');
	        	 $("#tip").html("客户手机号："+phoneNo+"，用气证号:"+gasCardNo+" 暂无信息");
	        	 reset();
	         }
	         
	      },
	      error : function(d){
	    	  sy.progressClose();
	    	  $.messager.alert('提示',"网络异常,"+d,'error');
	      }
	 });
}



//派单：新增或修改用户，然后保存订气订单
function requestOrderDown() {
	
	var bottleType = $("#bottleType").val();
	if(bottleType == '安检' || bottleType == '维修') {
		 maintainOrderDown();
		 return;
	}
	
	//验证数据
	var price = $("#price").val();//单价
	var money = $("#money").val();//总金额
	
	if($.trim(price) == "") {
		$.messager.alert('提示',"请填写单价",'error');
		return;
	}
	if($.trim(money) == "") {
		$.messager.alert('提示',"请填写总金额",'error');
		return;
	}
	
	$.messager.confirm('提示', '派单吗?', function(r){
		if (r){
			sy.progress();//进度条
			
            //1、新增订单
		     $.ajax({
			      type : "post",
			      url : "requestOrderDown.do",
			      data : $("#requestOrderForm").serialize(),
			      dataType:"json",
			      success : function(d){
     		    	  sy.progressClose();//关闭进度条
			          //console.log(d);
			          if(d) {
			        	  $.messager.alert('提示',d.tip);
			        	  if(d.suc) {
				        	  reset();
					          $('#requestorderDataGrid').datagrid('load');//刷新今日订单情况
				          }
			        	  //用气证号获取焦点
			        	  $("#gasCardNo").focus();
			          }else {
			        	  $.messager.alert('提示',"网络异常",'error');
			        		//用气证号获取焦点
			        		$("#gasCardNo").focus();
			          }
			      },
			      error : function(d){
			    	  sy.progressClose();
			    	  $.messager.alert('提示',"网络异常,"+d,'error');
			    	 //用气证号获取焦点
			    	 $("#gasCardNo").focus();
			      }
			 }); 
			
		}
	});
}

//业务受理
function maintainOrderDown(){
    
	$.messager.confirm('提示', '派检查维修单吗?', function(r){
		if (r){
			sy.progress();//进度条			
			
            //1、新增订单
		     $.ajax({
			      type : "post",
			      url : "maintainOrderDown.do",
			      data : $("#requestOrderForm").serialize(),
			      dataType:"json",
			      success : function(d){
     		    	  sy.progressClose();//关闭进度条
			          //console.log(d);
			          if(d) {
			        	  $.messager.alert('提示',d.tip);
			        	  if(d.suc) {
				        	  reset();
					          $('#requestorderDataGrid').datagrid('load');//刷新今日订单情况
				          }
			        	  //用气证号获取焦点
			        	  $("#gasCardNo").focus();
			          }else {
			        	  $.messager.alert('提示',"网络异常",'error');
			        		//用气证号获取焦点
			        		$("#gasCardNo").focus();
			          }
			      },
			      error : function(d){
			    	  sy.progressClose();
			    	  $.messager.alert('提示',"网络异常,"+d,'error');
			    	 //用气证号获取焦点
			    	 $("#gasCardNo").focus();
			      }
			 }); 
		}
	});
    
}

//清空表单
function reset() {
	 /*$("#customerForm").each(function(){
	 	//$(this)[0].reset();
		$(this).val("");
	 });*/
	$("#id").val("");
	$("#gasCardNo").val("");
	$("#cusName").val("");
	$("#cusContactTel").val("");
	$("#receiptPlace").val("");
	$("#cusContactIdNo").val("");
	$("#accountOpenDate").val("");
	$("#note").val("");	
	
	$('#money').val("");
	$('#quantity').val("1");
	
	if($("#bottleType").val() != '15KG') {
		$("#bottleType").val("15KG");
		//$("#price").val("");
	}
	
	//预约时间恢复
	
	$("#expectArriveTime").val(today);
	$("#reserve1").val("");
	$("#reserve2").val("");
	$("#reserve3").val("");
	$("#reserve4").val("");
	
	 //$("#customerForm")[0].reset();//清空
     //$("#requestOrderForm")[0].reset();//清空
}

var acceptStation = [{ "value": "海圳荣江南门店", "text": "海圳荣江南门店" }, { "value": "海圳荣江滨门店", "text": "海圳荣江滨门店" }
			, { "value": "海圳荣涌泉门店", "text": "海圳荣涌泉门店" }, { "value": "海圳荣红光气站", "text": "海圳荣红光气站" }
			, { "value": "海圳荣汛桥门店", "text": "海圳荣汛桥门店" }, { "value": "海圳荣东塍门店", "text": "海圳荣东塍门店" }
			];

//加载订单列表
function requestorderDataGrid() {

	$("#requestorderDataGrid").datagrid({
	      title: '订单情况',
	      url: 'requestorderDataGrid.do',
	      toolbar: "#requestorderToolbar",
	      width: "100%",
          height: "300px",
	      pagination: true,//是否分页       
	      rownumbers: true,//行号
	      singleSelect: true,
	      striped: true,//交替行换色
	      nowrap: true,
	      collapsible: true,
	      fitColumns: true,//自动适应。
	      //queryParams: { gasCardNo:""},
	      onLoadSuccess: _onLoadSuccess,
	      onClickCell:onClickCell,
	      columns: [[
	        { field: 'requestOrderNo', title: '工单编号',  sortable: true },
	        {field: 'orderSource', title: '订单来源',  sortable: true,          
	          formatter: function (val, row, index) {
	            if(val =="CALLCENTER") {
	            	return "呼叫中心";
	            }else if(val =="WECHAT") {
	            	return "微信";
	            }
	            return "手工录入";
	          }
	        },
	        { field: 'customerName', title: '客户名称', sortable: true,
	        	styler:rowColor	        
	        },
	        { field: 'gasCardNo', title: '用气证号', sortable: true },
	        { field: 'customerTel', title: '客户电话',  sortable: true },
	        { field: 'bottleType', title: '瓶型',  sortable: true },
	        { field: 'statusNm', title: '受理状态' },
	        { field: 'acceptStation', title: '指派门店', editor: { type: 'combobox', options: { data: acceptStation, valueField: "value", textField: "text" } } },
	        { field: 'acceptPerson', title: '接单人', sortable: true },
	        { field: 'acceptPersonTel', title: '接单人电话', sortable: true },
	        { field: 'expectArriveTime', title: '预约时间', sortable: true },
	        { field: 'scanFinishTime', title: '扫描时间', sortable: true },
	        { field: 'customerAddress', title: '客户地址', width: '280',  sortable: true },
	        { field: 'quantity', title: '数量', sortable: true },
	        { field: 'price', title: '单价', sortable: true },
	        { field: 'money', title: '金额', sortable: true },
	        { field: 'note', title: '备注',  
	        	formatter: function (val, row, index) {
	        		var str = "";
	                str += '<span title="'+val+'" >' + val+ '</span>';
	                return str;
	          }
	        },
	        { field: 'CreateBy', title: '操作',
	        	formatter: function (val, row, index) {
	        		var str = "";
	              /*  str += '<a href="javascript:void(0);" onclick="doEdit(' + row.requestOrderNo + ')">编辑</a>';*/
	                str += '  ';
	                str += '<a href="javascript:void(0);" onclick="orderDel(' + row.requestOrderNo + ')">撤销</a>';
	                return str;
	          }}
	      ]],
	      onAfterEdit:function(index,row){
              var updated  = $('#requestorderDataGrid').datagrid('getChanges','updated');
              if(updated.length>0){
                  
            	  $.ajax({
				      type : "post",
				      url : "updateAcceptStation.do",
				      data : {requestOrderNo:row.requestOrderNo,acceptStation:row.acceptStation},
				      dataType:"json",
				      success : function(d){
				          if(d) {
				        	  $.messager.alert('提示',d.tip);
				        	  doSearch();
				          }else {
				        	  $.messager.alert('提示',"修改失败："+d.tip,'error');
				          }
				      }
				 }); 
            	  
              }
			},
	     
	    });
}

var editIndex = undefined;  
function onClickCell(index, field) {  
    if (endEditing()) {  
        $('#requestorderDataGrid').datagrid('selectRow', index)  
                .datagrid('editCell', { index: index, field: field });  
        editIndex = index;  
    }  
}  
function endEditing() {  
    if (editIndex == undefined) { return true }  
    if ($('#requestorderDataGrid').datagrid('validateRow', editIndex)) {  
        $('#requestorderDataGrid').datagrid('endEdit', editIndex);  
        editIndex = undefined;  
        return true;  
    } else {  
        return false;  
    }  
}  

$.extend($.fn.datagrid.methods, {  
    editCell: function (jq, param) {  
        return jq.each(function () {  
            var opts = $(this).datagrid('options');  
            var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));  
            for (var i = 0; i < fields.length; i++) {  
                var col = $(this).datagrid('getColumnOption', fields[i]);  
                col.editor1 = col.editor;  
                if (fields[i] != param.field) {  
                    col.editor = null;  
                }  
            }  
            $(this).datagrid('beginEdit', param.index);  
            for (var i = 0; i < fields.length; i++) {  
                var col = $(this).datagrid('getColumnOption', fields[i]);  
                col.editor = col.editor1;  
            }  
        });  
    }  
}); 

//颜色设置
function rowColor(value, row, index) {
    if(row.statusNm=='待派单'){
        return 'background-color:red;color:white';
    } else if(row.statusNm == '待扫描') {
    	 return 'background-color:green;color:white';
    }else if(row.statusNm == '预约中') {
    	return 'background-color:grey;color:white';
    }
}

var needPayFee = false;
var today = new Date().format("yyyy-MM-dd");
function warnNeedPayFee(value, row, index) {
    if(index == 0 && value <= today){
        return 'background-color:red;color:white';
        needPayFee = true;
    }
}


//撤销
function orderDel(id) {
	$.messager.confirm('提示', '确认撤销吗?', function(r){
        if (r){ 
        	//订单状态改为删除
		     $.ajax({
			      type : "post",
			      url : "orderDel.do?requestOrderNo="+id,
			      dataType:"json",
			      success : function(d){
			          if(d) {
			        	  $.messager.alert('提示',d.tip);
			        	  if(d.suc) {
			        		  doSearch();
				          }
			          }else {
			        	  $.messager.alert('提示',"网络异常",'error');
			          }
			      },
			      error : function(d){
			    	  $.messager.alert('提示',"网络异常,"+d.responseText,'error');
			      }
			 }); 
        }
    });
}

//搜索
function doSearch() {
    $('#requestorderDataGrid').datagrid('load', {
    	gasCardNo: $("#gasCardNoQuery").val()
    });
}

//刷新
function refresh() {
	$("#gasCardNoQuery").val("");
    $('#requestorderDataGrid').datagrid('load');
}

//选择地址
function selectAddress() {
	
	var customerId = $('input[name="customerIdItem"]:checked').val();
	if(!customerId) {
		 $.messager.alert('提示','请先选择一条记录','error');
		 return;
	}
	
	 if(customerList != null && customerList.length >0) {
		 for(var i=0;i<customerList.length;i++){
			 if(customerList[i].id == customerId) {
				 
				 $("#customerForm").find("#id").val(customerList[i].id);
				 $("#requestOrderForm").find("#customerId").val(customerList[i].id);
        		 $("#gasCardNo").val(customerList[i].gasCardNo);
	        	 $("#cusName").val(customerList[i].cusName);
	        	 $("#cusContactTel").val(customerList[i].cusContactTel);
	        	 $("#cusContactIdNo").val(customerList[i].cusContactIdNo);
	        	 
	        	 PCASNew(customerList[i].province,customerList[i].city,customerList[i].county);//送气地址
	        	 $("#receiptPlace").val(customerList[i].town+customerList[i].village);//送气地址:详细地址
	        	 
				 break;
			 }
		 }
	 }
	
	$(".AAd").hide();
}

//今日订单已受理数量，待受理数量 ?r="+Math.random()
function orderHandleCount() {
	$.ajax({
	      type : "post",
	      url : "orderHandleCount.do",
	      dataType:"json",
	      success : function(d){
	          if(d) {
	        	  $("#unhandledOrderCount").html(d.unhandledOrderCount);
	        	  $("#handledOrderCount").html(d.handledOrderCount);
	          }
	      }
	 }); 
}



//更新客户信息
function updateCustomer(type) {
	
	//验证数据
	
	if($.trim($("#gasCardNo").val()) == "") {
		$.messager.alert('提示',"请填写用气证号",'error');
		return;
	}
	if($.trim($("#cusName").val()) == "") {
		$.messager.alert('提示',"请填写客户姓名",'error');
		return;
	}
	if($.trim($("#cusContactTel").val()) == "") {
		$.messager.alert('提示',"请填写联系电话",'error');
		return;
	}
	
		sy.progress();//进度条
		
		//新增或修改客户信息
		$.ajax({
		      type : "post",
		      url : "customerAddOrUpdate.do",
		      data : $("#customerForm").serialize(),
		      dataType:"json",
		      success : function(d){
		          //console.log(d);
		          sy.progressClose();
		         if(d && d.suc) {
		        	 $("#id").val(d.data);
		        	 $.messager.alert('提示',d.tip);
		         }else {
		        	 console.warn(d)
		        	 $.messager.alert('提示',"更新客户信息失败,"+d.tip,'error');
		         }
		      },
		      error : function(d){
		    	  sy.progressClose();
		    	  $.messager.alert('提示',"网络异常,"+d,'error');
		      }
		 });
}



//用气历史+费用历史
function requestOrderHistory() {
	
	$('.click-div .celltabel-div').addClass("celltabel-active");
	
	$("#feeOrderHistory").datagrid({
	      //title: '历史缴费记录',
	      url: 'feeOrderHistory.do',
	     // toolbar: "#requestorderToolbar",
	      width: "100%",
        height: "270px",
	      pagination: true,//是否分页       
	      rownumbers: true,//行号
	      singleSelect: true,
	      striped: true,//交替行换色
	      nowrap: true,
	      collapsible: true,
	      fitColumns: true,//自动适应
	      queryParams: { gasCardNo:$("#gasCardNo").val(),customerTel:getPhone()},
	      onLoadSuccess: _checkNeedPayFee,
	      columns: [[
	        { field: 'id', title: '记录号',  sortable: true },
	        {field: 'gasCardNo', title: '用户卡号',  sortable: true },
	        { field: 'feeDate', title: '日期', sortable: true},
	        {field: 'inOutFlag', title: '收/退',  sortable: true,          
		          formatter: function (val, row, index) {
		            if(val =="1") {
		            	return "收费";
		            }else {
		            	return "退费";
		            }
		          }
		     },	        
	        { field: 'bottleType', title: '瓶型', sortable: true },
	        { field: 'bottleNum', title: '数量', sortable: true },
	        { field: 'price', title: '单价', sortable: true },
	        { field: 'effectiveDate', title: '起算日期',  sortable: true },
	        { field: 'expireDate', title: '终止日期',  sortable: true , styler:warnNeedPayFee},
	        { field: 'money', title: '金额' },
	        { field: 'depreciation', title: '折旧费' },
	        { field: 'amountReceived', title: '实收金额' },
	        { field: 'note', title: '说明', sortable: true },
	        { field: 'statusNm', title: '状态', sortable: true }      
	      ]],
	 });
	
	$("#requestOrderHistory").datagrid({
	      //title: '用气历史',
	      url: 'requestOrderHistory.do',
	     // toolbar: "#requestorderToolbar",
	      width: "100%",
	      height: "270px",
	      pagination: true,//是否分页       
	      rownumbers: true,//行号
	      singleSelect: true,
	      striped: true,//交替行换色
	      nowrap: true,
	      collapsible: true,
	      fitColumns: true,//自动适应
	      queryParams: { gasCardNo:$("#gasCardNo").val(),customerTel:getPhone()},
	      onLoadSuccess: _onLoadSuccessHistory,
	      columns: [[
	        {field: 'requestOrderNo', title: '工单编号',  sortable: true },
	        {field: 'orderSource', title: '订单来源',  sortable: true,          
	          formatter: function (val, row, index) {
	            if(val =="CALLCENTER") {
	            	return "呼叫中心";
	            }else if(val =="WECHAT") {
	            	return "微信";
	            }
	            return "手工录入";
	          }
	        },
	        { field: 'customerName', title: '客户名称', sortable: true,
	        	//styler:rowColor	        
	        },
	        { field: 'gasCardNo', title: '用气证号', sortable: true },
	        { field: 'customerTel', title: '客户电话',  sortable: true },
	        { field: 'acceptPerson', title: '送气工', sortable: true },
	        { field: 'bottleType', title: '瓶型',  sortable: true },
	        { field: 'statusNm', title: '受理状态' },
	        { field: 'acceptStation', title: '指派门店', sortable: true },
	        { field: 'orderCreateTime', title: '创建时间', sortable: true },
	        { field: 'customerAddress', title: '客户地址', width: '250',  sortable: true },
	        { field: 'quantity', title: '数量', sortable: true },
	        { field: 'price', title: '单价', sortable: true },
	        { field: 'money', title: '金额', sortable: true },
	      
	      ]],
	    });
		
}

function _onLoadSuccess(data) {

	if (data.total == 0) {
       var body = $(this).data().datagrid.dc.body2;

       var tipHtml = '<tr><td width="' + body.width() + '" style="height: 35px; text-align: center;"><h1>';
       if($("#gasCardNoQuery").val()) {
     	  tipHtml += "用气证号:"+$("#gasCardNoQuery").val();
       }
       tipHtml += ' 暂无数据</h1></td></tr>';
       
       body.find('table tbody').append(tipHtml);
       $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
     }
       //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
     else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
}

//用气历史表加载数据成功后，调用此方法
function _onLoadSuccessHistory(data) {
	_onLoadSuccess(data);
	//根据上次送气情况，设置默认送气门店
	if (data && data.total > 0) {
		var acceptStation = data.rows[0].acceptStation;
		if(acceptStation) {
			$("input[type=radio][name=acceptStation][value="+acceptStation+"]").attr("checked",true);
		}
	}
}

//获取钢瓶使用费,检查是否需要通知客户缴费
function _checkNeedPayFee(data) {
	
}

//获取价格
function getPrice(){
    
	var bottleType = $("#bottleType").val();//瓶型
	var gasCardNo = $("#gasCardNo").val();//用气证号
	var cusName = $("#cusName").val();//客户姓名
	
	if(bottleType && bottleType !="安检"&& bottleType !="维修") {
		$.ajax({
		      type : "post",
		      url : "../civilPrice/getPrice.do",
		      data :{bottleType:bottleType,gasCardNo:gasCardNo,customerName:cusName} ,
		      dataType:"json",
		      success : function(civilPrice){
	        	  if(civilPrice && civilPrice.price) { 
	        		  $("#price").val(civilPrice.price);
	        		  $("#price").change();
		          }
		      }
		 }); 
	}
}