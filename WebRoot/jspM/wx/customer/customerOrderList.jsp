<%  
response.setHeader("Pragma","No-cache");  
response.setHeader("Cache-Control","no-cache");  
response.setDateHeader("Expires", 0);

String openid	  = request.getParameter("openid");
%>
<!DOCTYPE html>
<html>
  <head>
	<%@ page pageEncoding="UTF-8"%>
	<%@ include file="/jsp/include/taglib.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    
    <title>LPG能源新零售</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

	<link rel="stylesheet" href="<%=request.getContextPath()%>/cssM/light7/light7.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/cssM/light7/light7-swiper.min.css">
  </head>
  <body>

  <div class="page-group">
      <!-- 你的html代码 -->
      <div class="page">
          <header class="bar bar-nav">

              <a class="button button-link button-nav pull-left" href="<%=request.getContextPath()%>/jspM/wx/customer/startSUI_Order.jsp?openid=<%=openid%>">
                  <span class="icon icon-left"></span>
                  返回
              </a>
    
              <h1 class="title">我的订单</h1>
          </header>
          <nav class="bar bar-tab">
              <a class="tab-item" href="<%=request.getContextPath()%>/jspM/wx/customer/startSUI_Order.jsp?openid=<%=openid%>">
                  <span class="icon icon-cart"></span>
                  <span class="tab-label">定气</span>
              </a>
              <a class="tab-item" href="<%=request.getContextPath()%>/wx/listRequestOrder.do?openid=<%=openid%>">
                  <span class="icon icon-menu active"></span>
                  <span class="tab-label">订单</span>
              </a>
              <a class="tab-item" href="<%=request.getContextPath()%>/jspM/wx/customer/customerAddresslist.jsp?openid=<%=openid%>">
                  <span class="icon icon-star"></span>
                  <span class="tab-label">地址</span>
              </a>
              <a class="tab-item" href="#">
                  <span class="icon icon-me"></span>
                  <span class="tab-label">我</span>
              </a>
          </nav>
          <div class="content">
              <!-- 这里是页面内容区 -->
			  <div class="list-block media-list">
			    <ul>
			     <c:forEach items="${orderlist}" var="o"> 
			      <li>
			        <div class="item-content">
			          <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder15.jpg" style='width: 2.2rem;'></div>
			          <div class="item-inner">
			            <div class="item-title-row">
			              <div class="item-title">${o.orderCreateTime}&nbsp;${o.bottleType}</div>
			            </div>
			            <div class="item-subtitle">${o.quantity}瓶 &nbsp;&nbsp;${o.price}元/瓶 &nbsp;&nbsp;共计:${o.money}元</div>
			          </div>
			        </div>
			      </li>
			      </c:forEach>
			    </ul>
			  </div>            
		  </div>
      </div>
  </div>

	<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/jquery.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7-swiper.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/app.js' charset='utf-8'></script>

	<script>

	</script>
  </body>
</html>
