<%@page import="com.lng.util.Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String bizType = request.getParameter("bizType");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
		
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.autocomplete.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"/>
<script type="text/javascript">

function showAddedEndPoint(flag) {
	if(flag) {
		  $("#endPoint2").show();
		  $("#endPoint3").show();
	} else {
		  $("#endPoint2").hide();
		  $("#endPoint3").hide();		  
	}

}
var emails = [
   { name: "南京华润_njhr", id: "peter@pan.de" },
   { name: "哈纳斯_HNS", id: "molly@yahoo.com" },
   { name: "Forneria Marconi", id: "live@japan.jp" },
   { name: "Master <em>Sync</em>", id: "205bw@samsung.com" },
   { name: "Dr. <strong>Tech</strong> de Log", id: "g15@logitech.com" },
   { name: "Don Corleone", id: "don@vegas.com" },
   { name: "Mc Chick", id: "info@donalds.org" },
   { name: "Donnie Darko", id: "dd@timeshift.info" },
   { name: "Quake The Net", id: "webmaster@quakenet.org" },
   { name: "Dr. Write", id: "write@writable.com" },
   { name: "GG Bond", id: "Bond@qq.com" },
   { name: "Zhuzhu Xia", id: "zhuzhu@qq.com" }
];


/**
$(function() {
	$('#customerName').autocomplete(emails, {
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  formatItem: function(row, i, max) {
	      return i + '/' + max + ':"' + row.name + '"[' + row.id + ']';
	  },
	  formatMatch: function(row, i, max) {
	      return row.name + row.id;
	  },
	  formatResult: function(row) {
	      return row.name;
	  }
	}).result(function(event, row, formatted) {
	  alert(row.name);
	});
});
*/

$(function() {
	$('#supplierName').autocomplete("<%=request.getContextPath()%>/corporation/listinfor.do", {
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  formatItem: function(row, i, max, value) {
	      return i + '/' + max + ':"' + row[0].split("_")[0] + '"[' + row[0].split("_")[1] + ']';
	  },
	  formatMatch: function(row, i, max) {
	      return row[0].split("_")[0] + row[0].split("_")[1];
	  },
	  formatResult: function(row) {
	  	  //alert(row[0].split("_")[1]);
	      return row[0].split("_")[1];
	  }
	}).result(function(event, row, formatted) {
	  var array = row[0].split("_");
	  $('#supCorpPartyId').val(array[2]);
	});
	
	
	$('#endPointName1').autocomplete("<%=request.getContextPath()%>/endPoint/listAll.do", {
	  dataType:'json',
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  parse:function(data) {//解释返回的数据，把其存在数组里  
          var parsed = [];  
          for (var i = 0; i < data.length; i++) {  
              parsed[parsed.length] = {  
              data: data[i],  
              value: data[i].name,  
              result: data[i].name //返回的结果显示内容  
              };  
          }  
          return parsed;  
      }, 	  
	  formatItem: function(row, i, max, value) {
	      return i + '/' + max + ':"' + row.name + "_" + row.py;
	  },
	  formatMatch: function(row, i, max) {
	      return row.name + row.id;
	  },
	  formatResult: function(row) {
	      return row.name;
	  }
	}).result(function(event, row, formatted) {
	  $('#endPointPartyId1').val(row.id);
	});
	
	$('#endPointName2').autocomplete("<%=request.getContextPath()%>/endPoint/listAll.do", {
	  dataType:'json',
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  parse:function(data) {//解释返回的数据，把其存在数组里  
          var parsed = [];  
          for (var i = 0; i < data.length; i++) {  
              parsed[parsed.length] = {  
              data: data[i],  
              value: data[i].name,  
              result: data[i].name //返回的结果显示内容  
              };  
          }  
          return parsed;  
      }, 	  
	  formatItem: function(row, i, max, value) {
	      return i + '/' + max + ':"' + row.name + "_" + row.py;
	  },
	  formatMatch: function(row, i, max) {
	      return row.name + row.id;
	  },
	  formatResult: function(row) {
	      return row.name;
	  }
	}).result(function(event, row, formatted) {
	  $('#endPointPartyId2').val(row.id);
	});
	
	$('#endPointName3').autocomplete("<%=request.getContextPath()%>/endPoint/listAll.do", {
	  dataType:'json',
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  parse:function(data) {//解释返回的数据，把其存在数组里  
          var parsed = [];  
          for (var i = 0; i < data.length; i++) {  
              parsed[parsed.length] = {  
              data: data[i],  
              value: data[i].name,  
              result: data[i].name //返回的结果显示内容  
              };  
          }  
          return parsed;  
      }, 	  
	  formatItem: function(row, i, max, value) {
	      return i + '/' + max + ':"' + row.name + "_" + row.py;
	  },
	  formatMatch: function(row, i, max) {
	      return row.name + row.id;
	  },
	  formatResult: function(row) {
	      return row.name;
	  }
	}).result(function(event, row, formatted) {
	  $('#endPointPartyId3').val(row.id);
	});	
	
	
	$('#sourceName').autocomplete("<%=request.getContextPath()%>/sourcePoint/listAll.do", {
	  dataType:'json',
	  max: 12,    //列表里的条目数
	  minChars: 0,    //自动完成激活之前填入的最小字符
	  width: 400,     //提示的宽度，溢出隐藏
	  scrollHeight: 300,   //提示的高度，溢出显示滚动条
	  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
	  autoFill: false,    //自动填充
	  parse:function(data) {//解释返回的数据，把其存在数组里  
          var parsed = [];  
          for (var i = 0; i < data.length; i++) {  
              parsed[parsed.length] = {  
              data: data[i],  
              value: data[i].name,  
              result: data[i].name //返回的结果显示内容  
              };  
          }  
          return parsed;  
      }, 	  
	  formatItem: function(row, i, max, value) {
	      return i + '/' + max + ':"' + row.name + "_" + row.py;
	  },
	  formatMatch: function(row, i, max) {
	      return row.name;
	  },
	  formatResult: function(row) {
	      return row.name;
	  }
	}).result(function(event, row, formatted) {
	  $('#sourcePartyId').val(row.id);
	});
});	



