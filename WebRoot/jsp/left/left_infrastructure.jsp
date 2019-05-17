<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>


</head>

<body style="background:#f0f9fd;">
    
    <dl class="leftmenu">
        
    <dd>
	    <div class="title">
	    	<span><img src="<%=request.getContextPath()%>/images/leftico01.png" /></span>基础设施
	    </div>
    	<ul class="menuson">
	        <li class="active"><cite></cite><a href="<%=request.getContextPath()%>/corporation/list.do" target="rightFrame">公司管理</a><i></i></li>
	        <li><cite></cite><a href="<%=request.getContextPath()%>/allPerson/list.do" target="rightFrame">人员管理</a><i></i></li>	        
	        <li><cite></cite><a href="<%=request.getContextPath()%>/sourcePoint/list.do" target="rightFrame">气源管理</a><i></i></li>
	        <li><cite></cite><a href="<%=request.getContextPath()%>/endPoint/list.do" target="rightFrame">终端管理</a><i></i></li>
	        <li><cite></cite><a href="<%=request.getContextPath()%>/address/list.do" target="rightFrame">地址管理</a><i></i></li>	 
	        <li><cite></cite><a href="<%=request.getContextPath()%>/route/list.do" target="rightFrame">路线管理</a><i></i></li>	  	               
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="<%=request.getContextPath()%>/images/leftico02.png" /></span>菜单设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath()%>/menu/list.do" target="rightFrame">菜单管理</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath()%>/role/list.do" target="rightFrame">角色管理</a><i></i></li>
        </ul>     
    </dd> 
    
   
    </dl>
    
</body>
</html>
