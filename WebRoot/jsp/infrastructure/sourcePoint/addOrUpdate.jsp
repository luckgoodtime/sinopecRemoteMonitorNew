<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css2.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>
<script type="text/javascript">
	$(function() {
		//$(".selec").uedSelect();
		//省市县级联
		new PCAS("state","city","county","${address.state}","${address.city}","${address.county}");
	});
	

</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">气源管理</a></li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>气源信息</span>
		</div>
		<form method="post"
			action="<%=request.getContextPath()%>/sourcePoint/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="partyId" type="hidden" value="${o.partyId}" />
			</c:if>
			<ul class="forminfo">
				<li><label> 名称<b>*</b>
				</label> <input name="sourceName" type="text" class="dfinput"
					value="${o.sourceName}" /> <i></i></li>
				<li><label> 简称<b>*</b>
				</label> <input name="sourceShortName" type="text" class="dfinput"
					value="${o.sourceShortName}" /> <i></i></li>
				<li><label> 类型<b>*</b>
				</label>
					<div class="vocation">
						<select name="sourceType" class="ssx">
							<option  value="生产型">生产型</option>
							<option <c:if test="${o.sourceType=='存储型'}">selected="selected"</c:if> value="存储型">存储型</option>
						</select>
					</div></li>
				<li><label> 生产日能力(方) </label> <input name="dailyAbility"
					type="text" class="dfinput" value="${o.dailyAbility}" /> <i></i></li>
				<li><label> 生产年能力 (吨)</label> <input name="annualAbility"
					type="text" class="dfinput" value="${o.annualAbility}" /></li>
				<li><label> 热值 </label> <input name="calorificValue"
					type="text" class="dfinput" value="${o.calorificValue}" /></li>
				<li><label> 气化率 </label> <input name="gasificationRate"
					type="text" class="dfinput" value="${o.gasificationRate}" /></li>
						
						<li>
						<label>
							省
						</label>
						
						<div class="vocation">
							<select id="state"  name="state" class="ssx" style=""></select>
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
							乡镇地址
						</label>
						<input name="address1" type="text" class="dfinput"
							value="${address.address1}" />
					</li>
					<li>
						<label>
							联系电话
						</label>
						<input name="tel" type="text" class="dfinput"
							value="${address.tel}" />
					</li>					
					<li>
						<label>
							联系手机
						</label>
						<input name="mobile" type="text" class="dfinput"
							value="${address.mobile}" />
					</li>					
					
				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>