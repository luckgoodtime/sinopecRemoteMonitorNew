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
    <span><img src="<%=request.getContextPath()%>/images/leftico01.png" /></span>基础
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath()%>/person/baseInfo.do" target="rightFrame">基本信息</a><i></i></li>
        <li class="active"><cite></cite><a href="<%=request.getContextPath()%>/person/list.do" target="rightFrame">我的员工</a><i></i></li>
        <li><cite></cite><a href="imgtable.html" target="rightFrame">我的形象</a><i></i></li>
        <li><cite></cite><a href="form.html" target="rightFrame">我的新闻</a><i></i></li>
        </ul>    
    </dd>
    
    <dd>
    <div class="title">
    <span><img src="<%=request.getContextPath()%>/images/leftico02.png" /></span>我的资源
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="<%=request.getContextPath()%>/trucks/mylist.do" target="rightFrame">我的车辆</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath()%>/sourcePoint/mylist.do" target="rightFrame">我的气源</a><i></i></li>
        <li><cite></cite><a href="<%=request.getContextPath()%>/endPoint/mylist.do" target="rightFrame">我的终端</a><i></i></li>
     </ul>     
    </dd> 
    
    </dl>
</body>
</html>
