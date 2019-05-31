var excelthsize = 18, exceltdsize = 15;

function initExcelcolum(gridid, urls) {
	var extitlehtml = "<tr style='font-weight:bold;'><td>列名</td>", exstatehtml = "<tr><td style='font-weight:bold;'>显示</td>", exwidthhtml = "<tr><td style='font-weight:bold;'>列宽</td>", exjichuhtml = "<tr><td style='font-weight:bold;'>基础</td>";

	var grid = $("#" + gridid);
	var columns = grid.datagrid("options").columns[0]; // 得到columns对象
	var colhtml = "";
	exjichuhtml += "<td align='center' colspan='"
			+ columns.length
			+ "'>Excel名称：<input type='text' \
style='width:150px;border-color:#95B8E7;border-radius: 5px;border-style:solid;border-width: 1px;' \
value='Excel导出-"
			+ new Date().toLocaleString()
			+ "' id='excelName'/>  \
标题文字大小：<input type='text' \
style='width:20px;border-color:#95B8E7;border-radius: 5px;border-style:solid;border-width: 1px;' \
value='"
			+ excelthsize
			+ "' id='excelthsize'/>px  \
普通行文字大小：<input type='text' \
style='width:20px;border-color:#95B8E7;border-radius: 5px;border-style:solid;border-width: 1px;' \
value='"
			+ exceltdsize + "' id='exceltdsize'/>px  \
</td></tr>";
	var exbuttonhtml = "<tr><td align='center' colspan='"
			+ (columns.length + 1)
			+ "'>\
<input type='button' value='重新生成' onclick=changeColum('"
			+ gridid
			+ "') />\
<input type='button' value='Excel预览' onclick=Excelyulan('"
			+ gridid
			+ "') />\
<input type='button' value='导出Excel' onclick=daochuExcel('"
			+ gridid + "','" + urls + "') /></td></tr>"
	for (var i = 0; i < columns.length; i++) {
		var checkstr = columns[i].hidden ? "" : "checked";
		extitlehtml += "<td align='center'>" + columns[i].title + "</td>"
		exstatehtml += "<td align='center' onclick='Excelcbtdclick(this)'><input class='excelcbstate' type='checkbox' "
				+ checkstr + " value='" + columns[i].field + "'/></td>"
		exwidthhtml += "<td align='center'><input type='text' name='"
				+ columns[i].field
				+ "' style='width:20px;border-color:#95B8E7;border-radius: 5px;border-style:solid;border-width: 1px;' class='cbwidth' value='"
				+ parseInt(columns[i].width) + "'/>%</td>"
	}
	colhtml = "<table border='1' style='border:0px;font-weight:bold;font-size: 13px;font-family: Microsoft YaHei, Helvetica Neue, Helvetica, Arial, sans-serif;border-collapse: collapse;border-spacing: 0;border-color:#95B8E7;width:100%;'>";
	colhtml += exjichuhtml + extitlehtml + exstatehtml + exwidthhtml
			+ exbuttonhtml + "</table>";
	$("#colums_father").html(colhtml);
	$(".excelcbstate").click(function(e) { // 阻止checkbox选中事件冒泡
		e.stopPropagation();
	})

}

