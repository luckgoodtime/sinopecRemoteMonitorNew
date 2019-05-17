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
		/*$("select[name='endPointPartyId']").change(function(){
			if(this.value=="创建卸气点"){
				window.local.href="<%=request.getContextPath()%>/endPoint/addOrUpdate.do";
			}
		});*/
	});
	
	function countWeight() {
		var loadingGW = $("#unLoadingGW").val();//毛重
		var loadingTW =  $("#unLoadingTW").val();//皮重
		if(!isNaN(loadingGW)&&!isNaN(loadingTW)){
			$("#unLoadingNW").val((Number(loadingGW)-Number(loadingTW)).toFixed(2));//净重
		}
	}
	
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>业务执行</li>
				<li>卸气</li>
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>卸气信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/shippingOrderUnload/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="id" type="hidden" value="${o.id}"/>
				</c:if>
				<ul class="forminfo">
				<li>
						<label>运输单</label>
						<div class="vocation">
							<select name="shippingOrderId" class="selec">
							<option value="">请选择</option>
							<c:forEach items="${shippingOrderList}" var="s">
								<option  <c:if test="${o.shippingOrderId==s.id}">selected="selected"</c:if>
								  value="${s.id}">${s.id}</option>
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
						<div><a href="<%=request.getContextPath()%>/endPoint/addOrUpdate.do" target="_blank">创建卸气点</a></div>
					</li>
					<li>
						<label>
							到达卸液点时间
						</label>
						<input name="arrivedTime" type="text" class="dfinput" value="${o.arrivedTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
						<i></i>
					</li>
					<li>
						<label>
							开始卸液时间
						</label>
						<input name="startUnload" type="text" class="dfinput" value="${o.startUnload}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
						<i></i>
					</li>
					<li>
						<label>
							卸液结束时间
						</label>
						<input name="endUnload" type="text" class="dfinput" value="${o.endUnload}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
						<i></i>
					</li>
					<li>
						<label>
							卸车毛重
						</label>
						<input id="unLoadingGW" name="unLoadingGW" type="text" class="dfinput" value="${o.unLoadingGW}" onchange="countWeight();"/>
						<i></i>
					</li>
					<li>
						<label>
							卸车皮重
						</label>
						<input id="unLoadingTW" name="unLoadingTW" type="text" class="dfinput" value="${o.unLoadingTW}" onchange="countWeight();"/>
						<i></i>
					</li>
					
					<li>
						<label>
							卸车净重
						</label>
						<input id="unLoadingNW" name="unLoadingNW" type="text" class="dfinput" value="${o.unLoadingNW}"/>
						<i></i>
					</li>
					<li>
						<label>
							离开时间
						</label>
						<input name="leavingTime" type="text" class="dfinput" value="${o.leavingTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
						<i></i>
					</li>
					<li>
						<label>
							备注
						</label>
						<input name="memo" type="text" class="dfinput" value="${o.memo}" />
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

