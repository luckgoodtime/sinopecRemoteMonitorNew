<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"/>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.autocomplete.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".selec").uedSelect();
		
		//自动加载公司
		$('#corpName').autocomplete("<%=request.getContextPath()%>/corporation/listAll.do", {
			  dataType:'json',
			  max: 100,    //列表里的条目数
			  minChars: 0,    //自动完成激活之前填入的最小字符
			  width: 400,     //提示的宽度，溢出隐藏
			  scrollHeight: 300,   //提示的高度，溢出显示滚动条
			  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
			  autoFill: false,    //自动填充
			  parse:function(data) {//解释返回的数据，把其存在数组里  
	              var parsed = [];  
	              for (var i = 0; i < data.length; i++) {  
	                  parsed[parsed.length] = {  
	                  data: data[i],  
	                  value: data[i].name,  
	                  result: data[i].name //返回的结果显示内容  
	                  };  
	              }  
	              return parsed;  
	          },  
			  formatItem: function(row, i, max) {
			      return i + '/' + max + ':"' + row.name + '"[' + row.id + ']';
			  }
			}).result(function(event, row, formatted) {
			  	$('#corpPartyId').val(row.id);
			});
		
	});
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>我的伙伴</li>
			<li>信息导入</li>
			<li>文件列表</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>文件列表</span>
		</div>
		<form method="post" action="<%=request.getContextPath()%>/customer/importcustomer.do">
			<ul>
				<li>
					<label>公司属于</label>
					<input id="corpName" name="corpName" type="text" class="dfinput" value="${c.corpName}"/>
					<input id="corpPartyId" name="corpPartyId" type="hidden" value="${o.corpPartyId}"/>
				</li>			
				<c:forEach items="${p}" var="o">
					<li><label>${o.filename}</label></li>
				</c:forEach>
				<li><label> &nbsp; </label> <input type="submit" class="btn" value="导入" /></li>
			</ul>
		</form>
	</div>
</body>
</html>