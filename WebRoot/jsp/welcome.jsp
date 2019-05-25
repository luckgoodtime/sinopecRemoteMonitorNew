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
 	window.location.href="person/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">欢迎页面</a>
				</li>
			
				
			</ul>
		</div>


	<center style="margin-top: 200px;font-size: 40px;">欢迎登录数字化加气站云平台</center>

	</body>
</html>
