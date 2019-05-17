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
	
	
})	
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					欢迎您来激活帐号
				</li>
				
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>还差一步，完成激活</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/active/savePwd">
				<input type="hidden" name="info" value="${param.info}" />
				<ul class="forminfo">
					<li>
						<label>
							填写密码：
						</label>
						<input name="pwd" type="password" class="dfinput" />
						<i></i>
					</li>
					<li>
						<label>
							确认密码：
						</label>
						<input name="pwd2" type="password" class="dfinput" />
						<i></i>
					</li>
					
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="激活" />
							<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>


	</body>

</html>

