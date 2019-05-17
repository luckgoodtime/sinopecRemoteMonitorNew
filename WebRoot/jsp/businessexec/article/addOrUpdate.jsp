<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>

<script type="text/javascript">

$(function(){	
	var toolbar = [['Source', 'Preview', '-','Cut', 'Copy', 'Paste'],['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'], 
		        	  ['Image', 'Flash', 'Table', 'HorizontalRule',  'SpecialChar'], ['Bold', 'Italic', 'Underline', '-', 'Subscript', 'Superscript'], 
		        	  ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'], 
		        	  ['Link', 'Unlink'] ,['Format', 'Font', 'FontSize'],['TextColor', 'BGColor'],['Maximize'] ];
	
	
	CKEDITOR.replace('content', {enterMode:CKEDITOR.ENTER_BR,shiftEnterMode:CKEDITOR.ENTER_P,toolbar:toolbar}); 
	
});

</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">新闻管理</a></li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>新闻信息</span>
		</div>
		<form method="post"
			action="<%=request.getContextPath()%>/article/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				<li><label> 标题：</label><input name="title" type="text" class="dfinput" value="${o.title}" /> </li>
					
				<li>内容：<textarea class="textinput" name="content" >${o.content}</textarea> </li>
				
					
				
					
				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
