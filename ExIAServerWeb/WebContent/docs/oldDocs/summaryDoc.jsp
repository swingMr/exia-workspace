<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>自动摘要</title>
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
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文本自动摘要系统的主要功能是实现文本内容的精简提炼，从长篇文章中自动提取关键词和关键段落，构成摘要内容，方便用户快速预览文本内容，提高工作效率。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.功能概述</legend>
		</fieldset>
		<blockquote class="layui-elem-quote layui-quote-nm">
			1、基于关键句抽取的摘要技术<br>
			目前主流的文摘技术，从文本中提取关键句作为摘要。<br>
			2、语言学知识的综合运用<br>
			在TRS自动摘要中，计算词权、句权、选择文摘句的依据是文本的6种形式特征：<br>
			（1）词频<br>
			能够指示文章主题的词称为“有效词”。任何文献中都有不少“介词”、“连词”、“助词”等虚词。这些词在一般情况下，都不是有效词。对实词，其作为有效词的价值也是不同的，可给予不同的价值。<br>
			有效词往往是高频词。根据句子中有效词的个数以及每个词的权重可以计算句子的权值，这是自动摘要方法的基本依据。<br>
			TRS公司对50年人民日报、新华社新闻报道等大量的语料进行统计处理，获取了汉语词汇中“有效词”的分布表，在此基础上，为每个“有效词”计算了一个权值，将此权值和词汇在文章中出现的次数、位置等信息相结合，就得到该词在文章中的权值。此权值可用于计算句子的权值。<br>
			（2）标题<br>
			标题是作者给出的提示文章内容的短语或者句子，借助停用词词表，在标题或小标题中剔除功能词或只具有一般意义的名词，剩下的词串和原文内容往往有紧密的联系，可以作为有效词。<br>
			TRS自动摘要系统可以有效地识别文章的篇章结构，包括段落，大小标题等信息，极大地方便了摘要的生成。<br>
			（3）篇章位置<br>
			调查结果显示：段落的论题是段落首句的概率为85%，是段落末句的概率为7%。其中首段和末段的重要性更大一些。另外，文章的各级标题也往往包含文章的重要信息。因此，有必要提高处于特殊位置的句子的权值。<br>
			另外，文献中用括号括起来的部分，如：ISDN(综合业务数据网)；用破折号引出来的部分，如“数据的自动识别输入——条码技术”；用“所谓”所引出的部分，如“所谓的预置关键词”，其中的实词往往也应当给予特别的加权。<br>
			（4）句法结构<br>
			句式与句子的重要性之间存在着某种联系，比如文摘中的句子大多是陈述句，而疑问句、感叹句等则不宜进入文摘。<br>
			（5）线索词<br>
			Edmundson的文摘系统中有一个预先编制的线索词词典，词典中的线索词分为3种：取正值的褒义词(Bonus Words)，取负指的贬义词(Stigma Words)，取零值的无效词(Null Words)。句子的权值就等于句中每个线索词的权值之和。70年代初，俄亥俄州立大学的James A.Rush教授和他的学生开发了ADAM(Automatic Document Abstracting Method)系统。ADAM强调的是排斥句子的标准而不是选择句子的标准，词控表(WCL)中大多数词是否定性的。<br>
			TRS自动摘要系统在长期积累的大量语料基础上，总结了自己的线索词词典。<br>
			（6）指示性短语<br>
			各种“指示性短语”如“本文讨论了”、“综上所述”等所在的句子往往是很好的文摘候选句。因为其中往往高度地概括了文献主题。<br>
			
			以上是文本的6种形式特征，即：F-词频、T-标题、L-篇章位置、S-句法结构、C-线索词、I-指示性短语。这6种特征是TRS自动摘要的依据，它们从不同角度指示了文章的主题，但都不够准确，不够全面。<br>
			TRS自动摘要系统采用统计学习方法，在长期积累的大量语料基础上，统计整理了词汇分布表、线索词表、同义词典等信息。通过统计算法得到了文本的多种形式特征之间的加权关系，把上述各种特征有机地结合在一起，提高了摘要质量。<br>		
		</blockquote><br>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">待开发中…</span><br><br><br><br><br><br>
	</div>
</body>
</html>