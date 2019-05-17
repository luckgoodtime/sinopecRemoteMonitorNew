<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jsp/include/css.jsp"%>
<%@ include file="/jsp/include/js.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".selec").uedSelect();
		
	});
	

</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">车辆管理</a></li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>车辆信息</span>
		</div>
		<form method="post"
			action="<%=request.getContextPath()%>/truck/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				
				<li><label> 类型<b>*</b>
				</label>
					<div class="vocation">
						<select name="truckType" class="selec">
							<option  value="车头">车头</option>
							<option <c:if test="${o.truckType=='罐车'}">selected="selected"</c:if> value="罐车">罐车</option>
						</select>
					</div></li>
				<li><label> 车牌号 </label> <input name="plateNo" type="text" class="dfinput" value="${o.plateNo}" /> <i></i></li>	
				<li><label> 荷载吨数 </label> <input name="tonnage" type="text" class="dfinput" value="${o.tonnage}" /> <i></i></li>
				<li><label>下次检测日期 </label> <input name="nextCheckDate" type="text" class="dfinput" value="${o.nextCheckDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> <i></i></li>
				<li><label> 荷载吨数 </label> <input name="tonnage" type="text" class="dfinput" value="${o.tonnage}" /> <i></i></li>
				<li><label> 最新槽罐号 </label> <input name="lastTankerNo" type="text" class="dfinput" value="${o.lastTankerNo}" /> <i></i></li>
					<li>
						<label>
								所属公司		
						</label>
						<div class="vocation">
							<select name="corpPartyId" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${corporationList}" var="c">
								<option  <c:if test="${o.corpPartyId==c.partyId}">selected="selected"</c:if>
								  value="${c.partyId}">${c.corpName}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					
				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>
