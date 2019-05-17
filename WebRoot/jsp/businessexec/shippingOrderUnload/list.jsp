<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		<script type="text/javascript">
$(function(){



  $(".click").click(function(){
 	window.location.href="<%=request.getContextPath()%>/shippingOrderUnload/addOrUpdate.do";
  });

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>业务执行</li>
				<li>卸气</li>
				
			</ul>
		</div>




	
		<div class="rightinfo">

			<div class="tools">

				<ul class="toolbar">
					<li class="click">
						<span><img src="<%=request.getContextPath()%>/images/t01.png" /> </span>添加
					</li>
					
					<li class="del">
						<span><img src="<%=request.getContextPath()%>/images/t03.png" /> </span>删除
					</li>
				</ul><!--


				<ul class="toolbar1">
					<li>
						<span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>查询
					</li>
				</ul>

			--></div>

			<form id="myForm" action="<%=request.getContextPath()%>/shippingOrderUnload/del.do" method="post">
			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input id="selectAllCheck" type="checkbox" />
						</th>
						<th>
							编号
						</th>
						<th>
							运输单id
						</th>
						<th>
							到达卸液点时间
						</th>
						<th>
							开始卸液时间
						</th>
						<th>
							卸液结束时间
						</th>
							<th>
							卸车毛重
						</th>	<th>
							卸车皮重
						</th>
							<th>
							卸车净重
						</th>
						<th>
							离开时间
						</th>
						<th>
							备注
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>




					<c:forEach items="${p.dataList}" var="o">
						<tr>
							<td>
								<input name="ids" type="checkbox"  value="${o.id}"/>
							</td>
							<td>
								${o.id}
							</td>
							<td>
							
							<a href="#" class="easyui-tooltip" data-options="
								content: $('<div></div>'),
								onShow: function(){
									$(this).tooltip('arrow').css('left', 20);
									$(this).tooltip('tip').css('left', $(this).offset().left);
								},
								onUpdate: function(cc){
									cc.panel({
										width: 500,
										height: 'auto',
										border: false,
										href: '<%=request.getContextPath()%>/shippingOrder/addOrUpdate.do?tooltip=true&id=${o.shippingOrderId}'
									});
								}
							">${o.shippingOrderId}(查看)</a> 
		
								
							</td>
							<td>
								${o.arrivedTime}
							</td>
							<td>
								${o.startUnload}
							</td>
							<td>
								${o.endUnload}
							</td>
							<td>
								${o.unLoadingGW}
							</td>
							<td>
								${o.unLoadingTW}
							</td>
							<td>
								${o.unLoadingNW}
							</td>
							<td>
								${o.leavingTime}
							</td>
							<td>
								${o.memo}
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/shippingOrderUnload/addOrUpdate.do?id=${o.id}" class="tablelink">修改</a>
								&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>



				</tbody>
			</table>
			</form>

	<%@ include file="/jsp/include/page.jsp"%>

		</div>
	</body>
</html>
