<%  
response.setHeader("Pragma","No-cache");  
response.setHeader("Cache-Control","no-cache");  
response.setDateHeader("Expires", 0);
String openid	  = (String)request.getAttribute("openid");

if(openid == null || openid.length() == 0) {
	openid = request.getParameter("openid");
	request.setAttribute("openid", openid);
}
%>
<!DOCTYPE html>
<html>
  <head>
  	<%@ page pageEncoding="UTF-8"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LPG能源新零售</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">

  </head>
  <body>

  <div class="page-group">
      <!-- 你的html代码 -->
      <div class="page">
          <header class="bar bar-nav">
		  	 
              <a class="button button-link button-nav pull-left back" href="<%=request.getContextPath()%>/jspM/wx/customer/startSUI_Order.jsp">
                  <span class="icon icon-left"></span>
                  返回
              </a>
			  
              <h1 class="title">我的绑定地址(测试)</h1>
          </header>          
          <div class="content">
              <!-- 这里是页面内容区 -->
              <div class="page-index">
                  <div class="card">
                      <div class="card-header color-white no-border">我的地址</div>
                      <div class="card-content">
                          <div class="card-content-inner">
                              <p class="color-gray">15700085652</p>
                              <p>浙江省临海市沿江镇红光村294号</p>
                          </div>
                      </div>
                      <div class="card-footer">
                           <a href="#" class="link">自己</a>
						   <a href="#" class="link">修改</a>
                      </div>
                  </div>
                  <div class="card">
                      <div class="card-header color-white no-border">父母地址</div>
                      <div class="card-content">
                          <div class="card-content-inner">
                              <p class="color-gray">18857628002</p>
                              <p>浙江省临海市沿江镇红光村294号</p>
                          </div>
                      </div>
                      <div class="card-footer">
						  <a href="#" class="link">父母</a>
                          <a href="#" class="link">修改</a>
                      </div>
                  </div>
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
