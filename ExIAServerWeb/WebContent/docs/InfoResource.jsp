<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源</title>
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
		  <legend typeVal="resource">2. 信息资源</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">2.1 获取指定信息资源的属性信息</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/{资源库编号}/{资源唯一ID}/info</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取指定信息资源的属性信息</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
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
				   "textFileExt":"正文格式：HTML、DOC",<br>
				   },<br>
				   "viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"<br>
				  } <br>


				  }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.2 获取信息资源的正文内容</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/{资源库编号}/{资源唯一ID}/text</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取信息资源的正文内容</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					"status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":正文内容的数据流   #返回结果<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.3  获取信息资源指定附件的文件内容</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/{资源库编号}/{资源唯一ID}/attach/{附件ID}</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取信息资源指定附件的文件内容</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>context</td><td>String</td><td>[必须提供]上下文参数信息的JSON字符串，具体格式参考：context上下文的JSON格式。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					"status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":正文内容的数据流   #返回结果<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.4  获取信息资源的数量</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="3">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/count</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取信息资源的数量</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>lib</td><td>String</td><td>库名，例如法律法规库</td></tr>
			<tr><td>channel</td><td>String</td><td>栏目名，多个栏目名则用“宪法及相关法/宪法”表示</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 {<br>
					   "count":10 <br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.5  通过资源url获取资源列表</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="4">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/resourceListByUrl</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过资源url获取资源列表</td></tr>
			<tr><td class="titleName" rowspan="5">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>url</td><td>String</td><td>[必须提供]资源url，例如http://<host>/wcm/资源库/法律法规库/宪法</td></tr>
			<tr><td>pageNo</td><td>int</td><td>[必须提供]</td></tr>
			<tr><td>pageSize</td><td>int</td><td>[必须提供]</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 {<br>
					  documents:[<br>
						  {<br>
							id: 1,<br>
							text: xxx,   // 文档标题<br>
							channelId: 3, // 文档的父栏目id<br>
					        url: http://<host>/wcm/法律法规库/宪法/xxx法<br>
					        cruser: admin, // 提供者<br>
					        createTime: 2017-08-21 12:32:21 // 创建时间<br>
						  },<br>
						  ...<br>
						],<br>
					  total:20<br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method"> 2.6 通过资源url获取资源的详细内容</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="5">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/text</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过资源url获取资源列表</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>url</td><td>String</td><td>[必须提供]资源url，例如http://<host>/wcm/资源库/法律法规库/宪法</td></tr>
			<tr><td>attributes</td><td>String</td><td>格式为：{title: “xxx”}</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 outputstream类型，格式为：<br>
					{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					&lt;root&gt;&lt;htmlContent&gt;&lt;![CDATA[网页正文]]&gt;&lt;/htmlContent&gt;&lt;genre&gt;&lt;![CDATA[体裁]]&gt;&lt;/genre&gt;&lt;title&gt;…&lt;/title&gt;…. &lt;/root&gt;<br>
					}<br>

				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">2.7 通过参数获取资源列表</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="6">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/resourceList</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">通过参数获取资源列表</td></tr>
			<tr><td class="titleName" rowspan="8">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>id</td><td>int</td><td>[必须提供]节点id</td></tr>
			<tr><td>text</td><td>String</td><td>[必须提供]节点名字</td></tr>
			<tr><td>type</td><td>String</td><td>[必须提供]节点类型：library、site、channel</td></tr>
			<tr><td>parentPath</td><td>String</td><td>[必须提供]该节点的父路径，例如 资源库/法律法规库</td></tr>
			<tr><td>condition</td><td>String</td><td>其他检索条件，格式为：{title: “xxx”}</td></tr>
			<tr><td>pageNo</td><td>int</td><td>当前页</td></tr>
			<tr><td>pageSize</td><td>int</td><td>每页数据数量</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 {<br>
					  documents:[<br>
						  {<br>
							id: 1,<br>
							text: xxx,   // 文档标题<br>
							channelId: 3, // 文档的父栏目id<br>
					        url: http://<host>/wcm/法律法规库/宪法/xxx法<br>
					        cruser: admin, // 提供者<br>
					        createTime: 2017-08-21 12:32:21 // 创建时间<br>
						  },<br>
						  ...<br>
						],<br>
					  total:20<br>
					 }<br>

				}<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method"> 2.8 新建资源</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="7">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/createResource</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">新建资源</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>channelId</td><td>int</td><td>[必须提供]栏目id</td></tr>
			<tr><td>parentPath</td><td>String</td><td>[必须提供]栏目的父路径，例如 资源库/法律法规库</td></tr>
			<tr><td>title</td><td>String</td><td>[必须提供]资源标题</td></tr>
			<tr><td>content</td><td>String</td><td>[必须提供]资源内容</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 { <br>
					  id: 1, // 已保存的id<br>
					  url: http://<host>/wcm/法律法规库/宪法/xxx法<br>
					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.9 更新资源属性</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="8">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/updateResource</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">更新资源属性</td></tr>
			<tr><td class="titleName" rowspan="6">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>channelId</td><td>int</td><td>[必须提供]栏目id</td></tr>
			<tr><td>docId</td><td>int</td><td>[必须提供]资源id</td></tr>
			<tr><td>libName</td><td>String</td><td>[必须提供]库名（资源库、文字库）</td></tr>
			<tr><td>attributes</td><td>String</td><td>[必须提供]要更新的资源属性，json格式，{genre: “体裁”}</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
 					"msg":""    #异常数据<br>

					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method">2.10  获取资源目录</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="9">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/catalog</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取资源目录</td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>url</td><td>String</td><td>资源url，例如http://<host>/wcm/资源库/法律法规库/宪法?article=xxx</td></tr>
			<tr><td>layer</td><td>int</td><td>目录展开到第几层；如果层数大于最深层数，则取其最深层度，即叶子栏目；如果该值为null，则取其最深层数；如果有值，则必须大于或者等于0，如果为0则返回""</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 {<br>
					    sites: [{<br>
							site1: { // 站点（库）<br>
							  id: xx,<br>
							  text: xx,<br>
							  children: [<br>
								channel1:{<br>
							  	  id: xx,<br>
								  text: xx,<br>
								  children: [<br>
									document1: {<br>
									  id: xx,<br>
									  text: xx,<br>
					                  url: xx, <br>
					                  cruser: admin, // 提供者<br>
					                  createTime: 2017-08-21 12:32:21 // 创建时间<br>
									},<br>
									document2: {},<br>
									...<br>
								]},<br> 
								channel2:{},<br>
								...<br>
							]},<br>
							site2: {},<br>
							...<br>
						}],<br>
						total:10, // 文章总数<br>
					  }<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.11 获取所有属性映射集合</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="10">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/attributeMappings</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有属性映射集合</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					 {<br>
					  title: 标题,<br>
					  content: 正文纯文本,<br>
					  ...<br>
					 }<br>


					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.12	通过url获取资源的属性集合</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="11">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/attributes</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有属性映射集合</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>url</td><td>String</td><td>[必须提供] 资源url，例如http://<host>/wcm/资源库/法律法规库/宪法?article=xxx</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					 "status":1,<br>
					 "msg":"",    #异常数据<br>
					 "data":    #返回结果<br>
					{resourceName:资源名称, attributes:{{genre:体裁}, {form:形式}, ...}}<br>

					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
		
		<!--  -->
		<span class="method"> 2.13 获取每日推荐数据</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="12">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/res/getDailyPushData</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取每日推荐数据</td></tr>
			<tr><td class="titleName" rowspan="5">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberId</td><td>String</td><td>[必须提供] 会员id</td></tr>
			<tr><td>page</td><td>Int</td><td>[可选项]当前页码，默认为1。</td></tr>
			<tr><td>pageSize</td><td>Int</td><td>[可选项]分页大小，默认为8。</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
					  "status":1,<br>
					 "msg":"",    #异常提示信息<br>
					 "data":   #返回结果<br>
					   信息资源检索结果的分页结果JSON格式<br>

					}<br>

				}<br>
				</td>
			</tr>
		</table><br> 
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>