<%@ page pageEncoding="UTF-8"%>

				  
	<div class="summary_main_day">
			<div>7 天</div>
			<div>30天</div>
			<div>60天</div>
			<div>90天</div>
		</div>
		
	<div class="exec_nav">
		<div <%if(request.getServletPath().indexOf("summary")>-1) {%>class="exec_nav_select"<%}%>><a href="<%=request.getContextPath()%>/tongji/summary.do">概述</a></div>
		<div <%if(request.getServletPath().indexOf("geographicDistribution")>-1) {%>class="exec_nav_select"<%}%>><a href="<%=request.getContextPath()%>/tongji/geographicDistribution.do">地理分布</a></div>
		<div <%if(request.getServletPath().indexOf("customType")>-1) {%>class="exec_nav_select"<%}%>><a href="<%=request.getContextPath()%>/tongji/customType.do">客户类型</a></div>
		<div class="exec_nav_divend <%if(request.getServletPath().indexOf("mileageDistribution")>-1) {%>exec_nav_select<%}%>">
			<a href="<%=request.getContextPath()%>/tongji/mileageDistribution.do">里程分布</a></div>
	</div>
	


