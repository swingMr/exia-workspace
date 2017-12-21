<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>中文分词</title>
</head>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.css"  media="all">
 <link rel="stylesheet" href="/ExIAServer/js/layui-v2/css/layui.mobile.css"  media="all">
 <style type='text/css'>
	.titleName {
		width:100px;
		text-align: right;
	}
	.subHeand{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
	.method{
		margin-left: 15px;
		padding: 0 10px;
		font-size: 15px;
		font-weight: 300;"
	}
 </style>
<body >
	<div style="margin-left:5px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>1.概述</legend>
		</fieldset>
		<blockquote class="layui-elem-quote">
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中文分词系统采用基于规则与统计相结合的分词技术，将中文的汉字序列切分成有意义的词，可应用于文献检索、搜索引擎等诸多领域，提高检索的相关性排序的准确度等。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.功能概述</legend>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">
			1、分词歧义的处理<br>
			采用正向最大分词技术和二次扫描技术，在保证分词效率的同时，可以发现绝大多数的交集型分词歧义。<br>
			采用基于实例的切分歧义处理技术，对歧义进行准确处理，并使系统具有良好的可扩充性。<br>
			2、多语言支持<br>
			支持GB18030和UTF8两种编码，具有良好的多语言支持能力。<br>
			系统包含韩文和日文词典，支持这两种语言的分词。<br>
			系统支持英、俄、法、德等多种西文的切词，并进行了词根处理（stem）。<br>
			3、知识词典<br>
			在参考其他语法词典的基础上，人工整理了一部7万多词条的分词词典。<br>
			根据50年人民日报等语料的统计数据，人工整理了一部歧义实例词典，包含数万条，可以对常见的分词歧义进行有效的处理。<br>
			词典是开发式的，可以人工进行维护，添加新词条。<br>
			4、词性标注和未登录词识别功能<br>
			提供词性标注功能和提供未登录词识别功能。两个识别功能都是基于HMM模型。<br>
		</blockquote><br>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">待开发中…</span><br><br>
		
	</div>
</body>
</html>