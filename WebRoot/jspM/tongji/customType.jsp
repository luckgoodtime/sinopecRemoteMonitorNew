<!DOCTYPE html>
<html>
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jspM/include/meta.jsp"%>
		<%@ include file="/jspM/include/css.jsp"%>
		<%@ include file="/jspM/include/js.jsp"%>

	</head>
	<body>
		<jsp:include page="/jspM/include/header_tongji.jsp" />

		<jsp:include page="/jspM/include/navigation_tongji.jsp" />



		<div id="main" style="width:100%;height: 430px;margin-bottom: 10px;"></div>
		<!-- ECharts单文件引入 -->
		<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
		<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
		// 使用
        require(
            [
                'echarts',
                'echarts/chart/pie' 
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
              
               
              
                
        option = {
   
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
   legend: {
        orient : 'vertical',
        x : 'left',
        data:['贸易商','其他','工业户','加气站']
    },
    calculable : true,
    series : [
        {
            name:'占比',
            type:'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:27, name:'其他'},
                {value:58, name:'贸易商'},
                {value:5, name:'加气站'},
                {value:10, name:'工业户'},
            ] /* , itemStyle:{ 
            normal:{ 
                  label:{ 
                    show: true, 
                    formatter: '{b} : {c} ({d}%)' 
                  }, 
                  labelLine :{show:true} 
                }
            } */ 
        }
    ]
};
                    
                    
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>

		<jsp:include page="/jspM/include/navigation.jsp" />
	</body>
</html>
