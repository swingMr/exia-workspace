<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>附录</title>
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
		  <legend>1. Python脚本编写一些注意事项</legend>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>2. JSON数据格式的各种定义</legend>
		</fieldset>
		<span class="method">2.1 context上下文的JSON格式</span> 
		<pre class="layui-code">{
	    "user": {
	        "id": "当前id", 
	        "account": "当前用户账号", 
	        "ip": "当前IP地址", 
	        "name": "当前用户姓名", 
	        "exploreName": "浏览器名称", 
	        "exploreVersion": "浏览器版本", 
	        "osName": "操作系统名称", 
	        "osVersion": "操作系统版本"
	    }, 
	    "org": {
	        "unitName": "所在单位名称", 
	        "unitCode": "所在单位组织代码", 
	        "superiorUnitName": "上级单位名称"
	    }, 
	    "location": {
	        "countryName": "所在国家地区名称", 
	        "provinceName": "所在省份洲名称", 
	        "cityName": "所在城市名称"
	    }, 
	    "time": {
	        "year": "当前年份", 
	        "month": "当前年月，格式：yyyy-MM", 
	        "date": "当前日期，格式：yyyy-MM-dd"
	    }
		}</pre> 
		
		<span class="method">2.2基于“两系统五要素”的知识需求标识的JSON格式</span> 
		<pre class="layui-code">{
			"domains":
				    [{"id": "领域ID","content":"领域名称","relevantLevel":"相关度", "synonyms":[],"relevantConcepts":[{"id": "领域ID","content":"领域名称","relevantLevel":"相关度", "synonyms":[]},{"id": "领域ID","content": "领域名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"}],
			"acts":
				    [{"id": "行为ID","content":"行为名称","relevantLevel":"相关度", "synonyms":[],"relevantConcepts":"相关概念及相关度"},{"id": "行为ID","content": "行为名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"}],
			"organdpersons":
				    [{"id": "主体ID","content":"主体名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"},{"id": "主体ID","content": "主体名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"}],
			"objects":
				    [{"id": "客体ID","content":"客体名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"},{"id": "客体ID","content": "客体名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"}],
			"spaces":
				    [{"id": "空间ID","content":"空间名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"},{"id": "空间ID","content": "空间名称","relevantLevel":"相关度"}],
			"times":
				    [{"id": "时间ID","content":"时间名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"},{"id": "时间ID","content": "时间名称","relevantLevel":"相关度","relevantConcepts":"相关概念及相关度"}],
			"keywords":
				    [{"content":"主题词1","relevantLevel":"相关度"},{"content":"主题词2","relevantLevel":"相关度"}]
		}</pre> 
		
		<span class="method">2.3 信息资源查询条件JSON格式</span> 
		<pre class="layui-code">{
				    "title":"资源标题，做精准查询",
				    "titles":[资源标题字符串数组，做精准查询],
				    "originalAddress":"原文地址，做精准查询",
				    "source":"资源来源平台名称，做精准查询",
				    "keywords":[主题词字符串数组，做精准查询],
				    "publishDateMin":"发布日期最小值，格式：yyyy-MM-dd",
				    "publishDateMax":"发布日期最大值，格式：yyyy-MM-dd"
		}</pre> 
		
		<span class="method">2.4 信息资源检索结果的分页结果JSON格式</span> 
		<pre class="layui-code">{
	    "num": 627, 
	    "page": 1, 
	    "pageSize": 10, 
	    "informations": [
	        {
	            "id": 34174, 
	            "title": "中华人民共和国宪法修正案", 
	            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08", 
	            "attributes": {
	                "content": "", 
	                "abstract": "", 
	                "genre": "", 
	                "symbol": "", 
	                "form": "", 
	                "keywords": "法律规定;集体经济组织;中华人民共和国;宪法;民主管理", 
	                "belong": "wcmmetatableflfg", 
	                "bbrq": "1993-3-29"
	            }, 
	            "viewPageUrl": "http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"
	        },…
	    ]
		}</pre> 
		
		
	</div>
<script src="/ExIAServer/js/layui-v2/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('code', function(){ //加载code模块
	layui.code({
		about: false
		});
	});
</script>
</body>
</html>