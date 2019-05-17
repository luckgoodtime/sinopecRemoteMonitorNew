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
 	window.location.href="<%=request.getContextPath()%>/shippingOrder/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>业务执行</li>
				<li>运输单</li>
				
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
					<li class="notice">
						<span><img src="<%=request.getContextPath()%>/images/t04.png" /> </span>统计
					</li>
				</ul>


				<ul class="toolbar1">
					<li>
						<span><img src="<%=request.getContextPath()%>/images/ico06.png" /> </span>查询
					</li>
					<li>
						<span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>关注
					</li>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/shippingOrder/del.do" method="post">
			<div style="overflow:scroll;width:100%;">
			<table class="tablelist" style="width:2000px;">
				<thead>
					<tr>
						<th>
							<input id="selectAllCheck" type="checkbox" />
						</th>
						<th>
							序号
						</th>
						<th>
							状态
						</th>						
						<th>
							运输公司
						</th>
						<th>
							业主
						</th>
						<th>
							车牌号
						</th>
						<th>
							挂车号
						</th>	
						<th>
							司机姓名
						</th>
						<th>
							司机电话
						</th>
						<th>
							气源点名称
						</th>
						<th>
							气源点地址
						</th>
						<th>
							卸气点名称
						</th>
						<th>
							卸气地点
						</th>
						<th>
							要求日期
						</th>
						<th>
							是否分卸
						</th>
						<th>
							计划装车日期
						</th>
						<th>
							装车时间
						</th>
						<th>
							装车皮重
						</th>
						<th>
							装车毛重
						</th>
						<th>
							装车净重
						</th>
						<th>
							运费结算量
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>




					<c:forEach items="${p.dataList}" var="o" varStatus="s">
						<tr>
							<td>
								<input name="ids" type="checkbox"  value="${o[0]}"/>
							</td>
							<td>
								${p.pageSize*(p.currentPage-1) + s.index+1}
							</td>
							<td>
								状态
							</td>							
							<td>
								${o[1]}
							</td>
							<td>
								${o[2]}
							</td>
							<td>
								${o[3]}
							</td>
							<td>
								${o[4]}
							</td>
							<td>
								${o[5]}
							</td>
							<td>
								${o[6]}
							</td>
							<td>
								${o[7]}
							</td>
							<td>
								${o[8]}
							</td>
							<td>
								${o[9]}
							</td>
							<td>
								${o[10]}
							</td>
							<td>
								${o[11]}
							</td>
							<td>
								${o[12]}
							</td>
							<td>
								${o[13]}
							</td>
							<td>
								${o[14]}
							</td>
							<td>
								${o[15]}
							</td>
							<td>
								${o[16]}
							</td>
							<td>
								${o[17]}
							</td>
							<td>
								${o[18]}
							</td>
							<td>
								${o[19]}
							</td>
							<td>
								<a href="<%=request.getContextPath()%>/shippingOrder/addOrUpdate.do?id=${o[0]}" class="tablelink">修改</a>
								&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>



				</tbody>
			</table>
			</div>
			</form>

	<%@ include file="/jsp/include/page.jsp"%>

		</div>
	</body>
</html>
