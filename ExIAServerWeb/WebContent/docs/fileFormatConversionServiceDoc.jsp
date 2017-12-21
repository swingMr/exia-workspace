<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>文件格式转换服务</title>
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
		  <legend>文件格式转换服务</legend>
		</fieldset>
		<!-- <blockquote class="layui-elem-quote">
		       基础服务是所有应用都需要的服务，比如：安全方便、日志审计方面的服务。
		</blockquote> -->
		<table class="layui-table"  lay-skin="line" style="width:100%">
		<thead>
			<tr><td class="titleName">服务名</td><td colspan="3">REST接口访问路径</td></tr>
		</thead>
			<tr><td class="titleName">上传下载文件</td><td colspan="3">/ExIAServer/services/file/ud</td></tr>
			<tr><td class="titleName">文件转TXT</td><td colspan="3">/ExIAServer/services/file/convert/txt</td></tr>
			<tr><td class="titleName">文件转PDF</td><td colspan="3">/ExIAServer/services/file/convert/pdf</td></tr>
			<tr><td class="titleName">文件转HTML</td><td colspan="3">/ExIAServer/services/file/convert/html</td></tr>
			<tr><td class="titleName">文件转图片</td><td colspan="3">/ExIAServer/services/file/convert/pic</td></tr>
			<tr><td class="titleName">文件转WORD</td><td colspan="3">/ExIAServer/services/file/convert/word</td></tr>
		</table><br>

	</div>
</body>
</html>