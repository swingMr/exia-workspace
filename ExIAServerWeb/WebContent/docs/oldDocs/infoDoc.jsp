<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源获取和检索接口文档</title>
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
		  <legend>1.信息资源检索和获取</legend>
		</fieldset>
	
		<!-- getInformationsByUrlAndAttribute -->
		<span class="method">1.1 getInformationsByUrlAndAttribute</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getInformationsByUrlAndAttribute</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">解析url，获取信息资源的流</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>url</td><td>String</td><td>参考文件资源路径</td></tr>
			<tr><td>attribute</td><td>String</td><td>信息资源过滤属性json字符串</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">信息资源的流</td>
			</tr>
		</table>		
	
		<!-- 自定义服务 -->
		<span class="method">1.2 自定义服务</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">推理服务</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">解析url，获取信息资源的流</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>mount</td><td>String</td><td>挂载点</td></tr>
			<tr><td>params</td><td>String</td><td>服务参数，字符串或json字符串</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">Json格式数据</td>
			</tr>
			<tr><td class="titleName">调用例子</td>
				<td colspan="3">例子1<br>
				访问路径：<br>
				http://172.3.4.5:8080/ExKP/service/getKnowledge?id= concept_class/thing& condition={aaa:111}<br>
				//参数：<br>
				Id=" concept_class/thing",<br>
				condition.aaa=111<br>
				//js脚本<br>
				var tt = id;<br>
				var ss = tt+condition.aaa<br>
				ss<br>
				//返回结果：<br>
				concept_class/thing111<br>
				
				例子2：<br>
				访问路径：<br>
				http://172.3.4.5:8080/ExKP/service/getKnowledge?text=[国务院]<br>
				//参数：<br>
				text =[国务院] <br>
				//Js脚本内容：<br>
				var tt = JSON.parse(_searchKnowledgeService.getOntologiesByKeywords(text).toString());<br>
				var concepts = new Array();<br>
				for(var i = 0 ; i < tt.length; i++){<br>
					var temp = tt[i]['concepts'];<br>
					if(temp.length>1){<br>
						var concept = JSON.parse(_searchKnowledgeService.getConcept(temp[1]["id"]));<br>
						_out.println(concept)<br>
						concepts.push(concept);<br>
					}<br>
				}<br>
				concepts; <br>
				//返回的结果<br>
				返回结果结构：概念集合</td>
			</tr>
		</table>		
	
		<!-- searchText（全文检索接口） -->
		<span class="method">1.3 searchText（全文检索接口）</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">searchText（全文检索接口）</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">全文检索知识库</td></tr>
			<tr ><td class="titleName" rowspan="3">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>conditions</td><td>String</td><td>挂载点</td></tr>
			<tr><td>params</td><td>String</td><td>检索条件，json对象字符串，key如下：<br>
				//查询词，多个查询词用”,”分隔，必需<br>
				"text": ""<br>
				//当前页数，必需<br>
				"page":1<br>
				//每页条数，必需<br>
				"pageSize":10<br>
				//排序方式，选填0：相关度排序（默认）,1：按字段排序<br>
				"sortType":1<br>
				//排序字段，当sortType为1时生效，默认按该字段升序排序，字段名前可以冠以加号'+'或减号'-'，加号'+'表示升序，减号'-'表示降序<br>
				"sortField":"-bbrq"//按颁布日期倒序<br>
				//查询库标识，”;”分隔，选填<br>
				"infoSources":""<br>
				//分类，”;”分隔，选填<br>
				"categorys":""<br>
				//体裁，”;”分隔，选填<br>
				"genres":""<br>
				//形态，”;”分隔，选填<br>
				"forms": ""<br>
				//符号，”;”分隔，选填<br>
				"symbols", ""<br>
				//制定机关名称，选填<br>
				"zdjg":""<br>
				//颁布文号，选填<br>
				"bbwh":""<br>
				//时效性，选填<br>
				"sxx":""</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">Json字符串<br>
				{<br>
				"num": 627,<br>
				"page": 1,<br>
				"pageSize": 10,<br>
				<br>
				"informations": [<br>
				{<br>
				           "id": 34174,<br>
				            "title": "中华人民共和国宪法修正案",<br>
				            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08",<br>
				"attributes":{<br>
				"content": "",<br>
				"abstract": "",<br>
				 "genre": "",<br>
				"symbol": "",<br>
				"form": "",<br>
				                "keywords": "法律规定;集体经济组织;中华人民共和国;宪法;民主管理",<br>
				"belong": "wcmmetatableflfg",<br>
				 "bbrq": "1993-3-29",<br>
				                "html_content": "中国共产党中央委员会关于修改宪法部分内容的建议",<br>
				"relevance": "100%"<br>
				},<br>
				"viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"<br>
				        },….<br>
				    ]<br>
				}</td>
			</tr>
		</table>		
	
		<!-- getComplexUrl -->
		<span class="method">1.4 getComplexUrl</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getComplexUrl</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取复杂的资源url（其组成元素为资源路径+资源名称）</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>simpleUrl</td><td>String</td><td>简单的资源url（其组成元素为id）</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">复杂的资源url</td>
			</tr>
		</table>		
	
		<!-- getSimpleUrl -->
		<span class="method">1.5 getSimpleUrl</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">getSimpleUrl</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取简单的资源url（其组成元素为id）</td></tr>
			<tr ><td class="titleName" rowspan="2">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>complexUrl</td><td>String</td><td>复杂的资源url（其组成元素为资源路径+资源名称）</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">简单的资源url</td>
			</tr>
		</table>		
	
		<!-- absText -->
		<span class="method">1.6 absText</span>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法名</td><td colspan="3">absText</td></tr>
		</thead>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取简单的资源url（其组成元素为id）</td></tr>
			<tr ><td class="titleName" rowspan="5">参数</td><td >参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>text</td><td>String</td><td>正文文本</td></tr>
			<tr><td>numOfSub</td><td>Integer</td><td>主题词个数，默认5个</td></tr>
			<tr><td>percent</td><td>Integer</td><td>摘要长度占文章长度百分比，默认10%的摘要比例</td></tr>
			<tr><td>dictName</td><td>String</td><td>摘要词典名称（为空表示加载系统默认词典）默认词典为./ DICT/absdict;</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">json字符串，格式为：{keywords:主题词，abstract:摘要}</td>
			</tr>
		</table>		
	</div>
</body>
</html>