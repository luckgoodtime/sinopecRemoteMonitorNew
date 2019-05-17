<%  
response.setHeader("Pragma","No-cache");  
response.setHeader("Cache-Control","no-cache");  
response.setDateHeader("Expires", 0); 

String orderInfor = request.getParameter("orderInfor");
String openid	  = request.getParameter("openid");

//"5KG_30_1_30-15KG_95_1_95-50KG_300_1_300";

String[] orderInfors = orderInfor.split("-");

int[] qtys = {0,0,0};
int[] prices = {0,0,0};
double[] amounts = {0.00, 0.00, 0.00};

for(int i = 0; i < orderInfors.length; i++) {
	String oneStr = orderInfors[i];
	
	String[] oneOderInfor = oneStr.split("_");
	if("5KG".equalsIgnoreCase(oneOderInfor[0])) {
		
		prices[0]	= Integer.valueOf(oneOderInfor[1]);	
		qtys[0]		= Integer.valueOf(oneOderInfor[2]);
		amounts[0]	= Double.valueOf(oneOderInfor[3]).doubleValue();
		
	} else if("15KG".equalsIgnoreCase(oneOderInfor[0])){
		
		prices[1]	= Integer.valueOf(oneOderInfor[1]);		
		qtys[1]		= Integer.valueOf(oneOderInfor[2]);
		amounts[1]	= Double.valueOf(oneOderInfor[3]).doubleValue();
		
	} else if("50KG".equalsIgnoreCase(oneOderInfor[0])){
		prices[2]	= Integer.valueOf(oneOderInfor[1]);	
		qtys[2]		= Integer.valueOf(oneOderInfor[2]);
		amounts[2]	= Double.valueOf(oneOderInfor[3]).doubleValue();
	}
}

%>
<!DOCTYPE html>
<html>
  <head>
	<%@ page pageEncoding="UTF-8"%>
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
		  	  <!--
              <a class="button button-link button-nav pull-left" href="/demos/card">
                  <span class="icon icon-left"></span>
                  返回
              </a>
			  -->
              <h1 class="title">填写收货地址</h1>
          </header>
          <nav class="bar bar-tab">
              <a class="tab-item external active" href="<%=request.getContextPath()%>/jspM/wx/customer/startSUI_Order.jsp?openid=<%=openid%>">
                  <span class="icon icon-cart"></span>
                  <span class="tab-label">定气</span>
              </a>
              <a class="tab-item external" href="<%=request.getContextPath()%>/wx/listRequestOrder.do?openid=<%=openid%>">
                  <span class="icon icon-menu"></span>
                  <span class="tab-label">订单</span>
              </a>
              <a class="tab-item external" href="#">
                  <span class="icon icon-star"></span>
                  <span class="tab-label">地址</span>
              </a>
              <a class="tab-item external" href="#">
                  <span class="icon icon-me"></span>
                  <span class="tab-label">我</span>
              </a>
          </nav>
          <div class="content">
              <!-- 这里是页面内容区 -->
              
			  <div class="list-block" style="margin:.5rem">
			    <ul>
			      <!-- Text inputs -->
			      <li>
			        <div class="item-content">
			          <div class="item-media"></div>
			          <div class="item-inner">
			            <div class="item-title label">用气证号</div>
			            <div class="item-input">
			              <input type="text" id="gasCardNo" placeholder="用气证请正确输入">
			            </div>
			          </div>
			        </div>
			      </li>
			      <li>
			        <div class="item-content">
			          <div class="item-media"></div>
			          <div class="item-inner">
			            <div class="item-title label">联系人</div>
			            <div class="item-input">
			              <input type="text" id="customerName">
			              <input id="customerId" name="customerId" type="hidden"/>
			            </div>
			          </div>
			        </div>
			      </li>
			      <li>
			        <div class="item-content">
			          <div class="item-media"></div>
			          <div class="item-inner">
			            <div class="item-title label">联系电话</div>
			            <div class="item-input">
			              <input type="text" id="customerTel">
			            </div>
			          </div>
			        </div>
			      </li>
			      <li>
			        <div class="item-content">
			          <div class="item-media"></div>
			          <div class="item-inner">
			            <div class="item-title label">送气地址</div>
			            <div class="item-input">
			              <input type="text" id="customerAddress">
			            </div>
			          </div>
			        </div>
			      </li>
			      <li>
			        <div class="item-content">
			          <div class="item-media"></div>
			          <div class="item-inner">
			            <div class="item-title label">送气站点</div>
			            <div class="item-input">
			             <select id="acceptStation">
							<option  value="海圳荣江南门店">江南门店</option>
							<option  value="海圳荣江滨门店">江滨门店</option>
							<option  value="海圳荣红光气站">红光气站</option>
							<option  value="海圳荣涌泉门店">涌泉门店</option>
              			 </select>
			            </div>
			          </div>
			        </div>
			      </li>				      
			    </ul>
			  </div>              
              
			  <div class="list-block media-list" style="margin:.5rem;display:<%=qtys[1]==0?"none":"block" %>">
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder15.jpg" style='width: 2rem;height:4rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">15KG</div>
						  <div class="item-after"><span id="prc15"><%=prices[1]%></span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty15" name="qty15" readonly value="<%=qtys[1]%>" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(:元)</div>
						  <div>
								<input type="text" id="amount15" name="amount15" readonly value="<%=amounts[1]%>" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>              
			  <div class="list-block media-list" style='margin:.5rem; display:<%=qtys[0]==0?"none":"block" %>'>
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder5.jpg" style='width: 2rem;height:3rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">5KG</div>
						  <div class="item-after"><span id="prc5"><%=prices[0]%></span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty5" name="qty5" readonly value="<%=qtys[0]%>" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(元)</div>
						  <div>
								<input type="text" id="amount5" name="amount5" readonly value="<%=amounts[0]%>" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>
			  <div class="list-block media-list" style="margin:.5rem;display:<%=qtys[2]==0?"none":"block" %>">
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder50.jpg" style='width: 2rem;height:5.2rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">50KG</div>
						  <div class="item-after"><span id="prc50"><%=prices[2]%></span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty50" name="qty50" readonly value="<%=qtys[2]%>" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(元)</div>
						  <div>
								<input type="text" id="amount50" name="amount50" readonly value="<%=amounts[2]%>" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>
			  <div class="content-block">
				<div class="row">
				  <div class="col-75"><div>&nbsp;</div></div>
				  <div class="col-25"><a href="#" class="button button-big button-fill button-danger" onclick="javascript:submitOrder();">提交</a></div>
				</div>
			  </div>     
		  </div>
      </div>
  </div>

	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/jquery.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7-swiper.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/app.js' charset='utf-8'></script>

  </body>
</html>
