<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
		
		
<script type="text/javascript">
	
	
	function mySubmit(){
		var menuIds = getChecked();
		if(menuIds=="") {
			sy.messagerAlert('提示', '请选择菜单', 'error');
			return;
		}
		$("input[name='menuIds']").val(menuIds);
		
		$('form')[0].submit();
	}
	
	
	
	function getChecked(){
		var nodes = $('#menuUl').tree('getChecked');
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].id;
		}
		return s;
	}
	
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>基础设施</li>
				<li>角色管理</li>
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>角色信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/role/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="id" type="hidden" value="${o.id}" />
				</c:if>
				<ul class="forminfo">
					<li>
						<label>
							&nbsp;
						</label>
						<input type="button" class="btn" value="确认保存" onclick="mySubmit();"/>
							<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
					<li>
						<label>
							名称
						</label>
						<input name="name" type="text" class="dfinput" value="${o.name}"/>
						<i></i>
					</li>
					<li>
						<label>
							菜单
						</label>
						
						
						<div class="easyui-panel" style="padding:5px;width: 345px;">
							<ul id="menuUl" class="easyui-tree" data-options="url:'<%=request.getContextPath()%>/menu/allMenuJson.do?roleId=${o.id}',method:'get',animate:true,checkbox:true"></ul>
						</div>
						<input name="menuIds" type="hidden" class="dfinput" value="${menuIds}"/>
	
					</li>
					
					<li>
						<label>
							&nbsp;
						</label>
						<input type="button" class="btn" value="确认保存" onclick="mySubmit();"/>
							<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>


	</body>

</html>

