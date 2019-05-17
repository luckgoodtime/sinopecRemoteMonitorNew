<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ page pageEncoding="UTF-8"%>
		<%@ include file="/jsp/include/taglib.jsp"%>
		<%@ include file="/jsp/include/title.jsp"%>
		<%@ include file="/jsp/include/js.jsp"%>
		<%@ include file="/jsp/include/easyui_js_css.jsp"%>
    
<script type="text/javascript">
var datagrid;
	$(function() {
		//列表
		datagrid = $('#treegrid').treegrid({
			url : '${pageContext.request.contextPath}/menu/listJson.do',
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = datagrid.treegrid('getSelected');
					if (node) {
						datagrid.treegrid('expandAll', node.id);
					} else {
						datagrid.treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = datagrid.treegrid('getSelected');
					if (node) {
						// 选中子菜单时折叠其父菜单
						var node_children = datagrid.treegrid('getChildren', node.id);
						var node_parent = datagrid.treegrid('getParent', node.id);
						if (node_children == false && node_parent != null) {
							datagrid.treegrid('collapseAll', node_parent.id);
						}
						datagrid.treegrid('collapseAll', node.id);
					} else {
						datagrid.treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					datagrid.treegrid('reload');
				}
			}, '-', {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					add();
				}
			}, '-', {
				text : '快速增加',
				iconCls : 'icon-add',
				handler : function() {
					window.location.href="<%=request.getContextPath()%>/menu/addMenu.do";
				}
			},'-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} ],
			title : '菜单管理',
			idField : 'id',
			treeField : 'des',
			fit : true,
			fitColumns : true,
			nowrap : false,
			animate : true,
			border : false,
			columns : [ [  {
				title : 'id',
				field : 'id',
				width : 150,
				hidden : false
			}, {
				field : 'des',
				title : '名称',
				align:'left',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'perm',
				title : '权限',
				align:'center',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'url',
				title : '地址',
				align:'center',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'layer',
				title : '层数',
				align:'center',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'image',
				title : '菜单图片',
				align:'center',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				field : 'menuOrder',
				title : '顺序',
				align:'center',
				width : 150,
				formatter : function(value) {
					if (value) {
						return sy.fs('<span title="{0}">{1}</span>', value, value);
					}
				}
			}] ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onExpand : function(row) {
				datagrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				datagrid.treegrid('unselectAll');
			}
		});

	});

function add() {
		var p = sy.dialog({
			title : '新增',
			href : '${pageContext.request.contextPath}/menu/addOrUpdate.do',
			width : 370,
			height : 270,
			buttons : [ {
				text : '保存',
				handler : function() {
					var des = p.find('#des').val();
					if(des==""){
						sy.messagerAlert('提示', '名称不能空', 'error');
						return;
					}
					
					$.ajax({
				      type : "post",
				      url : "${pageContext.request.contextPath}/menu/addDo.do",
				      data : p.find('form').serialize(),
				      success : function(json){
						if (json.suc) {
							datagrid.treegrid('reload');
							p.dialog('close');
							
						}
						sy.messagerShow({msg : json.tip,title : '提示'});
				      }
					 });
				}
			} ],
			onLoad : function() {
				var f = p.find('form');
				var f_parent = f.find('input[name=parentId]');
				f_parent.combotree({
				    url:'${pageContext.request.contextPath}/menu/listJson.do?text=true'
				});
				
			}
		});
	}
	

	
	
	function update() {
		var node = datagrid.treegrid('getSelected');
		if (node) {
			var p = sy.dialog({
				title : '修改',
				href : '${pageContext.request.contextPath}/menu/addOrUpdate.do?id='+node.id,
				width : 540,
				height : 280,
				buttons : [ {
					text : '保存',
					handler : function() {
						var des = p.find('#des').val();
						if(des==""){
							sy.messagerAlert('提示', '名称不能空', 'error');
							return;
						}
						
						
						$.ajax({
					      type : "post",
					      url : "${pageContext.request.contextPath}/menu/updateDo.do",
					      data : p.find('form').serialize(),
					      success : function(json){
							if (json.suc) {
								datagrid.treegrid('reload');
								p.dialog('close');
							}
							sy.messagerShow({msg : json.tip,title : '提示'});
					      }
						 });
					}
				} ],
				onLoad : function() {
					var f = p.find('form');
					var f_parent = f.find('input[name=parentId]');
					var mycombotree = f_parent.combotree({
					    url:'${pageContext.request.contextPath}/menu/listJson.do?text=true',
					    lines : true
					});
					
					
			}
				
			});
		} else {
			sy.messagerAlert('提示', '请选中要编辑的记录！', 'error');
		}
	}
	
	function del() {
		var node = datagrid.treegrid('getSelected');
		var del_msg = '';
		if (node) {
			if (!datagrid.treegrid('isLeaf', node.id)) {
				del_msg = '<strong>该菜单有子菜单</strong>，您确定要删除【' + node.des + '】?';
			} else {
				del_msg = '您确定要删除【' + node.des + '】？';
			}
			sy.messagerConfirm('提醒', del_msg, function(b) {
				if (b) {
					$.ajax({
						url : '${pageContext.request.contextPath}/menu/del.do',
						data : {
							ids : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.tip) {
								datagrid.treegrid('reload'); 
							}
							sy.messagerShow({msg : r.tip,title : '提示'});
						}
					});
				}
			});
		}else {
			sy.messagerAlert('提示', '请选择记录！', 'error');
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="treegrid"></table>
	</div>
	
</body>
</html>
