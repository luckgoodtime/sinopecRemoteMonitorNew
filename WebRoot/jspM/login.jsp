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
  	//显示记住账号
  	if ($.cookie("remMe") == "true") {
		$("#remMe").attr("checked", true);
		$("#username").val($.cookie("username"));
	} 
 });
 
 function checkSubmit(input) {
 	//保存记住账号
	if ($("#remMe").attr("checked")) {
		var inputValue = $("#" + input).val();
		$.cookie("remMe", "true", {expires:30});
		$.cookie(input, inputValue, {expires:30});
	} else {
		$.cookie("remMe", "false", {expire:-1});
		$.cookie(input, "", {expires:-1});
	}
	return true;
}
  
</script>
	</head>
	<body>
		<div id="content">
			<h2 class="con_h2_title">
				欢迎登录数字化加气站云平台
			</h2>
			<div class="con_login">
				<form action="<%=request.getContextPath()%>/loginDo.do" onsubmit="checkSubmit('username');" method="post">
					<p>
						<input type="text" id="username" name="username"
							value="${member.email}" placeholder="请输入邮箱/手机号/QQ" />
					</p>
					<p>
						<input type="password" id="pwd" name="pwd"  placeholder="请输入密码" />
					</p>
					<p class="p_input">
						<input type="checkbox" id="remMe" name="remMe" value="checkbox" />
						记住账号
					</p>
					<p class="p_input1">
						<input type="submit" value="登录" />
					</p>
					<p class="tipInfo">${tip}</p>
					<%-- <div class="log_a">
						<span><a href="<%=request.getContextPath()%>/active/active">我要激活</a>
						</span>
					</div>
					<div class="log_a">
						<span><a href="<%=request.getContextPath()%>/orderM/wxLogin.do">微信定气</a>
						</span>
					</div>
					<div class="log_a">
						<span><a href="<%=request.getContextPath()%>/jspM/wappay/pay.jsp">支付宝支付测试</a>
						</span>
					</div> --%>
				</form>
			</div>
		</div>

		<footer class="foot_con">
		<section><!--
		版权所有2015-2020
		--></section>
		</footer>
		
	</body>
</html>
