/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn'; config.uiColor = '#f7f5f4';
	config.width = '99.7%';
	if (config.height == ''){
		config.height = '900px';
	}
	config.removePlugins = 'elementspath,resize'; // 移除编辑器底部状态栏显示的元素路径和调整编辑器大小的按钮
	config.allowedContent = false; // 是否允许使用源码模式进行编辑
	config.forcePasteAsPlainText = true; // 是否强制复制过来的文字去除格式
	config.enterMode = CKEDITOR.ENTER_BR; // 编辑器中回车产生的标签CKEDITOR.ENTER_BR(<br>),CKEDITOR.ENTER_P(<p>),CKEDITOR_ENTER(回车)
	// 设置快捷键
	// 用于实现Ctrl + V进行粘贴
	// 无此配置，无法进行快捷键粘贴
	config.keystrokes = [
		[CKEDITOR.CTRL + 86 /* V */, 'paste']
		];

	// 设置快捷键，可能与浏览器冲突plugins/keystrokes/plugin.js
	// 用于实现Ctrl + V进行粘贴
	// 此配置将会启动粘贴之前进行过滤，若无此配置，将会出现粘贴之后才弹出过滤框
	config.blockedKeystrokes = [
		CKEDITOR.CTRL + 86
		];
	// 图片上传相关
	//config.filebrowserImageUploadUrl = './upload'; // 图片上传路径
	config.image_previewText = ' '; // 图片信息面板预览区内容的文字内容，默认显示CKEditor自带的内容
	config.removeDialogTabs = 'image:advanced;image:Link'; // 移除图片上传页面的'高级','链接'页签

	// 上传文件时浏览服务文件夹
	config.filebrowserBrowseUrl = '/ckProject/ckfinder/ckfinder.html';
// 上传图片时浏览服务文件夹
	config.filebrowserImageBrowseUrl ='/ckProject/ckfinder/ckfinder.html?Type=Images';
// 上传Flash时浏览服务文件夹
	config.filebrowserFlashBrowseUrl ='/ckProject/ckfinder/ckfinder.html?Type=Flash';
// 上传文件按钮(标签)
	config.filebrowserUploadUrl ='/ckProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
// 上传图片按钮(标签)
	config.filebrowserImageUploadUrl ='http://localhost:8080/ckProject/uploadImg.htm';
// 上传Flash按钮(标签)
	config.filebrowserFlashUploadUrl ='/ckProject/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';

};
