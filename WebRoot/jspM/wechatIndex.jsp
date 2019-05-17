<!DOCTYPE html>
<html>
<head>
	<%@ page pageEncoding="UTF-8"%>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="../cssM/light7/light7.min.css">
	<link rel="stylesheet" href="../cssM/light7/light7-swiper.min.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.cookie.js"></script> 
</head>
<body>
<div class="page-group">
    <div class="page page-current" id="page-index">
        <!-- 你的html代码 -->
        <div class="content">
            <div class="content-inner" style="background-color: white;">
                <div id="register_card" class="card" style="display: block;background-color: #39A3E3;">
                    <div valign="bottom" class="card-header color-white no-border" style="color: white;padding: 0.5rem;">
                        <div class="item-media" style="display: inline;width: 100%; height: 2rem;line-height: 2rem;position: relative;">
                            <img class="pull-left" style="border-radius: 50%;width: 2.5rem;" src="<%=request.getContextPath()%>/imagesM/companyicon/HZRLPG.png" alt="">
                            <span class="pull-left" style="margin-left: .5rem;"></span>
                            <span class="pull-right" style="">浙江 台州临海</span>
                        </div>
                    </div>
                    <div class="card-content text-center">
                        <div class="card-content-inner text-center">
                            <a href="#" class="button" style="color: #EDE425;border:0px solid #EDE425;width: 10rem; height:3rem; font-size:1.5rem;margin: 0 auto">浙江海圳荣</a>
                            <p class="" style="color: white">微信定气平台即将推行使用，请关注微信公众号"海圳荣新零售"</p>
                            <a class="button button-big button-fill button-danger" href="<%=request.getContextPath()%>/orderM/wxLogin.do">请开始定气</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content-inner" style="background-color: white; margin: .5rem 0; padding: .5rem">
                <div style="margin: 0">
                    <span>我的特权</span>
                    <a href="#" class="button button-link pull-right">
                        更多
                        <span class="icon icon-right"></span>
                    </a>
                </div>
                <div class="row" style="margin-top: .5rem">
                    <a class="col-33 text-center" href="#">
                        <span class="icon icon-star"></span><br/>
                        <span class="" style="color: black">生活福利</span>
                    </a>
                    <a class="col-33 text-center" href="#">
                        <span class="icon icon-home"></span><br/>
                        <span class="" style="color: black">积分区</span>
                    </a>
                    <a class="col-33 text-center" href="#">
                        <span class="icon icon-settings"></span><br/>
                        <span class="" style="color: black">安全用气</span>
                    </a>
                </div>
            </div>
            <!-- 
            <div class="content-inner" style="background-color: white; margin: .5rem 0; padding: .5rem">
                <div class="">海圳荣的风采</div>
                <div class="swiper-container swiper-container-horizontal" data-space-between="10" data-autoplay="1500" style="height: 10rem">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <a href="#">
                                <img style="width: 100%" class='card-cover' src="//gd1.alicdn.com/bao/uploaded/i1/TB1kzlgHVXXXXbtXpXXXXXXXXXX_!!0-item_pic.jpg_320x320.jpg" alt="">
                            </a>
                        </div>
                        <div class="swiper-slide">
                            <a href="#">
                                <img style="width: 100%" class='card-cover' src="//gd3.alicdn.com/imgextra/i3/238126515/TB21mEfcFXXXXc5XXXXXXXXXXXX_!!238126515.jpg_320x320.jpg" alt="">
                            </a>
                        </div>
                        <div class="swiper-slide">
                            <a href="#">
                                <img style="width: 100%" class='card-cover' src="//gd2.alicdn.com/imgextra/i2/238126515/TB2TpgmcFXXXXbwXXXXXXXXXXXX_!!238126515.jpg_320x320.jpg" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="swiper-pagination">
                        <span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span>
                        <span class="swiper-pagination-bullet"></span>
                        <span class="swiper-pagination-bullet"></span>
                    </div>
                 </div>
            </div>
             -->
        </div>
    </div>
</div>
 
	<!--<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>-->
	<script type='text/javascript' src='../jsM/light7/jquery.min.js' charset='utf-8'></script>
	<script>
		$(document).ready(function () {
	 
		});
	 
	</script>
 
</body>
</html>
