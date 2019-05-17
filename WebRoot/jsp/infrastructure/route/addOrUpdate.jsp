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
		$(".addr").uedSelect();
	})
</script>
</head>
<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">基础设施</a></li>
			<li><a href="#">线路管理</a></li>
			<li><c:if test="${o==null}">新增</c:if> <c:if test="${o!=null}">修改</c:if>
			</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>线路信息</span>
		</div>
		<form method="post"
			action="<%=request.getContextPath()%>/route/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
			<c:if test="${o!=null}">
				<input name="id" type="hidden" value="${o.id}" />
			</c:if>
			<ul class="forminfo">
				<li>
						<label>地址1</label>
						<div class="vocation">
							<select name="addressId1" class="addr">
							<option>请选择</option>
							<c:forEach items="${addressList}" var="addr">
								<option  <c:if test="${o.addressId1==addr.addressId}">selected="selected"</c:if>
								  value="${addr.addressId}">${addr.state}${addr.city}${addr.county}${addr.address1}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>地址2</label>
						<div class="vocation">
							<select name="addressId2" class="addr">
							<option>请选择</option>
							<c:forEach items="${addressList}" var="addr">
								<option  <c:if test="${o.addressId2==addr.addressId}">selected="selected"</c:if>
								  value="${addr.addressId}">${addr.state}${addr.city}${addr.county}${addr.address1}</option>
							</c:forEach>
							</select>
						</div>
					</li>
					<li>
						<label>距离 </label>
						<input name="distance" type="text" class="dfinput"
							value="${o.distance}" />
					</li>
					<li>
						<label>描述 </label>
						<input name="routeMarking" type="text" class="dfinput"
							value="${o.routeMarking}" />
					</li>

				<li><label> &nbsp; </label> <input type="submit" class="btn"
					value="确认保存" /> <input type="button" class="btn" value="返回"
					onclick="history.go(-1);" /></li>
			</ul>
		</form>
	</div>
</body>
</html>