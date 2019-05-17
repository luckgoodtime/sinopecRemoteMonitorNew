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
	$(".click").click(function(){
	 	window.location.href="<%=request.getContextPath()%>/dic/addOrUpdate.do";
	  });
  dataGridTable();
});

//加载列表
function dataGridTable() {

	$("#dataGridTable").datagrid({
	      //title: '订单情况',
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
	      //queryParams: { gasCardNo:""},
	      //onLoadSuccess: _onLoadSuccess,
	      columns: [[
	        { field: 'id', title: 'id'},
	        { field: 'type', title: '类型'},
	        { field: 'value', title: '值' },
	        { field: 'des', title: '描述' },
	        { field: 'byOrder', title: '顺序' },
	        { field: 'CreateBy', title: '操作',
		        	formatter: function (val, row, index) {
		        		var str = "";
		              	str += '<a href="<%=request.getContextPath()%>/dic/addOrUpdate.do?id='+row.id+'" class="tablelink">修改</a>';
		                return str;
		          }
	         }
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
				<li>
					<a href="#">基础管理</a>
				</li>
				<li>
					<a href="#">数据字典</a>
				</li>
				
			</ul>
		</div>

		<div class="rightinfo">
			

		<div class="tools">
						<ul class="toolbar">
							<li class="click">
								<span><img src="<%=request.getContextPath()%>/images/t01.png" /> </span>添加
							</li>
						</ul>
						<form id="searchForm" >
							<ul class="prosearch" >
								<li> 
									类型 ： <input   name="type" type="text"  class="scinput"  />
                            
                            	    <input type="button"  onclick="searchForm();" class="sure" value="查询" />
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
