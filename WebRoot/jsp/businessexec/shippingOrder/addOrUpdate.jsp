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
		
		
	});
	
	
	function countWeight() {
		var loadingGW = $("#loadingGW").val();//毛重
		var loadingTW =  $("#loadingTW").val();//皮重
		if(!isNaN(loadingGW)&&!isNaN(loadingTW)){
			$("#loadingNW").val((Number(loadingGW)-Number(loadingTW)).toFixed(2));//净重
		}
	}
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>业务执行</li>
				<li>运输单</li>
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>运输单信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/shippingOrder/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="id" type="hidden" value="${o.id}"/>
				</c:if>
				<ul class="forminfo">
				<li>
						<label>承担运输公司</label>
						<div class="vocation">
							<select name="transportCorpPartyId" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${corporationList}" var="c">
								<option  <c:if test="${o.transportCorpPartyId==c.partyId}">selected="selected"</c:if>
								  value="${c.partyId}">${c.corpName}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					
					<li>
						<label>
								运输业主		
						</label>
						<div class="vocation">
							<select name="transportOwner" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${corporationList}" var="c">
								<option  <c:if test="${o.transportOwner==c.partyId}">selected="selected"</c:if>
								  value="${c.partyId}">${c.corpName}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>
							车牌号
						</label>
						<input name="truckNo" type="text" class="dfinput" value="${o.truckNo}"/>
						<i></i>
					</li>
					<li>
						<label>
							挂车号
						</label>
						<input name="tankNo" type="text" class="dfinput" value="${o.tankNo}"/>
						<i></i>
					</li>
					<li>
						<label>
							司机姓名
						</label>
						<input name="driverName" type="text" class="dfinput" value="${o.driverName}"/>
						<i></i>
					</li>
					<li>
						<label>
							司机电话
						</label>
						<input name="driverTel" type="text" class="dfinput" value="${o.driverTel}"/>
						<i></i>
					</li>
					
					<li>
						<label>
								气源点名称		
						</label>
						<div class="vocation">
							<select name="sourcePartyId" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${sourcePointList}" var="sp">
								<option  <c:if test="${o.sourcePartyId==sp[0].partyId}">selected="selected"</c:if>
								  value="${sp[0].partyId}">${sp[0].sourceName}--${sp[1].state}${sp[1].city}${sp[1].county}${sp[1].address1}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>
								卸气点名称		
						</label>
						<div class="vocation">
							<select name="endPointPartyId" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${endPointList}" var="ep">
								<option  <c:if test="${o.endPointPartyId==ep[0].partyId}">selected="selected"</c:if>
								  value="${ep[0].partyId}">${ep[0].pointName}--${ep[1].state}${ep[1].city}${ep[1].county}${ep[1].address1}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>
							要求日期
						</label>
						<input name="requiredString" type="text" class="dfinput" value="${o.requiredString}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" />
						<i></i>
					</li>
						<li>
						<label>
							是否分卸
						</label>
						<div class="vocation">
							<select name="unLoadOnRoad" class="selec">
								<option value="否">否</option>
								<option value="是" <c:if test="${o.unLoadOnRoad=='是'}">selected="selected"</c:if>>是</option>
							</select>
						</div>
					</li>
					<li>
						<label>
							计划装车日期
						</label>
						<input name="planLoadingString" type="text" class="dfinput" value="${o.planLoadingString}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"/>
						<i></i>
					</li>
					<li>
						<label>
							装车时间
						</label>
						<input name="loadingTime" type="text" class="dfinput" value="${o.loadingTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
						<i></i>
					</li>
					<li>
						<label>
							装车毛重
						</label>
						<input id="loadingGW" name="loadingGW" type="text" class="dfinput" value="${o.loadingGW}" onchange="countWeight();"/>
						<i></i>
					</li>
					<li>
						<label>
							装车皮重
						</label>
						<input id="loadingTW" name="loadingTW" type="text" class="dfinput" value="${o.loadingTW}" onchange="countWeight();"/>
						<i></i>
					</li>
					
					<li>
						<label>
							装车净重
						</label>
						<input id="loadingNW" name="loadingNW" type="text" class="dfinput" value="${o.loadingNW}" />
						<i></i>
					</li>
					<li>
						<label>
							运费结算量
						</label>
						<input name="settleWeight" type="text" class="dfinput" value="${o.settleWeight}" />
						<i></i>
					</li>
					
					
				
					
					
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="保存" />
						<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>


	</body>

</html>

