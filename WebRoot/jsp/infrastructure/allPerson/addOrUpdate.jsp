<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"/>
		
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.autocomplete.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".selec").uedSelect();
		
		//自动加载公司
		$('#corpName').autocomplete("<%=request.getContextPath()%>/corporation/listAll.do", {
			  dataType:'json',
			  max: 100,    //列表里的条目数
			  minChars: 0,    //自动完成激活之前填入的最小字符
			  width: 400,     //提示的宽度，溢出隐藏
			  scrollHeight: 300,   //提示的高度，溢出显示滚动条
			  matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
			  autoFill: false,    //自动填充
			  parse:function(data) {//解释返回的数据，把其存在数组里  
                  var parsed = [];  
                  for (var i = 0; i < data.length; i++) {  
                      parsed[parsed.length] = {  
                      data: data[i],  
                      value: data[i].name,  
                      result: data[i].name //返回的结果显示内容  
                      };  
                  }  
                  return parsed;  
              },  
			  formatItem: function(row, i, max) {
			      return i + '/' + max + ':"' + row.name + '"[' + row.id + ']';
			  }
			}).result(function(event, row, formatted) {
			  	$('#corpPartyId').val(row.id);
			});
		
	});
</script>
	</head>
	<body>

		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>基础设施</li>
				<li>人员管理</li>
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<form method="post" action="<%=request.getContextPath()%>/allPerson/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<c:if test="${o!=null}">
					<input name="partyId" type="hidden" value="${o.partyId}"/>
				</c:if>
				<ul class="forminfo">
					<li>
						<label>公司属于</label>
						<input id="corpName" name="corpName" type="text" class="dfinput" value="${c.corpName}"/>
						<input id="corpPartyId" name="corpPartyId" type="hidden" value="${o.corpPartyId}"/>
					</li>
					<li>
						<label>
							姓名
						</label>
						<input name="firstName" type="text" class="dfinput" value="${o.firstName}"/>
						<i></i>
					</li>
						<li>
						<label>
							性别
						</label>
						<cite>
						<input name="gender" type="radio" value="男" 
							<c:if test="${o.gender=='男'}">checked="checked"</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="gender" type="radio" value="女"  
							<c:if test="${o.gender=='女'}">checked="checked"</c:if>/>女</cite>
					</li>
					<li>
						<label>
							QQ
						</label>
						<input name="QQ" type="text" class="dfinput" value="${o.QQ}"/>
						<i></i>
					</li>
					<li>
						<label>
							手机号
						</label>
						<input name="mobile" type="text" class="dfinput" value="${o.mobile}"/>
						<i></i>
					</li>
					<li>
						<label>
							email
						</label>
						<input name="email" type="text" class="dfinput" value="${o.email}"/>
						<i></i>
					</li>
					<li>
						<label>
							对外公开
						</label>
						<cite>
						<input name="isOpen" type="radio" value="是" 
							<c:if test="${o.isOpen=='是'}">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="isOpen" type="radio" value="否"  
							<c:if test="${o.isOpen=='否'}">checked="checked"</c:if>/>否</cite>
					</li>
					
				<li>
						<label>
							设置默认密码
						</label>
						<cite>
						<input name="defaultPwd" type="radio" value="是" />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="defaultPwd" type="radio" value="否"  checked="checked"/>否</cite>
					</li>
					
					<li>
						<label>
							生日
						</label>
						<input name="birthDate" type="text" class="dfinput"
							 onclick="WdatePicker();" value="${o.birthDate}"/>
					</li>
					
					<li>
						<label>
							角色
						</label>
						<c:forEach items="${roleList}" var="r">
							<input name="roleIds" type="checkbox"  <c:if test="${r.checked}">checked="checked"</c:if> value="${r.id}"/> ${r.name}   
						</c:forEach>
						
					</li>
					
					<li>
						<label>
							属性
						</label>
							<input name="roleIds" type="checkbox"  <c:if test="${r.checked}">checked="checked"</c:if> value="${r.id}"/> ${r.name}   
					</li>
					
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="保存返回" />
						<input type="submit" class="btn" value="保存并继续添加" />
						<input type="button" class="btn" value="返回"  onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>


	</body>

</html>

