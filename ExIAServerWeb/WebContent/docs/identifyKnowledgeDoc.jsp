<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>识别知识概念</title>
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
		  <legend typeVal="ontology.recognise">1.识别知识概念</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">1.1 从文本中识别知识本体需求</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/recognise/concepts</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">从一个文本识别出以知识本体为表达方式的知识需求</td></tr>
			<tr><td class="titleName" rowspan="7">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td>title</td><td>String</td><td>[必须提供]文件标题</td></tr>
			<tr><td>keyWord</td><td>String[]</td><td>[可选项]主题词内容</td></tr>
			<tr><td>text</td><td>String</td><td>[可选项]文本内容</td></tr>
			<tr><td>clsNames</td><td>String[]</td><td>[可选项]限定的要素类型（为空时，不限定），比如：领域、行为、自然人、法人、时间、空间、问题、指标、关键词等。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				 {<br>
				  参考：基于“两系统五要素”的知识需求标识的JSON格式。<br>
				 }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">1.2 生成HTML及纯文本内容的标引</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/recognise/docIndex</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">生成HTML及纯文本内容的标引</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]文本内容</td></tr>
			<tr><td>htmlClazz</td><td>String[]</td><td>[可选项]生成html内容的标签类型，默认ia-link</td></tr>
			<tr><td>words</td><td>String</td><td>[可选项] 自定义标引词汇，json数组，格式如下： {"name”:”名称”,"type”:”分类名”}</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				 {#标引后的正文<br>
				  htmlContent  #例如<br>
				 }<br>
				}<br>
				针对标引中的词，其格式为：&lt;span class="{ htmlClazz }|ia-link" data-id="{概念id}" data-content="{概念名称}"  data-type="{概念分类名}" data-parentNames="{概念父类名，用;分割}">{标引词语}&lt;/span&gt;<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>