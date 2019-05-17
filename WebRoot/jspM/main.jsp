<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jspM/include/meta.jsp"%>
<%@ include file="/jspM/include/css.jsp"%>
<%@ include file="/jspM/include/js.jsp"%>
<script type="text/javascript">
	$(function() {

	});
</script>
</head>
<body>
	<div class="main_header">
		<img alt="欢迎" src=""> <span>xxx运输公司</span> <span>今日发车</span>
		<div class="main_header_num">65</div>
		<div class="main_header_zrfc">昨日发车 34</div>
		<div class="main_header_zt">在途 48 卸液中 24 回程 23 维修 9</div>
	</div>
	<div class="main_middle">
	
	
		<c:forEach items="${articleList}" var="o">
		<p>
			<a href="<%=request.getContextPath()%>/articleM/view.do?id=${o.id}">${o.title}</a>
		</p>
		</c:forEach>
		
		
		
	</div>
	<div class="main_footer">
		<div>本月发车量 230 平均每天 11</div>
		<div>平均里程 1480 平均每趟天数 7</div>
		<div>平均油耗 300 平均收入 20000 平均成本 1800</div>
	</div>
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
