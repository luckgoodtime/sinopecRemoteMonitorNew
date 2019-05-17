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
		 	window.location.href="<%=request.getContextPath()%>/biz/addOrUpdate.do?bizType=PURCHASE&from=${param.from}";
		  });
		  
		  dataGridTable();
		
		});
		
		//加载列表
		function dataGridTable() {
		
			$("#dataGridTable").datagrid({
			      //title: '订单情况',
			      url: '<%=request.getContextPath()%>/biz/ajaxPlist.do',
			      width: "100%",
		          height: "600px",
			      pagination: true,//是否分页       
			      rownumbers: true,//行号
			      pageSize: 15,
			      pageList: [15, 20, 30], 
			      singleSelect: true,
			      striped: true,//交替行换色
			      nowrap: true,//可换行
			      fitColumns: true,//自动适应
			      scrollbarSize:0,
			      //queryParams: paramsFromSearchForm,
			      //onLoadSuccess: _onLoadSuccess,
			      columns: [[
			        { field: 'id', title: '编号'},
			        { field: 'supplierName', title: '供应商'},
			        { field: 'gasType', title: '气品'},
			        { field: 'trackNumber', title: '单据号'},
			        { field: 'sourceName', title: '气源点'},
		        
			        { field: 'plateNo', title: '车牌号'},
			        { field: 'driverName', title: '司机'},
			        { field: 'loadingTime', title: '装车时间'},
			        { field: 'loadingTW', title: '装车皮重'},
			        { field: 'loadingGW', title: '装车毛重'},
			        { field: 'loadingNW', title: '装车净重'},
			        { field: 'unloadingTime', title: '卸车时间'},
			        { field: 'unLoadingGW', title: '卸车毛重'},
			        { field: 'unLoadingTW', title: '卸车皮重'},
			        { field: 'unLoadingNW', title: '卸车净重'},
			        
			        { field: 'CreateBy', title: '操作',
			        	formatter: function (val, row, index) {
		        		 var str = '<a href="<%=request.getContextPath()%>/biz/addOrUpdate.do?bizType=PURCHASE&id='+row.id+'" class="tablelink">修改</a>';
		        		 str += '&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/biz/del.do?bizType=PURCHASE&ids='+row.id+'" class="tablelink">删除</a>';		        		 
		                 return str;
			          }}
			        
			        
			      ]]
			});	    
		}
		
		function SearchFormSubmit(){
			var params = {};
            var t = $('#searchForm').serializeArray();
            $.each(t, function () {
            	params[this.name] = this.value;
            });
			
            $("#dataGridTable").datagrid("load",params);
		}
	</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">业务执行</a>
				</li>
				<li>
					<a href="#">采购订单</a>
				</li>				
			</ul>
		</div>

		<div class="rightinfo">

			<div class="tools">

				<ul class="toolbar">
					<li class="click">
						<span><img src="<%=request.getContextPath()%>/images/t01.png" /> </span>添加
					</li>
					
					<li class="del">
						<span><img src="<%=request.getContextPath()%>/images/t03.png" /> </span>删除
					</li>
				</ul>
				<form id="searchForm" action="<%=request.getContextPath()%>/biz/purchaseorderlist.do" method="post">
					<ul class="prosearch" style="float: left;margin: 10px 0 10px 0">
						<li> 
							供应商 ： <input  name="supplierName"  type="text"  class="scinput"  value="${bizOrder.supplierName}"/>
							起源点 ： <input  name="sourceName"  type="text"  class="scinput"  value="${bizOrder.sourceName}"/>
							开始 ： <input  name="startTime"  type="text" readonly="readonly" class="scinput"  value="<fmt:formatDate value="${bizOrder.startTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker();"/>
							结束 ： <input  name="endTime"  type="text" readonly="readonly" class="scinput"  value="<fmt:formatDate value="${bizOrder.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker();"/>
							<input type="submit" onclick="SearchFormSubmit();return false;" class="sure" value="查询" />
						</li>
				  </ul>
				</form>	

				<ul class="toolbar1">
					<li>
						<!-- <span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>查询 -->
					</li>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/biz/del.do?bizType=PURCHASE" method="post">
			<div style="overflow:scroll;width:100%;">
				<table id="dataGridTable" data-options="fitColumns:true,scrollbarSize:0"></table>
			</div>
			</form>
		</div>
	</body>
</html>
