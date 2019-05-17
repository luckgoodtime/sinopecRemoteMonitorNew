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
              <h1 class="title">海圳荣欢迎 ${nickname}自助定气</h1>
          </header>
          <nav class="bar bar-tab">
              <a class="tab-item active" href="<%=request.getContextPath()%>/jspM/wx/customer/startSUI_Order.jsp?openid=${openid}">
                  <span class="icon icon-cart"></span>
                  <span class="tab-label">定气</span>
              </a>
              <a class="tab-item myorder" href="<%=request.getContextPath()%>/wx/listRequestOrder.do?openid=${openid}">
                  <span class="icon icon-menu"></span>
                  <span class="tab-label">订单</span>
              </a>
              <a class="tab-item" href="<%=request.getContextPath()%>/jspM/wx/customer/customerAddresslist.jsp">
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
			  <div class="list-block media-list" style="margin:.5rem">
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder15.jpg" style='width: 2rem;height:4rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">15KG</div>
						  <div class="item-after"><span id="prc15">105</span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty15" name="qty15" placeholder="0" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(:元)</div>
						  <div>
								<input type="text" id="amount15" name="amount15" readonly placeholder="0" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>              
			  <div class="list-block media-list" style="margin:.5rem">
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder5.jpg" style='width: 2rem;height:3rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">5KG</div>
						  <div class="item-after"><span id="prc5">35</span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty5" name="qty5" placeholder="0" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(元)</div>
						  <div>
								<input type="text" id="amount5" name="amount5" readonly placeholder="0" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>
			  <div class="list-block media-list" style="margin:.5rem">
				<ul>
				  <li>
					<div class="item-content">
					  <div class="item-media"><img src="<%=request.getContextPath()%>/imagesM/cylinder/cylinder50.jpg" style='width: 2rem;height:5.2rem;'></div>
					  <div class="item-inner">
						<div class="item-title-row">
						  <div class="item-title">50KG</div>
						  <div class="item-after"><span id="prc50">355</span>元/瓶</div>
						</div>
						<div class="item-title-row">
							<div class="item-title label">数量(瓶)</div>
							<div>
								<input type="number" id="qty50" name="qty50" placeholder="0" style="border-bottom:.1rem solid #f60;text-align:right;height:1.50rem;">
							</div>
						</div>
						<div class="item-title-row">
						  <div class="item-title label">金额(元)</div>
						  <div>
								<input type="text" id="amount50" name="amount50" readonly placeholder="0" style="text-align:right;height:1.20rem;font-size:1.40rem;">
						  </div>
						</div>
					  </div>
					</div>
				  </li>
				 </ul>
				</div>
			  <div class="content-block">
				<div class="row">
				  <div class="col-75"><div id="orderDes" class="button button-big button-fill button-success">汇总</div></div>
				  <div class="col-25"><a href="#" class="button button-big button-fill button-danger" onclick="javascript:submitRequest();">提交</a></div>
				</div>
			  </div>     
			  <!-- 
				  <div>
					<p>nickname： ${nickname} </p>
					<p>openid： ${openid} </p>
					<p>sex： ${sex} </p>
					<p>country： ${country} </p>
					<p>province： ${province} </p>
					<p>city： ${city} </p>
					<p>headimgurl：<img alt="" src="${headimgurl}"> </p>
				  </div>
			   -->
		     
		  </div>
      </div>
  </div>

	<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/jquery.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/light7-swiper.min.js' charset='utf-8'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/jsM/light7/app.js' charset='utf-8'></script>

	<script>
	
		var openId = "${openid}";
	
	    // 5KG,15KG,50KG顺序
		var qtys = [0,0,0];
		var prices = [0,0,0];
		var amounts = [0,0,0];
		
		//结构化的orderInfor,格式 :5KG_30_1_30|15KG_95_2_190|
		var orderInfor;
		
		$(document).ready(function () {			
  			setInterval("freshData();",600);
		});
		
		var freshData = function(){
			
			prices[0] = $.trim($('#prc5').text()) * 1;			
			prices[1] = $.trim($('#prc15').text()) * 1;
			prices[2] = $.trim($('#prc50').text()) * 1;			
			
			var price 	= prices[0];
			var qty5 	= $('#qty5').val()*1;
			var amount5 = qty5 * price;
			$('#amount5').val(amount5);
			qtys[0] 	= qty5;
			amounts[0]	= amount5;
			  
  			price		= prices[1];
			var qty15	= $('#qty15').val()*1;
			var amount15= qty15 * price; 
			$('#amount15').val(amount15);    		
			qtys[1] 	= qty15;
			amounts[1]	= amount15; 
			
			price 		= prices[2];  			
			var qty50	= $('#qty50').val()*1;
			var amount50= qty50 * price;
			$('#amount50').val(amount50);
			qtys[2] 	= qty50;
			amounts[2]	= amount50; 
			
			ordeSumDes();
		}
		
		var ordeSumDes = function() {		
			var type 		= 0;
			var totalQty	= 0
			var totalAmount = 0;
			
			for(var k in qtys) {
				if(qtys[k] > 0) {
					type++;
					totalQty += qtys[k];
				}
			}
			
			for(var m in amounts) {
				totalAmount += amounts[m];
			}
			
			var msg = type + "种商品," + totalQty + "瓶,共计 " + totalAmount + "元";
			
			$('#orderDes').text(msg);
		};
		
		var orderDetailDes = function() {
			
			freshData();
			
			var type 		= 0;
			var totalQty	= 0
			var totalAmount = 0;
			orderInfor		= "";
			
			var msg = "";
			if(qtys[0] > 0) {
				msg = "5KG " + qtys[0] + "瓶," + prices[0] + "元/瓶,计" + amounts[0] + "元";
				totalQty += qtys[0];
				totalAmount += amounts[0];
				orderInfor = "5KG_" + prices[0] + "_" + qtys[0] + "_" + amounts[0];
			}
			if(qtys[1] > 0) {
				if(msg.length > 0) msg = msg + ";";			
				msg = msg + "15KG " + qtys[1] + "瓶," + prices[1] + "元/瓶,计" + amounts[1] + "元";
				totalQty += qtys[1];
				totalAmount += amounts[1];
				
				if(orderInfor.length > 0) orderInfor = orderInfor + "-";	
				orderInfor = orderInfor + "15KG_" + prices[1] + "_" + qtys[1] + "_" + amounts[1];
				
			}
			if(qtys[2] > 0) {
				if(msg.length > 0) msg = msg + ";";
				msg =  msg + "50KG " + qtys[2] + "瓶," + prices[2] + "元/瓶,计" + amounts[2] + "元";
				totalQty += qtys[2];
				totalAmount += amounts[2];
				
				if(orderInfor.length > 0) orderInfor = orderInfor + "-";	
				orderInfor = orderInfor +  "50KG_" + prices[2] + "_" + qtys[2] + "_" + amounts[2];
			}				
			
			if(msg.length > 0) msg = msg + "。";
			msg = msg + "共 " + totalQty + "瓶,合计" + totalAmount + "元";
			
			return msg;
		}
		
		function submitRequest() {			
			$.confirm(orderDetailDes(),'请确认订单', function () {
		          $.router.loadPage({
		        	  url: "<%=request.getContextPath()%>/jspM/wx/customer/selectAddress.jsp?orderInfor=" + orderInfor + "&openid=" + "555555555",
		        	  noAnimation: false,
		        	  replace: false
		        	});
		    });
		};
		
		var submitOrder = function(){
			
			//alert(orderInfor);
			
			var gasCardNo		= $("#gasCardNo").val();
			var customerName	= $("#customerName").val();
			var customerTel		= $("#customerTel").val();
			var customerAddress = $("#customerAddress").val();
			var acceptStation	= $("#acceptStation").val();
			
			var params = "orderInfor=" + orderInfor + "&gasCardNo="+gasCardNo+"&customerName=" + customerName + "&customerTel="
			 + customerTel + "&customerAddress="+customerAddress+"&acceptStation=" + acceptStation + "&openid=" + openId;
			
			//alert(params);
			
			var url = "<%=request.getContextPath()%>/wx/saveRequestOrder.do?" + params;
			
	        $.router.loadPage({
	        	 url: url,
	        	 noAnimation: false,
	        	 replace: false
	        });
		};
		

	 
	</script>
  </body>
</html>
