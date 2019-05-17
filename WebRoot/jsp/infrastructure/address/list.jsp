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
 	window.location.href="<%=request.getContextPath()%>/address/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					基础设施
				</li>
				<li>
					地址管理
				</li>				
			</ul>
		</div>

		<div class="rightinfo">

			<div class="tools">

				<ul class="toolbar">
				<shiro:hasPermission name="address:addDo">
					<li class="click">
						<span><img src="<%=request.getContextPath()%>/images/t01.png" /> </span>添加
					</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="address:del">
					<li class="del">
						<span><img src="<%=request.getContextPath()%>/images/t03.png" /> </span>删除
					</li>
					</shiro:hasPermission>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/address/del.do" method="post">
			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input id="selectAllCheck" type="checkbox" />
						</th>
						<th>
							编号
							<i class="sort"><img src="<%=request.getContextPath()%>/images/px.gif" /> </i>
						</th>
						<th>
							省
						</th>
						<th>
							市
						</th>
						<th>
							区/县
						</th>						
						<th>
							地址
						</th>
						<th>
							邮编
						</th>
						<th>
							单位
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
								<input name="ids" type="checkbox"  value="${o.addressId}"/>
							</td>
							<td>
								${o.addressId}
							</td>
							<td>
								${o.state}
							</td>
							<td>
								${o.city}
							</td>
							<td>
								${o.county}
							</td>
							<td>
								${o.address1}
							</td>
							<td>
								${o.postalCode}								
							</td>
							<td>
								${o.corpNames}								
							</td>
							<td>
							<shiro:hasPermission name="address:updateDo">
								<a href="<%=request.getContextPath()%>/address/addOrUpdate.do?addressId=${o.addressId}" class="tablelink">修改</a>
								</shiro:hasPermission>
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
