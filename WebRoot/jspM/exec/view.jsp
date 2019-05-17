<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jspM/include/meta.jsp"%>
<%@ include file="/jspM/include/css.jsp"%>
<%@ include file="/jspM/include/js.jsp"%>

</head>
<body>
<jsp:include page="/jspM/include/header.jsp"/>	
	
	


<div class="exec_view">
		<div class="exec_view_cont">
			<p>${shippingOrder.truckNo} ${shippingOrder.driverName} ${shippingOrder.driverTel}</p> 
			<p>云南省昆明市第三大道xxx路  <span class="exec_view_cont_navi">导航</span></p> 
			<p>要求达到时间：2015-03-05</p> 
			<p>装车量：${shippingOrder.loadingNW}t </p> 
			<p>到达时间：2015-03-11	卸车量：10t</p> 
			
		</div>
		<div class="exec_view_cont">
			<p>总费用：xxx元</p>
			<p>油/气费：	2000元	路桥费：880元</p>
			<p>其他费：1280元</p>
		</div>
		<div class="exec_view_cont">
			费用明细  <span class="exec_view_cont_jia">+</span>
		</div>
	</div>
	
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
