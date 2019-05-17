<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<script language="javascript">
	$(function() {
		/*$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
		$(window).resize(function(){  
		$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
		}) */
	});
</script>


</head>


<body style="background: #edf6fa;">
	<center>
		<div class="error">
			<h2>非常遗憾，服务内部错误！</h2>
			<div class="reindex">
				<a href="javascript:history.go(-1);">返回前页面</a>
			</div>
		</div>
	</center>
</body>

</html>
