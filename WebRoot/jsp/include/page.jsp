<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<c:if test="${p.totalPages>0}">
<script type="text/javascript">
        function go(pageMethod) {
	        var pageForm = $("#pageForm");
	        $("#pageMethod").val(pageMethod);
		    pageForm.submit();
        }
        
</script>  
<form id="pageForm"  method="post">
<input type="hidden" id="currentPage" name="currentPage"  value="${p.currentPage}"/>
<input type="hidden" id="pageMethod" name="pageMethod"  />
<% //查询参数
	Enumeration enu=request.getParameterNames();
	while(enu.hasMoreElements()){
	String paraName=(String)enu.nextElement();
	if(!"currentPage".equals(paraName)&&!"pageMethod".equals(paraName)) {
	%>
	<input type="hidden"  name="<%=paraName %>"  value="<%=request.getParameter(paraName) %>"/>
	<%}
	} 
%>

			<div class="pagin">
				<div class="message">				    
					共<i class="blue">${p.totalRows}</i>条,
					<i class="blue">${p.totalPages}</i>页,当前显示第&nbsp;
					<i class="blue">${p.currentPage}&nbsp;</i>页
				</div>
				<ul class="paginList">
				
					<li class="paginItem">
						<a href="javascript:go('first');">&lt;&lt;</a>
					</li>
					<li class="paginItem">
						<a href="javascript:go('previous');">&lt;</a>
					</li>
					<li class="paginItem">
						<a href="javascript:go('next');">&gt;</a>
					</li>
					<li class="paginItem">
						<a href="javascript:go('last');">&gt;&gt;</a>
					</li>
					
				</ul>
			</div>
</form>
</c:if>