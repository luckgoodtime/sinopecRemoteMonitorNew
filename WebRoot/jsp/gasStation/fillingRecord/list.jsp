<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>
<script type="text/javascript">
$(function(){
  dataGridTable();
});

//加载列表
function dataGridTable() {

	$("#dataGridTable").datagrid({
	      title: '加气记录',
	      url: 'ajaxList.do',
	      width: "98%",
          height: "500px",
	      pagination: true,//是否分页       
	      rownumbers: true,//行号
	      singleSelect: true,
	      striped: true,//交替行换色
	      nowrap: true,//可换行
	      collapsible: true,
	      fitColumns: true,//自动适应
	      columns: [[
	        { field: 'id', title: '编号'},
	        { field: 'recordId', title: '站级记录ID'},
	        { field: 'gunNo', title: '枪号',align:'center'},
	        { field: 'cardNo', title: '卡号' },
	        { field: 'holderName', title: '持卡人姓名' ,align:'center'},
	        { field: 'plateNo', title: '车牌号' },
	        { field: 'fillTime', title: '加气时间',type:'date', 
	        	formatter: function (val, row, index) {
	                return (new Date(val)).format("yyyy-MM-dd hh:mm:ss");
	             }
	        },
	        { field: 'price', title: '单价',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'volume', title: '原始气量',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'receivable', title: '原始金额',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'discount', title: '优惠金额',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'receiptTotal', title: '实际金额',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'cardBalance', title: '卡余额',align:'right',
	        	formatter: function (value, row, index) {
                    if (row != null) {
                        return (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                  	}
                  }
	       },
	        { field: 'cardType', title: '卡类型' ,align:'center'},
	        { field: 'fillType', title: '加气类型' ,align:'center'},
	        { field: 'ttc', title: '加气机流水号' },
	        { field: 'stationNo', title: '加气站编号' },
	        { field: 'stationName', title: '加气站名称' },
	        { field: 'note', title: '说明' }
	        
	      ]]
	      
	});	   
	
	/*var pager = $('#dataGridTable').datagrid('getPager');
	pager.pagination({showPageList:true});*/
}

function searchForm() {
	$('#dataGridTable').datagrid('load',sy.serializeObject($("#searchForm").form()));
}



</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#"></a></li>
			<li><a href="#">加气记录</a></li>

		</ul>
	</div>

	<div class="rightinfo">


		<div class="tools">
			
			<form id="searchForm">
				<ul class="prosearch">
					<li>
						持卡人姓名 ： <input name="holderName" type="text" class="scinput" /> 
						加气开始时间 ： <input  name="fillTimeBegin"  type="text" class="scinput"  value="" onclick="WdatePicker();"/>
						加气结束时间 ： <input  name="fillTimeEnd"  type="text" class="scinput"  value="" onclick="WdatePicker();"/>
						卡类型 ： <select name="cardType" class="scinput" style="opacity:100;width: 80px;">
									<option value="">全部</option>
									<c:forEach items="${cardTypeList}" var="dic">
		                                <option <c:if test="${o.gasType==dic.value}">selected="selected"</c:if> value="${dic.value}">${dic.des}</option>
		                            </c:forEach>	
								</select>
							
									
						 加气类型： <select name="fillType" class="scinput" style="opacity:100;width: 80px;">
									<option value="">全部</option>
									<c:forEach items="${fillTypeList}" var="dic">
		                                <option <c:if test="${o.gasType==dic.value}">selected="selected"</c:if> value="${dic.value}">${dic.des}</option>
		                            </c:forEach>	
								</select>
						
						
						<input type="button" onclick="searchForm();" class="sure" value="查询" />
						<input type="button"  class="sure exportExcel"  /><!-- 导出excel -->
					</li>
				</ul>
			</form>
		</div>


		<div>
			<table id="dataGridTable"></table>
		</div>

	</div>
</body>
</html>
