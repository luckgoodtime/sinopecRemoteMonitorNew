<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css2.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>
<script type="text/javascript">
	$(function() {
	});
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">菜单管理</a></li>
			<li>快速增加菜单
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>快速增加菜单</span>
		</div>
		<form method="post"
			action="<%=request.getContextPath()%>/menu/addMenuDo.do">
			
			<ul class="forminfo">
				<li><label>des<b>*</b>
				</label> <input name="des" type="text" class="dfinput"  /> <i></i>
				</li>
				
				<li><label>pre<b>*</b>
				</label> <input name="pre" type="text" class="dfinput"  /> <i></i>
				</li>
				
				<li><label>parentId<b>*</b>
				</label> <input name="parentId" type="text" class="dfinput"  /> <i></i>
				</li>
			
				
				
						
				
				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>