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

	var lineTemplate = "<div class='exec_list_cont'>"
					  +"<div><a href='<%=request.getContextPath()%>/articleM/view.do?id={id}' target='_blank'>{title}</a></div></div>";
	$(function() {
		 $('.top_header_title').text("新闻");
	   //加载发车列表
	   getExecList();
	});

var _currentPage =0;
function getExecList() {

	$.ajax({
	      type : "post",
	      url : "<%=request.getContextPath()%>/articleM/listJson.do",
	      data : {pageMethod:"go",currentPage:_currentPage+1,pageSize:10},
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
		for(var item in data){  
			var reg=new RegExp("\\{"+item+"\\}","g");
			result = result.replace(reg, data[item]);
  		} 
		return  result;
	}

</script>
</head>
<body>
<jsp:include page="/jspM/include/header.jsp"/>	
	
	


<div  class="exec_list">
	<div class="exec_list_show">
		
	</div>
	<div class="loadMore">
		<div><a href="javascript:getExecList();">点击加载10条&#8595;</a> </div>
	</div>
</div>
	
<jsp:include page="/jspM/include/navigation.jsp"/>
</body>
</html>
