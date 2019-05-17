<%@ page pageEncoding="UTF-8"%>

				  
<div id="navigation">
	<div onclick="window.location.href='<%=request.getContextPath()%>/main.do'" <%if(request.getServletPath().indexOf("main")>-1) {%>class="menu1_select"<%}%>>
		<a href="#">首页</a>
	</div>
	<div onclick="window.location.href='<%=request.getContextPath()%>/exec/list.do'" <%if(request.getServletPath().indexOf("exec")>-1) {%>class="menu1_select"<%}%>>
		<a href="#">执行</a>
	</div>
	<div onclick="window.location.href='<%=request.getContextPath()%>/tongji/summary.do'" <%if(request.getServletPath().indexOf("tongji")>-1) {%>class="menu1_select"<%}%>>
		<a href="#">统计</a>
	</div>
	<div onclick="alert('维护');">
		<a href="#">维护</a>
	</div>
	<div onclick="window.location.href='<%=request.getContextPath()%>/articleM/list.do'" <%if(request.getServletPath().indexOf("articleM")>-1) {%>class="menu1_select"<%}%>>
		<a href="#">新闻</a>
	</div>
</div>


