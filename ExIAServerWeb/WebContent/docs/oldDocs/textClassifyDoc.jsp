<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文本分类</title>
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
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文本分类是指计算机根据文献内容进行类别划分的功能，可以用于新闻分类、简历分类、邮件分类、办公文档分类、区域分类等诸多应用。TRS文本分类系统的主要功能是可以自动地对文档进行分类，赋予文档一个预先定义的类别主题词，便于电子文档的组织，不需人工干预。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.功能概述</legend>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">
			通常，词是最重要的分类知识，根据文章中特定词的出现或者不出现、出现次数等信息，可以进行文本类别的判定。对于一个具体的分类问题，适合做分类知识的词往往只占词典的一小部分，而且这些词对分类的作用大小也是不同的。<br>
			一个区分国内新闻和国外新闻的分类问题中，国家名、国内外地名、人名等是分类知识。<br>
			一个区分篮球、足球、羽毛球等体育运动的分类问题，专业体育术语、运动队名称、运动员名称等是分类知识。<br>
			如何获取这些分类知识，是设计分类器的一个很关键的步骤。<br>
			由于现有分词算法和词典规模的限制，文档有很多具有分类价值的词或短语没有被识别出来，如各学科的专业术语以及人名、地名、组织机构名称等。为了获取更多的分类知识，提高分类的准确率，TRS公司采用了一种基于统计方法的复合短语和未定义词的识别方法。可以有效地提取文本中的分类知识词或短语。<br>
			在获取更多的词补充分类知识后，需要确定这些词中哪些是真正的分类知识，哪些是噪音。解决这个问题的技术是特征提取，根据统计方法计算每个词对于分类的作用大小，选择其中分类作用大的词作为分类知识，把不重要甚至无关的特征词去掉。<br>
			特征提取是文本分类中最重要的问题之一，它具有降低文本向量空间维数、简化计算、防止过分拟合等作用。主要算法是构造一个评估函数，对特征集中的每个特征独立计算评估值，然后对所有特征根据评估值大小进行排序，选取预定数目的最佳特征作为结果的特征子集。特征评估函数有：词和类别的互信息量、信息增益、期望交叉熵、文本证据权、几率比、词频等。其中期望交叉熵（CHI）是效果最好的方法之一。<br>
			评估函数只从统计意义上，考虑了特征词的词频或文档频率分布情况。没有考虑特征词在具体文档中的实际分布情况，实际上，特征词在具体的文档中所起的分类作用受文档长度、文档中同类特征词个数、其他类特征词个数等多种因素的影响。<br>
			为了弥补这一缺陷，TRS公司设计了一种新的基于特征分类贡献度的特征提取方法，改进了特征提取的性能。<br>
		</blockquote><br>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">待开发中…</span><br><br>
	</div>
</body>
</html>