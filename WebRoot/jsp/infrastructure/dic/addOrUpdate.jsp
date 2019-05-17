<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>


</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">数据字典</a></li>
			<li><c:if test="${o.id==null}">新增</c:if> <c:if test="${o.id !=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		
		<form method="post"
			action="<%=request.getContextPath()%>/dic/<c:if test="${o.id==null}">addDo</c:if><c:if test="${o.id!=null}">updateDo</c:if>.do" >
			<c:if test="${o.id!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				 
				<li><label>类型</label> <input name="type" type="text" class="dfinput" value="${o.type}" /> <i></i></li>	
				<li><label>值</label> <input name="value" type="text" class="dfinput" value="${o.value}" /> <i></i></li>
				<li><label>描述</label> <input name="des" type="text" class="dfinput" value="${o.des}" /> <i></i></li>
				<li><label>顺序</label> <input name="byOrder" type="text" class="dfinput" value="${o.byOrder}" /> <i></i></li>
				<li><label> &nbsp; </label> <input type="submit" class="btn" value="保存" /> </li>
			</ul>
		</form>
	</div>
</body>
</html>
