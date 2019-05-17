<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<script type="text/javascript">
	$(function() {
		$(".selec").uedSelect();
	})
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>我的伙伴</li>
			<li>信息导入</li>
			<li>导入列表</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>导入结果</span>
		</div>
		<form method="post" action="<%=request.getContextPath()%>/customer/importdata.do">
			<ul>
				<c:forEach items="${p}" var="o">
					<li><label>${o.filename} 导入 ${o.line} 条</label></li>
				</c:forEach>
			</ul>
		</form>
	</div>
</body>
</html>