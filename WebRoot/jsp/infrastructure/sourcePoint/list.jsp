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
 	window.location.href="<%=request.getContextPath()%>/sourcePoint/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">基础设施</a>
				</li>
				<li>
					<a href="#">气源管理</a>
				</li>
				
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
				</ul>


				<ul class="toolbar1">
					<li>
						<span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>查询
					</li>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/sourcePoint/del.do" method="post">
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
							名称
						</th>
						<th>
							简称
						</th>
						<th>
							类型
						</th>
						<th>
							生产日能力
						</th>
						<th>
							生产年能力
						</th>
						<th>
							热值
						</th>
						<th>
							气化率
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
								${o.partyId}
							</td>
							<td>
								${o.sourceName}
							</td>
							<td>
								${o.sourceShortName}
							</td>
							<td>
								${o.sourceType}
							</td>
							<td>
								${o.dailyAbility}
							</td>
							<td>
								${o.annualAbility}
							</td>
							<td>
								${o.calorificValue}
							</td>
							<td>
								${o.gasificationRate}
							</td>
						
					
							<td>
								<a href="<%=request.getContextPath()%>/sourcePoint/addOrUpdate.do?partyId=${o.partyId}" class="tablelink">修改</a>
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
