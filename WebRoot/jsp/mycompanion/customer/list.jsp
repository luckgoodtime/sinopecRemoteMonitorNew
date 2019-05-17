<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		<style>
			.prosearch {
			    padding-left: 3px;
			    margin-bottom: 8px;
			    overflow: hidden;
			    height: 35px;
			   }
		</style>
		<script type="text/javascript">
$(function(){
  $(".click").click(function(){
 	window.location.href="<%=request.getContextPath()%>/customer/addOrUpdate.do";
  });
  

});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>我的伙伴</li>
				<li>客商</li>
				
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

			<ul class="prosearch">
				<form id="searchForm" action="<%=request.getContextPath()%>/customer/list.do" method="post">
					<li>
					<i> 用气证号 ： </i><a><input id="gasCardNo"  name="gasCardNo"  type="text" class="scinput"  value="${customer.gasCardNo}"/></a>
					<i> 客户名称 ： </i><a><input id="cusName"  name="cusName"  type="text" class="scinput"  value="${customer.cusName}"/></a>
					<i> 联系电话 ： </i><a><input id="cusContactTel"  name="cusContactTel"  type="text" class="scinput"  value="${customer.cusContactTel}"/></a>
					<i> 送气地址 ： </i><a><input id="receiptPlace"  name="receiptPlace"  type="text" class="scinput"  value="${customer.receiptPlace}"/></a>
					<a><input type="submit" class="sure" value="查询" /></a></li>
				</form>
			</ul>
			
			</div>

			<form id="myForm" action="<%=request.getContextPath()%>/customer/del.do" method="post">
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
							用气证号
						</th>
						<th>
							客户名称
						</th>
						<th>
							联系电话
						</th>
						
						<th>
							送气地址
						</th>
						<th>
							行业
						</th>
						<th>
							业务员
						</th>						
						<th>
							租瓶方式
						</th>
						<th>
							上级客户
						</th>
						<th>
							上级用气证号
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

					<c:forEach items="${p.dataList}" var="o">
						<tr>
							<td>
								<input name="ids" type="checkbox"  value="${o.id}"/>
							</td>
							<td>
								${o.id}
							</td>
							<td>
								${o.gasCardNo}
							</td>
							<td>
								${o.cusName}
							</td>
							<td>
								${o.cusContactTel}
							</td>
							<td>
								${o.receiptPlace}
							</td>
							<td>
								${o.cusType}
							</td>
							<td>
								${o.bizRep}
							</td>							
							<td>
								${o.bottleRentType}
							</td>
							<td>
								${o.parentCusName}
							</td>							
							<td>
								${o.parentSerialNo}
							</td>										
							<td>
								${o.state}
							</td>							
							<td>
								<a href="<%=request.getContextPath()%>/customer/addOrUpdate.do?id=${o.id}" class="tablelink">修改</a>
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
