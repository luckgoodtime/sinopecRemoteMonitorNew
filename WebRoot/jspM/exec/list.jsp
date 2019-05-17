<!DOCTYPE html>
<html>
<head>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/taglib.jsp"%>
<%@ include file="/jsp/include/title.jsp"%>
<%@ include file="/jspM/include/meta.jsp"%>
<%@ include file="/jspM/include/css.jsp"%>
<%@ include file="/jspM/include/js.jsp"%>
<script type="text/javascript">

	var lineTemplate = "<div class='exec_list_cont' onclick='window.location.href=\"<%=request.getContextPath()%>/exec/view.do?id={0}\";'>"
					  +"<div>{3}  {5} {6} </div><div>云南省昆明市第三大道xxx路</div></div>";
	$(function() {
	   //加载发车列表
	   getExecList();
	});

var _currentPage =0;
function getExecList() {

	$.ajax({
	      type : "post",
	      url : "<%=request.getContextPath()%>/exec/listJson.do",
	      data : {pageMethod:"go",currentPage:_currentPage+1,pageSize:2},
	      dataType:"json",
	      success : function(d){
	         showExecList(d);
	         _currentPage = d.currentPage;
	         if(d.currentPage>=d.totalPages){
	         	$(".loadMore").hide(); 
	         }
	         
	      },
	      error : function(d){
  	  			alert("加载列表出现异常,"+d);
	      }
	 });
  
}

	function showExecList(d) {

		var result = "";
		for(var i=0;i<d.dataList.length;i++){
			result = result+replaceValue(d.dataList[i]);
		}
		$(".exec_list_show").append(result); 
	  
	}
	
	function replaceValue(data) {
		var result = lineTemplate;
		for(var i=0;i<data.length;i++){
			var reg=new RegExp("\\{"+i+"\\}","g");
			result = result.replace(reg, data[i]);
		}
		return  result;
	}

</script>
</head>
<body>
<jsp:include page="/jspM/include/header.jsp"/>	
	
	<div class="exec_nav">
		<div><a href="#">今日发车</a></div>
		<div class="exec_nav_select"><a href="#">在 途</a></div>
		<div><a href="#">卸液中</a></div>
		<div><a href="#">回程</a></div>
		<div class="exec_nav_divend"><a href="#">昨日发车</a></div>
	</div>


<div  class="exec_list">
	<div class="exec_list_show">
		
	</div>
	<div class="loadMore">
		<div><a href="javascript:getExecList();">点击加载5条&#8595;</a> </div>
	</div>
</div>
	
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
