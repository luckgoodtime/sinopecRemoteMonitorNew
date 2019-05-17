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
 	window.location.href="<%=request.getContextPath()%>/biz/addOrUpdate.do?bizType=SALE";
  });

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>
					<a href="#">业务执行</a>
				</li>
				<li>
					<a href="#">销售订单</a>
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
				<form id="searchForm" action="<%=request.getContextPath()%>/biz/list.do" method="post">
					<ul class="prosearch" style="float: left;margin: 10px 0 10px 0">
						<li> 
							供应商 ： <input  name="gasCardNo"  type="text" class="scinput"  value="${bizOrder.supplierName}"/>
							开始 ： <input  name="startTime"  type="text" class="scinput"  value="${bizOrder.startTime}" onclick="WdatePicker();"/>
							结束 ： <input  name="endTime"  type="text" class="scinput"  value="${bizOrder.endTime}" onclick="WdatePicker();"/>
							<input type="submit" class="sure" value="查询" />
						</li>
				  </ul>
				</form>				

				<ul class="toolbar1">
					<li>
						<span><img src="<%=request.getContextPath()%>/images/t02.png" /> </span>查询
					</li>
				</ul>

			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/biz/del.do?bizType=SALE" method="post">
			<div style="overflow:scroll;width:100%;">
			<table class="tablelist" style="width:100%;">
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
							供应商
						</th>
						<th>
							品名
						</th>
						<th>
							单号
						</th>												
						<th>
							气源点
						</th>						
						<th>
							客户
						</th>
						<th>
							卸气点
						</th>
						<th>
							车号
						</th>
						<th>
							司机
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
							装车量
						</th>	
						<th>
							卸车时间
						</th>
						<th>
							卸车毛重
						</th>
						<th>
							卸车皮重
						</th>
						<th>
							卸车量
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
								${o.supplierName}
							</td>							
							<td>
								${o.gasType}
							</td>
							<td>
								${o.trackNumber}
							</td>							
							<td>
								${o.sourceName}
							</td>														
							<td>
								${o.customerName}
							</td>
							<td>
								${o.endPointName1}
							</td>
							<td>
								${o.plateNo}
							</td>
							<td>
								${o.driverName}
							</td>
							<td>
								${o.loadingTime}
							</td>
							<td>
								${o.loadingTW}
							</td>
							<td>
								${o.loadingGW}
							</td>
							<td>
								${o.loadingNW}
							</td>	
							<td>
								${o.unloadingTime}
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
								<a href="<%=request.getContextPath()%>/biz/addOrUpdate.do?bizType=SALE&id=${o.id}" class="tablelink">修改</a>
								&nbsp;&nbsp;
								<!-- <a href="#" class="tablelink">查看</a> -->
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
