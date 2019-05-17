<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

function openPage(parentId) {
	
	window.top.leftFrame.location.href = "<%=request.getContextPath()%>/left.do?parentId="+parentId;
	window.top.rightFrame.location.href = "<%=request.getContextPath()%>/jsp/welcome.jsp";
}

function openPage2(url) {
	
	window.top.leftFrame.location.href = url;
	window.top.rightFrame.location.href = "jsp/welcome.jsp";
}
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="<%=request.getContextPath()%>" target="_parent"><img src="<%=request.getContextPath()%>/images/logo1.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    
     <c:forEach items="${menuList}" var="m" varStatus="s">
     	<c:if test="${m.parentId==0}">
    	<li><a href="javascript:openPage(${m.id});"  <c:if test="${s.index==0}">class="selected"</c:if>><img src="<%=request.getContextPath()%>/images/${m.image}"  /><h2>${m.des}</h2></a></li>
    	</c:if>
    </c:forEach> 
   <!--  
     <li><a href="javascript:openPage2('jsp/left/left_myinfor.jsp');"  class="selected"><img src="images/icon01.png"  /><h2>我的信息</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_myacount.jsp');" ><img src="images/icon02.png"  /><h2>我的账户</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_mycompanion.jsp');" ><img src="images/icon03.png"  /><h2>我的伙伴</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_marketquotation.jsp');" ><img src="images/icon04.png"  /><h2>市场行情</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_askprice.jsp');" ><img src="images/icon05.png"  /><h2>询价报价</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_businessexec.jsp');" ><img src="images/icon06.png"  /><h2>业务执行</h2></a></li>
    <li><a href="javascript:openPage2('jsp/left/left_myacount.jsp');"  ><img src="images/icon07.png"  /><h2>对账统计</h2></a></li>
 	<li><a href="javascript:openPage2('jsp/left/left_infrastructure.jsp');"  ><img src="images/icon08.png"  /><h2>基础设施</h2></a></li>   
 	 -->   
    </ul>
          
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="<%=request.getContextPath()%>/loginOut.do" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>欢迎，${sessionScope.person.firstName}</span>
   <!--  <i>消息</i>
    <b>5</b> -->
    <i>${sessionScope.person.corp.corpName}</i>
    
    </div>    
    
    </div>

</body>
</html>
