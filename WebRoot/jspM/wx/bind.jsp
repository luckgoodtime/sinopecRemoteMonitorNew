<!DOCTYPE html>
<html>
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jspM/include/meta.jsp"%>
		<%@ include file="/jspM/include/css.jsp"%>
		<%@ include file="/jspM/include/js.jsp"%>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.cookie.js"></script>
		<script type="text/javascript">
 $(function() {
  
 });
 

  
</script>
	</head>
	<body>
		<div id="content">
			<h2 class="con_h2_title">
				绑定系统账号
			</h2>
			<div class="con_login">
				<form action="<%=request.getContextPath()%>/orderM/bind.do" onsubmit="checkSubmit('username');" method="post">
					<p>
						<input type="text" id="username" name="username"
							value="${member.email}" placeholder="请输入邮箱/手机号/QQ" />
					</p>
					<p>
						<input type="password" id="pwd" name="pwd"  placeholder="请输入密码" />
					</p>
					
					<p class="p_input1">
						<input type="submit" value="绑定" />
					</p>
					<p class="tipInfo">${tip}</p>
					
				</form>
			</div>
		</div>

		<footer class="foot_con">
		<section></section>
		</footer>
		
	</body>
</html>
