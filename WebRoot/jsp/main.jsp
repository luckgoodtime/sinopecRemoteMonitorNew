<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/title.jsp"%>
	</head>
	<frameset rows="88,*" cols="*" frameborder="no" border="0"
		framespacing="0">
		<frame src="<%=request.getContextPath()%>/top.do" name="topFrame" scrolling="auto" noresize="noresize"
			id="topFrame" title="topFrame" />
		<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		
			
			<frame src="<%=request.getContextPath()%>/left.do?parentId=${m.parentId}" name="leftFrame" scrolling="no"
				noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="<%=request.getContextPath()%>${m.url}" name="rightFrame" id="rightFrame"
				title="rightFrame" />
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
