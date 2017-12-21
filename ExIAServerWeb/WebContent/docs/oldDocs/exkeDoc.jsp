<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>知识本体引擎文档</title>
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
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;知识引擎是基于JAVA平台进行开发的，具有良好的跨平台特性。其主要功能包括识别调度，检索本体，检索调度，汇总新词。知识引擎以http形式对外提供服务。其主要接口有获取概念的详细内容，通过主题词获取概念集合，获取相关本体，获取概念间连通路径，查找关联文档，查找主题词列表......
		本文档描述了京华知识引擎系统对外提供的接口和接入第三方系统的接口规范，可以通过阅读本接口文档来帮助开发。文档预期的读者为第三方应用开发人员、系统开发人员、相关技术人员等。
		</blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2.对外提供的接口</legend>
		</fieldset>
		<span class="subHeand">2.1 知识本体引擎查询</span><br><br>
		<blockquote class="layui-elem-quote">访问路径：
		主机:端口/ExKP/searchKnowledge.do?action=方法名&方法参数
		<br>如：<br>
		http://localhost:8080/ExKP/searchKnowledge.do?action=getConcept&id=concept_concrete/55795F3A-D6BC-44E1-C889-60418266B359</blockquote><br>
		
		<!-- getConcept -->
		<span class="method">2.1.1 getConcept</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getConcept</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取某个概念的详细内容。</td></tr>
			<tr ><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>id</td><td>String</td><td>概念id</td></tr>
		</table>
		
		<!-- getOntologiesByKeywords -->
		<span class="method">2.1.2 getOntologiesByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologiesByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过主题词获取本体集合。</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>word</td><td>String</td><td>主题词json字符串数组，如[“国务院”,”教育部”]</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回相关本体集合（概念集合+关系集合）详情参考3.1和3.2
				其结构如下：
				[{
				“concepts”:<a href="#概念集合">概念集合</a>,
				“relations”:<a href="#关系集合">关系集合</a>
				}]</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">详情请看3.1<a href="#概念集合">概念集合</a>，3.2<a href="#关系集合">关系集合</a></td></tr>
		</table>
		
		<!-- getOntologies -->
		<span class="method">2.1.3 getOntologies</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologies</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取相关本体</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptId</td><td>String</td><td>概念id</td></tr>
			<tr><td>conditions</td><td>String</td><td>检索条件，json字符串数组，检索时按照数组中的条件顺序进行检索，如数组中有三个检索条件，第一，第三个检索条件是针对关系的，第二个检索条件是针对概念的，则从概念出发，先根据第一个关系检索条件和第三个概念检索条件往外扩展一层，然后根据第二个关系检索条件往外扩展一层，数组里每一项包含
				elementType - 检索条件类型（概念，关系），可选值为concept,relation
				type - 概念类型 clazz/concrete
				direction - 关系方向，当检索条件类型为关系时生效，可选值为inbound,outbound,any
				content - 概念名称，当检索条件类型为概念时生效
				如果elementType为relation时，content为关系定义条件名称。
				parentIds - 概念类型id数组
				parentNames - 关系类型或概念类型数组
				attributes - 属性数组，里面每一项包括
				   Name - 属性名,如”性别”
				   Value - 属性值或属性值数组，如[“男”,”女”]
				   Operator - 操作符号，如>,<,=,>=,like...
				   arrayPreOperator - 数组操作符号 any,all,none</td></tr>
   			<tr><td>level</td><td>int</td><td>知识展开层级，默认为1</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回相关本体集合（概念集合+关系集合）详情参考3.1和3.2
				其结构如下：
				[{
				“concepts”:<a href="#概念集合">概念集合</a>,
				“relations”:<a href="#关系集合">关系集合</a>
				}]</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">详情请看3.1<a href="#概念集合">概念集合</a>，3.2<a href="#关系集合">关系集合</a></td></tr>
		</table>
		
				<!-- getOntologiesByIds -->
		<span class="method">2.1.4 getOntologiesByIds</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologiesByIds</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取id相关本体集合</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>ids</td><td>String</td><td>id数组，其中里面每一个对象包括概念id，如[“xxx”,”xxxx”]</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回本体集合（仅有概念集合）详情参考3.1</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">详情请看3.1<a href="#概念集合">概念集合</a></td></tr>
		</table>
		
				<!-- getOntologies -->
		<span class="method">2.1.5 getRoute(暂不开放)</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getRoute</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取概念间连通路径</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptId1</td><td>String</td><td>概念id</td></tr>
			<tr><td>conceptId2</td><td>String</td><td>概念id</td></tr>
			<tr><td>conditions</td><td>String</td><td>检索条件，json字符串数组，检索时按照数组中的条件顺序进行检索，如数组中有三个检索条件，第一，第三个检索条件是针对关系的，第二个检索条件是针对概念的，则从概念出发，先根据第一个关系检索条件和第三个概念检索条件往外扩展一层，然后根据第二个关系检索条件往外扩展一层，数组里每一项包含
				elementType - 检索条件类型（概念，关系），可选值为concept,relation
				type - 概念类型 clazz/concrete
				direction - 关系方向，当检索条件类型为关系时生效，可选值为inbound,outbound,any
				content - 概念名称，当检索条件类型为概念时生效
				如果elementType为relation时，content为关系定义条件名称。
				parentIds - 概念类型id数组
				parentNames - 关系类型或概念类型数组
				attributes - 属性数组，里面每一项包括
				   Name - 属性名,如”性别”
				   Value - 属性值或属性值数组，如[“男”,”女”]
				   Operator - 操作符号，如>,<,=,>=,like...
				   arrayPreOperator - 数组操作符号 any,all,none</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">返回相关本体集合（概念集合+关系集合）详情参考3.1和3.2
				其结构如下：
				[{
				“concepts”:<a href="#概念集合">概念集合</a>,
				“relations”:<a href="#关系集合">关系集合</a>
				}]</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">详情请看3.1<a href="#概念集合">概念集合</a>，3.2<a href="#关系集合">关系集合</a></td></tr>
		</table>
		
		<!-- getInformationsByIdsAndCondition -->
		<span class="method">2.1.6 getInformationsByIdsAndCondition</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologiesByIds</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过概念查找关联信息</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>ids</td><td>String</td><td>概念json对象数组，其中里面包括概念id，如["xxxx","xxxx"]</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>信息资源过滤属性json字符串</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回关联信息集合详情参考3.3</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">输入参数实例
				ids：["concept_concrete/D83C32D8-F485-1BFB-619B-39C26D9A7458"]
				infoCondition：{"政治":"政策","type":"WCM模糊搜索","宪法":"外交"}
				返回结果实例：
				详情请看3.3<a href="#关联信息集合">关联信息集合</a></td></tr>
		</table>
		
		<!-- getInformationsByKeywordsAndCondition -->
		<span class="method">2.1.7 getInformationsByKeywordsAndCondition</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getInformationsByKeywordsAndCondition</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过关键词查找关联文档</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>words</td><td>String</td><td>主题词json数组，如["国务院","政务中心"}]</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>信息资源过滤属性json字符串</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回关联信息集合详情参考3.3</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">输入参数实例
				words:["外交政策","西方国家"]
				infoCondition: {"type":"WCM模糊搜索","宪法":"外交","身份":"总理"}
				返回结果实例：
				详情请看3.3<a href="#关联信息集合">关联信息集合</a></td></tr>
		</table>
		
		<!-- getInformations -->
		<span class="method">2.1.8 getInformations</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getInformationsByKeywordsAndCondition</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">查找关联文档</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptId</td><td>String</td><td>概念id</td></tr>
			<tr><td>conditions</td><td>String</td><td>检索条件，json字符串数组，检索时按照数组中的条件顺序进行检索，如数组中有三个检索条件，第一，第三个检索条件是针对关系的，第二个检索条件是针对概念的，则从概念出发，先根据第一个关系检索条件和第三个概念检索条件往外扩展一层，然后根据第二个关系检索条件往外扩展一层，数组里每一项包含
				elementType - 检索条件类型（概念，关系），可选值为concept,relation
				type - 概念类型 clazz/concrete
				direction - 关系方向，当检索条件类型为关系时生效，可选值为inbound,outbound,any
				content - 概念名称，当检索条件类型为概念时生效
				如果elementType为relation时，content为关系定义条件名称。
				parentIds - 概念类型id数组
				parentNames - 关系类型或概念类型数组
				attributes - 属性数组，里面每一项包括
				   Name - 属性名,如”性别”
				   Value - 属性值或属性值数组，如[“男”,”女”]
				   Operator - 操作符号，如>,<,=,>=,like...
				   arrayPreOperator - 数组操作符号 any,all,none</td></tr>
			<tr><td>infoCondition</td><td>String</td><td>信息资源过滤属性json字符串</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">返回 <a href="#关联信息集合">关联信息集合</a>详情参考3.3</td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">输入参数实例
				Id: concept_class/D17541D2-2B55-B342-AD60-AA31A5719EE8
				conditions:[{"elementType":"concept","content":"外交#问题目标"}]
				infoCondition:{"type":"WCM模糊搜索","宪法":"外交","身份":"总理"}
				详情请看3.3<a href="#关联信息集合">关联信息集合</a></td></tr>
		</table>
		
		<!-- getThemeWordsOutputStream -->
		<span class="method">2.1.9 getThemeWordsOutputStream</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getThemeWordsOutputStream</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取主题词流数据</td></tr>
			<tr ><td class="titleName" >参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">主题词字符串流数据，主题词之间以\n隔开，比如：国务院\n办公厅\n教育部...</td>
			</tr>
		</table>
		
		<!-- getTermsByText -->
		<span class="method">2.1.10 getTermsByText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getTermsByText</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">对文本句段进行分词</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr ><td >text</td><td >String</td><td >文本句段内容</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">字符串的JSON数组字符比如：["国家中心城市","广州市","浙江"]</td>
			</tr>
		</table>		
				
		<!-- getActionConceptDefsByBodyAndObject -->
		<span class="method">2.1.11 getActionConceptDefsByBodyAndObject</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getActionConceptDefsByBodyAndObject</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据当前主体和当前客体获得对应的行为概念列表</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>context</td><td>String</td><td>上下文参数:
				{ 
					CURRENT_BOBY_IDS—当前主体ID数组
					CURRENT_ACTION_IDS—当前行为ID数组
					CURRENT_OBJECT_IDS—当前客体ID数组
					CURRENT_SPACE_IDS—当前空间ID数组
					CURRENT_TIME_IDS—当前时间ID数组
				}
				如{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>bodyConceptId</td><td>String</td><td>当前主体的概念ID</td></tr>
			<tr><td>objConceptId</td><td>String</td><td>当前客体的概念ID</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合</a></td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3">详情请看3.1<a href="#概念集合">概念集合</a></td></tr>
		</table>
				
		<!-- getRelatedKnowledgesByAction -->
		<span class="method">2.1.12 getRelatedKnowledgesByAction</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getRelatedKnowledgesByAction</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取某个行为的相关知识</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>context</td><td>String</td><td>上下文参数:
			{ 
				CURRENT_BOBY_IDS—当前主体ID数组
				CURRENT_ACTION_IDS—当前行为ID数组
				CURRENT_OBJECT_IDS—当前客体ID数组
				CURRENT_SPACE_IDS—当前空间ID数组
				CURRENT_TIME_IDS—当前时间ID数组
			}
			如{
			    "CURRENT_BOBY_IDS": [
			        "xxx",
			        "xxx"
			    ],
			    "CURRENT_ACTION_IDS": [
			        "xxx",
			        "xxx"
			    ]
			}</td></tr>
			<tr><td>actConceptId</td><td>String</td><td>当前行为的概念ID</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">返回Json对象，对象结构如下：
				{
				“相关主体”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关依据”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关参考”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关行为”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关客体”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关时间”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关空间”:[<a href="#概念数组">概念数组(参考3.4)</a>],
				“相关领域”:[<a href="#概念数组">概念数组(参考3.4)</a>]
				}</td>
			</tr>
		</table>
								
		<!-- getOntologiesAndInfosByKeywords -->
		<span class="method">2.1.13 getOntologiesAndInfosByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologiesAndInfosByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取词对应的概念及概念关联的信息资源列表</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>context</td><td>String</td><td>上下文参数:
				{
					CURRENT_BOBY_IDS—当前主体ID数组
					CURRENT_ACTION_IDS—当前行为ID数组
					CURRENT_OBJECT_IDS—当前客体ID数组
					CURRENT_SPACE_IDS—当前空间ID数组
					CURRENT_TIME_IDS—当前时间ID数组
				}
				如{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>words</td><td>String</td><td>主题词json字符串数组，如[“国务院”,”教育部”]</td></tr>
			<tr><td>inforMaxSize</td><td>Int</td><td>关联信息资源的最大个数。</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">返回<a href="#概念数组">本体集合（仅概念集合，其中该概念里面包含关联信息资源。）详情参考3.4</a></td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3"><a href="#概念数组">详情请看3.4包含关联信息的概念集合</a></td></tr>
		</table>
		
		<!-- getConceptsByKeywords -->
		<span class="method">2.1.14 getConceptsByKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getConceptsByKeywords</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过关键词获取概念数组</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>words</td><td>String</td><td>主题词json字符串数组，如[“国务院”,”教育部”]</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">返回<a href="#概念集合">概念集合</a></td>
			</tr>
			<tr><td class="titleName">例子</td><td colspan="3"><a href="#概念集合">详情请看3.1概念集合</a></td></tr>
		</table>
		
		<!-- getExtensionalConcepts -->
		<span class="method">2.1.15 getExtensionalConcepts</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getExtensionalConcepts</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取一个概念的外延（内涵->外延）概念集合</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptId</td><td>String</td><td>概念id</td></tr>
			<tr><td>type</td><td>String</td><td>概念类型，clazz/concrete，不填则两种类型都返回</td></tr>
			<tr><td>num</td><td>Int</td><td>返回数量</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合，参考3.1</a></td>
			</tr>
		</table>
		
		<!-- getIntensionalConcepts -->
		<span class="method">2.1.16 getIntensionalConcepts</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getIntensionalConcepts</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取一个概念的内涵（外延->内涵）概念集合</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptId</td><td>String</td><td>概念id</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合，参考3.1</a></td>
			</tr>
		</table>
		
		<!-- getIntensionalConcepts -->
		<span class="method">2.1.17 getBodysOfObjects</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getBodysOfObjects</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取某些客体的提供单位（主体）</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>objectIds</td><td>String</td><td>概念Ids数组</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合，参考3.1</a></td>
			</tr>
		</table>
		
		<!-- getOntologiesByConceptIdsAndConditions -->
		<span class="method">2.1.18 getOntologiesByConceptIdsAndConditions</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getOntologiesByConceptIdsAndConditions</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过概念id集合和关系过滤条件获取本体</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>conceptIds</td><td>String</td><td>概念conceptIds数组</td></tr>
			<tr><td>conditions</td><td>String</td><td>关系过滤数组,数组每一项包括关系名relationName，关系方向direction[{'relationName':'外延', 'direction':'outbound'},{'relationName':'输入', 'direction':'any'}...]</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">返回相关本体集合（概念集合+关系集合）详情参考3.1和3.2</a>
				其结构如下：
				[{
				“concepts“:<a href="#概念集合">概念集合</a>,
				“relations“:<a href="#关系集合">关系集合</a>
				}]</td>
			</tr>
		</table>
		
		<!-- getRelationDefinitionsByNames -->
		<span class="method">2.1.19 getRelationDefinitionsByNames</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getRelationDefinitionsByNames</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过名字获取关系定义集合</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>names</td><td>String</td><td>关系定义名数组</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合</a></td>
			</tr>
		</table>
		
		<!-- getRecommendedKeywords -->
		<span class="method">2.1.20 getRecommendedKeywords</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getRecommendedKeywords</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取某个检索词的推荐检索词汇</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>words</td><td>String</td><td>检索词数组</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">字符串JSON数组如：["中国","教育部"]</td>
			</tr>
		</table>
		
		<!-- searchConceptDefsOfText -->
		<span class="method">2.1.21 searchConceptDefsOfText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">searchConceptDefsOfText</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据关键词句检索相关知识</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>context</td><td>String</td><td>上下文参数:
				{
					CURRENT_BOBY_IDS—当前主体ID数组
					CURRENT_ACTION_IDS—当前行为ID数组
					CURRENT_OBJECT_IDS—当前客体ID数组
					CURRENT_SPACE_IDS—当前空间ID数组
					CURRENT_TIME_IDS—当前时间ID数组
				}
				如{
				    "CURRENT_BOBY_IDS": [
				        "xxx",
				        "xxx"
				    ],
				    "CURRENT_ACTION_IDS": [
				        "xxx",
				        "xxx"
				    ]
				}</td></tr>
			<tr><td>text</td><td>String</td><td>关键词句</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">返回Json对象，对象结构如下：
					{
					“相关主体”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关依据”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关参考”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关行为”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关客体”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关时间”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关空间”:[<a href="#概念数组">概念数组(参考3.4)</a>],
					“相关领域”:[<a href="#概念数组">概念数组(参考3.4)</a>]
					}</td>
			</tr>
		</table>
				
		<!-- searchRelevantContent -->
		<span class="method">2.1.22 searchRelevantContent</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">searchRelevantContent</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">查询关联的概念</td></tr>
			<tr ><td class="titleName" rowspan="4">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>text</td><td>String</td><td>文本内容</td></tr>
			<tr><td>domainIds</td><td>String</td><td>领域id json数组字符串</td></tr>
			<tr><td>parentIds</td><td>String</td><td>父类Id json 数组字符串</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3"><a href="#概念集合">概念集合，参考3.1</a></td>
			</tr>
		</table>
		
		<!-- 23ruleCATClassifyText -->
		<span class="method">2.1.23 ruleCATClassifyText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">ruleCATClassifyText</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">对内容进行领域分类，分析出内容所属的领域</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td >数据类型</td><td>参数说明</td></tr>
			<tr><td>text</td><td>String</td><td>文本内容，可用逗号分隔多个词，例如共产党,章程</td></tr>
			<tr><td class="titleName">返回值</td> 
				<td colspan="3">JSON数组，每个元素代表一个领域分类，如：[‘领域实例\\外交’,‘领域实例\\教育’, …]</td>
			</tr>
		</table>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>3.返回值及例子说明</legend>
		</fieldset>
		
		<span class="method"><a name="概念集合">3.1 概念集合</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">返回值</td><td colspan="3">概念的详细内容，其结构为标准json字符串。<br>
			其字符串包含以下内容：<br>
			id - 概念id<br>
			content - 概念名称<br>
			definition - 概念定义<br>
			parentNames - 概念类型数组<br>
			parentIds- 概念类型ID数组<br>
			keywords - 关键词数组<br>
			synonyms - 同义词数组<br>
			attributes - 概念属性，json数组字符串，里面每一项为json对象，包括name，dateType和value， value为属性值数组，如 [{"value": ["国家"],"type": "string","name": "范围"}<br>
			createDate–概念创建时间,<br>
			type-  概念类型，<br>concrete/clazz,</td></tr>
		</thead>
			<tr><td class="titleName">例子</td><td >返回结果实例：<br>
				[{<br>
				"id": "concept_concrete/D83C32D8-F485-1BFB-619B-39C26D9A7458",<br>
				"content": "外交#问题目标",<br>
				"parentNames": [],<br>
				"parentIds": [],<br>
				"keywords": [<br>
				"外交政策",<br>
				"西方国家"<br>
				    ],<br>
				"definition": "",<br>
				"synonyms": [],<br>
				"attributes": [<br>
				        {<br>
				"dataType": "array",<br>
				"name": "BELONG_DOMAIN_IDS",<br>
				"value": [<br>
				"concept_concrete/8C15BDC1-B245-069F-176A-25EE992E3FDC"<br>
				            ]<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "问题",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "目标",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "array",<br>
				"name": "BELONG_DOMAIN_NAMES",<br>
				"value": [<br>
				"住房和城乡建设"<br>
				            ]<br>
				        },<br>
				        {<br>
				"dataType": "string",<br>
				"name": "IS_COMMON",<br>
				"value": "true"<br>
				        },<br>
				        {<br>
				"dataType": "concept",<br>
				"name": "指标",<br>
				"value": {}<br>
				        },<br>
				        {<br>
				"dataType": "string",<br>
				"name": "ALIAS",<br>
				"value": "问题目标"<br>
				        }<br>
				    ],<br>
				"createDate": "2017-01-06 11:46:32",<br>
				"type": "concrete",<br>
				"creator": "$ANYBODY"<br>
				}]</td></tr>
		</table>
		
		<span class="method" ><a name="关系集合">3.2 关系集合</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">返回值</td><td colspan="3">对应获取到的关系数组，数组中每一项为关系详情，包括<br>
			content- 关系名称<br>
			  startNodeId - 关系起始的概念id<br>
			  endNodeId - 关系结束的概念id<br>
			attributes - 概念属性，json数组字符串，里面每一项为json对象，包括name，dateType和value， value为属性值数组，如 [{"value": ["国家"],"type": "string","name": "范围"}<br>
			createDate–创建时间,<br>
			type: 关系定义id<br>
			 creator: - 创建人<br>
			 keyword –关键词</td></tr>
		</thead>
			<tr><td class="titleName">例子</td><td >返回结果实例：<br>
        [<br>
            {<br>
                "id": "relation_word/58576747",<br>
                "content": "北京市湿地保护区",<br>
                "keywords": [],<br>
                "endNodeId": "word/153787B9-014C-C4A8-A221-83C603B5E359",<br>
                "attributes": [],<br>
                "createDate": "2017-01-12 14:20:23",<br>
                "type": "concept_class/C952D48E-2B90-FB4C-5BAD-FD592F2FEY8",<br>
                "startNodeId": "concept_concrete/C952D48E-2B90-FB4C-5BAD-FD592F2FE2F6",<br>
                "creator": "$ANYBODY"<br>
            }<br>
        ]</td></tr>
		</table>
		
		<span class="method"><a name="关联信息集合">3.3 关联信息集合</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">返回值</td><td colspan="3">文档json数组集合，<br>
				其中数组的每一项包含以下内容：<br>
				title - 文章标题<br>
				type - 文章分类<br>
				id - 文章id<br>
				url - 资源Url<br>
				viewPageUrl - 查看资源url<br>
				attributes -<br>
				_url –该关联信息的url<br>
				“key”:”value” -属性键值对<br>
				….<br>
				creator –创建者</td></tr>
		</thead>
			<tr><td class="titleName">例子</td><td >返回结果实例：<br>
			{<br>
			"informations": [<br>
			        {<br>
			"id": "information/D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			"title": "条件：外交（标题）",<br>
			"attributes": {<br>
			"_rev": "56594007",<br>
			"政治": "政策",<br>
			"宪法": "外交",<br>
			"_key": "D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			"_url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>
			            },<br>
			"type": "WCM模糊搜索",<br>
			"createDate": "2017-01-09 17:16:02",<br>
			"url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>,
			"viewPageUrl": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			"creator": "$ANYBODY"<br>
			        }<br>
			    ]<br>
			}</td></tr>
		</table>
		
		<span class="method"><a name="概念数组">3.4 包含关联信息的概念集合</a></span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">返回值</td><td colspan="3">id - 概念id<br>
			content - 概念名称<br>
			definition - 概念定义<br>
			parentNames - 概念类型数组<br>
			parentIds- 概念类型ID数组<br>
			keywords - 关键词数组<br>
			synonyms - 同义词数组<br>
			attributes - 概念属性，json数组字符串，里面每一项为json对象，包括name，dateType和value， value为属性值数组，如 [{"value": ["国家"],"type": "string","name": "范围"}<br>
			createDate–概念创建时间,<br>
			type-  概念类型，concrete/clazz,<br>
			info –<br>
			title - 文章标题<br>
			type - 文章分类<br>
			id - 文章id<br>
			  url - 资源Url<br>
			  viewPageUrl - 查看资源url<br>
			attributes -<br>
			_url –该关联信息的url<br>
			“key”:”value” -属性键值对<br>
			….<br>
			creator –创建者</td></tr>
		</thead>
			<tr><td class="titleName">例子</td><td >返回结果实例：<br>
			[<br>
			    {<br>
			        "id": "concept_concrete/55795F3A-D6BC-44E1-C889-60418266B359",<br>
			        "content": "李克强",<br>
			        "parentNames": [<br>
			            "事物",<br>
			            "主体",<br>
			            "自然人",<br>
			            "内容"<br>
			        ],<br>
			        "parentIds": [<br>
			            "concept_class/thing",<br>
			            "concept_class/046E7344-35C0-7ADA-8B11-81F721ECFAF8",<br>
			            "concept_class/09242942-EDF6-843A-1AB5-729A798F6706",<br>
			            "concept_class/53070072-8509-E71B-D985-686293D55F53"<br>
			        ],<br>
			        "keywords": [],<br>
			        "infos": [<br>
			            {<br>
			                "id": "information/D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			                "title": "条件：外交（标题）",<br>
			                "attributes": {<br>
			                    "_rev": "56594007",<br>
			                    "政治": "政策",<br>
			                    "宪法": "外交",<br>
			                    "_key": "D9B2D703-BEB6-2DC6-E5A2-27BC6EC32CF3",<br>
			                    "_url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25"<br>
			                },<br>
			                "type": "WCM模糊搜索",<br>
			                "createDate": "2017-01-09 17:16:02",<br>
			                "url": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			                "viewPageUrl": "http://<host>/wcmSearch#title=%25%E5%A4%96%E4%BA%A4%25",<br>
			                "creator": "$ANYBODY"<br>
			            }<br>
			        ],<br>
			        "definition": "",<br>
			        "synonyms": [],<br>
			        "attributes": [<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "性别",<br>
			                "value": "男"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "IS_COMMON",<br>
			                "value": "true"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "身份证号码",<br>
			                "value": "425324523452452435452452"<br>
			            },<br>
			            {<br>
			                "dataType": "string",<br>
			                "name": "ALIAS",<br>
			                "value": "李克强"<br>
			            }<br>
			        ],<br>
			        "createDate": "2016-12-24 11: 04: 10",<br>
			        "type": "concrete",<br>
			        "creator": "$ANYBODY"<br>
			    }<br>
			]</td></tr>
		</table>
		
		<span class="method">3.5 TRS信息资源集合</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">返回值</td><td colspan="3">num: 总条数,<br>
			page: 当前页码,<br>
			pageSize: 当前页条数,<br>
			data- 信息资源数据，json数组字符串，里面每一项为json对象，包括id、content、abstract等：<br></td></tr>
		</thead>
			<tr><td class="titleName">例子</td><td >返回结果实例：<br>
			{<br>
			    "num": 627,<br>
			    "page": 1,<br>
			    "pageSize": 10,<br>
			    "data": [<br>
			        {<br>
			            "id": 34174,<br>
			            "content": "",<br>
			            "abstract": "",<br>
			            "genre": "",<br>
			            "title": "中华人民共和国宪法修正案",<br>
			            "keywords": "法律规定;集体经济组织;中华人民共和国;宪法;民主管理",<br>
			            "belong": "wcmmetatableflfg",<br>
			            "bbrq": "1993-3-29",<br>
			            "html_content": "中国共产党中央委员会关于修改宪法部分内容的建议",<br>
			            "relevance": "100%",<br>
			            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08"<br>
			        },….<br>
			    ]<br>
			}</td></tr>
		</table>
	</div>
</body>
</html>