<%@page import="com.lng.util.Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css2.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		<style type="text/css">
			/*#map{height:200px;width:50%} */
		</style>
		

<script type="text/javascript"  src="<%=request.getContextPath()%>/js/baiduMap.js"></script>
<script type="text/javascript">
$(function(){	
	//省市县级联
	new PCAS("state","city","county","${o.state}","${o.city}","${o.county}");
	
});

//初始化地图
var map;
function initMap() {
	map = new BMap.Map("map");//对应id为container的div
} 
function search() {
	var addr = $("#state option:selected").val()+ $("#city option:selected").val()+ 
				$("#county option:selected").val()+ $("#address1").val();
	alert(addr);
     	var options = {      
		      onSearchComplete: function(results){      
		          if (local.getStatus() == BMAP_STATUS_SUCCESS){      
		                // 判断状态是否正确  
		                var longitude = results.getPoi(0).point.lng;
		                var latitude = results.getPoi(0).point.lat;
		                $("#longitude").val(longitude);
		                $("#latitude").val(latitude);
		                
		               // alert(longitude+","+latitude);      
		          } else{
		        	  alert("返回状态不正确，无法正确获取地址");
		          }     
		      }      
		 };      
		var local = new BMap.LocalSearch(map, options);    
		local.search(addr);
} 
</script>
	</head>
	<body>
<div id="map" style="display: none;"></div>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">基础设施</a>
				</li>
				<li>
					<a href="#">地址管理</a>
				</li>	
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>地址信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/address/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="addressId" type="hidden" value="${o.addressId}" />
				</c:if>
				<ul class="forminfo">
					<li>
						<label>
							省
						</label>
						
						<div class="vocation">
							<select id="state"  name="state" class="ssx"></select>
						</div>
					
					</li>
					<li>
						<label>
							市
						</label>
						
						<div class="vocation">
							<select id="city" name="city" class="ssx"></select>
						</div>
						
					</li>
					<li>
						<label>
							县
						</label>
						
						<div class="vocation">
							<select id="county"  name="county" class="ssx"></select>
						</div>
						
					</li>															
					<li>
						<label>
							详细地址
						</label>
						<input id="address1" name="address1" type="text" class="dfinput"
							value="${o.address1}" />
					</li>
					<li>
						<label>
							邮编
						</label>
						<input name="postalCode" type="text" class="dfinput"
							value="${o.postalCode}" />
					</li>
					<li>
						<label>
							联系电话
						</label>
						<input name="tel" type="text" class="dfinput"
							value="${o.tel}" />
					</li>					
					<li>
						<label>
							联系手机
						</label>
						<input name="mobile" type="text" class="dfinput"
							value="${o.mobile}" />
					</li>
					<li>
						<label>
							经度
						</label>
						<input id="longitude" name="longitude" type="text" class="dfinput"
							value="${o.longitude}" />
							<input type="button" value="自动填充" onclick="search();"/>
					</li>	

					<li>
						<label>
							纬度
						</label>
						<input id="latitude" name="latitude" type="text" class="dfinput"
							value="${o.latitude}" /> 
					</li>	
					
					<li>
						<label>
							海拔
						</label>
						<input name="altitude" type="text" class="dfinput"
							value="${o.altitude}" />
					</li>											

																															
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="确认保存" />
							<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>
	</body>

</html>

