<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<script language="javascript">
	$(function() {
		
	});
</script>


</head>


<body style="background: #edf6fa;">
	<center>
		<div style="margin-top: 200px;" >
			
				<form:form commandName="o">  
				    <form:errors path="*" cssStyle="color:red;font-size: 25px;"></form:errors><br/>  
				</form:form>  
			 
			<div class="reindex">
				<a href="javascript:history.go(-1);">返回前页面</a>
			</div>
		</div>
	</center>
</body>

</html>
