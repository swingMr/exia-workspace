<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>服务查询</title>
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
		  <legend typeVal="calculate.search">1. 服务查询</legend>
		</fieldset>
		
		<!--  -->
		<span class="method">1.1 获取所有服务列表</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/servicelist</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">获取所有服务定义列表</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				  [<br>
				    {"serviceId":服务标识,"serviceName":服务名称,"serviceCnName":中文名称,"typeName":分类名称,"createDate":创建时间},<br>
				    {"serviceId":服务标识,"serviceName":服务名称,"serviceCnName":中文名称,"typeName":分类名称,"createDate":创建时间}]<br>

				 }<br>
				</td>
			</tr>
		</table><br>
		
		<!--  -->
		<span class="method">1.2 获取服务基本信息</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/calculate/info/{服务英文名称}</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">根据服务名称获取服务的详细信息</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,<br>
				 "msg":"",    #异常数据<br>
				 "data":    #返回结果<br>
				 {<br>
				   "serviceId":服务标识,<br>
				   "serviceName":服务名称,<br>
				   "serviceCnName":中文名称,<br>
				   "typeName":分类名称,<br>
				   "createDate":创建时间,<br>
				   "remark":备注,<br>
				   "serviceInput":<br>
				   [<br>
				     {"paramName":参数名称,"paramDataType":数据类型,"paramCnName ":参数中文名},<br>
				     {"paramName":参数名称,"paramDataType":数据类型,"paramCnName ":参数中文名},<br>
				     {"paramName":参数名称,"paramDataType":数据类型,"paramCnName ":参数中文名}
				   ]<br>
				   "serviceOutput":输出结果定义<br>
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