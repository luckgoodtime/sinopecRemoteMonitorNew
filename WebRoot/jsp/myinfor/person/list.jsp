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
 	window.location.href="<%=request.getContextPath()%>/person/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">我的信息</a>
				</li>
				<li>
					<a href="#">我的员工</a>
				</li>
				
			</ul>
		</div>

		<div class="rightinfo">

			<div class="tools">

				<ul class="toolbar">
					<shiro:hasPermission name="person:addDo">
						<li class="click">
							<span><img src="<%=request.getContextPath()%>/images/t01.png" /> </span>添加
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="person:del">
					<li class="del">
						<span><img src="<%=request.getContextPath()%>/images/t03.png" /> </span>删除
					</li>
					</shiro:hasPermission>
				</ul>


				<ul class="toolbar1">
					<li>
						<span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>查询
					</li>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/person/del.do" method="post">
			<table class="tablelist">
				<thead>
					<tr>
						<th>
							<input id="selectAllCheck" type="checkbox" />
						</th>
							<th>
							名字
						</th>
						<th>
							性别
						</th>
						<th>
							qq
						</th>
							<th>
							手机号
						</th>	<th>
							email
						</th>
							<th>
							是否对外公开
						</th>
							<th>
							激活状态
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
								<input name="ids" type="checkbox"  value="${o.partyId}"/>
							</td>
							<td>
								${o.firstName}
							</td>
							<td>
									${o.gender}
							</td>
							<td>
								${o.QQ}
							</td>
							<td>
								${o.mobile}
							</td>
							<td>
								${o.email}
							</td>
							<td>
								${o.isOpen}
							</td>
							<td>
								${o.active}
							</td>
							
							
							
							
							
							<td>
							<shiro:hasPermission name="person:updateDo">
								<a href="<%=request.getContextPath()%>/person/addOrUpdate.do?partyId=${o.partyId}" class="tablelink">修改</a>
								</shiro:hasPermission>
								&nbsp;&nbsp;
								<!-- <a href="#" class="tablelink">查看</a> -->
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
