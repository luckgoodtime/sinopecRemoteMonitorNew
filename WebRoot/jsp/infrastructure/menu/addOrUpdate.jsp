<%@page import="com.lng.util.Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
</head>
<body>
<div class="formbody">
	
		<form method="post">
			<c:if test="${o!=null}"><input name="id" type="hidden" value="${o.id}" /></c:if>
			<ul class="forminfo">
					<li>
						上级:  <input id="parentId" name="parentId" type="text" style="width: 200px;" class="dfinput" value="${o.parentId}" /> 
					</li>
					<li>
						名称: <input id="des" name="des" type="text" style="width: 200px;" class="dfinput" value="${o.des}" />
					</li>
					<li>
						权限: <input id="perm" name="perm" type="text" style="width: 200px;" class="dfinput" value="${o.perm}" />
					</li>
					<li>
						地址: <input id="url" name="url" type="text" style="width: 200px;" class="dfinput" value="${o.url}" />
					</li>
						<li>
						顺序: <input id="url" name="menuOrder" type="text" style="width: 200px;" class="dfinput" value="${o.menuOrder}" />
					</li>
						<li>
						图片: <input id="url" name="image" type="text" style="width: 200px;" class="dfinput" value="${o.image}" />
					</li>
				
			</ul>
		</form>
	</div>

	</body>

</html>