</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">业务执行</a>
				</li>
				<li>
					<a href="#">订单执行</a>
				</li>	
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>采购订单信息</span>
			</div>
			<form name="form1" method="post" action="<%=request.getContextPath()%>/biz/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do?from=${param.from}">
				<c:if test="${o.id != null}">
					<input name="id" type="hidden" value="${o.id}" />
				</c:if>				
				<c:if test="${o.shippingOrderId != null}">
					<input name="shippingOrderId" type="hidden" value="${o.shippingOrderId}" />
				</c:if>
				<input name="bizType" type="hidden" value="<%=bizType%>" />
				
				<ul class="forminfo">
					<li>
						<label>
							商品名称
						</label>
							<select name="gasType" style="opacity:100">
								<c:forEach items="${gasTypeList}" var="dic">
	                                <option <c:if test="${o.gasType==dic.value}">selected="selected"</c:if> value="${dic.value}">${dic.des}</option>
	                            </c:forEach>	
							</select>
						<i></i>
					</li>				
					<li>
						<label>
							供应商
						</label>
						<input id="supplierName" name="supplierName" type="text" class="dfinput" value="${o.supplierName}"/>
						<input id="supCorpPartyId" name="supCorpPartyId" type="hidden" value="${o.supCorpPartyId}"/>
						<i></i>
					</li>
					<li>
						<label>
							气源
						</label>
						<input id="sourceName" name="sourceName" type="text" class="dfinput" value="${o.sourceName}"/>
						<input id="sourcePartyId" name="sourcePartyId" type="hidden" value="${o.sourcePartyId}"/>
						<i></i>
					</li>					
					<li>
						<label>
							要求到达日期
						</label>
						<input name="requiredDate" type="text" class="dfinput"
							 onclick="WdatePicker({lang:'zh-cn'});" value="${o.requiredString}"/>
						<i></i>
					</li>
					<li>
						<label>
							计划提货量
						</label>
						<input name="quantity" type="text" class="dfinput" value="${o.quantity}"/>
						<i></i>
					</li>					
					<li>
						<label>
							计划装车日期
						</label>
						<input name="planLoadingDate" type="text" class="dfinput"
							 onclick="WdatePicker({lang:'zh-cn'});" value="${o.planLoadingString}"/>
						<i></i>
					</li>
					<li>
						<label>
							单号
						</label>
						<input name="trackNumber" type="text" class="dfinput"
							value="${o.trackNumber}" />
					</li>						
					<li>
						<label>
							车号
						</label>
						<input name="plateNo" type="text" class="dfinput"
							value="${o.plateNo}" />
					</li>					
					<li>
						<label>
							司机
						</label>
						<input name="driverName" type="text" class="dfinput"
							value="${o.driverName}" />
					</li>
					<li>
						<label>
							司机电话
						</label>
						<input name="driverTel" type="text" class="dfinput"
							value="${o.driverTel}" />
					</li>										
					<li>
						<label>
							是否分卸
						</label>
						<input name="unLoadOnRoad" type="radio" value="1" onchange="showAddedEndPoint(true)"
							<c:if test="${o.unLoadOnRoad != null && o.unLoadOnRoad=='1'}">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="unLoadOnRoad" type="radio" value="0" onchange="showAddedEndPoint(false)" 
							<c:if test="${o.unLoadOnRoad == null || o.unLoadOnRoad=='0'}">checked="checked"</c:if>/>否							
					</li>
					<li>
						<label>
							卸气点1
						</label>
						<input id="endPointName1" name="endPointName1" type="text" class="dfinput"	value="${o.endPointName1}" />
						<input id="endPointPartyId1" name="endPointPartyId1" type="hidden" class="dfinput" value="${o.endPointPartyId1}" />						
						
					</li>	
					<li id="endPoint2" class="hidden">
						<label>
							卸气点2
						</label>
						<input id="endPointName2" name="endPointName2" type="text" class="dfinput"	value="${o.endPointName2}" />
						<input id="endPointPartyId2" name="endPointPartyId2" type="hidden" class="dfinput" value="${o.endPointPartyId2}" />							
					</li>	
					<li id="endPoint3" class="hidden">
						<label>
							卸气点3
						</label>
						<input id="endPointName3" name="endPointName3" type="text" class="dfinput"	value="${o.endPointName3}" />
						<input id="endPointPartyId3" name="endPointPartyId3" type="hidden" class="dfinput" value="${o.endPointPartyId3}" />	
					</li>																
					
					<li>
						<label>
							装车时间
						</label>
						<input name="loadingTime" type="text" class="dfinput"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',lang:'zh-cn'});" value="${o.loadingTime}"/>							
					</li>
					<li>
						<label>
							装车皮重
						</label>
						<input name="loadingTW" type="text" class="dfinput"
							value="${o.loadingTW}" />
					</li>
					<li>
						<label>
							装车毛重
						</label>
						<input name="loadingGW" type="text" class="dfinput"
							value="${o.loadingGW}" />
					</li>
					<li>
						<label>
							装车重
						</label>
						<input name="loadingNW" type="text" class="dfinput"
							value="${o.loadingNW == null?0:o.loadingNW}" />
					</li>	
					
					<li>
						<label>
							卸车时间
						</label>
						<input name="unloadingTime" type="text" class="dfinput"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',lang:'zh-cn'});" value="${o.unloadingTime}"/>								
					</li>
					<li>
						<label>
							卸车毛重
						</label>
						<input name="unLoadingGW" type="text" class="dfinput"
							value="${o.unLoadingGW}" />
					</li>
					<li>
						<label>
							卸车皮重
						</label>
						<input name="unLoadingTW" type="text" class="dfinput"
							value="${o.unLoadingTW}" />
					</li>
					<li>
						<label>
							卸车重
						</label>
						<input name="unLoadingNW" type="text" class="dfinput"
							value="${o.unLoadingNW}" />
					</li>	
					<li>
						<label>
							采购单价
						</label>
						<input id="unitPrice"  name="unitPrice" type="text" class="dfinput" onfocus="javascript:if(this.value == '0') this.value='';"  value="${o.unitPrice == null?0:o.unitPrice}" onblur="javascritp:calculate();if(this.value=='')this.value='0';"/>
					</li>
					<li>
						<label>
							金额
						</label>
						<input id="money"  name="money" type="text" class="dfinput"  value="${o.money}"/>
					</li>
					<li>
						<label>
							运费单价
						</label>
						<input id="freightPrice"  name="freightPrice" type="text" class="dfinput" onfocus="javascript:if(this.value == '0') this.value='';"  value="${o.freightPrice == null?0:o.freightPrice}" onblur="javascritp:calculate();if(this.value=='')this.value='0';"/>
					</li>					 
					<li>
						<label>
							运费
						</label>
						<input id="transportFee" name="transportFee" type="text" class="dfinput" onfocus="javascript:if(this.value == '0') this.value='';"  value="${o.transportFee == null?0:o.transportFee}" onblur="javascritp:calculate();if(this.value=='')this.value='0';"/>
					</li>	
					<li>
							<label>
								采购成本
							</label>
							<input id="purchaseCost" name="purchaseCost" readonly="readonly" type="text" class="dfinput" value="${o.purchaseCost}" />
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
		<script>
			function calculate() {
				var unitPrice = form1.unitPrice.value * 1;
				var freightPrice = form1.freightPrice.value *1;
				var loadingNW = form1.loadingNW.value * 1;
				
				var money = unitPrice * loadingNW;
				var transportFee = freightPrice * loadingNW;
				
				form1.money.value = money.toFixed(2);
				form1.transportFee.value = transportFee.toFixed(2);
				form1.purchaseCost.value = (money + transportFee).toFixed(2);
				
			}
		</script>
	</body>

</html>

