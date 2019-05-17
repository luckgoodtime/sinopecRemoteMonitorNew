/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn'; //配置语言 
	config.uiColor = '#c7d7f7';
  	config.width = 800; //宽度 
  	config.height = 500; //高度 
	 // config.skin = 'v2'; //编辑器皮肤样式 
	 // 取消 “拖拽以改变尺寸”功能 
	 // config.resize_enabled = false; 
	 // 使用基础工具栏 
	 // config.toolbar = "Basic"; 
	 // 使用全能工具栏 
	 config.toolbar = "Full"; 
	config.toolbarStartupExpanded = true;
	 config.font_names="宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;"+ config.font_names;
	 //使用自定义工具栏 
	  config.toolbar = 
	  [ 
	  ['Source', 'Preview', '-'], 
	  ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', ], 
	  ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'], 
	  ['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar','PageBreak'], 
	  '/', 
	  ['Bold', 'Italic', 'Underline', '-', 'Subscript', 'Superscript'], 
	  ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'], 
	  ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'], 
	  ['Link', 'Unlink', 'Anchor'] ,
	   '/', 
	   ['Format', 'Font', 'FontSize'], 
	   ['TextColor', 'BGColor'], 
	   ['Maximize', 'ShowBlocks'] 
	  ];
		config.resize_enabled = false;
		
	 config.filebrowserBrowseUrl = '/js/ckfinder/ckfinder.html',
	 config.filebrowserImageBrowseUrl = '/js/ckfinder/ckfinder.html?type=Images',
	 config.filebrowserFlashBrowseUrl = '/js/ckfinder/ckfinder.html?type=Flash',
	 config.filebrowserUploadUrl = '/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
	 config.filebrowserImageUploadUrl = '/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
	 config.filebrowserFlashUploadUrl = '/js/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
	 config.filebrowserWindowWidth = '1000',
	  config.filebrowserWindowHeight = '700'
};
