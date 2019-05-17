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
 	window.location.href="<%=request.getContextPath()%>/supplier/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>我的伙伴</li>
				<li>供应商</li>
				
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

			<form id="myForm" action="<%=request.getContextPath()%>/supplier/del.do" method="post">
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
							供应商编号
						</th>
						<th>
							公司名称
						</th>
						<th>
							区域
						</th>
						
						<th>
							业务开始日期
						</th>
						<th>
							创建日期
						</th>
						<th>
							创建人
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
								<input name="ids" type="checkbox"  value="${o[0].id}"/>
							</td>
							<td>
								${o[0].id}
							</td>
							<td>
								${o[0].serialNo}
							</td>
							<td>
								${o[1].corpName}
							</td>
							<td>
								${o[0].region}
							</td>
							<td>
								${o[0].bizStartDate}
							</td>
							<td>
								${o[0].createDate}
							</td>
							<td>
								${o[2].firstName}
							</td>
							
							<td>
								<a href="<%=request.getContextPath()%>/supplier/addOrUpdate.do?id=${o[0].id}" class="tablelink">修改</a>
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
