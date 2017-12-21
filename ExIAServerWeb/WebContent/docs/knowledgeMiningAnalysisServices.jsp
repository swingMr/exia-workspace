<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>知识挖掘&分析的内置服务</title>
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
		  <legend typeVal="calculate.services">3. 知识挖掘&分析的内置服务</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">3.1 中文文本分词</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/participle</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据服务名称获取服务的详细信息</td></tr>
			<tr><td class="titleName" rowspan="5">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]需要分词的文本内容。</td></tr>
			<tr><td>tagPart</td><td>boolean</td><td>[可选项]是否标注词性，默认为false</td></tr>
			<tr><td>patten</td><td>int</td><td>[可选项] 分词模式，默认为1，模式代码表：1--标准分词，2--索引分词，3--全分词，4--极速分词，5--CRF分词。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				   [<br>
				     {"word":词,"part":[词性1, 词性2]},<br>
				     {"word":词,"part":[词性1, 词性2]},<br>
				     {"word":词,"part":[词性1, 词性2]}<br>
				   ]<br>
				   
				  }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.2  关键词和摘要提取</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/abstract</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取某个文本的关键词和摘要</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>title</td><td>String</td><td>[可选项]文本内容对应的文件标题，可以不提供。</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]需要提取关键词和摘要的文本内容。</td></tr>
			<tr><td>keyWordLimit</td><td>int</td><td>[可选项]返回的关键词限制数，默认为20。</td></tr>
			<tr><td>abstractLength</td><td>Int</td><td>[可选项]返回的摘要的长度（字数），默认为250。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				  {<br>
				    "abstract":摘要内容,<br>
				    "keywords":<br>
				    [<br>
				      {"word":关键词," relevantLevel":相关度},<br>
				      {"word":关键词," relevantLevel":相关度},<br>
				      {"word":关键词," relevantLevel":相关度}<br>
				    ]<br>
				  }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.3 生成文本数据的标签</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/gentag</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">生成指定文本数据的标签</td></tr>
			<tr><td class="titleName" rowspan="7">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>title</td><td>String</td><td>[可选项]文本内容对应的文件标题，可以不提供。</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]需要提取关键词和摘要的文本内容。</td></tr>
			<tr><td>keyWordLimit</td><td>int</td><td>[可选项]是否根据标题识别归属领域，默认为false</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				  {<br>
				   "domains": [{"content":"领域词","similarity":相似度,"parentNames":[]}],<br>
				   "acts":[{"content":"行为词","similarity":相似度,"parentNames":[]}}],<br>
				   "organdpersons":[{"content":"主体词","similarity":相似度,"parentNames":[]}}],<br>
				   "objects": [{"content":"客体词","similarity":相似度,"parentNames":[]}}],<br>
				   "spaces":[{"content":"空间词","similarity":相似度,"parentNames":[]}}],<br>
				   "times":[{"content":"时间词","similarity":相似度,"parentNames":[]}}],<br>
				   "keywords":[{"content":"关键词","similarity":相似度}]
				  }<br>


				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.4  根据需求主题获取关键段落</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/keypara</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据需求主题获取某个文本的关键段落</td></tr>
			<tr><td class="titleName" rowspan="5">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>reqSubject</td><td>String</td><td>[必须提供] 需求主题的json格式。[{"content":"主题词1","relevantLevel":相关度},{"content":"主题词2","relevantLevel":相关度}]</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]需要提取关键词和摘要的文本内容。</td></tr>
			<tr><td>abstractLength</td><td>int</td><td>[可选项]返回关键段落的长度（字数），默认为250</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				  关键段落内容<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">3.5 文本分类</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/classify</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">对某个文本内容进行分类</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]需要提取关键词和摘要的文本内容。</td></tr>
			<tr><td>modelName</td><td>int</td><td>[必须提供]模型/模板名称，规则模型或特征模型。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				 [<br>
				   {"word":分类名," relevantLevel":相关度},<br>
				   {"word":分类名," relevantLevel":相关度},<br>
				   {"word":分类名," relevantLevel":相关度}<br>
				 ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>