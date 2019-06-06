/*
Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	// Define changes to default configuration here.
	// For the list of available options, check:
	// http://docs.cksource.com/ckfinder_2.x_api/symbols/CKFinder.config.html

	// Sample configuration options:
	// config.uiColor = '#BDE31E';
	// config.language = 'fr';
	// config.removePlugins = 'basket';
	config.filebrowserBrowseUrl = './ckfinder/ckfinder.html';  //路径需修改
	config.filebrowserImageBrowseUrl = './ckfinder/ckfinder.html?type=Images';  //路径需修改
	config.filebrowserFlashBrowseUrl = './ckfinder/ckfinder.html?type=Flash';  //路径需修改
	config.filebrowserUploadUrl = './ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files'; //路径需修改
	config.filebrowserImageUploadUrl = './ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images'; //路径需修改
	config.filebrowserFlashUploadUrl = './ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' ; //路径需修改

};
