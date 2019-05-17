<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
		<style type="text/css">
		label{font-size: 15px;}
		</style>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/baiduMap.js"></script>
<script type="text/javascript">
$(function(){
    $(".click").click(function(){
 		window.location.href="<%=request.getContextPath()%>/person/addOrUpdate.do";
 	});
});

//初始化地图
function initMap() {
	var addr = "${address.state}"+"${address.city}"+ "${address.county}" + "${address.address1}"
	//alert(addr);
	var map = new BMap.Map("map");
	map.centerAndZoom(addr, 16); 
	
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(addr, function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
		}else{
			alert("您选择地址没有解析到结果!");
		}
	},"${address.county}");
	
} 

</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">我的信息</a>
				</li>
				<li>
					<a href="#">基本信息</a>
				</li>
			</ul>
		</div>
		<div class="mainbox">
			<div class="mainleft">
				<div class="leftinfo">
					<div class="listtitle">
						<a href="#" class="more1">更多</a>公司信息
					</div>
					<ul class="newlist">
						<li>
							<label>
								名称:${corporation.corpName}
							</label>
						</li>
						<li>
							<label>
								简称:${corporation.corpShortName}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								上级公司:${corporation.parentCorp}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								是否独立核算:${corporation.selfAccounting}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								营业执照:${corporation.license}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								企业税号:${corporation.corporationTax}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								公司创建日期:${corporation.buildDate} 
							</label>
							<span></span>
						</li>
						<li>
							<label>
								企业网址:${corporation.webUrl} 
							</label>
							<span></span>
						</li>
						<li>
							<label>
								企业邮箱:${corporation.email}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								地址:${address.state}${address.city}${address.county}${address.address1}
							</label>
							<span> </span>
						</li>
						<li>
							<label>
								邮编:${address.postalCode}
							</label>
							<span></span>
						</li>

					</ul>
				</div>
			</div>
			<div class="mainright">
				<div class="dflist">
					<div class="listtitle">
						<a href="#" class="more1">更多</a>地图
					</div>

					<div id="map" style="height: 700px;"></div>
				</div>
			</div>
		</div>
	</body>
</html>
