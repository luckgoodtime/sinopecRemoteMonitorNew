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
    <span><img src="<%=request.getContextPath()%>/images/leftico01.png" /></span>菜单
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="index.html" target="rightFrame">出厂报价</a><i></i></li>
        <li ><cite></cite><a href="person/list.do" target="rightFrame">下游报价</a><i></i></li>
         <li ><cite></cite><a href="person/list.do" target="rightFrame">上游报价</a><i></i></li>
        </ul>    
    </dd>
        
    
   
    
   
    
    </dl>
    
</body>
</html>