function ChangeToTable(printDatagrid) { // 转换grid为table标签
	printData = $("#" + printDatagrid);
	var tableString = '<table border="1" class="exceltable" style="border-collapse: collapse;border:0px;">';
	var frozenColumns = printData.datagrid("options").frozenColumns; // 得到frozenColumns对象
	var columns = printData.datagrid("options").columns; // 得到columns对象
	var nameList = new Array();
	// 载入title
	if (typeof columns != 'undefined' && columns != '') {
		$(columns)
				.each(
						function(index) {
							tableString += '\n<tr>';
							for (var i = 0; i < columns[index].length; ++i) {
								if (!columns[index][i].hidden) {
									var width = columns[index][i].width;
									width = width +"";
									//console.log(width);
									if (width && width.indexOf("%") > 0) {
										width = (window.screen.availWidth - 100)
												* parseInt(width) / 100;
									}
									tableString += '\n<th width="' + width
											+ '"';
									if (typeof columns[index][i].rowspan != 'undefined'
											&& columns[index][i].rowspan > 1) {
										tableString += ' rowspan="'
												+ columns[index][i].rowspan
												+ '"';
									}
									if (typeof columns[index][i].colspan != 'undefined'
											&& columns[index][i].colspan > 1) {
										tableString += ' colspan="'
												+ columns[index][i].colspan
												+ '"';
									}
									if (typeof columns[index][i].field != 'undefined'
											&& columns[index][i].field != '') {
										nameList.push(columns[index][i]);
									}
									tableString += ' style="background-color: #DBDBDB;text-align:center;font-size:'
											+ excelthsize + 'px;" ';
									tableString += '>'
											+ columns[index][i].title + '</th>';
								}
							}
							tableString += '\n</tr>';
						});
	}
	
	var rows = getAllData(printData);
	// 载入内容
	//var rows = printData.datagrid("getRows"); // 这段代码是获取当前页的所有行
	//	console.log("rows:");
	//	console.log(rows);
	
	for (var i = 0; i < rows.length; ++i) {
		tableString += '\n<tr >';
		for (var j = 0; j < nameList.length; ++j) {
			var e = nameList[j].field.lastIndexOf('_0');
			tableString += '\n<td style="padding:3px;'
					+ (i % 2 == 1 ? 'background-color:#efefef;' : '')
					+ 'font-size:' + exceltdsize + 'px;';
			if (nameList[j].align != undefined && nameList[j].align != '') {
				tableString += ' text-align:' + nameList[j].align + ';';
			}
			
			tableString += '" >';
			if (e + 2 == nameList[j].field.length) {
				tableString += rows[i][nameList[j].field.substring(0, e)] == null ? ""
						: rows[i][nameList[j].field.substring(0, e)];
			} else {
				var value = rows[i][nameList[j].field];
				if(value) {
					if(nameList[j].type == "date") {
						try {
							value = (new Date(value)).format("yyyy-MM-dd hh:mm:ss");
						}
						catch(err) {
							console.log("err:");
							console.log(err);
						}
						
					}
					tableString += value;
				}
			}
				
			tableString += '</td>';
		}
		tableString += '\n</tr>';
	}
	tableString += '\n</table>';
	return tableString;
}


function getAllData(printData) {
	
	_url = printData.datagrid("options").url
	
	_url +="?page=1&rows=10000"
	var rows;
	$.ajax({
		type: 'post',
		dataType:'json',
		url: _url,
		async:false,
		data:$("#searchForm").serialize(),
		success:function(msg){
			console.log(msg);
			if(msg) {
				rows = msg.rows;
			}
		}
	});
	
	return rows;
}
function changeColum(gridid) { // 修改列宽和列的显示状态
	var grid = $("#" + gridid);
	var cbs = $("#colums_father").find('.excelcbstate');
	exceltdsize = $("#exceltdsize").val();
	excelthsize = $("#excelthsize").val();
	$.each(cbs, function(index, item) {
		if (!item.checked) {
			grid.datagrid("hideColumn", item.value);
		} else {
			grid.datagrid("showColumn", item.value);
			var wid = $(item).parents("table").find(
					"input[name='" + item.value + "']")[0].value
			grid.datagrid("getColumnOption", item.value).width = wid + "%";
		}
	});
}

function Excelyulan(gridid) { // 预览
	var htmlstr = ChangeToTable(gridid);
	$("#ExcelWin_content").html(htmlstr)
	$("#ExcelWin").window("open")
}

function daochuExcel(gridid, urls) { // 导出
	var htmlstr = ChangeToTable(gridid);
	var form = $('<form action="' + urls + '" method="post" id="fm1"></form>');
	var txtConent = $('<input type="hidden" id="txtConent" name="txtConent" />');
	var fileName = $('<input type="hidden" id="fileName" name="fileName" />');
	txtConent.val(htmlstr);
	txtConent.appendTo(form);
	
	$('.placeul li a:eq(1)').each(function(){
//		console.log($(this).html());
		fileName.val($(this).html());
		
	});
	//fileName.val($("#excelName").val());
	fileName.appendTo(form);
	form.appendTo(document.body).submit();
	//document.body.removeChild(form);
}

function Excelcbtdclick(td) {
	$(td).find(".excelcbstate")[0].checked = $(td).find(".excelcbstate")[0].checked ? false
			: true;
}
