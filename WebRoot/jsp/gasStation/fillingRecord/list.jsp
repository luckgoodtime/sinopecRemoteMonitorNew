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
	        { field: 'gunNo', title: '枪号' },
	        { field: 'carNo', title: '卡号' },
	        { field: 'holderName', title: '持卡人姓名' },
	        { field: 'plateNo', title: '车牌号' },
	        { field: 'fillTime', title: '加气时间' },
	        { field: 'price', title: '单价' },
	        { field: 'volume', title: '原始气量' },
	        { field: 'receivable', title: '原始金额' },
	        { field: 'discount', title: '优惠金额' },
	        { field: 'receiptTotal', title: '实际金额' },
	        { field: 'cardBalance', title: '卡余额' },
	        { field: 'cardType', title: '卡类型' },
	        { field: 'fillType', title: '加气类型' },
	        { field: 'note', title: '说明' },
	        { field: 'stationNo', title: '加气站编号' },
	        { field: 'stationName', title: '加气站名称' },
	        
	      ]]
	});	    
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
					<li>持卡人姓名 ： <input name="holderName" type="text"
						class="scinput" /> <input type="button" onclick="searchForm();"
						class="sure" value="查询" />
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
