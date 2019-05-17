<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"/>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.autocomplete.min.js"></script>

<script type="text/javascript">
$(function() {
	$(".selec").uedSelect();
	
	//自动加载公司
	$('#parentCusName').autocomplete("<%=request.getContextPath()%>/customer/listAll.do", {
		  dataType:'json',
		  max: 100,    //列表里的条目数
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
		  formatItem: function(row, i, max) {
		      return i + '/' + max + ':"' + row.name + '"[' + row.id + ']';
		  }
		}).result(function(event, row, formatted) {
		  	$('#parentCusId').val(row.id);
		  	$('#parentCusName').val(row.cusName);
		  	$('#parentSerialNo').val(row.no);
		});
	 
});
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>我的伙伴</li>
			<li>客商</li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>客商</span>
		</div>
		<form method="post" action="<%=request.getContextPath()%>/customer/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				
					<li>
						<label>客户名称</label>
						<input name="cusName" type="text" class="dfinput" value="${o.cusName}" />
					</li>	
					<li>
						<label>客户简称</label>
						<input name="cusShortName" type="text" class="dfinput" value="${o.cusShortName}" />
					</li>					
					<li>
						<label>联系人</label>
						<input name="cusContact" type="text" class="dfinput" value="${o.cusContact}" />
					</li>	
					<li>
						<label>联系电话</label>
						<input name="cusContactTel" type="text" class="dfinput" value="${o.cusContactTel}" />
					</li>
					<li>
						<label>身份证</label>
						<input name="cusContactIdNo" type="text" class="dfinput" value="${o.cusContactIdNo}" />
					</li>						
					<li>
						<label>送气地址</label>
						<input name="receiptPlace" type="text" class="dfinput" value="${o.receiptPlace}" />
					</li>
					
					<li>
						<label>客户编号</label>
						<input name="serialNo" type="text" class="dfinput"
							value="${o.serialNo}" />
					</li>						
					
					<li>
						<label>客户类型</label>
						<div class="vocation">
							<select name="cusCategory" class="selec">
								<option value="居民户" <c:if test="${o.cusCategory=='居民户'}">selected="selected"</c:if>>居民户</option>
								<option value="工商户" <c:if test="${o.cusCategory=='工商户'}">selected="selected"</c:if>>工商户</option>
								<option value="企事业" <c:if test="${o.cusCategory=='企事业'}">selected="selected"</c:if>>企事业</option>
							</select>
						</div>
					</li>
					<li>
						<label>企业信用代码</label>
						<input name="corpCreditCode" type="text" class="dfinput" value="${o.corpCreditCode}" />
					</li>											
					<li>
						<label>行业性质</label>
						<div class="vocation">
							<select name="cusType" class="selec">
								<option value="民用" <c:if test="${o.cusType=='民用'}">selected="selected"</c:if>>民用</option>							
								<option value="工业" <c:if test="${o.cusType=='工业'}">selected="selected"</c:if>>工业</option>
								<option value="商业" <c:if test="${o.cusType=='商业'}">selected="selected"</c:if>>商业</option>
								<option value="事业" <c:if test="${o.cusType=='事业'}">selected="selected"</c:if>>事业</option>								
							</select>
						</div>
					</li>					
					<li>
						<label>用气证号</label>
						<input name="gasCardNo" type="text" class="dfinput" value="${o.gasCardNo}" />
					</li>	
					<li>
						<label>销售类型</label>
						<div class="vocation">
							<select name="saleType" class="selec">
								<option value="直销" <c:if test="${o.saleType=='直销'}">selected="selected"</c:if>>直销</option>
								<option value="批发" <c:if test="${o.saleType=='批发'}">selected="selected"</c:if>>批发</option>
							</select>
						</div>
					</li>					
					<li>
						<label>钢瓶租用方式</label>
						<div class="vocation">
							<select name="bottleRentType" class="selec">
								<option value="全额押金" <c:if test="${o.bottleRentType=='全额押金'}">selected="selected"</c:if>>全额押金</option>
								<option value="部分押金" <c:if test="${o.bottleRentType=='部分押金'}">selected="selected"</c:if>>部分押金</option>
								<option value="免押金" <c:if test="${o.bottleRentType=='免押金'}">selected="selected"</c:if>>免押金</option>								
							</select>
						</div>
					</li>				
					
					<li>
						<label>用气周期（天</label>
						<input name="cycleDays" type="text" class="dfinput" value="${o.cycleDays}" />
					</li>	
					<li>
						<label>结算周期</label>
						<div class="vocation">
							<select name="payCycle" class="selec">
								<option value="现结" <c:if test="${o.payCycle=='现结'}">selected="selected"</c:if>>现结</option>
								<option value="月结" <c:if test="${o.payCycle=='月结'}">selected="selected"</c:if>>月结</option>
								<option value="周结" <c:if test="${o.payCycle=='周结'}">selected="selected"</c:if>>周结</option>								
							</select>
						</div>
					</li>					
					<li>
						<label>定价方式</label>
						<div class="vocation">
							<select name="priceType" class="selec">
								<option value="浮动价" <c:if test="${o.priceType=='浮动价'}">selected="selected"</c:if>>浮动价</option>
								<option value="月价" <c:if test="${o.priceType=='月价'}">selected="selected"</c:if>>月价</option>
								<option value="半月价" <c:if test="${o.priceType=='半月价'}">selected="selected"</c:if>>半月价</option>	
								<option value="季度价" <c:if test="${o.priceType=='季度价'}">selected="selected"</c:if>>季度价</option>
								<option value="年价" <c:if test="${o.priceType=='年价'}">selected="selected"</c:if>>年价</option>
								<option value="议价" <c:if test="${o.priceType=='议价'}">selected="selected"</c:if>>议价</option>																
							</select>
						</div>
					</li>
					<li>
						<label>
							业务代表
						</label>
						<input id="bizRep" class="dfinput" type="text"  name="bizRep" value="${o.bizRep}"/>											
						<i></i>
					</li>						
					<li>
						<label>
							上级客户
						</label>
						<input id="parentCusName" name="parentCusName" type="text" class="dfinput" value="${o.parentCusName}"/>
						<input id="parentCusId" name="parentCusId" type="hidden" value="${o.parentCusId}"/>
						<i></i>
					</li>
					<li>
						<label>
							上级用气证号
						</label>
						<input id="parentSerialNo" class="dfinput" type="text"  name="parentSerialNo" value="${o.parentSerialNo}"/>											
						<i></i>
					</li>										
										
				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>