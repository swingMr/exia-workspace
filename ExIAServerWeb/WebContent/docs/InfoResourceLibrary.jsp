<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>信息资源库</title>
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
		  <legend typeVal="resourceLib">1. 信息资源库</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">1.1 获取正常状态的资源库清单</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/resdb/list</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有正常状态的资源库清单</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				  "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				   [<br>
				     {"id":'资源库ID',"libNum":"资源库编号","libName":"资源库名称","type":"类型","resType":"资源类型"," displayOrder":'显示顺序',"remark":"备注"," extendAttrs":[]},<br>
				     {"id":'资源库ID',"libNum":"资源库编号","libName":"资源库名称","type":"类型","resType":"资源类型"," displayOrder":'显示顺序',"remark":"备注"," extendAttrs":[]},<br>
				     {"id":'资源库ID',"libNum":"资源库编号","libName":"资源库名称","type":"类型","resType":"资源类型"," displayOrder":'显示顺序',"remark":"备注"," extendAttrs":[]}<br>
				   ]<br>

				  }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">1.2  获取指定资源库的详细情况</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/resdb/dbinfo/{资源库编号}</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有正常状态的资源库清单</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				 {"id":'资源库ID',"libNum":"资源库编号","libName":"资源库名称","type":"类型","resType":"资源类型"," displayOrder":'显示顺序',"remark":"备注"," extendAttrs":[],"resCount":"资源数量"}<br>

				}<br>
				</td>
			</tr>
		</table><br>
	</div>
<script type="text/javascript" src="/ExIAServer/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/ExIAServer/js/interfaces.js"></script>
</body>
</html>