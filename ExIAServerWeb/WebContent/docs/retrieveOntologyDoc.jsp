<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>检索知识本体</title>
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
		  <legend typeVal="ontology.search">2.检索知识本体</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 获取大领域或子领域清单</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/search/domains</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有大领域或指定领域的子领域清单</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>domainName</td><td>String</td><td>[可选项]领域名称。当该参数为空，那么返回领域清单</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 {<br>
					 "status":1,#结果代码值<br>
					 "msg":"",    #异常数据<br>
					 "data":   #返回结果<br>
					  [<br>
					    {"id":ID,"content":领域名称,"parentNames":[],"type":""," ALIAS":领域别名},<br>
					    {"id":ID,"content":领域名称,"parentNames":[],"type":""," ALIAS":领域别名},<br>
					    {"id":ID,"content":领域名称,"parentNames":[],"type":""," ALIAS":领域别名}<br>
					  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2 获取指定领域下的所有关键词</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/search/keywords</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取指定领域的所有关键词</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>domainName</td><td>String</td><td>[必须提供]领域名称</td></tr>
			<tr><td>limit</td><td>int</td><td>[可选项]返回的关键词数量，默认200条。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				"status":1,#结果代码值<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  [<br>
				    {"content":关键词," confidenceLevel":置信度},<br>
				    {"content":关键词," confidenceLevel":置信度},<br>
				    {"content":关键词," confidenceLevel":置信度}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3 获取指定领域下的概念清单</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/search/concepts</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取指定领域的所有关键词</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>domainName</td><td>String</td><td>[必须提供]领域名称。</td></tr>
			<tr><td>parentNames</td><td>String[]</td><td>[必须提供]过滤概念的父类名数组。</td></tr>
			<tr><td>type</td><td>String</td><td>[可选项]概念的类型：类-- clazz、实例-- concrete。</td></tr>
			<tr><td>limit</td><td>int</td><td>[可选项]返回的概念数量，默认100条。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  [<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":""," ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":""," ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":""," ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4 根据概念名称获取概念对象</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/search/conceptsByNames</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">验证指定会员登录密码</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>conceptNames</td><td>String[]</td><td>[必须提供]概念名称/同义词数组。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  [<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词,"attributes":属性定义及属性值}<br>
				  ]<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5 获取指定概念的相关概念</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/search/relevance</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取指定概念相关的概念</td></tr>
			<tr><td class="titleName" rowspan="8">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>srcIds</td><td>String</td><td>[必须提供] 源概念ID数组。</td></tr>
			<tr><td>relationDefIds</td><td>String</td><td>[可选项]关系概念ID数组,如果为空，不限制关系。</td></tr>
			<tr><td>direction</td><td>String</td><td>[可选项]关系方向，当检索条件类型为关系时生效，可选值为inbound,outbound,any，默认any。</td></tr>
			<tr><td>objParentNames</td><td>String[]</td><td>[可选项]目标概念父类名称数组,如果为空，不限制父类。</td></tr>
			<tr><td>objType</td><td>String</td><td>[可选项]目标概念类型（clazz-类，concrete-实例）,如果为空，不限制概念类型。</td></tr>
			<tr><td>objLimit</td><td>Int</td><td>[可选项]返回目标概念的限制数量，默认为30。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  [<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词," relevantLevel":相关度},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词," relevantLevel":相关度},<br>
				    {"id":ID,"content":概念名称,"parentNames":[],"type":"","ALIAS":别名, "definition":概念定义,"synonyms":同义词," relevantLevel":相关度}<br>
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