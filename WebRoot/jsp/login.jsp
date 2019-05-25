<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>

<script type="text/javascript" src="js/cloud.js"></script>
<script type="text/javascript">
	$(function(){
		if(window.top != window.self ){
	        //页面是在框架中打开的
			window.top.location.href = "<%=request.getContextPath()%>/login";
		}
		
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })  
    
});  
</script>

	</head>

	<body
		style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">



		<div id="mainBody">
			<div id="cloud1" class="cloud"></div>
			<div id="cloud2" class="cloud"></div>
		</div>


		<div class="logintop">
			<span style="font-size:28px;">欢迎登录数字化加气站云平台</span>
			<ul>
				<li>
					<a href="<%=request.getContextPath()%>/active/active">我要激活</a>
				</li>
				<li>
					<a href="#">帮助</a>
				</li>
				<li>
					<a href="#">关于</a>
				</li>
			</ul>
		</div>

		<div class="loginbody">

			<span class="systemlogo"></span>

			<form method="post" action="<%=request.getContextPath()%>/loginDo.do">
				<div class="loginbox">
					<ul>
						<li>
							<input name="username" type="text" class="loginuser"
								value="" />
						</li>
						<li>
							<input name="pwd" type="password" class="loginpwd" 
								/>
						</li>
						<li>
							<input name="" type="submit" class="loginbtn" value="登录"
								onclick="javascript:window.location='main.html'" />
							<label>
								<input name="" type="checkbox" value="" checked="checked" />
								记住密码
							</label>
							<label>
								<a href="#">忘记密码？</a>
							</label>
						</li>
						<li class="tipInfo">
							${tip}
						</li>
					</ul>
				</div>
			</form>

		</div>



		<div class="loginbm">
			版权所有 2015-2020

		</div>


	</body>

</html>
