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
	})
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>我的伙伴</li>
			<li>运输商</li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>运输商</span>
		</div>
		<form method="post" action="<%=request.getContextPath()%>/transporter/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				
					<li>
						<label>运输商</label>
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
					
					<li>
						<label>编号</label>
						<input name="serialNo" type="text" class="dfinput"
							value="${o.serialNo}" />
					</li>
					<li>
						<label>区域</label>
						<input name="region" type="text" class="dfinput"
							value="${o.region}" />
					</li>
					<li>
						<label>我方客户经理</label>
						<div class="vocation">
							<select name="bizManager" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${personServiceList}" var="p">
								<option  <c:if test="${o.bizManager==p.partyId}">selected="selected"</c:if>
								  value="${p.partyId}">${p.firstName}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>业务开始日期</label>
						<input name="bizStartDate" type="text" class="dfinput"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" value="${o.bizStartDate}"/>
					</li>





				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>