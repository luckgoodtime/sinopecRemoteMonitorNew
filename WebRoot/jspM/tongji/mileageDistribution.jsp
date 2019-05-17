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



		<div id="main" style="height: 430px;margin-bottom: 10px;"></div>
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
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
					color: ['#00AA00'],
                    tooltip: {
						
                        show: true
                    },
                    legend: {
                        data:['销售']
                    },
                    yAxis : [
                        {
                            type : 'category',
                            data : ["<=200","<=500","<=1000","<=1500","<=2000","<=2500"]
                        }
                    ],
                    xAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"销售",
                            "type":"bar",
                            "data":[5, 20, 40, 10, 10, 20]
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
