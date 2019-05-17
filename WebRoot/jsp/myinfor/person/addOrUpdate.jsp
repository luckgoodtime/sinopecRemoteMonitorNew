<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
		
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
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
					<a href="#">我的信息</a>
				</li>
				<li>
					<a href="#">我的员工</a>
				</li>
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/person/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="partyId" type="hidden" value="${o.partyId}" />
				</c:if>
				<ul class="forminfo">
				
					<li>
						<label>
							姓名
						</label>
						<input name="firstName" type="text" class="dfinput" value="${o.firstName}"/>
						<i></i>
					</li>
						<li>
						<label>
							性别
						</label>
						<cite>
						<input name="gender" type="radio" value="男" 
							<c:if test="${o.gender=='男'}">checked="checked"</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="gender" type="radio" value="女"  
							<c:if test="${o.gender=='女'}">checked="checked"</c:if>/>女</cite>
					</li>
					<li>
						<label>
							QQ
						</label>
						<input name="QQ" type="text" class="dfinput" value="${o.QQ}"/>
						<i></i>
					</li>
					<li>
						<label>
							手机号
						</label>
						<input name="mobile" type="text" class="dfinput" value="${o.mobile}"/>
						<i></i>
					</li>
					<li>
						<label>
							email
						</label>
						<input name="email" type="text" class="dfinput" value="${o.email}"/>
						<i></i>
					</li>
					<li>
						<label>
							是否对外公开
						</label>
						<cite>
						<input name="isOpen" type="radio" value="是" 
							<c:if test="${o.isOpen=='是'}">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="isOpen" type="radio" value="否"  
							<c:if test="${o.isOpen=='否'}">checked="checked"</c:if>/>否</cite>
					</li>
					
				
					
					<li>
						<label>
							生日
						</label>
						<input name="birthDate" type="text" class="dfinput"
							 onclick="WdatePicker();" value="${o.birthDate}"/>
					</li>
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="确认保存" />
							<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>


	</body>

</html>

