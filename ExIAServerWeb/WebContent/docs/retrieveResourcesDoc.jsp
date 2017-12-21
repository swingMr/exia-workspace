<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>检索信息资源</title>
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
		  <legend typeVal="ontology.textsearch">4.检索信息资源</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">4.1 根据五要素检索信息资源</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/textsearch/byontology</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据采用基于“两系统五要素”描述的知识需求进行信息资源检索</td></tr>
			<tr><td class="titleName" rowspan="8">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td>ontoOfReqJson</td><td>String</td><td>[必须提供] 基于“两系统五要素”描述的知识需求。参考：基于“两系统五要素”的知识需求标识的JSON格式。</td></tr>
			<tr><td>infoSources</td><td>String[]</td><td>[可选项]信息资源库的代码</td></tr>
			<tr><td>infoYears</td><td>String</td><td>[可选项] 各信息资源库的年份限制，格式：[{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]},{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]}]</td></tr>
			<tr><td>page</td><td>Int</td><td>[可选项]当前页码，默认为1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[可选项]分页大小，默认为50。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常提示信息<br>
				 "data":   #返回结果<br>
				   信息资源检索结果的分页结果JSON格式<br>
				 }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">4.2全文检索SQL语句检索资源</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/textsearch/sql</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据SQL语句进行全文检索</td></tr>
			<tr><td class="titleName" rowspan="7">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td>sql</td><td>String</td><td>[必须提供] 全文检索的SQL语句。</td></tr>
			<tr><td>infoYears</td><td>String</td><td>[可选项] 各信息资源库的年份限制，格式：[{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]},{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]}]</td></tr>
			<tr><td>page</td><td>Int</td><td>[可选项]当前页码，默认为1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[可选项]分页大小，默认为50。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  信息资源检索结果的分页结果JSON格式,<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">4.3根据条件查询信息资源</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/ontology/textsearch/byontology</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据采用基于“两系统五要素”描述的知识需求进行信息资源检索</td></tr>
			<tr><td class="titleName" rowspan="9">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td>resOfReqJson</td><td>String</td><td>[必须提供]信息资源查询条件的JSON字符串，具体格式参考：</td></tr>
			<tr><td>infoSources</td><td>String</td><td>[可选项]信息资源库的代码</td></tr>
			<tr><td>orderName</td><td>String</td><td>可选项]排序字段名称：title-标题排序、publishDate-发布日期排序</td></tr>
			<tr><td>orderType</td><td>String</td><td>[可选项]排序方式：asc-升序、desc-降序。</td></tr>
			<tr><td>page</td><td>Int</td><td>[可选项]当前页码，默认为1</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[可选项]分页大小，默认为50。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":   #返回结果<br>
				  信息资源检索结果的分页结果JSON格式,<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>