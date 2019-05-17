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
			<li><a href="#">
			partyAttribute属性 
			
			</a></li>
			<li><c:if test="${o.partyAttributeId==null}">新增</c:if> <c:if test="${o.partyAttributeId !=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		
		<form method="post"
			action="<%=request.getContextPath()%>/partyAttribute/<c:if test="${o.partyAttributeId==null}">addDo</c:if><c:if test="${o.partyAttributeId!=null}">updateDo</c:if>.do" >
			<c:if test="${o.partyAttributeId!=null}">
				<input name="partyAttributeId" type="hidden" value="${o.partyAttributeId}" />
			</c:if>
			<ul class="forminfo">
				 
				<li><label>attrName</label> <input name="attrName" type="text" class="dfinput" value="${o.attrName}" /> <i></i></li>	
				<li><label>attrValue</label> <input name="attrValue" type="text" class="dfinput" value="${o.attrValue}" /> <i></i></li>
				<li><label>partyId</label> <input name="partyId" type="text" class="dfinput" value="${o.partyId}" /> <i></i></li>
				<li><label> &nbsp; </label> <input type="submit" class="btn" value="保存" /> </li>
			</ul>
		</form>
	</div>
</body>
</html>
