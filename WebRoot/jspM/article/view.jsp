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
	$(function(){
		
		
	    $('.top_header_title').text("新闻");
		  
    
});  
</script>
</head>
<body>
<jsp:include page="/jspM/include/header.jsp"/>	
	
	


<div class="article_view">
		<div class="article_view_title">
			<p>${article.title}</p> 
		</div>
		<hr />
		
		<div class="article_view_cont">
			${article.content}
		</div>
	</div>
	
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
