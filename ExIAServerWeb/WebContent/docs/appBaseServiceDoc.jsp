<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>APP基础服务</title>
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
		  <legend typeVal="base.security">1.APP基础服务</legend>
		</fieldset>
		 
		<!-- gettoken -->
		<span class="method">1.1  获取服务访问Token</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="0">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/security/gettoken</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3"></td></tr>
			<tr><td class="titleName" rowspan="4">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>appCode</td><td>String</td><td>APP应用的代码</td></tr>
			<tr><td>pwd</td><td>String</td><td>登录的APP口令</td></tr>
			<tr><td>timestamp</td><td>int</td><td>时间戳</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #异常数据<br>
				 "tokenid":"" #令牌字符串值<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!-- clearCache -->
		<span class="method">1.2 清除memcached缓存</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="1">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/security/clearCache</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3"></td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">清除memcached缓存</td></tr>
			<tr><td class="titleName" rowspan="2">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>group</td><td>String</td><td>缓存分组，分组key以统一格式表示分组，如[exke]_xxx</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				"status":1,<br>
				 "msg":"",    #异常数据<br>
				}<br>
				</td>
			</tr>
		</table><br>
		
		<!-- member -->
		<span class="method">1.3  授权某个会员使用APP</span>
		<div style="float:right;position:relative;"><span class="layui-btn btn layui-btn-small" style="margin-right:10px;margin-bottom: 3px" onclick="debug(this)" num="2">进行调试</span></div>
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">方法路径</td><td colspan="3">/ExIAServer/services/base/security/grant/member</td></tr>
		</thead>
			<tr><td class="titleName">调用方式</td><td colspan="3">HTTP请求</td></tr>
			<tr><td class="titleName">方法功能</td><td colspan="3">授权某个会员使用APP，已经关注的会员不再授权</td></tr>
			<tr><td class="titleName" rowspan="3">参数：</td><td>参数名</td><td>数据类型</td><td>参数说明</td></tr>
			<tr><td>extoken</td><td>String</td><td>[必须提供]访问服务的令牌</td></tr>
			<tr><td>memberId</td><td>String</td><td>[必须提供] 会员唯一标识</td></tr>
			<tr><td class="titleName">返回值</td>
				<td colspan="3">{<br>
				 "status":1,#结果代码值， -1：帐户已经存在<br>
				 "msg":"", #异常数据<br>
				 "data":   #返回结果<br>
				 {<br>
				    "menberId":"注册成功的会员ID",<br>
				    "appMenberId":"会员关注ID"<br>
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