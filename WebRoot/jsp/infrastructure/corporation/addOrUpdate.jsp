<%@page import="com.lng.util.Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/css2.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css"/>		
<script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery.autocomplete.min.js"></script>
<script type="text/javascript">

  $(function(){	
		//省市县级联
		new PCAS("state","city","county","${address.state}","${address.city}","${address.county}");
		
				
		//自动加载公司
		$('#parentCorpName').autocomplete("<%=request.getContextPath()%>/corporation/listAll.do", {
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
			  	$('#parentCorp').val(row.id);
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
					<a href="#">公司管理</a>
				</li>	
				<li>
					<c:if test="${o==null}">新增</c:if>
					<c:if test="${o!=null}">修改</c:if>
				</li>
			</ul>
		</div>

		<div class="formbody">

			<div class="formtitle">
				<span>公司信息</span>
			</div>
			<form name="dataform" method="post" action="<%=request.getContextPath()%>/corporation/<c:if test="${o==null}">addDo</c:if><c:if test="${o!=null}">updateDo</c:if>.do">
				<input name="goOnAddPerson" type="hidden" value=""/>
				<c:if test="${o!=null}">
					<input name="partyId" type="hidden" value="${o.partyId}"/>
				</c:if>
				<ul class="forminfo">
					<li>
						<label>
							名称
						</label>
						<input name="corpName" type="text" class="dfinput" value="${o.corpName}"/>
						<i></i>
					</li>
					<li>
						<label>
							简称
						</label>
						<input name="corpShortName" type="text" class="dfinput" value="${o.corpShortName}"/>
						<i></i>
					</li>
					<li>
						<label>
							上级公司
						</label>
						<input id="parentCorpName" name="parentCorpName" type="text" class="dfinput" value="${o.parentCorpName}"/>
						<input id="parentCorp" name="parentCorp" type="hidden" value="${o.parentCorp}"/>
						<i></i>
					</li>
					
					<li>
						<label>
							是否独立核算
						</label>
						<cite>
						<input name="selfAccounting" type="radio" value="1" 
							<c:if test="${o.selfAccounting == null || o.selfAccounting=='1'}">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="selfAccounting" type="radio" value="0"  
							<c:if test="${o.selfAccounting=='0'}">checked="checked"</c:if>/>否</cite>
					</li>
					<li>
						<label>
							营业执照
						</label>
						<input name="license" type="text" class="dfinput"
							value="${o.license}" />
					</li>
					<li>
						<label>
							企业税号
						</label>
						<input name="corporationTax" type="text" class="dfinput"
							value="${o.corporationTax}" />
					</li>					
					<li>
						<label>
							公司创建日期
						</label>
						<input name="buildDate" type="text" class="dfinput"
							 onclick="WdatePicker();" value="${o.buildDate}" />
					</li>
					
					<li>
						<label>
							企业网址
						</label>
						<input name="webUrl" type="text" class="dfinput"
							value="${o.webUrl}" />
					</li>
					<li>
						<label>
							企业邮箱
						</label>
						<input name="email" type="text" class="dfinput"
							value="${o.email}" />
					</li>
					<li>
						<label>
							企业微信
						</label>
						<input name="webChatId" type="text" class="dfinput"
							value="${o.webChatId}" />
					</li>
					<li>
						<label>
							省
						</label>
						
						<div class="vocation">
							<select id="state"  name="state" class="ssx" style=""></select>
						</div>
						
					</li>
					<li>
						<label>
							市
						</label>
						
						<div class="vocation">
							<select id="city" name="city" class="ssx"></select>
						</div>
						
					</li>
					<li>
						<label>
							县
						</label>
						
						<div class="vocation">
							<select id="county"  name="county" class="ssx"></select>
						</div>
					</li>																
					<li>
						<label>
							乡镇地址
						</label>
						<input name="address1" type="text" class="dfinput"
							value="${address.address1}" />
					</li>
					<li>
						<label>
							邮编
						</label>
						<input name="postalCode" type="text" class="dfinput"
							value="${address.postalCode}" />
					</li>
					
					<c:if test="${o==null}">	
					<li>
						<label>
							伙伴关系
						</label>
							<input name="supplier" type="checkbox"  value="供应商"/> 供应商   
							<input name="customer" type="checkbox"  value="客商"/> 客商   
							<input name="transport" type="checkbox" value="运输商"/> 运输商   
					</li>
					</c:if>
					<li>
						<label>
							详述
						</label>
						<textarea name="detail" style="width: 330px;"  class="textinput">${o.detail}</textarea>
						
					</li>																									
					<li>
						<label>
							&nbsp;
						</label>
						<input type="submit" class="btn" value="保存返回" />
						<input type="submit" class="btn" value="保存并添加联系人" onclick="dataform.goOnAddPerson.value='true';dataform.submit();"/>
						<input type="button" class="btn" value="返回" onclick="history.go(-1);"/>
					</li>
				</ul>

			</form>
		</div>
	</body>

</html>

