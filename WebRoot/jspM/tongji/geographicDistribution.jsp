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


		<div id="main" style="width:100%;height: 300px;"></div>
		<div id="main2" style="width:100%;height: 430px;margin-bottom: 10px;"></div>
		
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
                'echarts/chart/pie' ,
                'echarts/chart/bar'
            ],
            function (ec) {
                bar(ec);
          		pie(ec);
            }
        );
        
        //柱状图
        function bar(ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
					color: ['#00AA00'],
                    tooltip: {
						
                        show: true
                    },
                    legend: {
                        data:['单车毛利']
                    },
                    xAxis : [
                    	{
                            type : 'category',
                            data : ["A省","B省","C省","D省","其他"]
                        }
                       
                    ],
                    yAxis : [
                         {
                            type : 'value'
                        }
                    ],
                    
                    series : [
                        {
                            "name":"单车毛利",
                            "type":"bar",
                            "data":[2000, 2045, 1870, 1900, 1876]
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
            
        //饼图
        function pie(ec) {
                var myChart = ec.init(document.getElementById('main2')); 
				option = {
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    
				  /* legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:['A省','B省','C省','D省','其他']
				    },*/
				    calculable : true,
				    series : [
				        {
				            name:'占比',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '30%'],
				            data:[
				                {value:2000, name:'A省'},
				                {value:2045, name:'B省'},
				                {value:1870, name:'C省'},
				                {value:1900, name:'D省'},
				                {value:1876, name:'其他'}
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
                
                myChart.setOption(option); 
            }
    </script>

		<jsp:include page="/jspM/include/navigation.jsp" />
	</body>
</html>
