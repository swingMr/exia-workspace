<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>相似度计算</title>
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
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相似性检索是指对于给定样本文献，在文献数据集合中查找出与之内容相似的文献的技术。应用实践表明，相似性检索技术在网络内容自动排重、文章关联方面取得良好效果。TRS相似文本检索系统的主要功能是自动地对文档进行特征抽取，构造文档的“指纹”，然后根据该“指纹”到文档“指纹库”中检索与该文档相似或相同的文档。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.功能概述</legend>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">
			1、基于统计方法的文档“指纹”抽取技术<br>
			采用基于语料统计模型的主题词提取技术，根据词语在背景语料库的统计信息，确定该词语作为主题词的重要程度。<br>
			2、两种层次的查重技术，粗查重和细查重<br>
			   支持两种层次的查重技术，粗查重和细查重。分别对应不同的文档指纹抽取技术和相似度计算技术。<br>
			粗查重为每篇文档生成一个的指纹，如果两篇文档的指纹完全相同，则认为两篇文档是相同。即使两篇文档的指纹有少许不同，也认为两篇文档是不同的。因此粗查重技术的应用领域主要是稿件查重。<br>
			细查重为每篇文档生成一组指纹特征，计算两篇文档之间的指纹的相似程序，因此可以用来检索相似文档。<br>
			粗查重的效率很高，而细查重的效率低一些，这也是它们的重要差别。<br>
			3、支持跨语言检索<br>
			对中文文档进行特征抽取，使用翻译资源映射成英语的特征词，并构造英文的文档指纹，这样就可以检索到英文的相似文档。<br>
			目前支持中文到英文的互相检索，还可以根据需要扩展到其他语种。<br>
			该方法使用双语词典作为跨语言检索的翻译资源。如果没有，可以使用其他双语资源如翻译句对等构造统计翻译模型，作为翻译资源。该方法具有良好的可移植性。<br>
			4、其他特色<br>
			同时支持中英韩日俄德等多语种的相似文档检索，还可以根据用户需要扩展到其他语言。<br>
			可自由设定文档相似度的阈值和检索结果集的大小。<br>
			提供多种基于文本内容的相似度计算方法，保证检索结果的准确性。<br>
			提供索引自动重建功能，提高系统的稳定性。<br>
			内嵌高效准确的TRS检索引擎，可同时检索数百万篇文档，保证速度可用(秒级响应)。<br>
			可自由设定文档相似度的阈值和检索结果集的大小。<br>
			内嵌TRS汉语自动分词系统。<br>
			内置主题词典、分类词典等丰富的语言学资源。<br>
		</blockquote><br>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">待开发中…</span><br><br><br><br><br><br>
	</div>
</body>
</html>