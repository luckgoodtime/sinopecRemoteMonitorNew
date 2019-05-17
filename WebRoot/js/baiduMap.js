$(function(){	
	
	//加载百度地图
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "https://api.map.baidu.com/api?v=2.0&ak=R23LdB0r49sq56mzITGtH8NiZagSk9pg&callback=initMap";
	document.body.appendChild(script);
	
});