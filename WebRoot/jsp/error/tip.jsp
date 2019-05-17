<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
</head>
<body style="background: #edf6fa;">
	<center>
		<div class="myTip" >
		<h2><c:if test="${suc}"><img src="<%=request.getContextPath()%>/images/tip_suc.png" /></c:if>
		<c:if test="${!suc}"><img src="<%=request.getContextPath()%>/images/tip_err.png" /></c:if>
			${tip}</h2>
			<div class="reindex">
			<c:if test="${empty url}">
				<a href="javascript:history.go(-1);">返回前页面</a>
			</c:if>	
			<c:if test="${!empty url}">
				${url}
			</c:if>	
				
			</div>
		</div>
	</center>
</body>

</html>
