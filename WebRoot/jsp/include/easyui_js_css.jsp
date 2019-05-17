<%@ page  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/themes/icon.css"/>

<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/syUtil.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
$(function(){
${suc}
	 <c:if test="${suc!=null}">sy.messagerShow({title : '提示',msg : '${tip}'});</c:if>
});
</script>	