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
		
		
		
	});
</script>
	</head>
	<body>



		<div class="formbody">


			<ul class="forminfo">
				<li>
					承担运输公司:
					<c:forEach items="${corporationList}" var="c">
						<c:if test="${o.transportCorpPartyId==c.partyId}">${c.corpName}</c:if>
					</c:forEach>
				</li>

				<li>
					运输业主:

					<c:forEach items="${corporationList}" var="c">

						<c:if test="${o.transportOwner==c.partyId}">${c.corpName}</c:if>

					</c:forEach>

				</li>
				<li>
					车牌号:${o.truckNo}
				</li>
				<li>
					挂车号:${o.tankNo}

				</li>
				<li>

					司机姓名:${o.driverName}

				</li>
				<li>
					司机电话:${o.driverTel}

				</li>

				<li>
					气源点名称:
					<c:forEach items="${sourcePointService}" var="c">
						<c:if test="${o.sourcePartyId==c.partyId}">${c.sourceName}</c:if>
					</c:forEach>
				</li>
				<li>
					卸气点名称:

					<c:forEach items="${endPointService}" var="c">
						<c:if test="${o.endPointPartyId==c.partyId}">${c.pointName}</c:if>
					</c:forEach>
				</li>
				<li>
					要求日期:${o.requiredString}

				</li>
				<li>
					是否分卸:
					<c:if test="${o.unLoadOnRoad=='是'}">是</c:if>
					<c:if test="${o.unLoadOnRoad=='否'}">否</c:if>
				</li>

				<li>
					计划装车日期:${o.planLoadingString}

				</li>
				<li>
					装车时间:${o.loadingTime}
				</li>
				<li>
					装车皮重:${o.loadingTW}
				</li>
				<li>
					装车毛重:${o.loadingGW}
				</li>
				<li>
					装车净重：${o.loadingNW}
				</li>
				<li>
					运费结算量:${o.settleWeight}

				</li>





			</ul>

		</div>


	</body>

</html>

