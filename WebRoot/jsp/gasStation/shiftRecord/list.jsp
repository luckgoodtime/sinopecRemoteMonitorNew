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
	      title: '上下班表',
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
	        { field: 'shiftNo', title: '班组号' },
	        { field: 'carNo', title: '卡号' }, {
				field : 'startTime',
				title : '上班时间',
				type:'date',
				formatter : function(value) {
					var date = new Date(value);
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					var h = date.getHours();
					var M = date.getMinutes();
					var s = date.getSeconds();
					return y + '-' + (m<10?'0'+ m : m) + '-' + (d<10?'0'+d :d ) + ' ' + (h<10?'0'+h:h) + ':' + (M<10?'0'+M:M) + ':' + (s<10?'0'+s:s);
				}
			}, {
				field : 'endTime',
				title : '下班时间',
				type:'date',
				formatter : function(value) {
					var date = new Date(value);
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					var h = date.getHours();
					var M = date.getMinutes();
					var s = date.getSeconds();
					return y + '-' + (m<10?'0'+ m : m) + '-' + (d<10?'0'+d :d ) + ' ' + (h<10?'0'+h:h) + ':' + (M<10?'0'+M:M) + ':' + (s<10?'0'+s:s);
				}
			}, {
				field : 'confirmTime',
				title : '审核时间',
				type:'date',
				formatter : function(value) {
					var date = new Date(value);
					var y = date.getFullYear();
					var m = date.getMonth() + 1;
					var d = date.getDate();
					var h = date.getHours();
					var M = date.getMinutes();
					var s = date.getSeconds();
					return y + '-' + (m<10?'0'+ m : m) + '-' + (d<10?'0'+d :d ) + ' ' + (h<10?'0'+h:h) + ':' + (M<10?'0'+M:M) + ':' + (s<10?'0'+s:s);
				}
			},
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
			<li><a href="#">上下班表</a></li>

		</ul>
	</div>

	<div class="rightinfo">


		<div class="tools">

			<form id="searchForm">
				<ul class="prosearch">
					 <li>班组号 ： <input name="shiftNo" type="text"
						class="scinput" /> <input type="button" onclick="searchForm();"
						class="sure" value="查询" />
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
