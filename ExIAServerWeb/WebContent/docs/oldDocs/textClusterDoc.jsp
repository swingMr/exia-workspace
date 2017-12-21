<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文本聚类</title>
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
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文本聚类是基于相似性算法的自动聚类技术，自动对大量无类别的文档进行归类，把内容相近的文档归为一类，并自动为该类生成主题词，为用户确定类目名称提供方便。可支持自动生成新闻专题、重大新闻事件追踪、情报的可视化分析等诸多应用。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.功能概述</legend>
		</fieldset>
		
		<blockquote class="layui-elem-quote layui-quote-nm">1、聚类方法<br>
			本系统采用的聚类方法是K-means方法。该方法是最常用的聚类方法之一，聚类效果不错，速度也很快。文档相似度计算方法采用向量空间模型。文本特征选择词语和n-gram等多种特征，以提高聚类性能。<br>
			2、支持多层聚类<br>
			支持多层聚类，并自动生成每个类别的多个主题词。<br>
			3、图形化展示功能<br>
			提供信息岛图、树图和时间序列图等多种聚类结果图形化展现功能，帮助用户更加直观地理解聚类结果。<br>
			4、其他特色<br>
			基于相似性算法的聚类技术。<br>
			内嵌高效准确的TRS检索引擎。聚类速度快<br>
			内置主题词典、分类词典等丰富的语言学资源。可通过领域词典来优化聚类效果。
		</blockquote><br>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">待开发中…</span><br><br>
	</div>
</body>
</html>