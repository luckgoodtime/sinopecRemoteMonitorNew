<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jspM/include/meta.jsp"%>
<%@ include file="/jspM/include/css.jsp"%>
<%@ include file="/jspM/include/js.jsp"%>
<script type="text/javascript"  src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	$(function(){
		
		//alert(location.href.split('#')[0]);
	    //$('.top_header_title').text("新闻");
		  
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: '${APPID}', // 必填，公众号的唯一标识
		    timestamp: '${timestamp}', // 必填，生成签名的时间戳
		    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
		    signature: '${signature}',// 必填，签名
		    jsApiList: ['chooseImage','scanQRCode','chooseWXPay'] // 必填，需要使用的JS接口列表
		});
    
		wx.ready(function(){
		    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		   // alert("wx.ready");
		   
		});
		
		
		wx.error(function(res){
		    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			//alert("wx.error");
		});
		
});  
	
	function scan() {
		wx.scanQRCode({
			needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			success: function (res) {
				var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
				alert(result);
			}
		});
	}
	
	
	
	function pay() {
		wx.chooseWXPay({
		    timestamp: '${wxPay.timeStamp}',
		    nonceStr:'${wxPay.nonce_str}', 
		    package:'${wxPay.prepay_id}',
		    signType: 'MD5',
		    paySign: '${wxPay.paySign}',
		    success: function (res) {
		    	console.log(res);
		        alert("支付成功");
		    }
		});
	}
	
	
</script>
</head>
<body>
<jsp:include page="/jspM/include/header.jsp"/>	
	
	


<div class="article_view">
		<div class="article_view_title">
			<p> 欢迎</p> 
		</div>
		<hr />
		
		<div class="article_view_cont">
			欢迎，nickname： ${nickname}
			<p>openid： ${openid} </p>
			<p>sex： ${sex} </p>
			<p>country： ${country} </p>
			<p>province： ${province} </p>
			<p>city： ${city} </p>
			<p>headimgurl：<img alt="" src="${headimgurl}"> </p>
			<p><a href="javascript:scan();">扫一扫</a> </p>
			<p></p>
			<p><a href="javascript:pay();">支付一分钱</a> </p>
		</div>
	</div>
	
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
